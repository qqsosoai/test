package cn.bdqn.interceptor;

import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import cn.bdqn.pojo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionInterceptor implements Interceptor {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		String uri=request.getRequestURI();
		User user=(User) session.get("user");
		if (uri.contains("login") || (user!=null  && !user.equals(""))) {
			return invocation.invoke();
		}
		return "input";
	}

}
