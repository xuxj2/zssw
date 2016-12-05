package com.zrsf.backup.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.zrsf.backup.dao.TimerJobDao;
import com.zrsf.backup.timer.NewsTimer;
import com.zrsf.backup.vo.TimerJobVo;
import com.zrsf.common.util.FileUtil;
import com.zrsf.common.util.SpringBeanUtil;

public class ApplicationListener implements ServletContextListener {
	private static Logger LOG = Logger.getLogger(ApplicationListener.class);

	public void contextInitialized(ServletContextEvent event) {
		FileUtil.initResource();
		// 各栏目的定时器
		// NewsSynsDao dao = (NewsSynsDao) SpringBeanUtil.getBean("synsDao");
		// List<NewsSynsVo> synsList = dao.selectAllExp();
		// try {

		// 启动征管报表的抓取任务（内网），各新闻动态、政策法规、投诉信息的抓取（外网）
		// if (synsList != null && synsList.size() > 0) {
		// for (int i = 0; i < synsList.size(); i++) {
		// NewsSynsVo item = synsList.get(i);
		// if ("1".equals(item.getSftb())) {
		// new NewsTimer(item.getExp(), item.getLmdm()).run();
		// }
		// // 各新闻动态、政策法规、投诉信息的推送（仅外网适用）
		// // if(item.getTssj()!=null){
		// // new NewsTimer(item.getTssj(), item.getLmdm()).begin();
		// // }
		//
		// // 报表推送（仅内网适用）
		// if (item.getLmdm().equals("zgbb000")) {
		// new NewsTimer().pushReport(item);
		// }
		// }
		// }
		/**
		 * 周一至周五推送待办任务
		 */
		// new NewsTimer().begin("unreadedpush", "LMTS",
		// "0 20/30 8-18 ? * 2-6", "com.zrsf.msgpush.ShuixinService");
		// } catch (Exception e) {
		// LOG.error("定时器初始化出错");
		// e.printStackTrace();
		// }
		TimerJobDao dao = (TimerJobDao) SpringBeanUtil.getBean("timerJobDao");
		List<TimerJobVo> list = dao.selectAllJob();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TimerJobVo job = list.get(i);
				if (job.getEffect() == 1) {
					try {
						// System.out.println(job.getId()+job.getGroupName()+job.getJobName());
						new NewsTimer().begin(job.getJobName(), job
								.getGroupName(), job.getExpression(), job
								.getTask());
					} catch (Exception e) {
						e.printStackTrace();
						LOG.error("定时器初始化出错");
						continue;
					}

				}
			}
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

}