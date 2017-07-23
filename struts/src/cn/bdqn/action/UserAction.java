package cn.bdqn.action;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.bdqn.pojo.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private User user;//用户对象
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String execute() throws Exception {
		System.out.println("进入判断");
		boolean flag=false;
		if (user!=null) {
			if ("admin".equals(user.getUsername())
					&& "admin".equals(user.getPassword())) {
				flag = true;
				ActionContext ac = ActionContext.getContext();
				ac.getSession().put("user", user);
			}
		}
			return SUCCESS;
	}
	public String add(){
		System.out.println("执行添加方法");
		return "add_success";
	}
	public String del(){
		System.out.println("执行删除方法");
		return "del_success";
	}
	public String err(){
		System.out.println("进入错误页面");
		return ERROR;
	}

}
