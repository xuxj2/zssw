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
import com.zrsf.forclient.vo.bbcx.DjhsbbVo;
import com.zrsf.forclient.vo.bbcx.DjhsbdbbVo;
import com.zrsf.forclient.vo.bbcx.QszjlVo;
import com.zrsf.forclient.vo.bbcx.RwzqwclVo;
import com.zrsf.forclient.vo.bbcx.YqsbcflVo;
import com.zrsf.forclient.vo.bbcx.ZqrklVo;
import com.zrsf.forclient.vo.bbcx.ZqsblVo;

public class BbcxService {
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
		sqlMap.put("B04001", "com.zrsf.map.bbcx.selectDjhs");
		sqlMap.put("B04002", "com.zrsf.map.bbcx.selectDjhsbd");
		sqlMap.put("B04003", "com.zrsf.map.bbcx.selectZqsbl");
		sqlMap.put("B04004", "com.zrsf.map.bbcx.selectZqrkl");
		sqlMap.put("B04005", "com.zrsf.map.bbcx.selectQszjl");
		sqlMap.put("B04006", "com.zrsf.map.bbcx.selectYqsbcfl");
		sqlMap.put("B04007", "com.zrsf.map.bbcx.selectRwzqwcl");
		
		
//		sqlMap.put("B04010", "com.zrsf.map.bbcx.selectHjdjhs");
//		sqlMap.put("B04011", "com.zrsf.map.bbcx.selectHjhsbd");
	}
	/**
	 * 
	 * 征管报表查询权限：根据税务机关代码和职能范围进行匹配，职能范围最多匹配到7位数字，7位数字以下的职能范围以前7位为准进行匹配。
	 * 职能范围只有5位的用户，可以看到全市的征管报表；职能范围7位的用户，只能看到自身党组局的征管报表；
	 * 职能范围超出7位的用户，按照7位职能范围匹配，只能看到自身党组局的征管报表。
	 */
	public ResponseObject selectTaxCollectionReport(String code, String nf,
			String yf, String swjgdm, Swry swry) {
		ResponseObject resp = new ResponseObject();
		if (!sqlMap.containsKey(code)) {
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("未找到服务");
			return resp;
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
		if (znfw.length() > 7) {
			znfw = znfw.substring(0, 7);
		}
		if (swjgdm != null && !swjgdm.trim().equals("")) {
			if (!swjgdm.matches("23205\\d{0,6}")) {
				resp.setCode(ResponseObject.failCode);
				resp.setMessage("税务机关代码格式不正确");
				return resp;
			}
			/*
			 * 功能权限验证20140519 15:57
			 */
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
			// 验证数据查询权限（根据任职机关代码和职能范围验证），只能查询自己管辖下的
			if (!swjgdm.startsWith(znfw)) {
				resp.setCode(ResponseObject.failCode);
				resp.setMessage("您的权限不足");
				return resp;
			}
			swjgdm = swjgdm.replaceAll("(00)*$", "");
		} else {
			// 默认该税务人员最大数据权限
			swjgdm = znfw;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nf", nf);
		map.put("yf", yf);
		map.put("swjg", swjgdm.length() < 11 ? swjgdm + "__" : swjgdm);

		List<Object> entity = dao.selectTaxCollectionReport(sqlMap.get(code),
				map);
		combitePage(code, entity);
		resp.setEntity(entity);	
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	private List<Object> combitePage(String code, List<Object> entity) {
		if (entity != null && entity.size() > 0) {
			if (code.equals("B04001") ) {
				combitePageOne(entity);
			}else if (code.equals("B04002")) {
				combitePageTwo(entity);
			} else if (code.equals("B04003")) {
				combitePageSeven(entity);
			} else if (code.equals("B04004")) {
				combitePageThree(entity);
			} else if (code.equals("B04005")) {
				combitePageFour(entity);
			} else if (code.equals("B04006")) {
				combitePageFive(entity);
			} else if (code.equals("B04007")) {
				combitePageSix(entity);
			}
		}		
		return entity;

	}
	/**
	 * 准期申报率页
	 * @param entity
	 */
	private void combitePageSeven(List<Object> entity) {
		ZqsblVo hj=new ZqsblVo();
		hj.setSwjgmc("合计");
		for(int i=0;i<entity.size();i++){
			ZqsblVo peace=(ZqsblVo) entity.get(i);			
			hj.setYisbhs(hj.getYisbhs()+peace.getYisbhs());
			hj.setYsbhs(hj.getYsbhs()+peace.getYsbhs());
			hj.setZqsbhs(hj.getZqsbhs()+peace.getZqsbhs());			
		}
		hj.setSbl(hj.getYisbhs()*1.0/hj.getYsbhs()*100);
		hj.setZqsbl(hj.getZqsbhs()*1.0/hj.getYsbhs()*100);
		entity.add(hj);
	}
/**
 * 任务准期完成率页
 */
	private void combitePageSix(List<Object> entity) {
		RwzqwclVo hj=new RwzqwclVo();
		hj.setSwjgmc("合计");
		for(int i=0;i<entity.size();i++){
			RwzqwclVo peace=(RwzqwclVo) entity.get(i);
			hj.setYwcrws(hj.getYwcrws()+peace.getYwcrws());
			hj.setZqwcrws(hj.getZqwcrws()+peace.getZqwcrws());
		}
		hj.setRwzqwcl(hj.getZqwcrws()*1.0/hj.getYwcrws()*100);
		entity.add(hj);
	}
/**
 * 逾期申报处罚率页
 * @param entity
 */
	private void combitePageFive(List<Object> entity) {
		YqsbcflVo hj=new YqsbcflVo();
		hj.setSwjgmc("合计");
		for(int i=0;i<entity.size();i++){
			YqsbcflVo peace=(YqsbcflVo) entity.get(i);
			hj.setCfhs(hj.getCfhs()+peace.getCfhs());
			hj.setYqsbhs(hj.getYqsbhs()+peace.getYqsbhs());			
		}
		hj.setYqsbcfl(hj.getCfhs()*1.0/hj.getYqsbhs()*100);
		entity.add(hj);		
	}
/**
 * 欠税增减率页
 * @param entity
 */
	private void combitePageFour(List<Object> entity) {
		QszjlVo hj=new QszjlVo();
		hj.setSwjgmc("合计");
		for(int i=0;i<entity.size();i++){
			QszjlVo peace=(QszjlVo) entity.get(i);
			hj.setQcqsje(hj.catQcqsje()+peace.catQcqsje());
			hj.setQmqsje(hj.catQmqsje()+peace.catQmqsje());
			hj.setQqskje(hj.catQqskje()+peace.catQqskje());
			hj.setXzqsje(hj.catXzqsje()+peace.catXzqsje());
		}
		hj.setQszjl(hj.catQmqsje()/hj.catQcqsje()*100-100.0);
		entity.add(hj);
		
	}
/**
 * 准期入库率页
 * @param entity
 */
	private void combitePageThree(List<Object> entity) {
		ZqrklVo hj=new ZqrklVo();
		hj.setSwjgmc("合计");
		for(int i=0;i<entity.size();i++){
			ZqrklVo peace=(ZqrklVo) entity.get(i);
			hj.setYirkje(hj.catYirkje()+peace.catYirkje());
			hj.setYrkje(hj.catYrkje()+peace.catYrkje());
			hj.setYrkhs(hj.getYrkhs()+peace.getYrkhs());			
			hj.setZqrkhs(hj.getZqrkhs()+peace.getZqrkhs());
		}
		hj.setRkl(hj.catYirkje()/hj.catYrkje()*100);
		hj.setZqrkl(hj.getZqrkhs()*1.0/hj.getYrkhs()*100);
		entity.add(hj);
		
	}
/**
 * 登记户数变动页
 * @param entity
 */
	private void combitePageTwo(List<Object> entity) {
		DjhsbdbbVo hj=new DjhsbdbbVo();
		hj.setSwjgmc("合计");
		for(int i=0;i<entity.size();i++){
			DjhsbdbbVo peace=(DjhsbdbbVo) entity.get(i);
			hj.setQchs(hj.getQchs()+peace.getQchs());
			hj.setQmhs(hj.getQmhs()+peace.getQmhs());
		}
		entity.add(hj);
		
	}
/**
 * 登记户数页
 * @param entity
 */
	private void combitePageOne(List<Object> entity) {
		DjhsbbVo hj=new DjhsbbVo();
		hj.setSwjgmc("合计");
		for(int i=0;i<entity.size();i++){
			DjhsbbVo peace=(DjhsbbVo) entity.get(i);
			hj.setQyhs(hj.getQyhs()+peace.getQyhs());
			hj.setGths(hj.getGths()+peace.getGths());			
			hj.setFzchs(hj.getFzchs()+peace.getFzchs());
			hj.setTyhs(hj.getTyhs()+peace.getTyhs());
			hj.setZchs(hj.getZchs()+peace.getZchs());
		}
		entity.add(hj);		
	}
}
