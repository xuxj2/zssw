package com.zrsf.manage.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.manage.service.TaxIncomeService;

public class TaxIncomeAction extends BaseAction {
	TaxIncomeService service;

	public TaxIncomeService getService() {
		return service;
	}

	public void setService(TaxIncomeService service) {
		this.service = service;
	}

	private File file;
	private String fileFileName;
	
	public String execute() {
		return Action.SUCCESS;
	}
	
	
	/**
	 * 税收统计--分组织税收收入--非税收入上传： (1)保存文件; (2)解析文件，存入数据库
	 * 
	 * @return
	 */
	public String upload() {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		if (file != null) {
			try {
				String realPath = ServletActionContext.getServletContext()
						.getRealPath("reports");
				fileFileName = new String(fileFileName.getBytes("utf-8"),
						"utf-8");
				fileFileName =  System.currentTimeMillis()
						+ fileFileName;
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
			service.parseTaxStatistic(file);
		}
		return Action.SUCCESS;
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

}
