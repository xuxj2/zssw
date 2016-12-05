package com.zrsf.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.zrsf.forclient.vo.SwjgTxl;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.manage.vo.Tiaoxian;



public class TxlDao {
	private SqlSessionTemplate sessionTemplate;


	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	/**
	 * 
	 * 
	 * 
	 * 按组织结构形式查询出苏州地税全部人员的通讯录列表： 展开苏州地税下的二级和三级机构，二级机构没有下级机构的展开其下全部人员，
	 * 有下级机构的展开下级机构，三级机构后直接展开其全部人员（包括下级机构的人员）
	 * 
	 * @return
	 */
	public SwjgTxl selectSwjgZzWithSwry(String SwjgDm,String SwjgMc) {
		SwjgTxl szds = new SwjgTxl();
		szds.setSwjgDm("23205000000");
		szds.setSwjgMc("苏州市地税局");	
		
		List<SwjgTxl> list = sessionTemplate.selectList(
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
					List<SwjgTxl> sanjiJgList = sessionTemplate.selectList("com.zrsf.map.txl.selectSwjgZzWithSwry", erjiJg.getSwjgDm());
					if (sanjiJgList != null&&sanjiJgList.size()>0) {
						for (int j = 0; j < sanjiJgList.size(); j++) {
							SwjgTxl sanjiJg = sanjiJgList.get(j);
							List<Swry> ryList = sessionTemplate.selectList("com.zrsf.map.txl.selectSwryBySwjgCc",sanjiJg.getSwjgCc());
							if(ryList!=null&&ryList.size()>0){
								sanjiJg.setSwrys(ryList);								
							}							
						}
						erjiJg.setXjjgs(sanjiJgList);
					}					
				} else {
					List<Swry> ryList = sessionTemplate.selectList("com.zrsf.map.txl.selectSwryBySwjgDm", erjiJg.getSwjgDm());
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
		return szds;
	}
	/**
	 * 
	 * @param tx 条线代码
	 * @return
	 */
	public List<Swry> selectTxRy(String tx) {
		return sessionTemplate.selectList("", tx);
	}
	public List<Tiaoxian> selectTx()
	{
		return sessionTemplate.selectList("");
	}
	
}
