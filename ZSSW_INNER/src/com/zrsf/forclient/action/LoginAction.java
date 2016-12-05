package com.zrsf.forclient.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.common.util.IllegalPermissionException;
import com.zrsf.common.util.PermissionUtil;
import com.zrsf.forclient.service.SwryService;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;

public class LoginAction extends BaseAction {
	// 登陆用验证参数
	private String swryGh; // 工号
	private String deviceId; // 设备id
	private String password; // 密码
//	private String yddh; // 移动电话

	private ResponseObject resp;// 登陆错误时返回对象

	private SwryService service;

	public SwryService getService() {
		return service;
	}

	public void setService(SwryService service) {
		this.service = service;
	}

	/**
	 * 登陆 set方法中验证登陆参数格式合法性，不合法validate=false,对resp进行赋值，返回错误信息;
	 * 如果validate=true,对密码进行加密，再到数据库中验证用户信息； 验证不通过，返回登录错误信息，对resp进行赋值；
	 * 登陆成功，转发到查询未读消息
	 */

	public String execute() {	
		resp = service.toLogin(password, deviceId);		
//		resp = service.toLogin(password, swryGh);		
		if (resp.getEntity() != null) {
			Swry swry = (Swry) resp.getEntity();
			session.put("swry", swry);
			try {
				swry.setLimit(PermissionUtil.addPermission(swry.getLimit(), PermissionUtil.LOGIN));
			} catch (IllegalPermissionException e) {				
				e.printStackTrace();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("swryDm", swry.getSwryDm());
			map.put("swryXm", swry.getXm());
			map.put("txUrl", swry.getTxUri());
			map.put("swryGh", swry.getSwryGh());
			map.put("bgdh", swry.getBgdh());
			map.put("rzjgMc", swry.getRzjgMc());
			map.put("rzjgDm", swry.getRzjgDm());
			map.put("yddh", swry.getYddh());	
			map.put("zw", swry.getZw());
			map.put("xb", swry.getXb());
			map.put("qxz", swry.getLimit());
			map.put("yzsj", swry.getYzsj());
			map.put("sessionId", request.getSession().getId());		
			resp.setEntity(map);
		}
		return Action.SUCCESS;
	}

	public void setSwryGh(String swryGh) {	
		this.swryGh = swryGh;
	}

	public void setDeviceId(String deviceId) {
		// TODO
		this.deviceId = deviceId;
	}

	public void setPassword(String password) {
		// TODO
		this.password = password;
	}

//	public void setYddh(String yddh) {
//		// TODO
//		this.yddh = yddh;
//	}

	public ResponseObject getResp() {
		return resp;
	}

}
