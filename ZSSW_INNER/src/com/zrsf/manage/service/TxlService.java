package com.zrsf.manage.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.zrsf.manage.dao.TxlDao;
import com.zrsf.manage.vo.txlJsonBean;
import com.zrsf.forclient.vo.SwjgTxl;
import com.zrsf.forclient.vo.Swry;

public class TxlService {
	private TxlDao dao;
	private JSONArray jsonArray;

	public JSONArray selectTxl(String SwjgDm, String SwjgMc,List<String> txlxr ) {
		List<txlJsonBean> jsonBeans = new ArrayList<txlJsonBean>();

		SwjgTxl txl = dao.selectSwjgZzWithSwry(SwjgDm, SwjgMc);
		List<SwjgTxl> list = txl.getXjjgs();
		List<Swry> swrys = txl.getSwrys();
		String swjgDm = txl.getSwjgDm();
		txlJsonBean swjgJu = new txlJsonBean();
		swjgJu.setId(swjgDm);
		swjgJu.setpId(SwjgDm.substring(0, 6) + "0000");
		swjgJu.setName(SwjgMc);
		swjgJu.setOpen(true);
		jsonBeans.add(swjgJu);
		if (list != null) {
			for (SwjgTxl swjg : list)// 局
			{
				txlJsonBean xjjgJu = new txlJsonBean();
				xjjgJu.setId(swjg.getSwjgDm());
				xjjgJu.setpId(swjgDm);
				xjjgJu.setName(swjg.getSwjgMc());
				jsonBeans.add(xjjgJu);
				List<SwjgTxl> xjjgs = swjg.getXjjgs();
				List<Swry> xjrys = swjg.getSwrys();
				String xjjgDm = swjg.getSwjgDm();
				if (xjjgs != null)// 科
				{
					for (SwjgTxl xjjgTxl : xjjgs) {
						txlJsonBean xjjgKe = new txlJsonBean();
						xjjgKe.setId(xjjgTxl.getSwjgDm());
						xjjgKe.setpId(xjjgDm);
						xjjgKe.setName(xjjgTxl.getSwjgMc());
						jsonBeans.add(xjjgKe);
						List<Swry> ksswry = xjjgTxl.getSwrys();
						if (ksswry != null && ksswry.size() > 0)// 人
						{
							for (Swry swry : ksswry) {
								txlJsonBean xjjgKy = new txlJsonBean();
								xjjgKy.setId(swry.getSwryDm());
								xjjgKy.setpId(xjjgTxl.getSwjgDm());
								xjjgKy.setName(swry.getXm());
								if(txlxr!=null)
								{
									for(int i=0;i<txlxr.size();i++)
									{
										String swryDm=txlxr.get(i);
										if(swry.getSwryDm().equals(swryDm))
										{
											xjjgKy.setChecked(true);
										}
									}
								}
								jsonBeans.add(xjjgKy);
							}
						}
					}
				}
				if (xjrys != null) {
					for (Swry swry : xjrys) {
						txlJsonBean xjjgry = new txlJsonBean();
						xjjgry.setId(swry.getSwryDm());
						xjjgry.setName(swry.getXm());
						xjjgry.setpId(xjjgDm);
						if(txlxr!=null)
						{
							for(int i=0;i<txlxr.size();i++)
							{
								String swryDm=txlxr.get(i);
								if(swry.getSwryDm().equals(swryDm))
								{
									xjjgry.setChecked(true);
								}
							}
						}
						jsonBeans.add(xjjgry);
					}
				}
			}

		}
		if (swrys != null) {
			for (Swry swry : swrys) {
				txlJsonBean xjswrys = new txlJsonBean();
				xjswrys.setId(swry.getSwryDm());
				xjswrys.setName(swry.getXm());
				xjswrys.setpId(SwjgDm.substring(0, 6) + "0000");
				if(txlxr!=null)
				{
					for(int i=0;i<txlxr.size();i++)
					{
						String swryDm=txlxr.get(i);
						if(swry.getSwryDm().equals(swryDm))
						{
							xjswrys.setChecked(true);
						}
					}
				}
				jsonBeans.add(xjswrys);
			}
		}
		jsonArray = JSONArray.fromObject(jsonBeans);
		return jsonArray;

	}
	
	
	
	
	
	public TxlDao getDao() {
		return dao;
	}

	public void setDao(TxlDao dao) {
		this.dao = dao;
	}
	
}
