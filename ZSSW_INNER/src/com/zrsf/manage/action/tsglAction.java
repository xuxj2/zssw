package com.zrsf.manage.action;

import java.util.List;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.manage.service.TxlService;
import com.zrsf.manage.service.tsglService;

public class tsglAction  extends BaseAction{
	private TxlService txlService;
	private tsglService tsglService;
	private List<String> ry;
	private JSONArray lmRy;
	private boolean done;
	private String ids;
	private String lmid;
	
	public String execute(){		
		return Action.SUCCESS;
	}
	public String tsdz(){		
		return "tsdz";
	}
	
	public String selectRy()
	{
		ry=tsglService.selectDyr(lmid);
		lmRy=txlService.selectTxl("23205000000", "苏州地税局", ry);
		System.out.println(ry.toString());
		return "lmry";
	}
  public String updateRy()
  {
	  System.out.println(lmid);
	  done=tsglService.updateTx(ry, ids, lmid);
	  return "update";
  }
	public TxlService getTxlService() {
		return txlService;
	}

	public void setTxlService(TxlService txlService) {
		this.txlService = txlService;
	}

	public tsglService getTsglService() {
		return tsglService;
	}

	public void setTsglService(tsglService tsglService) {
		this.tsglService = tsglService;
	}

	public List<String> getRy() {
		return ry;
	}

	public void setRy(List<String> ry) {
		this.ry = ry;
	}

	public JSONArray getLmRy() {
		return lmRy;
	}

	public void setLmRy(JSONArray lmRy) {
		this.lmRy = lmRy;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getLmid() {
		return lmid;
	}

	public void setLmid(String lmid) {
		this.lmid = lmid;
	}
}
