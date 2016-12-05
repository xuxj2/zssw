package com.zrsf.backup.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.backup.timer.GcReportSendTimerJob;
import com.zrsf.backup.timer.ReportSendTimerJob;
import com.zrsf.common.action.BaseAction;

public class ReportPushAction extends BaseAction {
	private int year;
	private int month;
	private String bblmdm;
	 
	
	public String execute(){
		if(GcReportSendTimerJob.GCBBLMDM.equals(bblmdm)){
			GcReportSendTimerJob service=new GcReportSendTimerJob();
			service.pushReport(year, month);
		}else {
			ReportSendTimerJob service=new ReportSendTimerJob();
			service.pushReport(year, month);
		}
		
		return Action.SUCCESS;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

//	public ReportSendTimerJob getService() {
//		return service;
//	}
//
//	public void setService(ReportSendTimerJob service) {
//		this.service = service;
//	}
	
	
}
