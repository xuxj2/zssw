package com.zrsf.forclient.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.vo.ResponseObject;

public class NotFountAction extends BaseAction {
private ResponseObject resp;
	
	public String execute() {		
		resp=new ResponseObject();
		resp.setCode(ResponseObject.failCode);
		resp.setMessage("未找到您要的服务");		
		return Action.SUCCESS;
	}

	public ResponseObject getResp() {
		return resp;
	}
	public void setResp(ResponseObject resp) {
		this.resp = resp;
	}
}
