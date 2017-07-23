package cn.bdqn.action;

import com.opensymphony.xwork2.ActionSupport;

public class PageAction extends ActionSupport{
	private Integer page;
	private String rs;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getRs() {
		return rs;
	}
	public void setRs(String rs) {
		this.rs = rs;
	}
	@Override
	public String execute() throws Exception {
		if (page==1) {
			rs="index.jsp";
		}else {
			rs="chain.jsp";
		}
		return super.execute();
	}
	public String exception()throws Exception {
		if (true) {
			throw new RuntimeException("运行异常");
		}
		return SUCCESS;
	}
	
}
