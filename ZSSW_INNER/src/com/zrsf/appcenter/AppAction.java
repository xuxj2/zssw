package com.zrsf.appcenter;

import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.vo.ResponseObject;

public class AppAction extends BaseAction {
	private ResponseObject resp;
	
//	private ApplicationVo app;	
//	public ApplicationVo getApp() {
//		return app;
//	}
//	public void setApp(ApplicationVo app) {
//		this.app = app;
//	}

	private String packageName;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	private ApplicationService service;
	public ApplicationService getService() {
		return service;
	}
	public void setService(ApplicationService service) {
		this.service = service;
	}
	
	public String lastest(){
		try {
			resp=service.lastest(packageName);
		} catch (Exception e) {
			e.printStackTrace();
			resp=new ResponseObject();
			resp.setCode(ResponseObject.failCode);
		}		
		return "lastest";
	}
	public String list(){
		try {
			resp=service.list();
		} catch (Exception e) {
			resp=new ResponseObject();
			resp.setCode(ResponseObject.failCode);
		}		
		return "lastest";
	}
	
	
	public ResponseObject getResp() {
		return resp;
	}
	public void setResp(ResponseObject resp) {
		this.resp = resp;
	}
	
}
