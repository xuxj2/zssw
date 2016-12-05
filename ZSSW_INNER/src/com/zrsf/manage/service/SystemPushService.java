package com.zrsf.manage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zrsf.forclient.vo.Swry;
import com.zrsf.manage.dao.SystemPushDao;
import com.zrsf.msgpush.ToMessage;

public class SystemPushService {
	private SystemPushDao dao;
	
	
	public void insertSystemPush(String zw,String ids,String url)
	{
		List<String> list=new ArrayList<String>();
		list=idsToList(ids);
		String messId=null;
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("content", zw);
		message.put("file", url);
		message.put("xyhz", "0");		
		messId = dao.insertMessage(message);	
		Map<String, Object> fromTo = new HashMap<String, Object>();
		fromTo.put("fromRy", "XTTZ2014");
		fromTo.put("xxlx", "3");
		fromTo.put("messId", messId);
		fromTo.put("toRy", list);
		dao.insertSxxx(fromTo);
	}
	
	public SystemPushDao getDao() {
		return dao;
	}

	public void setDao(SystemPushDao dao) {
		this.dao = dao;
	}
	public List<String> idsToList(String ids) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < ids.length() / 12; i++) {
			if (!ids.substring((i * 12), i * 12 + 11).substring(0, 4)
					.equals("23205"))
				list.add(ids.substring((i * 12), i * 12 + 11));
		}
		return list;
	}
	public List<ToMessage> tomessage(String zw,String ids,String url,String fileName)
	{
		List<ToMessage> list=new ArrayList<ToMessage>();
		List<String> swglms=idsToList(ids);
		Calendar date=Calendar.getInstance();
		String time=date.get(Calendar.YEAR)+"年"+date.get(Calendar.MONTH)+"月"+date.get(Calendar.DATE)+"日";
		List<Swry> swglm=selectDeviceId(swglms);
		for(Swry swry:swglm)
		{
			ToMessage message=new ToMessage();
			JSONArray jsons=new JSONArray();
			JSONObject xxnr=new JSONObject();
			xxnr.put("title","系统通知");
			xxnr.put("time", time);
			xxnr.put("textContent", zw);
			xxnr.put("fileUrl", url);
			xxnr.put("filename",fileName);
			JSONObject json=new JSONObject();
			json.put("xxlxDm", "3");
			json.put("xxlyMc", "系统通知");
			json.put("xxlyDm", "xttx20140508");
			json.put("xxnr", xxnr.toString());
			jsons.add(json);
			message.setRecieveTime(new Date());
			message.setDeviceId(swry.getDeviceid());
			message.setMessage(jsons.toString());
			list.add(message);
		}
		return list;
	}
		/**
		 * 将工号转换为deviceid
		 * 
		 * @param toList
		 * @return
		 */
		public List<Swry> selectDeviceId(List<String> toList) {
			List<Swry> list = null;
			if (toList != null && toList.size() > 0) {
				list = dao.selectDeviceId(toList);
			}
			return list;
		}

}
