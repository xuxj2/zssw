package test;

import java.io.UnsupportedEncodingException;

import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zrsf.appcenter.ApplicationDao;
import com.zrsf.appcenter.ApplicationService;
import com.zrsf.appcenter.ApplicationVo;
import com.zrsf.manage.service.NewsService;
import com.zrsf.manage.vo.News;
import com.zrsf.manage.vo.PageModel;

public class NewsTest {
	@Test
	public void newsListTest() {
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				new String[] { "src/applicationContext-public.xml",
						"src/applicationContext-bean.xml",
						"src/applicationContext-manage.xml" });
		NewsService service = (NewsService) ac.getBean("newsService");
		PageModel<News> page = new PageModel<News>();
		page.setPageNo(2);
		page.setPageSize(20);
		page = service.selectPage("dsxw200", page);
		System.out.println(page.getTotalRecords());
		System.out.println(page.getList().size());
	}

	@Test
	public void appTest() {
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				new String[] { "src/applicationContext-public.xml",
						"src/applicationContext-bean.xml",
						"src/applicationContext-manage.xml" });
		ApplicationService service = (ApplicationService) ac
				.getBean("applicationService");
		ApplicationVo vo = (ApplicationVo) service.lastest("com.cn.szds2.app").getEntity();
		System.out.println(vo.toString());
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(new String("&#20026;".getBytes("gb2312"), "utf-8"));
	
	}

}
