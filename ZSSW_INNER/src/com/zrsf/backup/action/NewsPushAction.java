package com.zrsf.backup.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.backup.service.RecieveService;
import com.zrsf.common.action.BaseAction;

public class NewsPushAction extends BaseAction {
	private String messages;
	private RecieveService service;

	private boolean push = false;

	public String execute() {
		try {
			service.pushLmts(messages);
			push = true;
		} catch (Exception e) {
			e.printStackTrace();
			push = false;
		}
		return Action.SUCCESS;
	}
	
	public void setMessages(String messages) {
		this.messages = messages;
	}

	public RecieveService getService() {
		return service;
	}

	public void setService(RecieveService service) {
		this.service = service;
	}

	public boolean isPush() {		
		return push;
	}

	
	
	public static void main(String[] args) {
		for(int i=0;i<128;i++){
			System.out.println(i+": "+(char)i);
		}
	}
}
