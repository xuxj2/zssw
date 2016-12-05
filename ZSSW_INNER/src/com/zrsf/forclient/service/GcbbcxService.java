package com.zrsf.forclient.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zrsf.common.util.IllegalPermissionException;
import com.zrsf.common.util.PermissionUtil;
import com.zrsf.forclient.dao.BbcxDao;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;

public class GcbbcxService {
	private BbcxDao dao;
	public BbcxDao getDao() {
		return dao;
	}
	public void setDao(BbcxDao dao) {
		this.dao = dao;
	}

	private static Map<String, String> sqlMap;
	static {
		sqlMap = new HashMap<String, String>();		
		sqlMap.put("B05001", "com.zrsf.map.bbcx.selectGcbb1");//分税种税收收入入库
		
		sqlMap.put("B05002", "com.zrsf.map.bbcx.selectGcbb2");//分地区税收收入入库
		sqlMap.put("B05003", "com.zrsf.map.bbcx.selectGcbb3");//公共财政预算收入入库
		sqlMap.put("B05004", "com.zrsf.map.bbcx.selectGcbb4");//分地区税收收入预计
		sqlMap.put("B05005", "com.zrsf.map.bbcx.selectGcbb5");//分地区公共财政预算收入
		
		sqlMap.put("B05006", "com.zrsf.map.bbcx.selectGcbb6");//税收统计--主页面
		sqlMap.put("B05007", "com.zrsf.map.bbcx.selectGcbb7");//税收收入分行业表
		sqlMap.put("B05008", "com.zrsf.map.bbcx.selectGcbb8");//减免税分减免事项表
		
		
	}

	
	public ResponseObject selectTaxStatisticReport(String code, String nf,
			String yf,  Swry swry) {
		ResponseObject resp = new ResponseObject();
		if (!sqlMap.containsKey(code)) {
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("未找到服务");
			return resp;
		}		
		boolean per;
		try {
			per=PermissionUtil.hasPermission(swry.getLimit(), PermissionUtil.ZGBB);
			if(!per){
				resp.setCode(ResponseObject.failCode);
				resp.setMessage("您的权限不足");
				return resp;
			}			
		} catch (IllegalPermissionException e) {			
			e.printStackTrace();
		}
		
		Calendar calendar = Calendar.getInstance();
		if (nf == null || nf.equals("")) {
			nf = String.valueOf(calendar.get(Calendar.YEAR));
		}
		if (yf == null || yf.equals("")) {
			yf = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		}
		if (!nf.matches("\\d{4}") || !yf.matches("(0?[1-9])|(1[12])")) {
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("日期参数格式不正确");
			return resp;
		} else {
			int k = calendar.get(Calendar.YEAR) - Integer.parseInt(nf);
			if (k < 0 || k > 1) {
				resp.setCode(ResponseObject.failCode);
				resp.setMessage("查询日期范围超过可查询日期范围，系统现在支持查询两个年度内报表查询");
				return resp;
			}
		}		
		String znfw = swry.getZnfw();	
//		if (znfw==null||znfw.length()>5) {//201405190 13:32  由原znfw.length()>7改为znfw.length()>5，只让市局机关查看 
//			resp.setCode(ResponseObject.failCode);
//			resp.setMessage("您的权限不足");
//			return resp;
//		}else
		if(znfw.equals("23205")){
			znfw="232";			
		}		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("ssq", nf+(yf.length()<2?"0"+yf:yf));		
		map.put("swjg", znfw);	
		
		List<Object> list=dao.selectTaxStatisticData(sqlMap.get(code), map);	
		if(list!=null&&list.size()>0){
			resp.setEntity(list);
		}		
		resp.setCode(ResponseObject.successCode);
		return resp;
	}
	

	
}
