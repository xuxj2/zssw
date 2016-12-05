package com.zrsf.forclient.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.vo.ResponseObject;

public class NoPermissionAction extends BaseAction {
	private ResponseObject resp;
	
	public String execute() {		
		resp=new ResponseObject();
		resp.setCode(ResponseObject.loginCode);
		resp.setMessage("您还未登陆或登陆超时");		
		return Action.SUCCESS;
	}

	public ResponseObject getResp() {
		return resp;
	}
	public void setResp(ResponseObject resp) {
		this.resp = resp;
	}
	
}
