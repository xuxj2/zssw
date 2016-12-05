package com.zrsf.manage.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.vo.Swry;

public class ManageAction extends BaseAction {
	private Swry user=null;	
	/**
	 * 新闻、办税日历部分在外网，外网请求这里获取登录验证信息
	 * @return
	 */
	public String execute() {		
		user = (Swry) session.get("user");	
		return Action.SUCCESS;
	}
	
	public String head(){		
		return "head";
	}
	public String left(){		
		return "left";
	}
	public String main(){		
		return "main";
	}
	public Swry getUser() {
		return user;
	}
	public void setUser(Swry user) {
		this.user = user;
	}
	
}
