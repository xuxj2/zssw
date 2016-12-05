package com.zrsf.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.zrsf.manage.dao.TxDao;
import com.zrsf.manage.vo.PageModel;
import com.zrsf.manage.vo.Tiaoxian;

public class TxService {

	private TxDao dao;
	
	public void NewTx(String TxMc,String ids)
	{
		Map<String,String> map=new HashMap<String,String>();
		String txid=UUID.randomUUID().toString().replaceAll("-", "");
		map.put("txmc", TxMc);
		map.put("txid",txid);
		dao.insertTx(map);
		List<String> list=new ArrayList<String>();
		list=dao.idsToList(ids);
		for(int i=0;i<list.size();i++)
		{
			map.put("txid", txid);
			map.put("txlxr", list.get(i));
			dao.insertTxry(map);
		}
		
		
	}

	public boolean deleteTx(String tx)
	{
		try{
		dao.deleteTx(tx);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public PageModel<Tiaoxian> selectAllTx(String swjg,PageModel<Tiaoxian> page)
	{
		
		Map<String, Object> paramsMap=new HashMap<String, Object>();
//		paramsMap.put("swjg", swjg);
		paramsMap.put("startNum", page.getPageNo()*page.getPageSize()-page.getPageSize());
		paramsMap.put("endNum", page.getPageSize()*page.getPageNo());
		List<Tiaoxian> list=dao.selectAllTx(paramsMap);
		page.setList(list);
		page.setTotalRecords(dao.selectTxAmount());
		return page;
	}
	public void updateTx(List<String> ry , String ids,String txmc,String txid)
	{
		List<String> list=dao.idsToList(ids);
		Map<String, String> delete=new HashMap<String, String>();
		Map<String,String> insert=new HashMap<String,String>();
		Map<String, String> update=new HashMap<String, String>();
		update.put("txid", txid);
		update.put("txmc", txmc);
		dao.updateTx(update);
		for(int i=0;i<list.size();i++)
		{
			boolean result=true;
			if(ry!=null&&ry.size()>0)
			{
			for(int j=0;j<ry.size();j++)
			{
				if(ry.get(j).equals(list.get(i)))
				{
					result=false;
					ry.remove(j);
					continue;
				}
			}
			if(result)
			{
					insert.put("txid", txid);
					insert.put("txlxr", list.get(i));
					dao.insertTxry(insert);
				
			}
			}
			else{
				insert.put("txid", txid);
				insert.put("txlxr", list.get(i));
				dao.insertTxry(insert);
			}
			
		}
		
		if(ry!=null&&ry.size()>0)
		{
		for(int i=0;i<ry.size();i++)
		{
			delete.put("txid", txid);
			delete.put("txlxr", ry.get(i));
			dao.deleteTxry(delete);
		}
		}
		
	}
	public PageModel<Tiaoxian> selectTx(String txmc,PageModel<Tiaoxian> page)
	{
		Map<String, Object> paramsMap=new HashMap<String, Object>();
//		paramsMap.put("swjg", swjg);
		paramsMap.put("txmc", txmc);
		paramsMap.put("startNum", page.getPageNo()*page.getPageSize()-page.getPageSize());
		paramsMap.put("endNum", page.getPageSize()*page.getPageNo());
		List<Tiaoxian> list=dao.selectTx(paramsMap);
		page.setList(list);
		page.setTotalRecords(dao.selectTxAmount());
		return page;
	}
	public List<String> selectTxlxr(String tx)
	{
		return dao.selectTxlxr(tx);
	}
	public String selectTxmc(String txid)
	{
		return dao.selectTxmc(txid);
	}
	public TxDao getDao() {
		return dao;
	}
	public void setDao(TxDao dao) {
		this.dao = dao;
	}
	
	
	
	
}
