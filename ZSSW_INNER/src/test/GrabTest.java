package test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.htmlparser.util.ParserException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zrsf.backup.http.inner.ZQSBLGrab;

public class GrabTest {
	@Test
	public void hswzGrabTest() throws HttpException, ParserException,
			IOException {

	}

	@Test
	public void zskGrabTest() {
		
	}

	@Test
	public void zgsjGrab() throws HttpException, ParserException, IOException {
		ZQSBLGrab grab = new ZQSBLGrab();
		// QSZJLGrab grab=new QSZJLGrab();
		// /RWZQWCLGrab grab=new RWZQWCLGrab();
		// YQSBCFLGrab grab=new YQSBCFLGrab();
		// /ZQRKLGrab grab=new ZQRKLGrab();
		List<Map<String, String>> list = grab.grabOneMonth("2014", "01");
		for (Map<String, String> map : list) {
			System.out.print("[");
			// System.out.print(map.get("nf")+" ;  ");
			// System.out.print(map.get("yf")+" ;  ");
			// System.out.print(map.get("sjjg")+" ;  ");
			// System.out.print(map.get("bjjg")+" ;  ");
			// System.out.print(map.get("swjg_mc")+" ;  ");

			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				System.out.print(key + ":" + map.get(key) + ", ");
			}
			System.out.println("]");
		}
		System.out.println(list.size());
	}

	@Test
	public void jzxxGrab() throws HttpException, ParserException, IOException {
		
	}

	@Test
	public void dsxwGrab() throws HttpException, ParserException, IOException {
		
	}

	
	@Test
	public void StringtoArry() {
		System.out.println(Arrays.toString(" , ,ABC,H,  , ,	".replaceAll(
				"(^([,\\s]+))|(([,\\s]+)$)", "").split(",")));
		
		System.out.println(Arrays.toString("    ".trim().split("\\s+")));
	}

	@Test
	public void StringFormat() {
		System.out.println(Double.parseDouble("60.51%".replaceAll("%", "")));
	}

	@Test
	public void grabTest() throws HttpException, ParserException, IOException {
		
	}

	

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(URLEncoder.encode("《姑苏晚报》地税专栏 第158期", "UTF-8"));
	}


	
}
