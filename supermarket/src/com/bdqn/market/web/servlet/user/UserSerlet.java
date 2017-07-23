package com.bdqn.market.web.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.bdqn.market.bean.User;
import com.bdqn.market.service.user.UserService;
import com.bdqn.market.service.user.impl.UserServiceImpl;
import com.bdqn.market.util.PageUtil;

public class UserSerlet extends HttpServlet {
	private Logger log=Logger.getLogger(UserSerlet.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		switch (method) {
		case "select":
			select(request,response);
			break;
		case "code":
			code(request,response);
			break;
		case "view":
			view(request,response);
			break;
		case "insert":
			insert(request,response);
			break;
		case "update":
			update(request,response);
			break;
		case "isPassword":
			isPassword(request,response);
			break;
		case "updatePassword":
			updatePassword(request,response);
			break;
		case "updateInit":
			updateInit(request,response);
			break;
		case "delete":
			delete(request,response);
			break;
		}
	}
	/**
	 * 用户修改密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updatePassword(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		String password = request.getParameter("newPassword");
		User user=(User) request.getSession().getAttribute("user");
		if (password!=null && password!="") {
			UserService service=new UserServiceImpl();
			boolean flag=service.update(user.getId(), password);
			if (flag) {//判断数据库是否修改成功
				user.setUserPassword(password);
				PrintWriter print = response.getWriter();
				print.print("{\"flag\":\"true\"}");
			}
		}else{
			log.debug("用户跳过前台验证进入密码修改强制退出");
			response.sendRedirect("Login?method=out");
		}
	}

	/**
	 * 判断原密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void isPassword(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		String password = request.getParameter("password");//获取用户输入原密码
		String passWrod=((User)request.getSession().getAttribute("user")).getUserPassword();
		PrintWriter print = response.getWriter();
		StringBuffer out=new StringBuffer();
		if (passWrod.equals(password)) {//判断用户密码是否正确
			out.append("{\"flag\":\"true\"}");
		}else {
			out.append("{\"flag\":\"false\"}");
		}
		log.debug("用户输入原密码结果"+out.toString());
		print.print(out.toString());
		print.flush();
		print.close();
	}

	/**
	 * 验证注册用户账号
	 * @param request
	 * @param response
	 */
	private void code(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String code = request.getParameter("code");
		UserService service=new UserServiceImpl();
		User user=service.login(code);
		StringBuffer out=new StringBuffer();
		if (user!=null) {//代表该账号已存在
			out.append("{\"flag\":\"false\"}");
		}else{//代表改账号不存在
			out.append("{\"flag\":\"true\"}");
		}
		log.debug(out.toString());
		PrintWriter print = response.getWriter();
		print.print(out.toString());
		print.flush();
		print.close();
	}

	/**
	 * 修改用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Integer id=Integer.parseInt(request.getParameter("id"));//用户id
		String usertype=request.getParameter("userlei");//用户类型
		String userName=request.getParameter("userName");//用户名称
		Integer gender=Integer.parseInt(request.getParameter("sex"));//用户性别
		String date=request.getParameter("date");//出生日期
		String phone=request.getParameter("userphone");//用户手机号码
		String address=request.getParameter("userAddress");//用户地址
		Integer modifyBy=((User)request.getSession().getAttribute("user")).getId();//获取修改用户id
		Date modifyDate=new Date();
		Date birthday=null;
		try {
			birthday=new SimpleDateFormat("yyyy-MM-dd").parse(date);//转换日期
		} catch (ParseException e) {
			e.printStackTrace();
		}
		UserService service=new UserServiceImpl();
		Integer userType=null;
		if (usertype!=null) {//判断用户类型是否为空
			userType=Integer.parseInt(usertype);
		}
		User t=new User(id, userName, gender, birthday, phone, address, userType, modifyBy, modifyDate);
		if (service.update(t)) {
			response.sendRedirect("userList.jsp");
		}else {
			response.sendRedirect("userList.jsp?flag=upFalse");
		}
	}

	/**
	 * 删除操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//不可以删除自己
		Integer id = Integer.parseInt(request.getParameter("id"));
		User user=(User) request.getSession().getAttribute("user");
		int flag=0;
		if (user.getId()!=id) {//判断当前登录用户的id与要删除的ID
			UserService service=new UserServiceImpl();
			flag=service.delete(user.getId(), id);
			if (flag==-1) {
				response.sendRedirect("userList.jsp?flag=false");
			}else{
				response.sendRedirect("userList.jsp");
			}
		}else {
			response.sendRedirect("userList.jsp?flag=delFalse");
		}
	}

	/**
	 * 更新数据回显
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateInit(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		UserService service=new UserServiceImpl();
		User user = service.select(id);
		String userJson=JSON.toJSONString(user);
		log.debug(userJson);
		PrintWriter print = response.getWriter();
		print.print(userJson);
		print.flush();
		print.close();
	}

	/**
	 * 新增用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String userCode = request.getParameter("userId");//用户账号
		String userName = request.getParameter("userName");//用户名称
		String userPassword = request.getParameter("userpassword");//用户密码
		Integer gender=Integer.parseInt(request.getParameter("sex"));//用户性别
		Date birthday=null;
		try {
			birthday=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));//获取出生日期
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String phone = request.getParameter("userphone");//用户电话
		String address = request.getParameter("userAddress");//用户地址
		Integer userType = Integer.parseInt(request.getParameter("userlei"));
		Integer createdBy=((User)request.getSession().getAttribute("user")).getId();//获取创建者Id
		Date creationDate=new Date();
		User user=new User(userCode, userName, userPassword, gender, birthday, phone, address, userType, createdBy, creationDate);
		UserService service=new UserServiceImpl();
		if (service.insert(user)) {
			response.sendRedirect("userList.jsp");
		}else{
			response.sendRedirect("userList.jsp?flag=insFalse");
		}
	}

	/**
	 * 显示用户详细信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void view(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String id = request.getParameter("id");
		UserService service=new UserServiceImpl();
		User user=service.select(Integer.parseInt(id));
		String userJson=JSON.toJSONString(user);
		PrintWriter print = response.getWriter();
		log.debug(userJson);
		print.print(userJson);
		print.flush();
		print.close();
	}

	/**
	 * 条件查询用户并分页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String pageIndex = request.getParameter("pageIndex");
		String search = request.getParameter("search");
		PageUtil util=new PageUtil();
		UserService service=new UserServiceImpl();
		if (pageIndex!=null && pageIndex!="") {//判断当前页码
			util.setPageIndex(Integer.parseInt(pageIndex));
		}else {
			util.setPageIndex(1);
		}
		util.setPageSize(5);
		util.setSqlCount(service.selectCount(search));//获取总记录数
		List<User>list=service.selectForName(search, util.getPageIndex(), util.getPageSize());
		String listJson=JSON.toJSONString(list);
		PrintWriter print = response.getWriter();
		StringBuffer out=new StringBuffer();//拼接json字符串
		if (!listJson.equals("[]")) {//判断是否由符合数据
			out.append(listJson.replace("]", ","+JSON.toJSONString(util))+"]");
		}else {
			out.append("{\"flag\":\"没有找到相关数据\"}");
		}
		log.debug(out.toString());
		print.print(out.toString());
		print.flush();
		print.close();
	}

}
