package com.bdqn.market.bean;

import java.util.Date;

public class User {
	private Integer id;//主键ID
	private String userCode;//用户编码
	private String userName;//用户名称
	private String userPassword;//用户密码
	private Integer gender;//性别（1:女、 2:男）
	private Date birthday;//出生日期
	private String phone;//手机
	private String address;//地址
	private Integer userType;//用户类型（1：系统管理员、2：经理、3：普通员工）
	private Integer createdBy;//创建者（userId）
	private Date creationDate;//创建时间
	private Integer modifyBy;//更新者（userId）
	private Date modifyDate;//更新时间
	private Integer age;//用户年龄
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
		setAge(new Date().getYear()-birthday.getYear());
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 新增用户构造方法
	 * @param userCode
	 * @param userName
	 * @param userPassword
	 * @param gender
	 * @param birthday
	 * @param phone
	 * @param address
	 * @param userType
	 * @param createdBy
	 * @param creationDate
	 */
	public User(String userCode, String userName, String userPassword,
			Integer gender, Date birthday, String phone, String address,
			Integer userType, Integer createdBy, Date creationDate) {
		super();
		this.userCode = userCode;
		this.userName = userName;
		this.userPassword = userPassword;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.userType = userType;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
	}
	/**
	 * 系统管理员修改用户构造方法
	 * @param id
	 * @param userName
	 * @param gender
	 * @param birthday
	 * @param phone
	 * @param address
	 * @param userType
	 * @param modifyBy
	 * @param modifyDate
	 */
	public User(Integer id, String userName, Integer gender,
			Date birthday, String phone, String address, Integer userType,
			Integer modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.userType = userType;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}
	public User(Integer id, String userCode, String userName,
			String userPassword, Integer gender, Date birthday, String phone,
			String address, Integer userType, Integer createdBy,
			Date creationDate, Integer modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.userPassword = userPassword;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.userType = userType;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.age = (new Date().getYear()-birthday.getYear());
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userCode=" + userCode + ", userName="
				+ userName + ", userPassword=" + userPassword + ", gender="
				+ gender + ", birthday=" + birthday + ", phone=" + phone
				+ ", address=" + address + ", userType=" + userType
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate
				+ ", age=" + age + "]";
	}
	
}
