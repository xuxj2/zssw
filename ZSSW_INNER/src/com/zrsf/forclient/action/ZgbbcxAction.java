package com.zrsf.forclient.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.service.BbcxService;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;

/**
 * 报表查询
 * 
 * @author Terry
 * 
 */
public class ZgbbcxAction extends BaseAction {
	private String code;
	private String nf;
	private String yf;
	private String swjgdm;
	private ResponseObject resp;

	private BbcxService service;

	public BbcxService getService() {
		return service;
	}

	public void setService(BbcxService service) {
		this.service = service;
	}

	public String execute() {
		Swry swry = (Swry) session.get("swry");
		try {
			resp = service
					.selectTaxCollectionReport(code, nf, yf, swjgdm, swry);
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

	public void setSwjgdm(String swjgdm) {
		this.swjgdm = swjgdm;
	}

}
