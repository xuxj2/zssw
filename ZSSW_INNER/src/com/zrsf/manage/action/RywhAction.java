package com.zrsf.manage.action;

import java.util.List;

import net.sf.json.JSONArray;

import com.zrsf.common.action.BaseAction;
import com.zrsf.manage.service.RywhService;
import com.zrsf.manage.service.TxlService;
import com.zrsf.manage.vo.SwryVO;
import com.zrsf.manage.vo.rzjg;
import com.zrsf.manage.vo.zw;

public class RywhAction extends BaseAction {

	private String ids;
	private SwryVO swry;
	private List<zw> zws;
	private List<rzjg> rzjgs;
	private String swryDm;
	private String rzjgDm;
	private String zwDm;
	private boolean done;
	private RywhService rywhService;
	private TxlService txlService;
	private JSONArray txl;
	
	public String txl() {
		txl=txlService.selectTxl("23205000000", "苏州地税局", null);
		zws=rywhService.selectZw();
		rzjgs=rywhService.selectRzjg("23205000000");
		return "txl";
	}
	
	public String selectSwry()
	{
		try{
		swry=rywhService.selectSwry(swryDm);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			done=false;
		}
		done=true;
		return "swry";
	}
	public String updateSwry() {
		
		System.out.println(swry.toString());
		return "update";
	}

	public String updateSwrys() {
		done=rywhService.updateSwrys(ids, rzjgDm, zwDm);
		return "update";
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SwryVO getSwry() {
		return swry;
	}

	public void setSwry(SwryVO swry) {
		this.swry = swry;
	}

	public List<zw> getZws() {
		return zws;
	}

	public void setZws(List<zw> zws) {
		this.zws = zws;
	}

	public List<rzjg> getRzjgs() {
		return rzjgs;
	}

	public void setRzjgs(List<rzjg> rzjgs) {
		this.rzjgs = rzjgs;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public RywhService getRywhService() {
		return rywhService;
	}

	public String getRzjgDm() {
		return rzjgDm;
	}

	public void setRzjgDm(String rzjgDm) {
		this.rzjgDm = rzjgDm;
	}

	public String getZwDm() {
		return zwDm;
	}

	public void setZwDm(String zwDm) {
		this.zwDm = zwDm;
	}

	public void setRywhService(RywhService rywhService) {
		this.rywhService = rywhService;
	}

	public TxlService getTxlService() {
		return txlService;
	}

	public void setTxlService(TxlService txlService) {
		this.txlService = txlService;
	}

	public JSONArray getTxl() {
		return txl;
	}

	public void setTxl(JSONArray txl) {
		this.txl = txl;
	}
	public String getSwryDm() {
		return swryDm;
	}
	public void setSwryDm(String swryDm) {
		this.swryDm = swryDm;
	}
	

}
