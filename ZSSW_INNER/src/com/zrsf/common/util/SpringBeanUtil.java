package com.zrsf.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		SpringBeanUtil.applicationContext=arg0;
	}

	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}	
}
