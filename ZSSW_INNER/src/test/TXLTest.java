package test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.junit.Test;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.zrsf.backup.dao.LmtsDao;
import com.zrsf.backup.timer.ReportSendTimerJob;
import com.zrsf.backup.vo.LmdyVo;
import com.zrsf.forclient.dao.TxlDao;
import com.zrsf.forclient.service.DhsqService;
import com.zrsf.forclient.service.SwryService;
import com.zrsf.forclient.service.TxlService;
import com.zrsf.forclient.vo.SwjgTxl;
import com.zrsf.forclient.vo.Swry;
import com.zrsf.push.xmpp.model.NotificationVO;

public class TXLTest {
	
	
	@Test
	public void reportPushTest() throws JobExecutionException {
		 new FileSystemXmlApplicationContext(
				new String[] { "src/applicationContext-public.xml",
						"src/applicationContext-bean.xml" });
		
		new  ReportSendTimerJob().execute(null);
	}

	/**
	 * 查询所有联系人
	 */
	@Test
	public void selectAllSwry() {		
			ApplicationContext ac = new FileSystemXmlApplicationContext(
					new String[] { "src/applicationContext-public.xml",
							"src/applicationContext-bean.xml" });
			TxlService service = (TxlService) ac.getBean("txlService");
			System.out.println(JSONObject.fromObject(service.getSwjgZzWithSwry()).toString());
//			SwjgTxl jg=(SwjgTxl) service.getSwjgZzWithSwry().getEntity();
//			JSONArray jArray1=JSONArray.fromObject(jg.getXjjgs());
//			if(jArray1!=null){
//				for(int i=0;i<jArray1.size();i++){
//					JSONObject object1=jArray1.getJSONObject(i);
//					System.out.println(object1.getString("swjgMc"));
//					JSONArray ryArray1=JSONArray.fromObject(object1.get("swrys"));
//					System.out.println(ryArray1.toString());
//					
//					JSONArray jArray2=JSONArray.fromObject(object1.get("xjjgs"));
//					if(jArray2!=null){
//						for(int j=0;j<jArray2.size();j++){
//							JSONObject object2=jArray2.getJSONObject(j);
//							System.out.println("===="+object2.getString("swjgMc"));
//							JSONArray ryArray2=JSONArray.fromObject(object2.get("swrys"));
//							System.out.println(ryArray2.toString());
//							JSONArray jArray3=JSONArray.fromObject(object2.get("swrys"));
//							if(jArray3!=null){
//								for(int k=0;k<jArray3.size();k++){
//									JSONObject object3=jArray3.getJSONObject(k);
//									System.out.println("+++++++++"+object3.toString());
//									
//								}
//							}
//							
//							
//						}
//						
//					}
//					
//				}
//			}
	}

