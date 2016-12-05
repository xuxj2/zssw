package com.zrsf.manage.action;
import java.io.File;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.zrsf.common.action.BaseAction;
import com.zrsf.manage.service.POIReadExcel;
import com.zrsf.manage.service.POIReadword;
import com.zrsf.manage.service.SystemPushService;
import com.zrsf.manage.service.TxlService;
import com.zrsf.msgpush.ShuixinService;
import com.zrsf.msgpush.ToMessage;

public class SystemPushAction extends BaseAction{
	private SystemPushService service;
	private TxlService txlservice;
	private ShuixinService sxservice;
	private File file;
	private String fileFileName;
	private String ids;
	private String zw;
	private JSONArray txl;
	private String jmfs;
	
	public String push()
	{
		try{
			String realpath = ServletActionContext.getServletContext().getRealPath("/files");
	        if (file != null) {
	            File savefile = new File(new File(realpath),fileFileName);
	            if (!savefile.getParentFile().exists())
	                savefile.getParentFile().mkdirs();
	            FileUtils.copyFile(file, savefile);	            
	        }
	        int index=fileFileName.lastIndexOf(".");
	        String ext=fileFileName.substring(index+1);
	        String fileName=fileFileName.substring(0, index);
	        if(jmfs!=null)
	        {
	        	if(ext.equals("xls"))
	        	{
	        		POIReadExcel readExcel=new POIReadExcel();
	        		String html="http://61.177.61.251:8222/shuixin/files/"+readExcel.ReadExcke(file,fileName);
	        		List<ToMessage> list=service.tomessage(zw, ids, html,fileFileName);	
	        		for(ToMessage message:list)
	        		{
	        			sxservice.insertMessage(message);
	        			sxservice.pushAllNoSend(message.getDeviceId());
	        		}
	        		service.insertSystemPush(zw, ids, html);
	        		}
	        	
	        	else if(ext.equals("doc"))
	        	{
	        		POIReadword readWord=new POIReadword();
	        		String html="http://61.177.61.251:8222/shuixin/files/"+readWord.getWordAndStyle(file, fileName);
	        		List<ToMessage> list=service.tomessage(zw, ids, html,fileFileName);	
	        		for(ToMessage message:list)
	        		{
	        			sxservice.insertMessage(message);
	        			sxservice.pushAllNoSend(message.getDeviceId());
	        		}
	        		service.insertSystemPush(zw, ids, html);
	        	}
	        	else if(ext.equals("xlsx"))
	        	{
	        		String html="http://61.177.61.251:8222/shuixin/files/"+fileFileName;
	        		List<ToMessage> list=service.tomessage(zw, ids, html,fileFileName);	
	        		for(ToMessage message:list)
	        		{
	        			sxservice.insertMessage(message);
	        			sxservice.pushAllNoSend(message.getDeviceId());
	        		}
	        		service.insertSystemPush(zw, ids, html);
	        	}
	        	else{
	        		String html="http://61.177.61.251:8222/shuixin/files/"+fileFileName;
	        		List<ToMessage> list=service.tomessage(zw, ids, html,fileFileName);	
	        		for(ToMessage message:list)
	        		{
	        			sxservice.insertMessage(message);
	        			sxservice.pushAllNoSend(message.getDeviceId());
	        		}
	        		service.insertSystemPush(zw, ids, html);
	        	}
	        }
	        else{
	        	String html="http://61.177.61.251:8222/shuixin/files/"+fileFileName;
        		List<ToMessage> list=service.tomessage(zw, ids, html,fileFileName);	
        		for(ToMessage message:list)
        		{
        			sxservice.insertMessage(message);
        			sxservice.pushAllNoSend(message.getDeviceId());
        		}
        		service.insertSystemPush(zw, ids, html);
	        }
	        }
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        return "success";
	}
	
	public String alllxr()
	{
		txl=txlservice.selectTxl("23205000000", "苏州地税局", null);
		return "lxr";
	}
	
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	public void setService(SystemPushService service) {
		this.service = service;
	}
	public String getFilename() {
		return fileFileName;
	}


	public void setFilename(String filefilename) {
		this.fileFileName = filefilename;
	}


	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public TxlService getTxlservice() {
		return txlservice;
	}

	public void setTxlservice(TxlService txlservice) {
		this.txlservice = txlservice;
	}

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public SystemPushService getService() {
		return service;
	}
	public JSONArray getTxl() {
		return txl;
	}
	public void setTxl(JSONArray txl) {
		this.txl = txl;
	}

	public String getJmfs() {
		return jmfs;
	}

	public void setJmfs(String jmfs) {
		this.jmfs = jmfs;
	}
	public ShuixinService getSxservice() {
		return sxservice;
	}

	public void setSxservice(ShuixinService sxservice) {
		this.sxservice = sxservice;
	}

	
	
	
	
}
