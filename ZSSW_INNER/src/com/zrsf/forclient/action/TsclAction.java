package com.zrsf.forclient.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.vo.Swry;

/**
 * 投诉处理
 * @author Terry
 *
 */
public class TsclAction extends BaseAction {
	private Swry swry=null;	
	public String execute() {		
		swry = (Swry) session.get("swry");	
		return Action.SUCCESS;
	}
	public Swry getSwry() {
		return swry;
	}
	public void setSwry(Swry swry) {
		this.swry = swry;
	}
	
	
	
	
}
