package com.zrsf.backup.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.backup.vo.LmdyVo;
import com.zrsf.backup.vo.NewsSendVo;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.push.xmpp.model.NotificationVO;

public class LmtsDao {
	private SqlSessionTemplate sqlSessionTemplate;
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public void insertLmts(NewsSendVo vo) {
		sqlSessionTemplate.insert("com.zrsf.map.sx.insertLmts", vo);		
		sqlSessionTemplate.insert("com.zrsf.map.sx.insertDySx", vo);
	}
	
	public List<NotificationVO> selectNoPushnNewsByColumnId(String lmId, String swryDm) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("lmId", lmId);
		map.put("swryDm", swryDm);
		return sqlSessionTemplate.selectList("com.zrsf.map.sx.selectNoPushnNewsByColumnId", map);		
	}
	
	public List<Swry> selectSubscribers(String lmId) {		
		return sqlSessionTemplate.selectList("com.zrsf.map.sx.selectSubscribers", lmId);
	}
	
	public void updateMessageState(List<String> idList) {
		sqlSessionTemplate.update("com.zrsf.map.sx.updateMessageState", idList);		
	}

	public int selectLmtsForCheck(String id) {		
		return sqlSessionTemplate.selectOne("com.zrsf.map.sx.selectLmtsForCheck",id);
	}
/**
 * 确认某张报表是否已经完成入库
 * @param sql  sql语句
 * @param map  查询参数
 * @return     符合条件的结果个数
 */
	public int selectTaxReport(String sql, Map<String, Object> map) {		
		return sqlSessionTemplate.selectOne(sql,map);
	}

public List<LmdyVo> selectSubscribers(String[] keys) {
	String sql="com.zrsf.map.txl.selectLmdy";	
	return sqlSessionTemplate.selectList(sql,keys);
}
}
