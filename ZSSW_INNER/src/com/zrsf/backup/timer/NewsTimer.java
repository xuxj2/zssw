package com.zrsf.backup.timer;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;


public class NewsTimer {
	private static Scheduler sched;
	//默认每天2点执行
	private  String exp = "0 0 2 5 * ?";
	//默认同步涉税通告栏目
	private  String name="zgbb000";

	public NewsTimer() {}

	/***
	 * 同步新闻公告
	 * @param ex 同步策略
	 * @param lm 同步栏目名称
	 */
	public NewsTimer(String ex,String lm) {
		if(StringUtils.isNotEmpty(ex))
			this.exp = ex;
		if(StringUtils.isNotEmpty(lm))
			this.name=lm;
	}
//
//	public  void run() throws Exception {
//		sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();		
//		// 创建定时任务
//		JobDetail jobDetail = new JobDetail(this.name, Scheduler.DEFAULT_GROUP,NewsTimerJobZgbb.class);
//		// 创建任务计划
//		CronTrigger trigger = new CronTrigger(this.name,Scheduler.DEFAULT_GROUP,this.exp);		
//		sched.scheduleJob(jobDetail, trigger);
//		sched.start();
//	}

	// 停止
	public static void stop() throws Exception {
		sched.shutdown();
	}

	public  void begin(String jobName,String jobGroupName,String jobTimeExp,String jobServiceName) throws Exception {
		sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();		
		// 创建定时任务
		JobDetail jobDetail = new JobDetail(jobName, jobGroupName,Class.forName(jobServiceName));
		// 创建任务计划
		CronTrigger trigger = new CronTrigger(jobName,jobGroupName,jobTimeExp);		
		sched.scheduleJob(jobDetail, trigger);
		sched.start();
	}
	
	public  void begin(String jobName,String jobTimeExp,String jobServiceName) throws Exception {
		this.begin(jobName, Scheduler.DEFAULT_GROUP, jobTimeExp, jobServiceName);
	}
//	/**
//	 * 定时推送任务
//	 * @throws SchedulerException 
//	 * @throws ParseException 
//	 */
//	public void begin() throws SchedulerException, ParseException {
//		sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();		
//		JobDetail jobDetail = new JobDetail(this.name, "LMTS",NewsTimerSendJob.class);
//		// 创建任务计划
//		CronTrigger trigger = new CronTrigger(this.name,"LMTS", this.exp);
//		sched.scheduleJob(jobDetail, trigger);
//		sched.start();
//	}

//	public void pushReport(NewsSynsVo item) throws ParseException, SchedulerException {
//		this.exp=item.getTssj();
//		if(StringUtils.isEmpty(exp)){
//			this.exp="0 0 9 5 * ?";
//		}			
//		sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();		
//		JobDetail jobDetail = new JobDetail(item.getLmdm(), "LMTS",ReportSendTimerJob.class);
//		// 创建任务计划
//		CronTrigger trigger = new CronTrigger(item.getLmdm(),"LMTS", this.exp);
//		sched.scheduleJob(jobDetail, trigger);
//		sched.start();
//	}
}
