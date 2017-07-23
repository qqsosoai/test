package cn.bdqn.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;

import cn.bdqn.pojo.User;

public class TestAction extends ActionSupport{
	private Map<Integer,User> users;//用户
	private List<User> usersList;//用户
	
	public Map<Integer,User> getUsers() {
		return users;
	}

	public void setUsers(Map<Integer,User> users) {
		this.users = users;
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	public TestAction(){
		users=new HashMap<Integer,User>();
		users.put(1,new User("usera1", "pwda1",11));
		users.put(2,new User("usera2", "pwda2",12));
		users.put(3,new User("usera3", "pwda3",13));
		users.put(4,new User("usera4", "pwda4",14));
		users.put(5,new User("usera5", "pwda5",15));
		users.put(6,new User("usera6", "pwda6",16));
		users.put(7,new User("usera7", "pwda7",17));
		users.put(8,new User("usera8", "pwda8",18));
		users.put(9,new User("usera9", "pwda9",19));
		users.put(10,new User("usera10", "pwda10",20));
		users.put(11,new User("usera11", "pwda11",21));
		users.put(12,new User("usera12", "pwda12",22));
		usersList=new ArrayList<User>();
		usersList.add(new User("usera1", "pwda1",11));
		usersList.add(new User("usera2", "pwda2",12));
		usersList.add(new User("usera3", "pwda3",13));
		usersList.add(new User("usera4", "pwda4",14));
		usersList.add(new User("usera5", "pwda5",15));
		usersList.add(new User("usera6", "pwda6",16));
		usersList.add(new User("usera7", "pwda7",17));
		usersList.add(new User("usera8", "pwda8",18));
		usersList.add(new User("usera9", "pwda9",19));
		usersList.add(new User("usera10", "pwda10",20));
		usersList.add(new User("usera11", "pwda11",21));
		usersList.add(new User("usera12", "pwda12",22));
	}
	
	public String execute(){
		return SUCCESS;
	}
}
