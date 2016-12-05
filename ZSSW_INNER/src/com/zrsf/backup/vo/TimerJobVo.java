package com.zrsf.backup.vo;

import java.io.Serializable;


/**
 * 定时任务数据模型
 * @author Terry
 * 2014-5-25
 */
public class TimerJobVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;					//ID
	private String jobName;			//任务名称
	private String groupName;		//任务组名
	private String expression;		//任务时间表达式
	private String task;			//任务执行的类路径
	private String explain;			//任务描述
	private String lastTime;		//上次执行时间   yyyy-mm-dd hh:mi:ss
	private String modifiedTime;	//修改时间	  yyyy-mm-dd hh:mi:ss
	private int    effect;			//有否有效 0否 1是
	
	public TimerJobVo(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	
	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public int getEffect() {
		return effect;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}
	
	
	
	
}
