package com.zrsf.forclient.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.service.TxlService;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;

/**
 * 通讯录 查询
 * 
 * @author Administrator
 * 
 */
public class TXLAction extends BaseAction {
	private String code;
	private ResponseObject resp;

	// private File file;// 上传到临时文件全路径
	// private String fileContentType;// 上传文件类型
	// private String fileFileName;// 文件名字

	private TxlService service;

	public TxlService getService() {
		return service;
	}

	public void setService(TxlService service) {
		this.service = service;
	}

	public String execute() {
		try {
			Swry swry = (Swry) session.get("swry");		
			// 全部-联系人列表T10001
			if ("T10001".equals(code)) {
				resp = service.getAllSwry();
			}
			// 组织-联系人列表 T10002
			else if ("T10002".equals(code)) {
				resp = service.getSwjgZzWithSwry();
			}
			// 按照机构代码查询机构下所有人员T10020
			else if ("T10020".equals(code)) {
				String swjg = (String) request.getParameter("swjg");
				resp = service.getSwryBySwjg(swjg);
			}
			// 常用联系人T10003
			else if ("T10003".equals(code)) {

				resp = service.getCylxr(swry.getSwryDm());
			}
			// 条线列表T10004
			else if ("T10004".equals(code)) {
				resp = service.getAllTx();
			}
			// 条线联系人列表T10005
			else if ("T10005".equals(code)) {
				String tx = (String) request.getParameter("tx");
				resp = service.getTxlxr(tx);
			}
			// 联系人详细信息T10006
			else if ("T10006".equals(code)) {
				String lxr = (String) request.getParameter("lxr");
				resp = service.getXxzl(lxr);
			}
			// 订阅号
			else if ("T10007".equals(code)) {
				resp = service.getDyh(swry.getSwryDm());
			}
			// 发送消息
			else if ("T10008".equals(code)) {
				String swryTo = request.getParameter("lxr");			
				String message = request.getParameter("message");
				String xyhz = request.getParameter("xyhz");			
				resp =service.sendMessage(swryTo, swry,message,xyhz);
			}
			// 增加订阅号
			else if ("T10009".equals(code)) {
				String lm = (String) request.getParameter("lm");
				resp = service.insertDyh(lm, swry.getSwryDm());
			}
			// 退订
			else if ("T10010".equals(code)) {
				String lm = (String) request.getParameter("lm");
				resp = service.deleteDyh(lm, swry.getSwryDm());
			}
			// 修改用户的全部订阅号为传入的栏目代码
			else if ("T10012".equals(code)) {
				String lm = (String) request.getParameter("lm");
				resp = service.addOrDeleteDyh(lm, swry);
			}
			// 按组织结构展开所有税务人员
			else if ("T10011".equals(code)) {
				resp = service.getAllSwryInOrg();
			}
			//阅读消息后发送回执
			else if("T10013".equals(code)){
				String xxId = (String) request.getParameter("id");
				resp = service.updateMessageToBeenRead(xxId, swry.getSwryDm());
			}
			//查看已发消息，哪些人已读，哪些人未读
			else if("T10014".equals(code)){
				String messageId = (String) request.getParameter("id");
				resp = service.selectReadCount(messageId);
			}else if("T10015".equals(code)){
				String id = (String) request.getParameter("id");
				resp = service.selectSwryById(id);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	// public String getCode() {
	// return Code;
	// }
	public void setCode(String code) {
		this.code = code;
	}

	public ResponseObject getResp() {
		return resp;
	}

	// public void setResp(ResponseObject resp) {
	// this.resp = resp;
	// }

	// public File getFile() {
	// return file;
	// }
	//
	// public void setFile(File file) {
	// this.file = file;
	// }
	//
	// public String getFileContentType() {
	// return fileContentType;
	// }
	//
	// public void setFileContentType(String fileContentType) {
	// this.fileContentType = fileContentType;
	// }
	//
	// public String getFileFileName() {
	// return fileFileName;
	// }
	//
	// public void setFileFileName(String fileFileName) {
	// this.fileFileName = fileFileName;
	// }

}
