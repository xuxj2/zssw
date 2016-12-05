//package com.zrsf.backup.timer;
//
//import java.util.Calendar;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import com.zrsf.backup.service.ZGSJGrabService;
//import com.zrsf.common.util.SpringBeanUtil;
//
//public class NewsTimerJobZgbb implements Job {	
//private ZGSJGrabService service;
//	
//	public void execute(JobExecutionContext je) throws JobExecutionException {	
//		if(service==null){
//			service=(ZGSJGrabService) SpringBeanUtil.getBean("zgsjGrabService");
//		}		
//		Calendar cal=Calendar.getInstance();	
//		int nf=cal.get(Calendar.YEAR);
//		int yf=cal.get(Calendar.MONTH);
//		if(yf==0){
//			yf=12;
//			nf=nf-1;
//		}
//		service.zgsjGrab(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));		
//	}
//	
//}
