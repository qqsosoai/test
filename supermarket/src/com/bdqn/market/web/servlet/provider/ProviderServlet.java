package com.bdqn.market.web.servlet.provider;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.bdqn.market.bean.Provider;
import com.bdqn.market.bean.User;
import com.bdqn.market.service.provider.ProviderServicec;
import com.bdqn.market.service.provider.impl.ProviderServiceImpl;
import com.bdqn.market.util.PageUtil;

public class ProviderServlet extends HttpServlet {
	private Logger log=Logger.getLogger(ProviderServlet.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method=request.getParameter("method");
		switch (method) {
		case "select"://分页条件查询供应商
			select(request,response);
			break;
		case "insert":
			insert(request,response);
			break;
		case "updateInit":
			updateInit(request,response);
			break;
		case "update":
			update(request,response);
			break;
		case "view"://查看供应商详细信息
			view(request,response);
			break;
		case "delete"://删除供应商
			delete(request,response);
			break;
		}
	}
	/**
	 * 修改操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id=Integer.parseInt(request.getParameter("id"));//供应商ID
		String proCode=request.getParameter("providerId");//供应商编码
		String proName=request.getParameter("providerName");//供应商名称
		String proContact=request.getParameter("people");//供应商联系人
		String proPhone=request.getParameter("phone");//供应商联系电话
		String proAddress=request.getParameter("address");//供应商地址
		String proFax=request.getParameter("fax");//供应商传真
		String proDesc=request.getParameter("describe");//供应商描述
		HttpSession session = request.getSession();
		Integer modifyBy=((User)session.getAttribute("user")).getId();
		Date modifyDate=new Date();
		Provider provider=new Provider(id, proCode, proName, proDesc, proContact, proPhone, proAddress, proFax, modifyDate, modifyBy);
		ProviderServicec servicec=new ProviderServiceImpl();
		if (servicec.update(provider)) {
			log.debug("修改成功");
			response.sendRedirect("providerList.jsp");
		}else {
			log.debug("修改失败");
			response.sendRedirect("providerList.jsp?flag=upFalse");
		}
	}

	/**
	 * 供应商数据回显
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateInit(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		Provider provider=null;
		if (id!=null && id!="") {
			ProviderServicec servicec=new ProviderServiceImpl();
			provider=servicec.select(Integer.parseInt(id));
		}
		String proJson=JSON.toJSONString(provider);
		log.debug(proJson);
		PrintWriter print = response.getWriter();
		print.print(proJson);
		print.flush();
		print.close();
	}

	/**
	 * 新增供应商数据
	 * @param request
	 * @param response
	 */
	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String proCode=request.getParameter("providerId");//供应商编码
		String proName=request.getParameter("providerName");//供应商名称
		String proContact=request.getParameter("people");//供应商联系人
		String proPhone=request.getParameter("phone");//供应商联系电话
		String proAddress=request.getParameter("address");//供应商联系地址
		String proFax=request.getParameter("fax");//供应商传真
		String proDesc=request.getParameter("describe");//供应商描述
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Integer createdBy=user.getId();
		Date creationDate=new Date();
		Provider provider=new Provider(proCode, proName, proDesc, proContact, proPhone, proAddress, proFax, createdBy, creationDate);
		ProviderServicec servicec=new ProviderServiceImpl();
		if (servicec.insert(provider)) {
			log.debug("新增成功");
			response.sendRedirect("providerList.jsp");
		}else {
			log.debug("新增失败");
			response.sendRedirect("providerList.jsp?flag=insFalse");
		}
	}

	/**
	 * 删除供应商
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer id=Integer.parseInt(request.getParameter("id"));
		ProviderServicec servicec=new ProviderServiceImpl();
		if (servicec.delete(id)) {
			System.out.println("删除供应商成功");
			response.sendRedirect("providerList.jsp");
		}else {
			System.out.println("删除供应商失败");
			response.sendRedirect("providerList.jsp?flag=false");
		}
	}

	/**
	 * 显示供应商信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void view(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer id=Integer.parseInt(request.getParameter("id"));
		ProviderServicec servicec=new ProviderServiceImpl();
		Provider pro=servicec.select(id);
		String proJson=JSON.toJSONString(pro);
		System.out.println(proJson);
		PrintWriter print = response.getWriter();
		print.print(proJson);
		print.flush();
		print.close();
	}
	/**
	 * 条件查询供应商信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void select(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pageIndex=request.getParameter("pageIndex");
		String search=request.getParameter("search");
		ProviderServicec service=new ProviderServiceImpl();
		PageUtil util=new PageUtil();
		if (pageIndex!="" && pageIndex!=null) {//判断页码
			util.setPageIndex(Integer.parseInt(pageIndex));
		}else {
			util.setPageIndex(1);
		}
		util.setPageSize(5);
		util.setSqlCount(service.selectForNameCount(search));
		List<Provider>providers=service.selectForName(search,util.getPageIndex(),util.getPageSize());
		String prosJson=JSON.toJSONString(providers);
		String utilJson=JSON.toJSONString(util);
		StringBuffer out=new StringBuffer();
		if (!prosJson.equals("[]")) {//判断是否有数据
			out.append(prosJson.replace("]", ","+utilJson+"]"));
		}else {
			out.append("{\"flag\":\"没有找到相关数据\"}");
		}
		log.debug(out.toString());
		PrintWriter print = response.getWriter();
		print.print(out.toString());
		print.flush();
		print.close();
	}

}
