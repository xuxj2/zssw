package com.zrsf.forclient.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.service.DbrwService;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;

public class DbrwAction extends BaseAction {
	private String code;
	private int pagecode;	
	private ResponseObject resp;
	private DbrwService service;
	public DbrwService getService() {
		return service;
	}
	public void setService(DbrwService service) {
		this.service = service;
	}
	
	public String execute() {
		Swry swry = (Swry) session.get("swry");		
		try {
			resp=service.queryToDoList(swry,pagecode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getPagecode() {
		return pagecode;
	}
	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}
	public ResponseObject getResp() {
		return resp;
	}
	public void setResp(ResponseObject resp) {
		this.resp = resp;
	}
	

	
}
