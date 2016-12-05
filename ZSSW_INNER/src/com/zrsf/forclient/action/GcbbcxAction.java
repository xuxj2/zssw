package com.zrsf.forclient.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.service.GcbbcxService;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;

public class GcbbcxAction extends BaseAction {
	private String code;
	private String nf;
	private String yf;	
	private ResponseObject resp;

	private GcbbcxService service;
	public GcbbcxService getService() {
		return service;
	}
	public void setService(GcbbcxService service) {
		this.service = service;
	}

	public String execute() {
		Swry swry = (Swry) session.get("swry");
		try {
			resp = service
					.selectTaxStatisticReport(code, nf, yf,  swry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public ResponseObject getResp() {
		return resp;
	}

	public void setResp(ResponseObject resp) {
		this.resp = resp;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

}
