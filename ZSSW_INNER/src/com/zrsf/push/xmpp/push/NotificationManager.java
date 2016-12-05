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
package com.zrsf.push.xmpp.push;

import java.util.Random;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.xmpp.packet.IQ;

import com.zrsf.push.xmpp.session.ClientSession;
import com.zrsf.push.xmpp.session.SessionManager;

/** 
 * This class is to manage sending the notifcations to the users.  
 *
 * @author 7song
 * 13.12.30
 * 
 */
public class NotificationManager {

    private static final String NOTIFICATION_NAMESPACE = "androidpn:iq:notification";


    private SessionManager sessionManager;

    /**
     * Constructor.
     */
    public NotificationManager() {
        sessionManager = SessionManager.getInstance();
    }

  //发送消息底层方法
    public boolean sendNotifcationToUser(String apiKey, String username
    		,String json) {
        IQ notificationIQ = createNotificationIQ(apiKey, json);
        ClientSession session = sessionManager.getSession(username); 
        if (session != null&&session.getPresence().isAvailable()) {
            if (session.getPresence().isAvailable()) {
                notificationIQ.setTo(session.getAddress());
                session.deliver(notificationIQ);               
                return true;
            }
        }
        return false;
    }
    //自定义消息主体
    private IQ createNotificationIQ(String apikey,String json)    
    {
    	 Random random = new Random();
         String id = Integer.toHexString(random.nextInt());
         Element notification = DocumentHelper.createElement(QName.get(
                 "notification", NOTIFICATION_NAMESPACE));
         notification.addElement("id").setText(id);
         notification.addElement("apiKey").setText(apikey);
         notification.addElement("json").setText(json);
         IQ iq = new IQ();
         iq.setType(IQ.Type.set);
         iq.setChildElement(notification);
         return iq;
    	
    }
}
