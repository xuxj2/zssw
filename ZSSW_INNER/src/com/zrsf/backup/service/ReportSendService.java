//package com.zrsf.backup.service;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.json.JSONArray;
//
//import com.zrsf.backup.vo.NewsSendVo;
//import com.zrsf.common.util.SpringBeanUtil;
//import com.zrsf.forclient.dao.BbcxDao;
//import com.zrsf.forclient.vo.Swry;
//import com.zrsf.push.xmpp.console.Notification;
//import com.zrsf.push.xmpp.model.NotificationVO;
//import com.zrsf.push.xmpp.model.UserVO;
//
//public class ReportSendService {
//	private static Map<String, String> zgbb;
//	private static Map<String, String> gcbb;
//	private static Map<String, String> reportNameMap;
//	/*
//	 * 65589 征管报表-逾期申报处罚率
//	 * 65616 征管报表-登记户数（个体纳税人） 
//	 * 65617 征管报表-登记户数（企业纳税人） 
//	 * 65619 征管报表-登记户数变动（个体纳税人） 
//	 * 65618 征管报表-登记户数变动（企业纳税人） 
//	 * 65587 征管报表-欠税增减率
//	 * 65588 征管报表-任务准期完成率 
//	 * 65586 征管报表-准期入库率 
//	 * 65585 征管报表-准期申报率
//	 * 
//	 * 65609 规财报表-分地区公共财政预算收入预计情况表 
//	 * 65606 规财报表-分地区税收收入入库情况表 
//	 * 65608 规财报表-分地区税收收入预计情况表 
//	 * 65605 规财报表-分税种税收收入入库情况表 
//	 * 65607 规财报表-公共财政预算收入入库情况表
//	 */
//
//	static {
//		zgbb = new HashMap<String, String>();
//		zgbb.put("65617", "com.zrsf.map.bbcx.selectDjhs");
//		zgbb.put("65618", "com.zrsf.map.bbcx.selectDjhsbd");
//		zgbb.put("65585", "com.zrsf.map.bbcx.selectZqsbl");
//		zgbb.put("65586", "com.zrsf.map.bbcx.selectZqrkl");
//		zgbb.put("65587", "com.zrsf.map.bbcx.selectQszjl");
//		zgbb.put("65589", "com.zrsf.map.bbcx.selectYqsbcfl");
//		zgbb.put("65588", "com.zrsf.map.bbcx.selectRwzqwcl");
//		zgbb.put("65616", "com.zrsf.map.bbcx.selectGtdjhs");
//		zgbb.put("65619", "com.zrsf.map.bbcx.selectGthsbd");
//
//		gcbb = new HashMap<String, String>();
//		gcbb.put("65605", "com.zrsf.map.bbcx.selectGcbb1");
//		gcbb.put("65606", "com.zrsf.map.bbcx.selectGcbb2");
//		gcbb.put("65607", "com.zrsf.map.bbcx.selectGcbb3");
//		gcbb.put("65608", "com.zrsf.map.bbcx.selectGcbb4");
//		gcbb.put("65609", "com.zrsf.map.bbcx.selectGcbb5");
//		
//		reportNameMap= new HashMap<String, String>();
//		reportNameMap.put("65616", "登记户数（个体纳税人）");
//		reportNameMap.put("65617", "登记户数（企业纳税人）");
//		reportNameMap.put("65618", "登记户数变动（企业纳税人））");
//		reportNameMap.put("65619", "登记户数变动（个体纳税人）");
//		reportNameMap.put("65585", "准期申报率");
//		reportNameMap.put("65586", "准期入库率 ");
//		reportNameMap.put("65587", "欠税增减率");
//		reportNameMap.put("65588", "任务准期完成率");
//		reportNameMap.put("65589", "逾期申报处罚率");
//		
//		
//		
//		reportNameMap.put("65605", "分税种税收收入入库情况表 ");
//		reportNameMap.put("65606", "分地区税收收入入库情况表 ");
//		reportNameMap.put("65607", "公共财政预算收入入库情况表");
//		reportNameMap.put("65608", "分地区税收收入预计情况表 ");
//		reportNameMap.put("65609", "分地区公共财政预算收入预计情况表 ");
//	}
//	private BbcxDao dao;
//
//	public BbcxDao getDao() {
//		return dao;
//	}
//
//	public void setDao(BbcxDao dao) {
//		this.dao = dao;
//	}
//
//	
//	/**
//	 * 查询有无可推送的报表，有就添加需要推送的报表记录
//	 * @param nf
//	 * @param yf
//	 */
//	public void addReportNews(){
//		Calendar cal=Calendar.getInstance();	
//		int nf=cal.get(Calendar.YEAR);
//		int yf=cal.get(Calendar.MONTH);
//		if(yf==0){
//			yf=12;
//			nf=nf-1;
//		}
//		//生成征管报表推送记录
//		Iterator<String> it = zgbb.keySet().iterator();
//		while (it.hasNext()) {
//			String reportName = it.next();			
//			NewsSendVo vo=new NewsSendVo();		
//			vo.setNewsId(String.valueOf(nf) + yf);		
//			vo.setLmId(reportName);			
//			vo.setXxlx("8");
//			vo.setTitle(reportNameMap.get(reportName)+""+nf+(yf>9?yf:"0"+yf));
//			dao.insertReportSendMwssage(vo);
//			//TODO
//		}
//	
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	public void zgbbSend(int nf, int yf) {
//		/*
//		 * 推送征管报表:1、查询出所有需要推送征管报表的用户（所有已激活的税务人员）；2、分别查询每个用户的各征管报表；3、调用推送服务。
//		 * 征管报表查询权限：根据税务机关代码和职能范围进行匹配，职能范围最多匹配到7位数字，7位数字以下的职能范围以前7位为准进行匹配。
//		 * 职能范围只有5位的用户，可以看到全市的征管报表；职能范围7位的用户，只能看到自身党组局的征管报表；
//		 * 职能范围超出7位的用户，按照7位职能范围匹配，只能看到自身党组局的征管报表。
//		 */
//		dao = (BbcxDao) SpringBeanUtil.getBean("bbcxDao");
//		List<Swry> recipients = dao
//				.selectRecipients("com.zrsf.map.txl.selectColRecipients");
//		if (recipients != null) {
//			for (int i = 0; i < recipients.size(); i++) {
//				Swry recipient = recipients.get(i);
//				String znfw = recipient.getZnfw();
//				if (znfw.length() > 7) {
//					znfw = znfw.substring(0, 7);
//				}
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("nf", nf);
//				map.put("yf", yf);
//				map.put("swjg", znfw);
//
//				UserVO user = new UserVO();
//				user.setDeviceId(recipient.getDeviceid());
//				List<NotificationVO> nos = new ArrayList<NotificationVO>();
//				Iterator<String> it = zgbb.keySet().iterator();
//				while (it.hasNext()) {
//					String reportName = it.next();
//					List<Object> report = dao.selectTaxCollectionReport(zgbb
//							.get(reportName), map);
//					if (report != null && report.size() > 0) {
//						JSONArray jArray = JSONArray.fromObject(report);
//						NotificationVO notificationVO = new NotificationVO();
//						notificationVO.setXxid(String.valueOf(nf) + yf);
//						notificationVO.setCjsj(new Date());
//						notificationVO.setSxxxid(reportName
//								+ String.valueOf(nf) + yf);
//						notificationVO.setXxlyDm(reportName);
//						notificationVO.setXxlyMc("征管报表");
//						notificationVO.setXxlxDm("8");
//						notificationVO.setXxnr(jArray.toString());
//						nos.add(notificationVO);
//					}
//				}
//				user.setNotification(nos);
//				Notification notification = (Notification) SpringBeanUtil
//						.getBean("NotificationPush");
//				boolean sucess = notification.sendNotificationToUser(user);
//				if (!sucess) {
//					System.out.println("===========给" + user.getDeviceId()
//							+ "推送征管报表失败" );
//				}
//			}
//		}
//
//	}
//
//	/**
//	 * 推送一个人一个报表的内容
//	 * 
//	 * @param reportName
//	 * @param report
//	 * @param deviceId
//	 * @param year
//	 * @param month
//	 */
//	public void sendOneRepoertToOnePerson(String reportName,
//			List<Object> report, String deviceId, int year, int month) {
//		if (report == null || report.size() < 1) {
//			return;
//		}
//		UserVO user = new UserVO();
//		user.setDeviceId(deviceId);
//		NotificationVO notificationVO = new NotificationVO();
//		notificationVO.setXxid(String.valueOf(year) + month);
//		notificationVO.setCjsj(new Date());
//		notificationVO.setSxxxid(reportName + String.valueOf(year) + month);
//		notificationVO.setXxlyDm(reportName);
//		if (zgbb.keySet().contains(reportName)) {
//			notificationVO.setXxlyMc("征管报表");
//			notificationVO.setXxlxDm("8");
//		} else {
//			notificationVO.setXxlyMc("规财报表");
//			notificationVO.setXxlxDm("9");
//		}
//		JSONArray jArray = JSONArray.fromObject(report);
//		notificationVO.setXxnr(jArray.toString());
//		List<NotificationVO> nos = new ArrayList<NotificationVO>();
//		nos.add(notificationVO);
//		Notification notification = (Notification) SpringBeanUtil
//				.getBean("NotificationPush");
//		boolean sucess = notification.sendNotificationToUser(user);
//		if (!sucess) {
//			System.out.println("===========给" + deviceId + "推送"+reportName+"报表失败" );
//		}
//	}
//
//	
//	
//	
//}
