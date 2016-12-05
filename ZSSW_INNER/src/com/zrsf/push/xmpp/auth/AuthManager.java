/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.zrsf.push.xmpp.auth;

import com.zrsf.common.util.SpringBeanUtil;
import com.zrsf.msgpush.ShuixinService;
import com.zrsf.push.xmpp.UnauthenticatedException;
import com.zrsf.push.xmpp.XmppServer;

/** 
 * This class is to provide the methods associated with user authentication. 
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class AuthManager 
//implements BeanFactoryAware
{

//  private static BeanFactory  beanfactory;
//
//    public void setBeanFactory(BeanFactory factory) throws BeansException {
//		
//    	AuthManager.beanfactory=factory;
//		
//	}
    
	/**
     * Authenticates a user with a username and plain text password, and
     * returns an AuthToken.
     * 
     * @param username the username
     * @param password the password
     * @return an AuthToken
     * @throws UnauthenticatedException if the username and password do not match
     */
    public static AuthToken authenticate(String username, String password)
            throws UnauthenticatedException 
            {
        if (username == null || password == null) {
            throw new UnauthenticatedException();
        }
        username = username.trim().toLowerCase();
        if (username.contains("@")) {
            int index = username.indexOf("@");
            String domain = username.substring(index + 1);
            if (domain.equals(XmppServer.getInstance().getServerName())) {
                username = username.substring(0, index);
            } else {
                throw new UnauthenticatedException();
            }
        }
//        ShuixinService  service=(ShuixinService)SpringBeanUtil.getBean("shuixinService");
//        String swryDm=service.selectSwrydMByDeviceId(username);
        AuthToken au=null;
//       if(swryDm!=null&&swryDm!=""){
    	   au= new AuthToken(username);
////    	   service.sendNoPushMessages(username);    	   
//       }
////       else throw new UnauthenticatedException();
//            
        return au;
    }
    /**
     * Returns true if plain text password authentication is supported according to JEP-0078.
     * 
     * @return true if plain text password authentication is supported
     */
    public static boolean isPlainSupported() {
        return true;
    }

    /**
     * Returns true if digest authentication is supported according to JEP-0078.
     * 
     * @return true if digest authentication is supported
     */
    public static boolean isDigestSupported() {
        return false;
    }

    

}
