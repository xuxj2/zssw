package com.zrsf.forclient.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.service.IndividuationService;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;

/**
 * 个性化设置，修改个人信息： 修改头像
 * 
 * @author Terry
 * 
 */
public class IndividuationAction extends BaseAction {
	private String code;
	private ResponseObject resp;

	private File file; // 上传到临时文件全路径
	private String fileContentType; // 上传文件类型
	private String fileFileName; // 文件名字

	private String sex;
	private String cellphone;
	private String officePhone;

	private IndividuationService service;

	public IndividuationService getService() {
		return service;
	}

	public void setService(IndividuationService service) {
		this.service = service;
	}

	public String execute() {
		Swry swry = (Swry) session.get("swry");
		Swry swryUp = new Swry();
		swryUp.setXb(sex);
		swryUp.setYddh(cellphone);
		swryUp.setBgdh(officePhone);		
		swryUp.setSwryDm(swry.getSwryDm());
		resp = service.updateIndInfo(swryUp);		
		return Action.SUCCESS;
	}
	
	public String avr(){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		Swry swry = (Swry) session.get("swry");
		if (file != null) {
			try {				
				String realPath = ServletActionContext.getServletContext().getRealPath("tx");
				fileFileName = System.currentTimeMillis()
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
			
				resp=service.updateAvatar(swry.getSwryDm(), fileFileName);
			} catch (Exception e) {
				e.printStackTrace();
				resp=new ResponseObject();
				resp.setCode(ResponseObject.successCode);
				resp.setMessage("头像上传失败");
			} finally {
				try {
					bis.close();
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
		return Action.SUCCESS;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ResponseObject getResp() {
		return resp;
	}
	public void setResp(ResponseObject resp) {
		this.resp = resp;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
}
