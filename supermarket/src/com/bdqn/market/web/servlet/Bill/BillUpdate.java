package com.bdqn.market.web.servlet.Bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import com.alibaba.fastjson.JSON;
import com.bdqn.market.bean.Bill;
import com.bdqn.market.bean.Provider;
import com.bdqn.market.bean.User;
import com.bdqn.market.service.bill.BillService;
import com.bdqn.market.service.bill.impl.BillServiceImpl;
import com.bdqn.market.service.provider.ProviderServicec;
import com.bdqn.market.service.provider.impl.ProviderServiceImpl;

public class BillUpdate extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		switch (method) {
		case "init"://增则add页面的初始化
			proInit(request,response);
			break;
		case "updateInit"://更改页面的数据回显
			updateInit(request,response);
			break;
		case "insert"://新增操作
			insert(request,response);
			break;
		case "update"://更新操作
			update(request,response);
			break;
		case "delete"://删除操作
			delete(request,response);
			break;
		}
	}
	/**
	 * 删除账单信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer id=Integer.parseInt(request.getParameter("id"));
		BillService service=new BillServiceImpl();
		if (service.delete(id)) {
			System.out.println("删除账单成功，删除账单ID为"+id);
			response.sendRedirect("billList.jsp");
		}else {
			System.out.println("删除账单失败");
			request.getRequestDispatcher("billList.jsp").forward(request, response);
		}
	}

	/**
	 * 回显账单
	 * @param request
	 * @param response
	 */
	private void updateInit(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String id=request.getParameter("id");
		if (id!=null && id!="") {
			BillService service=new BillServiceImpl();
			Bill bill=service.select(Integer.parseInt(id));
			String BillJson=JSON.toJSONString(bill);
			PrintWriter print=response.getWriter();
			print.print(BillJson);
			print.flush();
			print.close();
		}
	}

	/**
	 * 更新账单数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer id=Integer.parseInt(request.getParameter("id"));
		String billCode=request.getParameter("billId");//账户编码
		String productName=request.getParameter("billName");//商品名称
		String productDesc=request.getParameter("billCom");//商品描述
		BigDecimal productCount=new BigDecimal(request.getParameter("billNum"));//商品数量
		BigDecimal totalPrice=new BigDecimal(request.getParameter("money"));//商品总额
		String productUnit=request.getParameter("supplier");//商品单位
		Integer isPayment=Integer.parseInt(request.getParameter("zhifu"));//是否支付（0：未支付 1：已支付）
		HttpSession session = request.getSession();
		Integer modifyBy=((User)session.getAttribute("user")).getId();
		Date modifyDate=new Date();
		Bill bill=new Bill(id, billCode, productName, productDesc, productUnit, productCount, totalPrice, isPayment, modifyBy, modifyDate);
		BillService service=new BillServiceImpl();
		if (service.update(bill)) {
			System.out.println("修改账单成功");
			response.sendRedirect("billList.jsp");
		}else {
			System.out.println("修改账单失败");
			request.getRequestDispatcher("billList.jsp").forward(request, response);
		}
	}

	/**
	 * 新增账单数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String billCode=request.getParameter("billId");//账单编码
		String productName=request.getParameter("billName");//商品名称
		String productDesc=request.getParameter("billCom");//商品数量单位
		BigDecimal productCount=new BigDecimal(request.getParameter("billNum"));//商品数量
		BigDecimal totalPrice=new BigDecimal(request.getParameter("money"));//商品总金额
		String productUnit = request.getParameter("supplier");//商品供应商
		Integer isPayment=Integer.parseInt(request.getParameter("zhifu"));//是否付款
		HttpSession session = request.getSession();
		Integer createdBy=((User)session.getAttribute("user")).getId();
		Date creationDate=new Date();
		Bill bill=new Bill(billCode, productName, productDesc, productUnit, productCount, totalPrice, isPayment, createdBy, creationDate);
		BillService service=new BillServiceImpl();
		if (service.insert(bill)) {
			System.out.println("添加账单成功");
			response.sendRedirect("billList.jsp");
		}else {
			System.out.println("添加账单失败");
			request.getRequestDispatcher("billList.jsp").forward(request, response);
		}
		
	}
	/**
	 * 查询所有供应商并返回
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void proInit(HttpServletRequest request,
			HttpServletResponse response)throws IOException {
		ProviderServicec servicec=new ProviderServiceImpl();
		List<Provider>pros=servicec.select();
		String prosJson=JSON.toJSONString(pros);
		PrintWriter print=response.getWriter();
		print.print(prosJson);
		print.flush();
		print.close();
	}

}
