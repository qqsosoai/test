package cn.bdqn.pojo;

public class User {
	private String username;//用户名
	private String password;//用户密码
	private Integer age;//用户年龄
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, Integer age) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
