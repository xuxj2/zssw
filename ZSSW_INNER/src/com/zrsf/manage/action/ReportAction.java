package com.zrsf.manage.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.manage.service.TaxExecelParseService;
import com.zrsf.manage.vo.TaxStatisticRow;

public class ReportAction extends BaseAction {
	TaxExecelParseService service ;
	public TaxExecelParseService getService() {
		return service;
	}
	public void setService(TaxExecelParseService service) {
		this.service = service;
	}
	private File file;
	private String fileFileName;
	private String ssq;
	private List<TaxStatisticRow> sssrList;
	private List<TaxStatisticRow> czysList;
	private List<String> sssrTitleList;
	private List<String> czysTitleList;

	public String execute(){
		sssrTitleList=service.selectSssrReportList();
		czysTitleList=service.selectCzysReportList();
		return Action.SUCCESS;
	}
	
	public String query(){
		sssrList=service.selectSssrReport(ssq);
		czysList=service.selectCzysReport(ssq);
		return "report";
	}
	
	/**
	 * 预计报表上传：
	 * (1)保存文件;
	 * (2)解析文件，存入数据库
	 * @return
	 */
	public String upload(){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;		
		if (file != null) {
			try {					
				String realPath = ServletActionContext.getServletContext().getRealPath("reports");
				fileFileName=	new String(fileFileName.getBytes("utf-8"),"utf-8");
				fileFileName = ssq+System.currentTimeMillis()
						+ fileFileName.substring(fileFileName.indexOf("."));
				String filePath = realPath + File.separatorChar + fileFileName;
				// 读取临时文件
				bis = new BufferedInputStream(new FileInputStream(file));
				// 写文件
				bos = new BufferedOutputStream(new FileOutputStream(filePath));
				int size = -1;
				while ((size = bis.read()) != -1) {
					bos.write(size);
					bos.flush();
				}					
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bis.close();
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			service.parseTaxStatistic(ssq, file);
		}
		return this.query();
	}
		
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}	
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getSsq() {
		return ssq;
	}
	public void setSsq(String ssq) {
		this.ssq = ssq;
	}
	public List<TaxStatisticRow> getSssrList() {
		return sssrList;
	}
	public void setSssrList(List<TaxStatisticRow> sssrList) {
		this.sssrList = sssrList;
	}
	public List<TaxStatisticRow> getCzysList() {
		return czysList;
	}
	public void setCzysList(List<TaxStatisticRow> czysList) {
		this.czysList = czysList;
	}
	public List<String> getSssrTitleList() {
		return sssrTitleList;
	}
	public void setSssrTitleList(List<String> sssrTitleList) {
		this.sssrTitleList = sssrTitleList;
	}
	public List<String> getCzysTitleList() {
		return czysTitleList;
	}
	public void setCzysTitleList(List<String> czysTitleList) {
		this.czysTitleList = czysTitleList;
	}
	
}
