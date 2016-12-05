package com.zrsf.forclient.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.forclient.vo.SwjgTxl;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.push.xmpp.model.NotificationVO;

public class TxlDao {
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<Swry> selectAllSwry() {
		List<Swry> list = null;
		list = sqlSessionTemplate.selectList("com.zrsf.map.txl.selectAllSwry");
		return list;
	}

	public List<Swry> selectTxllb(String swjg) {
		List<Swry> list = null;
		list = sqlSessionTemplate.selectList(
				"com.zrsf.map.txl.selectSwryBySwjgCc", swjg);
		return list;
	}

	public List<Swry> selectCylxr(String swry) {
		List<Swry> list = null;
		list = sqlSessionTemplate.selectList("com.zrsf.map.txl.selectCylxr",
				swry);
		return list;
	}

	public List<Map<String, String>> selectAllTx() {
		List<Map<String, String>> list = null;
		list = sqlSessionTemplate.selectList("com.zrsf.map.txl.selectAllTx");
		return list;
	}

	public List<Swry> selectTxlxr(String tx) {
		List<Swry> list = null;
		list = sqlSessionTemplate
				.selectList("com.zrsf.map.txl.selectTxlxr", tx);
		return list;
	}

	public Swry selectJtxx(String swryDm) {
		Swry swry = null;
		swry = sqlSessionTemplate.selectOne("com.zrsf.map.txl.selectJtxx",
				swryDm);
		return swry;
	}

	public List<Map<String, String>> selectDyh(String swry) {
		List<Map<String, String>> list = null;
		list = sqlSessionTemplate
				.selectList("com.zrsf.map.txl.selectDyh", swry);
		return list;
	}

	public int insertDyh(Map<String, Object> dy) {
		int num = sqlSessionTemplate.insert("com.zrsf.map.txl.insertDyh", dy);
		return num;
	}

	public int deletetDyh(Map<String, Object> dy) {
		int num = sqlSessionTemplate.delete("com.zrsf.map.txl.deleteDyh", dy);
		return num;
	}

	public String insertMessage(Map<String, Object> message) {
		sqlSessionTemplate.insert("com.zrsf.map.sx.insertMessage", message);
		return String.valueOf(message.get("messageId"));
	}

	public void insertSxxx(Map<String, Object> fromTo) {
		sqlSessionTemplate.insert("com.zrsf.map.sx.insertSxxx", fromTo);

	}

	public List<String> selectTxRy(String tx) {
		return sqlSessionTemplate.selectList("com.zrsf.map.sx.selectTxRy", tx);
	}

	public List<NotificationVO> selectNoSendMessage(Map<String, Object> message) {
		return sqlSessionTemplate.selectList("com.zrsf.map.sx.selectWdxx",
				message);
	}

	public void updateMessageState(List<String> list) {
		sqlSessionTemplate.update("com.zrsf.map.sx.updateMessageState", list);

	}

	public SwjgTxl selectAllSwryInOrg() {
		SwjgTxl jg = sqlSessionTemplate.selectOne(
				"com.zrsf.map.txl.selectOrgCatchSwry", "23205000000");
		return jg;
	}

	/**
	 * ？？？？？？？mybatis中直接配置，便于在缓存中管理？？？
	 * 
	 * 
	 * 
	 * 按组织结构形式查询出苏州地税全部人员的通讯录列表： 展开苏州地税下的二级和三级机构，二级机构没有下级机构的展开其下全部人员，
	 * 有下级机构的展开下级机构，三级机构后直接展开其全部人员（包括下级机构的人员）
	 * 
	 * @return
	 */
	public SwjgTxl selectSwjgZzWithSwry() {
		SwjgTxl szds = new SwjgTxl();
		szds.setSwjgDm("23205000000");
		szds.setSwjgMc("苏州市地税局");	
		
		List<SwjgTxl> list = sqlSessionTemplate.selectList(
				"com.zrsf.map.txl.selectSwjgZzWithSwry", "23205000000");
		
		SwjgTxl sjjg = new SwjgTxl();
		sjjg.setSwjgDm("23205010000");
		sjjg.setSwjgMc("市局机关");
		
		List<SwjgTxl> jgxjjg=new ArrayList<SwjgTxl>();
		if (list != null&&list.size()>0) {			
			for (int i = 0; i < list.size(); i++) {
				SwjgTxl erjiJg = list.get(i);
				String erjiJgCc = erjiJg.getSwjgCc();
				if (erjiJgCc.length() < 8) {
					List<SwjgTxl> sanjiJgList = sqlSessionTemplate.selectList("com.zrsf.map.txl.selectSwjgZzWithSwry", erjiJg.getSwjgDm());
					if (sanjiJgList != null&&sanjiJgList.size()>0) {
						for (int j = 0; j < sanjiJgList.size(); j++) {
							SwjgTxl sanjiJg = sanjiJgList.get(j);
							List<Swry> ryList = sqlSessionTemplate.selectList("com.zrsf.map.txl.selectSwryBySwjgCc",sanjiJg.getSwjgCc());
							if(ryList!=null&&ryList.size()>0){
								sanjiJg.setSwrys(ryList);								
							}							
						}
						erjiJg.setXjjgs(sanjiJgList);
					}					
				} else {
					List<Swry> ryList = sqlSessionTemplate.selectList("com.zrsf.map.txl.selectSwryBySwjgDm", erjiJg.getSwjgDm());
					if(ryList!=null&&ryList.size()>0){
						erjiJg.setSwrys(ryList);
					}
					list.remove(i--);
					jgxjjg.add(erjiJg);	
				}
			}
		}
		sjjg.setXjjgs(jgxjjg);
		list.add(0,sjjg);	
		szds.setXjjgs(list);
		// SwjgTxl szds =sqlSessionTemplate.selectOne(
		// "com.zrsf.map.txl.selectSwjgZzWithSwry", "23205000000");
		return szds;
	}

	public void updateDyh(Map<String, Object> dy) {
		sqlSessionTemplate.update("com.zrsf.map.txl.updateDyh",dy);		
	}

	public List<Swry> selectDeviceId(List<String> list) {
		return sqlSessionTemplate.selectList("com.zrsf.map.txl.selectDeviceId",
				list);
	}

	public void updateMessageStateToRead(Map<String, String> map) {
		sqlSessionTemplate.update("com.zrsf.map.sx.updateMessageStateToRead", map);		
	}

	public Map<String, String> selectMessageById(String messageId) {		
		return sqlSessionTemplate.selectOne("com.zrsf.map.sx.selectMessageById", messageId);
	}

	public List<Map<String, String>> selectToSwryByMessageId(String messageId) {
		return sqlSessionTemplate.selectList("com.zrsf.map.sx.selectToSwryByMessageId", messageId);
	}

	public Swry selectSwryById(String id) {		
		return sqlSessionTemplate.selectOne("com.zrsf.map.txl.selectSwryById",id);
	}

}
