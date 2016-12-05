package com.zrsf.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zrsf.manage.dao.RywhDao;
import com.zrsf.manage.vo.SwryVO;
import com.zrsf.manage.vo.rzjg;
import com.zrsf.manage.vo.zw;

public class RywhService {
	private RywhDao dao;

	public List<zw> selectZw() {
		return dao.selectZw();
	}

	public List<rzjg> selectRzjg(String jgDm) {
		return dao.selectRzjg(jgDm);
	}

	public boolean updateSwry(SwryVO swry) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("rzjgDm", swry.getRzjgDm());
			map.put("zwDm", swry.getZwDm());
			map.put("deviceid", swry.getDeviceid());
			map.put("jhbj", swry.getJhbj());
			map.put("swryDm", swry.getSwryDm());
			dao.updateSwry(map);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateSwrys(String ids, String rzjgDm, String zwDm) {
		try {
			List<String> list = dao.idsToList(ids);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("swryDm", list.get(i));
					map.put("rzjgDm", rzjgDm);
					map.put("zwDm", zwDm);
					dao.updateSwrys(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public SwryVO selectSwry(String swryDm) {
		SwryVO swry=dao.selectSwry(swryDm);
		if(swry.getSex().equals("2"))
		{
			swry.setSex("女");
		}
		else{
			swry.setSex("男");
		}
		return swry;
	}

	public RywhDao getDao() {
		return dao;
	}

	public void setDao(RywhDao dao) {
		this.dao = dao;
	}

}
