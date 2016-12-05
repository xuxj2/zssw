//package com.zrsf.backup.service;
//
//import java.util.List;
//import java.util.Map;
//
//import com.zrsf.backup.dao.ZGSJGrabDao;
//import com.zrsf.backup.http.inner.QSZJLGrab;
//import com.zrsf.backup.http.inner.RWZQWCLGrab;
//import com.zrsf.backup.http.inner.YQSBCFLGrab;
//import com.zrsf.backup.http.inner.ZQRKLGrab;
//import com.zrsf.backup.http.inner.ZQSBLGrab;
//
///**
// * 征管数据抓取：申报率、入库率、任务准期完成率、欠税增减率、逾期申报处罚率 分别按照年月抓取后存入对应的数据表
// * 
// * @author Administrator
// * 
// */
//public class ZGSJGrabService {
//	public  String[] ZGSJXM = { "sbl", "rkl", "rwzqwcl", "qszjl", "yqsbcfl" };
//	private static ZGSJGrabDao dao;
//
//	public ZGSJGrabDao getDao() {
//		return dao;
//	}
//
//	public void setDao(ZGSJGrabDao dao) {
//		ZGSJGrabService.dao = dao;
//	}
//
//	/**
//	 *   抓取某个月份某个项目的数据并入库
//	 * 	 
//	 * @param year   年份
//	 * @param month  月份，格式01,02,03,04,10,11,12
//	 * @param xm
//	 */	 
//	public void zgsjGrab(String year, String month, String xm) {		
//		try {
//			List<Map<String, String>> list = null;
//			if (ZGSJXM[0].equals(xm)) {
//				list = new ZQSBLGrab().grabOneMonth(year, month);
//				this.insertZqsbl(list);
//			} else if (ZGSJXM[1].equals(xm)) {
//				list = new ZQRKLGrab().grabOneMonth(year, month);
//				this.insertZqrkl(list);
//			} else if (ZGSJXM[2].equals(xm)) {
//				list = new RWZQWCLGrab().grabOneMonth(year, month);
//				this.insertRwzqwcl(list);
//			} else if (ZGSJXM[3].equals(xm)) {
//				list = new QSZJLGrab().grabOneMonth(year, month);
//				this.insertQszjl(list);
//			} else if (ZGSJXM[4].equals(xm)) {
//				list = new YQSBCFLGrab().grabOneMonth(year, month);
//				this.insertYqsbcfl(list);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 抓取某个月份全部项目的数据并入库
//	 */
//	public void zgsjGrab(int year, int month) {		
//		for (int i = 0; i < ZGSJXM.length; i++) {
//			this.zgsjGrab(String.valueOf(year), month>9?""+month:"0"+month,
//					ZGSJXM[i]);
//		}
//
//	}
//	
//	/**
//	 * 抓取某个项目2011年以后（包含2011年）的数据并入库
//	 */
//
//	/**
//	 * 抓取全部项目2011年以后（包含2011年）的数据并入库
//	 */
//
//	private void insertZqsbl(List<Map<String, String>> list) {
//		if (list != null && list.size() > 0) {
//			dao.insertZqsbl(list);
//		}
//
//	}
//
//	private void insertYqsbcfl(List<Map<String, String>> list) {
//		if (list != null && list.size() > 0) {
//			dao.insertYqsbcfl(list);
//		}
//	}
//
//	private void insertQszjl(List<Map<String, String>> list) {
//		if (list != null && list.size() > 0) {
//			dao.insertQszjl(list);
//		}
//	}
//
//	private void insertRwzqwcl(List<Map<String, String>> list) {
//		if (list != null && list.size() > 0) {
//			dao.insertRwzqwcl(list);
//		}
//	}
//
//	private void insertZqrkl(List<Map<String, String>> list) {
//		if (list != null && list.size() > 0) {
//			dao.insertZqrkl(list);
//		}
//	}
//
//}
