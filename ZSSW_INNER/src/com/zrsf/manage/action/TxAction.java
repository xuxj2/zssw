package com.zrsf.manage.action;

import java.util.List;

import net.sf.json.JSONArray;

import com.zrsf.common.action.BaseAction;
import com.zrsf.manage.service.TxService;
import com.zrsf.manage.service.TxlService;
import com.zrsf.manage.vo.PageModel;
import com.zrsf.manage.vo.Tiaoxian;

public class TxAction  extends BaseAction{
	private String txid;
	private String swjg;
	private PageModel<Tiaoxian>  page;
	private List<String> ry;
	private  boolean done;
	private TxService service;
	private TxlService txlservice;
	private JSONArray txlxr;
	private String ids;
	private String txmc;
	private String type;
	
	public String allTx()
	{
		page=service.selectAllTx(swjg,page);
		return "alltx";
	}
	public String allTxlxr()
	{
		if(type.equals("txlxr"))
		{
		txmc=service.selectTxmc(txid);
		ry=service.selectTxlxr(txid);
		txlxr=txlservice.selectTxl("23205000000", "苏州地税局", ry);
		return "txlxr";
		}
		else if(type.equals("add")){
			txlxr=txlservice.selectTxl("23205000000", "苏州地税局", null);
			return "add";
		}
		return "txlxr";
	}
	public String update()
	{
		service.updateTx(ry, ids, txmc, txid);
		ry=service.selectTxlxr(txid);
		return "update";
	}
	public String add()
	{
		service.NewTx(txmc, ids);
		return "add";
	}
	public String delete()
	{
		service.deleteTx(txid);
		page=service.selectAllTx(swjg,page);
		return "delete";
	}
	public String selectTx()
	{
		page=service.selectTx(txmc, page);
		return "selectTx";
	}
	public TxService getService() {
		return service;
	}
	public void setService(TxService service) {
		this.service = service;
	}
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public PageModel<Tiaoxian> getPage() {
		return page;
	}
	public void setPage(PageModel<Tiaoxian> page) {
		this.page = page;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public String getSwjg() {
		return swjg;
	}
	public void setSwjg(String swjg) {
		this.swjg = swjg;
	}
	public List<String> getRy() {
		return ry;
	}
	public void setRy(List<String> ry) {
		this.ry = ry;
	}
	public TxlService getTxlservice() {
		return txlservice;
	}
	public void setTxlservice(TxlService txlservice) {
		this.txlservice = txlservice;
	}
	public JSONArray getTxlxr() {
		return txlxr;
	}
	public void setTxlxr(JSONArray txlxr) {
		this.txlxr = txlxr;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getTxmc() {
		return txmc;
	}
	public void setTxmc(String txmc) {
		this.txmc = txmc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
