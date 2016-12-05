package com.zrsf.forclient.service;

import java.util.HashMap;
import java.util.Map;

import com.zrsf.common.util.MD5Util;
import com.zrsf.forclient.dao.SwryDao;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;

public class SwryService {
	private SwryDao dao;	
	public SwryDao getDao() {
		return dao;
	}
	public void setDao(SwryDao dao) {
		this.dao = dao;
	}


	/**
	 * 登录验证,登陆成功记写登陆记录
	 * @param swrygh
	 * @param password
	 * @param deviceid
	 * @param yddh
	 * @return
	 */
	public ResponseObject toLogin(String password, String deviceid) {
			password=MD5Util.md5(password);
			Map<String, Object> map=new HashMap<String, Object>();
//			map.put("swryGh", deviceid);
			map.put("password", password);
			map.put("deviceid", deviceid);
			String sql="com.zrsf.map.login.selectLoginSwry";
			Swry swry=dao.selectSwry(sql, map);
				
			//Swry swry=dao.selectLoginSwry(password,deviceid);							
			ResponseObject resp=new ResponseObject();
			if(swry!=null){				
				resp.setEntity(swry);
				resp.setCode(ResponseObject.successCode);				
			}else{
				resp.setCode(ResponseObject.failCode);
				resp.setMessage("登录信息错误，请检查您使用的手机和输入的密码");
			}
			
			return resp;
		}

}
