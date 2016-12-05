package com.zrsf.backup.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.backup.vo.TimerJobVo;

public class TimerJobDao {
	private  SqlSessionTemplate sqlSessionTemplate;
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	/**
	 * 查找某一个任务
	 * @return
	 */
	public TimerJobVo selectJobById(int id){
		String sql="com.zrsf.timer.selectJobById";		
		return sqlSessionTemplate.selectOne(sql,id);
	}
	
	public TimerJobVo selectByName(String groupName,String jobName){
		String sql="com.zrsf.timer.selectByName";	
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("groupName", groupName);
		map.put("jobName", jobName);
		return sqlSessionTemplate.selectOne(sql,map);
	}
	
	/**
	 * 查找所有定时任务
	 * @return
	 */
	public List<TimerJobVo> selectAllJob(){
		String sql="com.zrsf.timer.selectAll";		
		return sqlSessionTemplate.selectList(sql);
	}
	/**
	 * 修改最近执行时间
	 */
	public  void updateLastTime(Date date,String jobName,String groupName){		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String lastTime=sdf.format(date);
		this.updateLastTime(lastTime, jobName, groupName);
	}
	public  void updateLastTime(String lastTime,String jobName,String groupName){
		String sql="com.zrsf.timer.updateLastTime";		
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("groupName", groupName);
		map.put("jobName", jobName);
		map.put("lastTime", lastTime);
		sqlSessionTemplate.update(sql, map);
	}
	
	/**
	 * 修改执行时间表达式
	 */
	public void updateExpression(String expression,int id){
		String sql="com.zrsf.timer.updateExpression";		
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("expression", expression);
		map.put("id", id);		
		sqlSessionTemplate.update(sql, map);
	}
	
	/**
	 * 修改任务状态
	 */
	public void updateEffect(int effect,int id){
		String sql="com.zrsf.timer.updateEffect";		
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("effect", effect);
		map.put("id", id);		
		sqlSessionTemplate.update(sql, map);
	}
	
}
