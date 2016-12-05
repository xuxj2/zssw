package com.zrsf.manage.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.manage.vo.Tiaoxian;

public class TxDao {
	private SqlSessionTemplate sessionTemplate;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	/**
	 * 添加条线联系人
	 * 
	 * @param map
	 */
	public void insertTxry(Map<String, String> map) {
		sessionTemplate.insert("com.zrsf.manage.manager.insertTxlxr", map);
	}

	/**
	 * 添加条线
	 * 
	 * @param map
	 * @return
	 */
	public String insertTx(Map<String, String> map) {
		sessionTemplate.insert("com.zrsf.manage.manager.insertTx", map);
		return map.get("");
	}

	/**
	 * 更新条线
	 * 
	 * @param map
	 */
	public void updateTx(Map<String, String> map) {
		sessionTemplate.update("com.zrsf.manage.manager.updateTx", map);
	}

	/**
	 * 删除条线
	 * 
	 * @param map
	 */
	public void deleteTx(String tx) {
		sessionTemplate.delete("com.zrsf.manage.manager.deleteTx", tx);
	}

	/**
	 * 删除条线联系人
	 * 
	 * @param map
	 */
	public void deleteTxry(Map<String, String> map) {
		sessionTemplate.delete("com.zrsf.manage.manager.deleteTxlxr", map);
	}

	/**
	 * 查询所有条线
	 * 
	 * @return
	 */
	public List<Tiaoxian> selectAllTx(Map<String, Object> paramsMap) {
		List<Tiaoxian> list = new ArrayList<Tiaoxian>();
		list = sessionTemplate.selectList(
				"com.zrsf.manage.manager.selectAllTx", paramsMap);
		return list;
	}

	public List<Tiaoxian> selectTx(Map<String, Object> paramsMap) {
		List<Tiaoxian> list = new ArrayList<Tiaoxian>();
		list = sessionTemplate.selectList(
				"com.zrsf.manage.manager.selectTx", paramsMap);
		return list;

	}

	public int selectTxAmount() {
		return sessionTemplate
				.selectOne("com.zrsf.manage.manager.selectTxAmount");
	}

	/**
	 * 查询所有该条线下的联系人
	 * 
	 * @param tx
	 * @return
	 */
	public List<String> selectTxlxr(String tx) {
		List<String> list = new ArrayList<String>();
		list = sessionTemplate.selectList(
				"com.zrsf.manage.manager.selectTxlxr", tx);
		return list;
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

	public String selectTxmc(String txid) {
		return sessionTemplate.selectOne("com.zrsf.manage.manager.selectTxmc",
				txid);
	}
}
