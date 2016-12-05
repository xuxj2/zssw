package com.zrsf.backup.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zrsf.backup.dao.LmtsDao;
import com.zrsf.backup.vo.LmdyVo;
import com.zrsf.msgpush.ShuixinService;
import com.zrsf.msgpush.ToMessage;
import com.zrsf.push.xmpp.model.NotificationVO;

public class RecieveService {
	private LmtsDao dao;
	private ShuixinService service;

	public ShuixinService getService() {
		return service;
	}

	public void setService(ShuixinService service) {
		this.service = service;
	}

	public LmtsDao getDao() {
		return dao;
	}

	public void setDao(LmtsDao dao) {
		this.dao = dao;
	}

	public void pushLmts(String messages) {
		System.out.println("recieve push request:" + messages);
		JSONArray jsonArray = JSONArray.fromObject(messages);
		if (jsonArray != null && jsonArray.size() > 0) {
			Map<String, JSONArray> map = new HashMap<String, JSONArray>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String id = jsonObject.getString("id");
				String lmId = jsonObject.getString("lmId");
				String title = jsonObject.getString("title");
				String lmMc = jsonObject.getString("lmMc");
				String xxlx = null;
				if (lmId.contains("jzxx") || lmId.contains("hswz")) {
					xxlx = "6";
				} else {
					xxlx = "1";
				}
				NotificationVO vo = new NotificationVO();
				vo.setCjsj(new Date());
				vo.setXxid(id);
				vo.setXxlxDm(xxlx);
				vo.setXxlyDm(lmId);
				vo.setXxlyMc(lmMc);
				vo.setXxlyTb("");
				vo.setXxnr(title);
				vo.setXyhz("0");
				if (map.containsKey(lmId)) {
					map.get(lmId).add(vo);
				} else {
					JSONArray jArray = new JSONArray();
					jArray.add(vo);
					map.put(lmId, jArray);
				}
			}
			Set<String> set = map.keySet();
			String[] keys = new String[set.size()];
			Iterator<String> ite = set.iterator();
			int k = 0;
			while (ite.hasNext()) {
				keys[k++] = ite.next();
			}
			List<LmdyVo> subscribers = dao.selectSubscribers(keys);
			if (subscribers != null && subscribers.size() > 0) {
				for (int i = 0; i < subscribers.size(); i++) {
					try {
						JSONArray jsmg = new JSONArray();
						LmdyVo dy = subscribers.get(i);
						List<String> dyhs = dy.getDylms();
						if (dyhs != null && dyhs.size() > 0) {
							for (String dyh : dyhs) {
								jsmg.addAll(map.get(dyh));
							}
						}
						ToMessage message = new ToMessage(dy.getDeviceId(),
								jsmg.toString());
//						message.setType(ToMessage.DELTYPE);
						service.sendMessage(message);
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		}
	}
}