	@Test
	public void loginTest() {
		try {

			ApplicationContext ac = new FileSystemXmlApplicationContext(
					new String[] { "src/applicationContext-public.xml",
							"src/applicationContext-bean.xml" });
			SwryService service = (SwryService) ac.getBean("swryService");

			JSONObject json = new JSONObject();
			Object object = service.toLogin( "MEIYOUMIMA","123456789" ).getEntity();
			json = JSONObject.fromObject(object);

			System.out.println(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void removeTail0() {
		String swjg = "23205000890";
		System.out.println(swjg.replaceAll("0+$", ""));
	}

	@Test
	public void addDyh() {
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				new String[] { "src/applicationContext-public.xml",
						"src/applicationContext-bean.xml" });
		TxlService service = (TxlService) ac.getBean("txlService");
		service.insertDyh("dsxw200,dsxw300", "32050820026");

	}

	@Test
	public void deleteDyh() {
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				new String[] { "src/applicationContext-public.xml",
						"src/applicationContext-bean.xml" });
		TxlService service = (TxlService) ac.getBean("txlService");
		service.deleteDyh("dsxw200,dsxw300", "32050820026");

	}

//	@Test
//	public void sxxx() {
//		ApplicationContext ac = new FileSystemXmlApplicationContext(
//				new String[] { "src/applicationContext-public.xml",
//						"src/applicationContext-bean.xml" });
//		TxlService service = (TxlService) ac.getBean("txlService");
//		 service.insertMessage("","","55555","0","0", null);
//		
//		;
//
//	}

	@Test
	public void selectWfsxx() {
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				new String[] { "src/applicationContext-public.xml",
						"src/applicationContext-bean.xml" });
		TxlDao dao = (TxlDao) ac.getBean("txlDao");
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("fromLxr", "32050000033");
		message.put("toSwry", "32050820026");

		JSONArray json = new JSONArray();
		json = JSONArray.fromObject(dao.selectNoSendMessage(message));
		System.out.println(json.toString());
	}

//	@Test
//	public void sendMessage() {
//		ApplicationContext ac = new FileSystemXmlApplicationContext(
//				new String[] { "src/applicationContext-public.xml",
//						"src/applicationContext-bean.xml" });
//		TxlService service = (TxlService) ac.getBean("txlService");
//
//		SwryService swryService = (SwryService) ac.getBean("swryService");
//		Swry swry = (Swry) swryService.toLogin("32050820026", "MEIYOUMIMA",
//				"123456789", "13913195740").getEntity();
//		
//
//	}
@Test
	public void selectSwjgZzWithSwry() {
		try {
			ApplicationContext ac = new FileSystemXmlApplicationContext(
					new String[] { "src/applicationContext-public.xml",
							"src/applicationContext-bean.xml" });
			TxlService service = (TxlService) ac.getBean("txlService");
			SwjgTxl szds = (SwjgTxl) service.getSwjgZzWithSwry().getEntity();
			System.out.println(szds.getXjjgs().size());
			List<SwjgTxl> firJgs=szds.getXjjgs();
			for(int i=0;i<firJgs.size();i++){
				SwjgTxl fir=firJgs.get(i);
				System.out.println(fir.getSwjgMc());
				List<SwjgTxl> secJgs=fir.getXjjgs();
				if(secJgs!=null&&secJgs.size()>0){
					for(int j=0;j<secJgs.size();j++){
						SwjgTxl sec=secJgs.get(j);
						System.out.println("  --"+sec.getSwjgMc());
					}
				}
				
			}
			
//			System.out.println(JSONObject.fromObject(szds).toString());
//			System.out.println(szds.getSwjgDm() + szds.getSwjgMc() + "   下级数量"
//					+ szds.getXjjgs().size());
//			List<SwjgTxl> erjijg = szds.getXjjgs();
//			for (int i = 0; i < erjijg.size(); i++) {
//				SwjgTxl dzj = erjijg.get(i);
//				if(dzj.getSwrys()!=null){
//					System.out.println(dzj.getSwjgDm() + dzj.getSwjgMc()
//							+ i+"   人员数量" + dzj.getSwrys().size());
//				}
//				if(dzj.getXjjgs()!=null){
//					System.out.println(dzj.getSwjgDm() + dzj.getSwjgMc()
//							+ "   下级数量" + dzj.getXjjgs().size());
//					List<SwjgTxl> sanjijg = dzj.getXjjgs();
//					for (int j = 0; j < sanjijg.size(); j++) {
//						SwjgTxl yez = erjijg.get(i);
//						System.out.println(yez.getSwjgDm() + yez.getSwjgMc()
//								+j+ "   人员数量" + yez.getSwrys().size());
//					}
//				}
//				
//				
//
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

@SuppressWarnings("unchecked")
@Test
public void selectNsr() {
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				new String[] { "src/applicationContext-public.xml",
						"src/applicationContext-bean.xml" });
		DhsqService service = (DhsqService) ac.getBean("dhsqService");
		Swry swry =new Swry();
		swry.setRzjgDm("23205000800");
		swry.setSwryDm("32050820026");
		
		List<Map<String, String>> list=(List<Map<String, String>>) service.selectNsrByKeyWords("3205", "苏州 相 区", 1, swry).getEntity();
		
		JSONArray json = new JSONArray();		
		json = JSONArray.fromObject(list);
		System.out.println(json.toString());
}



@Test
public void toJson() {
	NotificationVO vo=new NotificationVO();
	vo.setCjsj(new Date());
	List<NotificationVO> list=new ArrayList<NotificationVO>();
	list.add(vo);
	JSONArray js=JSONArray.fromObject(list);
	System.out.println(js.toString());
}



@Test
public void lmdy() {
	try {
		
	
	ApplicationContext ac = new FileSystemXmlApplicationContext(
			new String[] { "src/applicationContext-public.xml",
					"src/applicationContext-bean.xml" });
	LmtsDao dao = (LmtsDao) ac.getBean("lmtsDao");
	String[] keys={"dsxw100","dsxw200","dsxw300","dsxw400"};
	List<LmdyVo> list=dao.selectSubscribers(keys);
	System.out.println(list.size());
	
	} catch (Exception e) {
		e.printStackTrace();
	}
}


public static void main(String[] args) throws UnsupportedEncodingException {
	String msg="[{\"id\":\"hswz276086\",\"lmId\":\"hswz000\",\"lmMc\":\"寒山闻钟\",\"title\":\"请常熟市地税局核查\"},{\"id\":\"hswz276145\",\"lmId\":\"hswz000\",\"lmMc\":\"寒山闻钟\",\"title\":\"请问2013年吴中和吴江的社保基数分别是多少\"},{\"id\":\"hswz276039\",\"lmId\":\"hswz000\",\"lmMc\":\"寒山闻钟\",\"title\":\"外地户籍能自己交社保吗？\"},{\"id\":\"hswz276019\",\"lmId\":\"hswz000\",\"lmMc\":\"寒山闻钟\",\"title\":\"社保局的服务就是这个样子的？\"}]";
	System.out.println(URLEncoder.encode(msg,"utf-8"));

}



}
