package com.bdqn.market.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Bill implements Serializable {
	private Integer id;//主键ID
	private String billCode;//账单编码
	private String productName;//商品名称
	private String productDesc;//商品描述
	private String productUnit;//商品单位
	private BigDecimal productCount;//商品数量
	private BigDecimal totalPrice;//商品总额
	private Integer isPayment;//是否支付（0：未支付 1：已支付）
	private Integer createdBy;//创建者（userId）
	private Date creationDate;//创建时间
	private Integer modifyBy;//更新者（userId）
	private Date modifyDate;//更新时间
	private String createdName;//创建者姓名
	private String modifyName;//更新者姓名
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public BigDecimal getProductCount() {
		return productCount;
	}
	public void setProductCount(BigDecimal productCount) {
		this.productCount = productCount;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(Integer isPayment) {
		this.isPayment = isPayment;
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
	public String getCreatedName() {
		return createdName;
	}
	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	public Bill(Integer id, String billCode, String productName,
			String productDesc, String productUnit, BigDecimal productCount,
			BigDecimal totalPrice, Integer isPayment, Integer createdBy,
			Date creationDate, Integer modifyBy, Date modifyDate,
			String createdName, String modifyName) {
		super();
		this.id = id;
		this.billCode = billCode;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productUnit = productUnit;
		this.productCount = productCount;
		this.totalPrice = totalPrice;
		this.isPayment = isPayment;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.createdName = createdName;
		this.modifyName = modifyName;
	}
	
	public Bill(Integer id, String billCode, String productName,
			String productDesc, String productUnit, BigDecimal productCount,
			BigDecimal totalPrice, Integer isPayment, Integer createdBy,
			Date creationDate, Integer modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.billCode = billCode;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productUnit = productUnit;
		this.productCount = productCount;
		this.totalPrice = totalPrice;
		this.isPayment = isPayment;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}
	/**
	 * 新增账单构造方法
	 * @param billCode
	 * @param productName
	 * @param productDesc
	 * @param productUnit
	 * @param productCount
	 * @param totalPrice
	 * @param isPayment
	 * @param createdBy
	 * @param creationDate
	 */
	public Bill(String billCode, String productName, String productDesc,
			String productUnit, BigDecimal productCount, BigDecimal totalPrice,
			Integer isPayment, Integer createdBy, Date creationDate) {
		super();
		this.billCode = billCode;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productUnit = productUnit;
		this.productCount = productCount;
		this.totalPrice = totalPrice;
		this.isPayment = isPayment;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
	}
	/**
	 * 修改的构造方法
	 * @param id
	 * @param billCode
	 * @param productName
	 * @param productDesc
	 * @param productUnit
	 * @param productCount
	 * @param totalPrice
	 * @param isPayment
	 * @param modifyBy
	 * @param modifyDate
	 */
	public Bill(Integer id, String billCode, String productName,
			String productDesc, String productUnit, BigDecimal productCount,
			BigDecimal totalPrice, Integer isPayment, Integer modifyBy,
			Date modifyDate) {
		this.id = id;
		this.billCode = billCode;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productUnit = productUnit;
		this.productCount = productCount;
		this.totalPrice = totalPrice;
		this.isPayment = isPayment;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}
	public Bill() {
		super();
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", billCode=" + billCode + ", productName="
				+ productName + ", productDesc=" + productDesc
				+ ", productUnit=" + productUnit + ", productCount="
				+ productCount + ", totalPrice=" + totalPrice + ", isPayment="
				+ isPayment + ", createdBy=" + createdBy + ", creationDate="
				+ creationDate + ", modifyBy=" + modifyBy + ", modifyDate="
				+ modifyDate + ", createdName=" + createdName + ", modifyName="
				+ modifyName + "]";
	}
	
	
}
