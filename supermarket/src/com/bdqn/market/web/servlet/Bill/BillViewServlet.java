package com.bdqn.market.web.servlet.Bill;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bdqn.market.bean.Bill;
import com.bdqn.market.service.bill.BillService;
import com.bdqn.market.service.bill.impl.BillServiceImpl;

public class BillViewServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id!=null && id!="") {
			BillService service=new BillServiceImpl();
			Bill bill=service.select(Integer.parseInt(id));
			PrintWriter print = response.getWriter();
			String billJson=JSON.toJSONString(bill);
			System.out.println(billJson);
			print.print(billJson);
			print.flush();
			print.close();
		}
	}

}
