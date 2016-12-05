package com.zrsf.backup.timer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zrsf.backup.dao.LmtsDao;
import com.zrsf.backup.dao.TimerJobDao;
import com.zrsf.common.util.SpringBeanUtil;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.msgpush.ShuixinService;
import com.zrsf.msgpush.ToMessage;
import com.zrsf.push.xmpp.model.NotificationVO;

public class GcReportSendTimerJob implements Job {
	//private static Map<String, String> zgbb;   
	private static Map<String, String> gcbb;
	public static final String GCBBLMDM= "gcbb000";
	private static Map<String, String> bbmc;

	/*	 
	 * 65609 规财报表-分地区公共财政预算收入预计情况表 65606 规财报表-分地区税收收入入库情况表 65608
	 * 规财报表-分地区税收收入预计情况表 65605 规财报表-分税种税收收入入库情况表 65607 规财报表-公共财政预算收入入库情况表
	 */
	
	static {
		gcbb = new HashMap<String, String>();
//		gcbb.put("65605", "com.zrsf.map.bbcx.checkGcbb1");
//		gcbb.put("65606", "com.zrsf.map.bbcx.checkGcbb2");
//		gcbb.put("65607", "com.zrsf.map.bbcx.checkGcbb3");
//		gcbb.put("65608", "com.zrsf.map.bbcx.checkGcbb4");
//		gcbb.put("65609", "com.zrsf.map.bbcx.checkGcbb5");
		
		gcbb.put("65680", "com.zrsf.map.bbcx.checkGcbb6");
//		gcbb.put("65681", "com.zrsf.map.bbcx.checkGcbb7");
//		gcbb.put("65682", "com.zrsf.map.bbcx.checkGcbb8");

		bbmc = new HashMap<String, String>();
		bbmc.put("65605", "规财报表-分税种税收收入入库情况表");
		bbmc.put("65606", "规财报表-分地区税收收入入库情况表");
		bbmc.put("65607", "规财报表-公共财政预算收入入库情况表");
		bbmc.put("65608", "规财报表-分地区税收收入预计情况表");
		bbmc.put("65609", "规财报表-分地区公共财政预算收入预计情况表");
		
		bbmc.put("65680", "规财报表");
		bbmc.put("65681", "规财报表-税收收入分行业表");
		bbmc.put("65682", "规财报表-减免税分减免事项表");
	}

	private LmtsDao dao = (LmtsDao) SpringBeanUtil.getBean("lmtsDao");
	private ShuixinService service = (ShuixinService) SpringBeanUtil
			.getBean("shuixinService");

	
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Calendar cal = Calendar.getInstance();
		int nf = cal.get(Calendar.YEAR);
		int yf = cal.get(Calendar.MONTH)+1;//默认推送当月数据		
		this.pushReport(nf,yf);
		TimerJobDao timerJobDao=(TimerJobDao) SpringBeanUtil.getBean("timerJobDao");
		timerJobDao.updateLastTime(new Date(), arg0.getJobDetail().getName(), arg0.getJobDetail().getGroup());
	}
	
	
	public void pushReport(int nf,int yf){	
		/*
		 * 推送规财: 规财报表只推给回台指定的人员，从swry_lmdy中查询订阅了征管报表的人员
		 */
		try {
			List<NotificationVO> nos = new ArrayList<NotificationVO>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ssq", String.valueOf(nf) + (yf > 9 ? yf : "0" + yf));
			Iterator<String> it = gcbb.keySet().iterator();
			while (it.hasNext()) {
				String reportName = it.next();
				int ok = dao.selectTaxReport(gcbb.get(reportName), map);
				if (ok > 0) {
					NotificationVO notificationVO = new NotificationVO();
					notificationVO.setXxid(String.valueOf(nf) + "-"
							+ (yf > 9 ? yf : "0" + yf));
					notificationVO.setCjsj(new Date());
					notificationVO.setSxxxid(reportName + String.valueOf(nf)
							+ yf);
					notificationVO.setXxlyDm(reportName);
					notificationVO.setXxlyMc("税收统计");
					notificationVO.setXxlxDm("9");
					
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH)+1;
				if(nf==year&&yf==month){
					notificationVO.setXxnr(nf + "年" + yf + "月"+(cal.get(Calendar.DATE)-1)+"日"
							+ bbmc.get(reportName));
				}else{
					notificationVO.setXxnr(nf + "年" + yf + "月"
							+ bbmc.get(reportName));
				}
					nos.add(0,notificationVO);
				}
			}
			JSONArray jArray = JSONArray.fromObject(nos);
			String message = jArray.toString();
			List<Swry> recipients = dao.selectSubscribers(GCBBLMDM);
			if (recipients != null) {
				for (int i = 0; i < recipients.size(); i++) {
					Swry recipient = recipients.get(i);
					ToMessage toMessage = new ToMessage();
					toMessage.setDeviceId(recipient.getDeviceid());
					toMessage.setMessage(message);
					toMessage.setType(ToMessage.DELTYPE);
					
					service.sendMessage(toMessage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
