package com.zrsf.common.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

public class BaseAction implements SessionAware, ServletRequestAware,
		ServletResponseAware, ServletContextAware {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;
	protected ServletContext application;

//	public String execute() {
//		return Action.SUCCESS;
//	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public void setSession(Map<String, Object> session) {		
		this.session = session;

	}

	public void setServletContext(ServletContext application) {
		this.application = application;
	}

}
