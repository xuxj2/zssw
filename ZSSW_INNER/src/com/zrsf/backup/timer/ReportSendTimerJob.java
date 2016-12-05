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

/**
 * 征管报表推送    2014-05-25 修改，征管每月推送，规财每日推送  
 */
public class ReportSendTimerJob implements Job {
	private static Map<String, String> zgbb;
	//private static Map<String, String> gcbb;
	public static final String GXBBLMDM= "zgbb000";
	private static Map<String, String> bbmc;

	/*
	 * 65589 征管报表-逾期申报处罚率 65616 征管报表-登记户数（个体纳税人） 65617 征管报表-登记户数（企业纳税人） 65619
	 * 征管报表-登记户数变动（个体纳税人） 65618 征管报表-登记户数变动（企业纳税人） 65587 征管报表-欠税增减率 65588
	 * 征管报表-任务准期完成率 65586 征管报表-准期入库率 65585 征管报表-准期申报率
	 * 
	 * 65609 规财报表-分地区公共财政预算收入预计情况表 65606 规财报表-分地区税收收入入库情况表 65608
	 * 规财报表-分地区税收收入预计情况表 65605 规财报表-分税种税收收入入库情况表 65607 规财报表-公共财政预算收入入库情况表
	 */
	static {
		zgbb = new HashMap<String, String>();
		zgbb.put("65617", "com.zrsf.map.bbcx.checkDjhs");
		zgbb.put("65618", "com.zrsf.map.bbcx.checkDjhs");
		zgbb.put("65585", "com.zrsf.map.bbcx.checkZqsbl");
		zgbb.put("65586", "com.zrsf.map.bbcx.checkZqrkl");
		zgbb.put("65587", "com.zrsf.map.bbcx.checkQszjl");		
		zgbb.put("65588", "com.zrsf.map.bbcx.checkRwzqwcl");
		zgbb.put("65589", "com.zrsf.map.bbcx.checkYqsbcfl");

//		gcbb = new HashMap<String, String>();
//		gcbb.put("65605", "com.zrsf.map.bbcx.checkGcbb1");
//		gcbb.put("65606", "com.zrsf.map.bbcx.checkGcbb2");
//		gcbb.put("65607", "com.zrsf.map.bbcx.checkGcbb3");
//		gcbb.put("65608", "com.zrsf.map.bbcx.checkGcbb4");
//		gcbb.put("65609", "com.zrsf.map.bbcx.checkGcbb5");

		bbmc = new HashMap<String, String>();
		bbmc.put("65617", "征管报表-登记户数");
		bbmc.put("65618", "征管报表-登记户数变动");
		bbmc.put("65585", "征管报表-准期申报率");
		bbmc.put("65586", "征管报表-准期入库率");
		bbmc.put("65587", "征管报表-欠税增减率");
		bbmc.put("65588", "征管报表-任务准期完成率");
		bbmc.put("65589", "征管报表-逾期申报处罚率");
//		bbmc.put("65605", "规财报表-分税种税收收入入库情况表");
//		bbmc.put("65606", "规财报表-分地区税收收入入库情况表");
//		bbmc.put("65607", "规财报表-公共财政预算收入入库情况表");
//		bbmc.put("65608", "规财报表-分地区税收收入预计情况表");
//		bbmc.put("65609", "规财报表-分地区公共财政预算收入预计情况表");
	}

	private LmtsDao dao = (LmtsDao) SpringBeanUtil.getBean("lmtsDao");
	private ShuixinService service = (ShuixinService) SpringBeanUtil
			.getBean("shuixinService");

	
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Calendar cal = Calendar.getInstance();
		/*
		 * 默认推送上月数据
		 */
		int nf = cal.get(Calendar.YEAR);
		int yf = cal.get(Calendar.MONTH);
		if (yf == 0) {			
			yf = 12;
			nf = nf - 1;
		}
		this.pushReport(nf,yf);
		TimerJobDao timerJobDao=(TimerJobDao) SpringBeanUtil.getBean("timerJobDao");
		timerJobDao.updateLastTime(new Date(), arg0.getJobDetail().getName(), arg0.getJobDetail().getGroup());
	}
	
	
	public void pushReport(int nf,int yf){		
		/*
		 * 推送征管报表: 征管报表只推给回台指定的人员，从swry_lmdy中查询订阅了征管报表的人员
		 */
		try {
			List<NotificationVO> nos = new ArrayList<NotificationVO>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nf", nf);
			map.put("yf", yf);
			Iterator<String> it = zgbb.keySet().iterator();
			while (it.hasNext()) {
				String reportName = it.next();
				int ok = dao.selectTaxReport(zgbb.get(reportName), map);
				if (ok > 0) {
					NotificationVO notificationVO = new NotificationVO();
					notificationVO.setXxid(String.valueOf(nf) + "-"
							+ (yf > 9 ? yf : "0" + yf));
					notificationVO.setCjsj(new Date());
					notificationVO.setXxlxDm("8");
					notificationVO.setXxlyDm(reportName);
					notificationVO.setSxxxid(reportName + String.valueOf(nf)
							+ yf);
					notificationVO.setXxlyMc("征管状况");
					notificationVO.setXxnr(nf + "年" + yf + "月"
							+ bbmc.get(reportName));
					nos.add(0,notificationVO);
				}
			}
			JSONArray jArray = JSONArray.fromObject(nos);
			String message = jArray.toString();
			List<Swry> recipients = dao.selectSubscribers(GXBBLMDM);
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
//		/*
//		 * 推送规财: 规财报表只推给回台指定的人员，从swry_lmdy中查询订阅了征管报表的人员
//		 */
//		try {
//			List<NotificationVO> nos = new ArrayList<NotificationVO>();
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("ssq", String.valueOf(nf) + (yf > 9 ? yf : "0" + yf));
//			Iterator<String> it = gcbb.keySet().iterator();
//			while (it.hasNext()) {
//				String reportName = it.next();
//				int ok = dao.selectTaxReport(gcbb.get(reportName), map);
//				if (ok > 0) {
//					NotificationVO notificationVO = new NotificationVO();
//					notificationVO.setXxid(String.valueOf(nf) + "-"
//							+ (yf > 9 ? yf : "0" + yf));
//					notificationVO.setCjsj(new Date());
//					notificationVO.setSxxxid(reportName + String.valueOf(nf)
//							+ yf);
//					notificationVO.setXxlyDm(reportName);
//					notificationVO.setXxlyMc("税收统计");
//					notificationVO.setXxlxDm("9");
//					notificationVO.setXxnr(nf + "年" + yf + "月"
//							+ bbmc.get(reportName));
//					nos.add(0,notificationVO);
//				}
//			}
//			JSONArray jArray = JSONArray.fromObject(nos);
//			String message = jArray.toString();
//			List<Swry> recipients = dao.selectSubscribers("gcbb000");
//			if (recipients != null) {
//				for (int i = 0; i < recipients.size(); i++) {
//					Swry recipient = recipients.get(i);
//					ToMessage toMessage = new ToMessage();
//					toMessage.setDeviceId(recipient.getDeviceid());
//					toMessage.setMessage(message);
//					toMessage.setType(9);
//					service.sendMessage(toMessage);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
