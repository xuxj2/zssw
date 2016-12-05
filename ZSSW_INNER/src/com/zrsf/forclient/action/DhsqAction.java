package com.zrsf.forclient.action;

import com.opensymphony.xwork2.Action;
import com.zrsf.common.action.BaseAction;
import com.zrsf.forclient.service.DhsqService;
import com.zrsf.forclient.vo.ResponseObject;
import com.zrsf.forclient.vo.Swry;

public class DhsqAction extends BaseAction {
	private String code;
	private int pagecode;
	private String swglm;
	private ResponseObject resp;

	private DhsqService service;

	public DhsqService getService() {
		return service;
	}

	public void setService(DhsqService service) {
		this.service = service;
	}

	public String execute() {
		Swry swry = (Swry) session.get("swry");
		// 纳税人查找（根据税务管理码swglm 和 纳税人名称 mc 模糊查找）B10001
		if ("B10001".equals(code)) {
			String mc = request.getParameter("mc");
			resp = service.selectNsrByKeyWords(swglm, mc, pagecode, swry);
		}
		// 纳税人登记信息--第一分页，基本情况
		else if ("B10002".equals(code)) {
			resp = service.queryJbxx(swglm, swry);
		}
		// 纳税人登记信息--第二分页，投资人、银行账户、任职人员信息
		else if ("B10003".equals(code)) {
			resp = service.queryDjxxSecond(swglm, swry);
		}
		// 纳税人登记信息--第三分页，投资人、银行账户、任职人员信息
		else if ("B10004".equals(code)) {
			resp = service.queryDjxxThird(swglm, swry);
		}
		// 纳税人登记信息--第四分页，投资人、银行账户、任职人员信息
		else if ("B10005".equals(code)) {
			resp = service.queryDjxxForth(swglm, swry);
		}
		// 纳税人鉴定核定信息
		else if ("B10006".equals(code)) {
			resp = service.queryJdhdxx(swglm, swry, pagecode);
		}
		// 纳税人申报信息
		else if ("B10007".equals(code)) {
			String startDate = request.getParameter("qsrq");
			String endDate = request.getParameter("jzrq");
			resp = service.querySbxx(swglm, swry, pagecode, startDate, endDate);
		}
		// 纳税人申报信息:汇总信息 20140520  11:42
		else if ("B10016".equals(code)) {
			String startDate = request.getParameter("qsrq");
			String endDate = request.getParameter("jzrq");
			resp = service.querySbxx(swglm, swry, startDate, endDate);
		}
		//纳税人申报信息:详细   20140520 11:42   http://localhost:8080/shuixin/bgmk.action?code=B05016&swglm=320500001614156&qsrq=2013-01-01&jzrq=2014-04-30
		else if ("B10017".equals(code)) {
			String startDate = request.getParameter("qsrq");
			String endDate = request.getParameter("jzrq");
			String zsxmDm = request.getParameter("zsxmDm");
			String zspmDm = request.getParameter("zspmDm");			
			resp = service.querySbxx(swglm, swry, pagecode,zsxmDm,zspmDm, startDate, endDate);
		}
		// 纳税人入库信息
		else if ("B10008".equals(code)) {
			String startDate = request.getParameter("qsrq");
			String endDate = request.getParameter("jzrq");
			resp = service.queryRkxx(swglm, swry, pagecode, startDate, endDate);
		}
		// 纳税人入库信息:汇总信息 20140520  15:40
		else if ("B10018".equals(code)) {
			String startDate = request.getParameter("qsrq");
			String endDate = request.getParameter("jzrq");
			resp = service.queryRkxx(swglm, swry, startDate, endDate);
		}
		//纳税人入库信息:详细   20140520 15:40   
		else if ("B10019".equals(code)) {
			String startDate = request.getParameter("qsrq");
			String endDate = request.getParameter("jzrq");
			String zsxmDm = request.getParameter("zsxmDm");
			String zspmDm = request.getParameter("zspmDm");			
			resp = service.queryRkxx(swglm, swry, pagecode,zsxmDm,zspmDm, startDate, endDate);
		}
		
		// 纳税人欠税信息
		else if ("B10009".equals(code)) {
//			String endDate = request.getParameter("jzrq");
//			resp = service.queryQsxx(swglm, swry, pagecode, endDate);
			resp = service.queryQsxx(swglm, swry, pagecode);
		}
		// 纳税人退税信息
		else if ("B10010".equals(code)) {
			String startDate = request.getParameter("qsrq");
			String endDate = request.getParameter("jzrq");
			resp = service.queryTsxx(swglm, swry, pagecode, startDate, endDate);
		}
		// 发票领用信息
		else if ("B10011".equals(code)) {
			String startDate = request.getParameter("qsrq");
			String endDate = request.getParameter("jzrq");
			resp = service.queryFpxx(swglm, swry, pagecode, startDate, endDate,
					0);
		}
		// 发票缴销核销信息
		else if ("B10012".equals(code)) {
			String startDate = request.getParameter("qsrq");
			String endDate = request.getParameter("jzrq");
			resp = service.queryFpxx(swglm, swry, pagecode, startDate, endDate,
					1);
		}
		// 发票库存信息
		else if ("B10013".equals(code)) {
			String startDate = request.getParameter("qsrq");
			String endDate = request.getParameter("jzrq");
			resp = service.queryFpxx(swglm, swry, pagecode, startDate, endDate,
					2);
		}
		// 财务信息
		else if ("B10014".equals(code)) {
			String nf = request.getParameter("nf");
			resp = service.queryCwxx(swglm, swry, pagecode, nf);
		}
		// 违章信息
		else if ("B10015".equals(code)) {
			String nf = request.getParameter("nf");
			try {
				resp = service.queryWzxx(swglm, swry, pagecode, nf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			resp = new ResponseObject();
			resp.setCode(ResponseObject.failCode);
			resp.setMessage("未找到服务");
		}

		return Action.SUCCESS;
	}

	public String getSwglm() {
		return swglm;
	}

	public void setSwglm(String swglm) {
		this.swglm = swglm;
	}

	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ResponseObject getResp() {
		return resp;
	}

}
