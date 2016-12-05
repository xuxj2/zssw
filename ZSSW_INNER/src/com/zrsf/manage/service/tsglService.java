package com.zrsf.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.zrsf.manage.dao.tsglDao;

public class tsglService {
	private tsglDao dao;
	public List<String> selectDyr(String dylm)
	{
		return dao.selectRy(dylm);
	}
	public boolean updateTx(List<String> ry , String ids,String dylm)
	{
		try{
		List<String> list=dao.idsToList(ids);
		Map<String, String> delete=new HashMap<String, String>();
		Map<String,String> insert=new HashMap<String,String>();
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
					insert.put("dylm", dylm);
					insert.put("dyr", list.get(i));
					dao.insertLmdy(insert);
				
			}
			}
			else{
				insert.put("dylm", dylm);
				insert.put("dyr", list.get(i));
				dao.insertLmdy(insert);
			}
			
		}
		
		if(ry!=null&&ry.size()>0)
		{
		for(int i=0;i<ry.size();i++)
		{
			delete.put("dylm", dylm);
			delete.put("dyr", ry.get(i));
			System.out.println(dylm);
			System.out.println(ry.get(i));
			dao.deleteLmdy(delete);
		}
		}
		}
		catch( Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public tsglDao getDao() {
		return dao;
	}
	public void setDao(tsglDao dao) {
		this.dao = dao;
	}

	
}
