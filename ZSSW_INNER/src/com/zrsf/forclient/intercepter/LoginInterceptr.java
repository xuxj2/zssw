package com.zrsf.forclient.intercepter;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zrsf.forclient.vo.Swry;

public class LoginInterceptr implements Interceptor{

	private static final long serialVersionUID = 1L;

	public void destroy() {		
	}

	public void init() {	
	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Swry swry=(Swry) actionInvocation.getInvocationContext().getSession().get("swry");
		if(swry==null){			
			return Action.LOGIN;
		}
		actionInvocation.invoke();	
		return null;
	}
}
