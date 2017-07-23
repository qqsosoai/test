package com.bdqn.market.web.servlet.Bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.bdqn.market.bean.Bill;
import com.bdqn.market.bean.Provider;
import com.bdqn.market.service.bill.BillService;
import com.bdqn.market.service.bill.impl.BillServiceImpl;
import com.bdqn.market.service.provider.ProviderServicec;
import com.bdqn.market.service.provider.impl.ProviderServiceImpl;
import com.bdqn.market.util.PageUtil;
/**
 * 
 * @author hasee
 *
 */
public class BillServlet extends HttpServlet {
	private Logger log=Logger.getLogger(BillServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		switch (method) {
		case "select"://查询数据并分页
			selectBill(req, resp);
			break;
		case "pro"://查询所有供应商
			proBill(req, resp);
			break;
		case "view"://显示详情
			viewBill(req, resp);
			break;
		}
	}
	/**
	 * 查询多有供应商
	 * @param req
	 * @param resp
	 */
	private void proBill(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		ProviderServicec servicec=new ProviderServiceImpl();//获取service层provider对象
		List<Provider>pros=servicec.select();
		String prosJson=JSON.toJSONString(pros);
		PrintWriter print = resp.getWriter();
		log.debug(prosJson);
		print.print(prosJson);
		print.flush();
		servicec=null;
		print.close();
	}


	/**
	 * 分页查询账单
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selectBill(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		String search=req.getParameter("search");
		String provider=req.getParameter("provider");
		String isPay=req.getParameter("isPay");
		String pageIndex=req.getParameter("pageIndex");
		String BillName=null;//用户输入的账单名称
		Integer proId=null;//供应商名称
		Integer isPayment=null;//是否付款
		PageUtil util=new PageUtil();//分页工具类
		if (search!="" && search!=null) {//判断用户输入账单是否为空
			BillName=search;
		}
		if (provider!="" && provider!=null) {//判断用户是否选择供应商
			proId=Integer.parseInt(provider);
		}
		if (isPay!="" && isPay!=null) {//判断用户是否选择已付款
			isPayment=Integer.parseInt(isPay);
		}
		if (pageIndex==null || pageIndex=="") {//判断当前页码
			util.setPageIndex(1);
		}else {
			util.setPageIndex(Integer.parseInt(pageIndex));
		}
		util.setPageSize(5);//每页大小5条
		
		BillService service=new BillServiceImpl();
		//设置条件查询的总记录数
		util.setSqlCount(service.selectForNameCount(BillName, proId, isPayment));
		//获取条件查询账单
		List<Bill> bills=service.selectForName(BillName, proId, isPayment,
				util.getPageIndex(), util.getPageSize());
		String billsJson=JSON.toJSONString(bills);//将账单集合转换为json
		String utilJson=JSON.toJSONString(util);
		PrintWriter print=resp.getWriter();
		StringBuffer out=new StringBuffer();
		System.out.println(billsJson);
		if (!billsJson.equals("[]")) {
			out.append(billsJson.replace("]", ","+utilJson+"]"));
		}else {
			out.append("{\"flag\":\"没有查找到符合的数据\"}");
		}
		
		log.debug(out.toString());
		print.print(out.toString());
		print.flush();
		service=null;
		util=null;
		print.close();
	}
	/**
	 * 查询具体账单展示
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void viewBill(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		String id=req.getParameter("id");
		BillService service=new BillServiceImpl();
		Bill bill=service.select(Integer.parseInt(id));
		String billJson=JSON.toJSONString(bill);
		System.out.println(billJson);
		PrintWriter print=resp.getWriter();
		print.print(billJson);
		print.flush();
		print.close();
	}
}
