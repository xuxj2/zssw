package com.zrsf.forclient.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;

import com.zrsf.forclient.dao.TxlDao;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.SwjgTxl;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.msgpush.ShuixinService;
import com.zrsf.msgpush.ToMessage;
import com.zrsf.push.xmpp.model.NotificationVO;

public class TxlService {
	private TxlDao dao;
	private ShuixinService service;

	public TxlDao getDao() {
		return dao;
	}

	public ShuixinService getService() {
		return service;
	}

	public void setService(ShuixinService service) {
		this.service = service;
	}

	public void setDao(TxlDao dao) {
		this.dao = dao;
	}

	/**
	 * 查询所有的税务人员通讯录列表
	 * 
	 * @return
	 */
	public ResponseObject getAllSwry() {
		ResponseObject resp = new ResponseObject();
		List<Swry> list = dao.selectAllSwry();

		if (list != null && list.size() > 0) {
			resp.setEntity(list);
		}

		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	/**
	 * 查询某个税务机构下所有人员通讯录列表
	 * 
	 * @param swjg
	 * @return
	 */
	public ResponseObject getSwryBySwjg(String swjg) {// 税务机关代码不能为空
		ResponseObject resp = new ResponseObject();
		swjg = swjg.replaceAll("0+$", "");
		List<Swry> list = dao.selectTxllb(swjg + "%");
		if (list != null && list.size() > 0) {
			resp.setEntity(list);
		}
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	/**
	 * 查询某个税务人员的常用联系人通讯录列表
	 * 
	 * @param userDm
	 * @return
	 */
	public ResponseObject getCylxr(String userDm) {
		ResponseObject resp = new ResponseObject();
		List<Swry> list = dao.selectCylxr(userDm);
		if (list != null && list.size() > 0) {
			resp.setEntity(list);
		}
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	/**
	 * 查询所有条线列表
	 * 
	 * @return
	 */
	public ResponseObject getAllTx() {
		ResponseObject resp = new ResponseObject();
		List<Map<String, String>> list = dao.selectAllTx();
		if (list != null && list.size() > 0) {
			resp.setEntity(list);
		}
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	/**
	 * 查询某个条线下所有联系人列表
	 * 
	 * @param tx
	 * @return
	 */
	public ResponseObject getTxlxr(String tx) {
		ResponseObject resp = new ResponseObject();
		if (StringUtils.isEmpty(tx)) {
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("参数不能为空");
		}
		List<Swry> list = dao.selectTxlxr(tx);
		if (list != null && list.size() > 0) {
			resp.setEntity(list);
		}
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	/**
	 * 获取某个联系人的详细资料
	 * 
	 * @param swry
	 * @return
	 */
	public ResponseObject getXxzl(String swryDm) {
		ResponseObject resp = new ResponseObject();
		Swry swry = dao.selectJtxx(swryDm);
		resp.setEntity(swry);
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	/**
	 * 查询用户已订阅的栏目
	 * 
	 * @param userDm
	 * @return
	 */
	public ResponseObject getDyh(String userDm) {
		ResponseObject resp = new ResponseObject();
		List<Map<String, String>> list = dao.selectDyh(userDm);
		if (list != null && list.size() > 0) {
			resp.setEntity(list);
		}
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	/**
	 * 增加订阅号
	 * 
	 * @param lm
	 * @param userDm
	 * @return
	 */
	public ResponseObject insertDyh(String lm, String userDm) {
		ResponseObject resp = new ResponseObject();
		String[] records = lm.trim().replaceAll("(^(,+))|((,+)$)", "").split(
				",");
		Map<String, Object> dy = new HashMap<String, Object>();
		dy.put("swry", userDm);
		dy.put("lms", records);
		int num = dao.insertDyh(dy);
		if (num == records.length) {
			resp.setCode(ResponseObject.successCode);
		} else {
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("抱歉，订阅失败");
		}
		return resp;
	}

	/**
	 * 退订某些栏目
	 * 
	 * @param lm
	 * @param userDm
	 * @return
	 */
	public ResponseObject deleteDyh(String lm, String userDm) {
		ResponseObject resp = new ResponseObject();
		String[] records = lm.trim().replaceAll("(^(,+))|((,+)$)", "").split(
				",");
		Map<String, Object> dy = new HashMap<String, Object>();
		dy.put("swry", userDm);
		dy.put("lms", records);
		int num = dao.deletetDyh(dy);
		if (num == records.length) {
			resp.setCode(ResponseObject.successCode);
		} else {
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("抱歉，退订失败");
		}
		return resp;
	}

	/**
	 * 将税务人员发送的消息添加到数据库:先保存消息内容，再保存消息发送记录
	 * 
	 * @param userDm
	 *            发送人税务人员代码
	 * @param toList
	 *            接收人代码集合
	 * @param content
	 *            消息内容
	 * @param xxlx
	 *            消息类型
	 * @param xyhz
	 *            是否需要回执标记
	 * @return 消息入库后的id
	 */
	public String insertMessage(String userDm, List<String> toList,
			String content, String xxlx, String xyhz) {
		String messId = savaSxnr(content, xyhz);
		Map<String, Object> fromTo = new HashMap<String, Object>();
		fromTo.put("fromRy", userDm);
		fromTo.put("xxlx", xxlx);
		fromTo.put("messId", messId);
		fromTo.put("toRy", toList);
		dao.insertSxxx(fromTo);
		return messId;
	}

	/**
	 * 保存消息内容
	 */
	private String savaSxnr(String content, String xyhz) {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("content", content);
		message.put("xyhz", xyhz);
		String messId = dao.insertMessage(message);
		return messId;
	}

	/**
	 * 查询条线下所有人员代码
	 * 
	 * @param tx
	 * @return
	 */
	public List<String> selectTxRy(String tx) {
		List<String> toList = dao.selectTxRy(tx);
		return toList;
	}

	/**
	 * 查找整个苏州地税的组织结构，及各个机构下的人员
	 * 
	 * @return
	 */
	public ResponseObject getAllSwryInOrg() {
		ResponseObject resp = new ResponseObject();
		resp.setCode(ResponseObject.successCode);
		SwjgTxl JG = dao.selectAllSwryInOrg();
		resp.setEntity(JG);
		return resp;
	}

	/**
	 * 客户端通讯录界面，组织下人员查找：查找出苏州地税局组织结构（下级和下下级机关），及各下下级机关的全部人员
	 * 
	 * @return
	 */
	public ResponseObject getSwjgZzWithSwry() {
		ResponseObject resp = new ResponseObject();
		resp.setCode(ResponseObject.successCode);
		SwjgTxl JG = dao.selectSwjgZzWithSwry();
		resp.setEntity(JG);
		return resp;
	}

	/**
	 * 根据客户端传过来的已订阅的栏目代码，删除和增加订阅号
	 * 
	 * @param lm
	 * @param swry
	 * @return
	 */
	public ResponseObject addOrDeleteDyh(String lm, Swry swry) {
		ResponseObject resp = new ResponseObject();
		lm = lm.replaceAll("\\s", "").replaceAll("(^(,+))|((,+)$)|(,{2})", "");
		Map<String, Object> dy = new HashMap<String, Object>();
		dy.put("swryDm", swry.getSwryDm());
		dy.put("lms", lm);
		dao.updateDyh(dy);
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	/**
	 * 将税务人员代码转换为deviceid
	 * 
	 * @param toList
	 * @return
	 */
	public List<Swry> selectDeviceId(List<String> toList) {
		List<Swry> list = null;
		if (toList != null && toList.size() > 0) {
			list = dao.selectDeviceId(toList);
		}
		return list;
	}

	/**
	 * 修改消息状态为已读
	 * 
	 * @param messageId
	 * @param swryDm
	 * @return
	 */
	public ResponseObject updateMessageToBeenRead(String messageId,
			String swryDm) {
		ResponseObject resp = new ResponseObject();
		Map<String, String> map = new HashMap<String, String>();
		map.put("messId", messageId);
		map.put("swryDm", swryDm);
		dao.updateMessageStateToRead(map);
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	/**
	 * 查询消息的已读人员和未读人员
	 * 
	 * @param messageId
	 * @return
	 */
	public ResponseObject selectReadCount(String messageId) {
		ResponseObject resp = new ResponseObject();
		Map<String, Object> entity = new HashMap<String, Object>();

		Map<String, String> message = dao.selectMessageById(messageId);
		if (message == null || message.size() < 0) {
			resp.setMessage("消息不存在，或已被删除");
			resp.setCode(ResponseObject.failCode);
		}
		List<Map<String, String>> swrys = dao
				.selectToSwryByMessageId(messageId);
		StringBuffer ydry = new StringBuffer();
		StringBuffer wdry = new StringBuffer();
		if (swrys != null) {
			for (Map<String, String> swry : swrys) {
				if ("2".equals(swry.get("ydyfs"))) {
					if (ydry.length() > 0) {
						ydry.append(",");
					}
					ydry.append(swry.get("xm"));
				} else {
					if (wdry.length() > 0) {
						wdry.append(",");
					}
					wdry.append(swry.get("xm"));
				}
			}
		}
		entity.putAll(message);
		entity.put("ydry", ydry.toString());
		entity.put("wdry", wdry.toString());
		resp.setEntity(entity);
		resp.setCode(ResponseObject.successCode);
		return resp;
	}

	public ResponseObject selectSwryById(String id) {
		ResponseObject resp = new ResponseObject();
		try {
			Swry entity = dao.selectSwryById(id);
			resp.setCode(ResponseObject.successCode);
			resp.setEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(ResponseObject.failCode);
		}
		return resp;
	}

	public void pushMessage(List<String> toList, Swry swry, String messageId,
			String message, String xyhz, String xxlx) {
		if (toList == null || toList.size() < 1) {
			return;
		}
		NotificationVO no = new NotificationVO();
		no.setCjsj(new Date());
		no.setXxid(messageId);
		no.setXxlxDm("0");
		no.setXxlyDm(swry.getSwryDm());
		no.setXxlyMc(swry.getXm());
		no.setXxlyTb(swry.getTxUri());
		no.setXxnr(message);
		no.setXyhz(xyhz);
		JSONArray jArray = new JSONArray();
		jArray.add(no);
		List<Swry> toDeviceList = this.selectDeviceId(toList);
		if (toDeviceList != null && toDeviceList.size() > 0) {
			for (int i = 0; i < toDeviceList.size(); i++) {
				ToMessage toMessage = new ToMessage(toDeviceList.get(i)
						.getDeviceid(), jArray.toString());
				toMessage.setType(0);
				service.sendMessage(toMessage);
			}
		}
	}

	/**
	 * 给一个或多个人发送消息
	 * 
	 * @param swryTo
	 * @param swry
	 * @param message
	 * @param xyhz
	 * @return
	 */
	public ResponseObject sendMessage(String swryTo, Swry swry, String message,
			String xyhz) {
		ResponseObject resp = new ResponseObject();
		try {
			String messageId = null;
			List<String> toList = null;
			String[] arr = swryTo.trim().replaceAll("(^(,+))|((,+)$)|\\s", "")
					.split(",");
			if (arr == null || arr.length < 1) {
				resp = new ResponseObject();
				resp.setCode(ResponseObject.failCode);
				resp.setMessage("消息接收人员参数设置错误");
				return resp;
			} else {
				toList = new ArrayList<String>();
				for (String str : arr) {
					toList.add(str);
				}
				messageId = this.insertMessage(swry.getSwryDm(), toList,
						message, "0", xyhz);
				this.pushMessage(toList, swry, messageId, message, xyhz, "0");
				resp = new ResponseObject();
				resp.setCode(ResponseObject.successCode);
				resp.setEntity(messageId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
