package com.zrsf.manage.action;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.manage.service.ReportService;

public class ZgUploadAction extends BaseAction {
	private File[] uploads;
	private String[] uploadsFileName;
	private String[] uploadsContentType;
	private String message;	
	private List<?> reports;
	
	private String reportId;
	private int year;
	private int month;
	
	
	private ReportService service;
	public ReportService getService() {
		return service;
	}
	public void setService(ReportService service) {
		this.service = service;
	}
	
	public String execute(){
		return Action.SUCCESS;
	}
	
	/**
	 * 查询某张报表
	 * @return
	 */
	public String view(){
		if(reportId==null||reportId.trim().length()!=6){
			reportId="B04001";
		}
		Calendar ca=Calendar.getInstance();
		int currentYear=ca.get(Calendar.YEAR);
		int currentMonth=ca.get(Calendar.MONTH)+1;
		if(year<2000||year>currentYear){
			year=currentYear;			
		}
		if(month<1||month>12){
			month=currentMonth;					
		}
		reports=service.selectReport(reportId,year,month);		
		if(reports!=null){
			return reportId;
		}
		message="reportId 参数错误";
		return Action.ERROR;
	}
	
/**
 * 报表上传
 * @return
 */
	public String upload() {
		if (uploads != null && uploads.length > 0) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/reports");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				for (int i = 0; i < uploads.length; i++) {					
					String nname=	new String(uploadsFileName[i].getBytes());					
					FileUtils.copyFile(uploads[i], new File(file,nname));
				//System.getProperty("file.encoding")
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				service.parseReports(uploads);
			} catch (Exception e) {				
				e.printStackTrace();
				message="报表解析出错";
				return Action.ERROR;
			} 
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 删除报表
	 * @return
	 */
	public String delete(){
		try {
			int count=service.removeReport(reportId, year, month);
			if(count<1){
				message="0";
			}else{
				message="1";
			}
			
		} catch (Exception e) {
			message="3";
		}
		
		
		return "error";
	}

	public File[] getUploads() {
		return uploads;
	}

	public void setUploads(File[] uploads) {
		this.uploads = uploads;
	}

	public String[] getUploadsFileName() {
		return uploadsFileName;
	}

	public void setUploadsFileName(String[] uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}

	public String[] getUploadsContentType() {
		return uploadsContentType;
	}

	public void setUploadsContentType(String[] uploadsContentType) {
		this.uploadsContentType = uploadsContentType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
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
	public List<?> getReports() {
		return reports;
	}
	public void setReports(List<?> reports) {
		this.reports = reports;
	}
	

	
	
	
	
	
	
	
	
	
	
}
