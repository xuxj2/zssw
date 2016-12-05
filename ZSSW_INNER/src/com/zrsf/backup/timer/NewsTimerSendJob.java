//package com.zrsf.backup.timer;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import com.zrsf.backup.service.NewsSendService;
//import com.zrsf.common.util.SpringBeanUtil;
//
//public class NewsTimerSendJob implements Job{
//	public void execute(JobExecutionContext je) throws JobExecutionException {	
//		NewsSendService service=(NewsSendService) SpringBeanUtil.getBean("newsSendService");
//		service.sendNews(je.getJobDetail().getName());
//	}
//
//}
