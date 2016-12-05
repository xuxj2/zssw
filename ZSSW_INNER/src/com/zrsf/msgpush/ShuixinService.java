package com.zrsf.msgpush;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zrsf.backup.dao.TimerJobDao;
import com.zrsf.common.util.SpringBeanUtil;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.push.xmpp.console.Notification;
import com.zrsf.push.xmpp.model.NotificationVO;

/**
 * 为推送服务提供查询支持： 当客户端获取与推送服务的连接时，判断其身份的合法性； 对于合法身份，获取连接时，查询其未读消息，并进行推送
 * 
 * @author Terry
 * 
 */


public class ShuixinService implements Job{
	private ShuixinDao dao;
	private Notification notificationer;

	public ShuixinDao getDao() {
		return dao;
	}
	public void setDao(ShuixinDao dao) {
		this.dao = dao;
	}
	public Notification getNotificationer() {
		return notificationer;
	}
	public void setNotificationer(Notification notificationer) {
		this.notificationer = notificationer;
	}
	
	
/**
 * 定时任务 ：推送待办任务：
 * 查出ts_xx表中所有type类型为2的消息进行推送
 */
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			//System.out.println("推送待办任务");
			dao=(ShuixinDao) SpringBeanUtil.getBean("shuixinDao");
			notificationer=(Notification) SpringBeanUtil.getBean("NotificationPush");
			List<ToMessage> messages = dao.selectNoSends("com.zrsf.push.selectDbrws");
			if (messages != null && messages.size() > 0) {
				for (int i = 0; i < messages.size(); i++) {
					try {
						boolean success = notificationer
								.sendNotificationToUser(messages.get(i));
						if (success) {
							this.deleteMessage(messages.get(i));
						}
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
			TimerJobDao timerJobDao=(TimerJobDao) SpringBeanUtil.getBean("timerJobDao");
			timerJobDao.updateLastTime(new Date(), arg0.getJobDetail().getName(), arg0.getJobDetail().getGroup());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 接收到推送请求，推送一条信息； 推送不成功，存入信息推送表
	 * 
	 * @param message
	 */
	public void sendMessage(ToMessage message) {
		if(message!=null&&message.getDeviceId()!=null&&message.getMessage()!=null){
			boolean success = notificationer.sendNotificationToUser(message);
			if (!success) {
				this.insertMessage(message);
			}
		}else{
			System.out.println("消息要件缺失");
		}
		
	}

	/**
	 * 查询某个设备号所有还未推送的信息进行推送； 推送成功，从信息推送表中删除
	 * 
	 * @param deviceId
	 */
	public void pushAllNoSend(String deviceId) {
		try {
			List<ToMessage> messages = dao.selectNoSends(
					"com.zrsf.push.selectNoSends", deviceId);
			if (messages != null && messages.size() > 0) {
				for (int i = 0; i < messages.size(); i++) {
					try {
						boolean success = notificationer
								.sendNotificationToUser(messages.get(i));
						if (success) {
							this.deleteMessage(messages.get(i));
						}
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertMessage(ToMessage message) {
		if(message.getType()==ToMessage.DELTYPE){
			dao.deleteMessage("com.zrsf.push.deleteOld", message);
		}
		dao.insertMessage("com.zrsf.push.insertMessage", message);
	}

	public void deleteMessage(ToMessage message) {
		dao.deleteMessage("com.zrsf.push.deleteMessage", message);
	}

	/**
	 * 通过设备id查询是否存在此税务人员
	 * 
	 * @param deviceId
	 * @return 当存在时，返回true，不存在返回false
	 */
	public boolean selectSwryByDeviceId(String deviceId) {
		Swry swry = selectSwrydMByDeviceId(deviceId);
		return swry != null;
	}

	/**
	 * 通过设备id查询持有该设备的税务人员
	 * 
	 * @param deviceId
	 * @return 持有该设备的税务人员
	 */
	public Swry selectSwrydMByDeviceId(String deviceId) {
		Swry swry = dao.selectSwryByDeviceId(
				"com.zrsf.push.selectSwryByDeviceId", deviceId);
		return swry;
	}

	/**
	 * 
	 * 通过设备id查询持有该设备的税务人员的 未推送消息
	 * 
	 * @param deviceId
	 * @return
	 */
	public List<NotificationVO> selectNoSendMessageByDeviceId(String deviceId) {
		List<NotificationVO> nos = dao.selectNoSendMessageByDeviceId(deviceId);
		return nos;
	}

	/**
	 * 修改一组消息状态为已发送
	 * 
	 * @param readedMessageList
	 *            已成功推送的消息的id集合
	 */
	public void updateSuccessSend(List<String> readedMessageList) {
		if (readedMessageList != null && readedMessageList.size() > 0) {
			dao.updateMessageState(readedMessageList);
		}
	}
}
