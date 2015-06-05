package com.shxt.framework.customer.model;

import java.io.Serializable;

import com.shxt.framework.customertype.model.CustomerType;

public class CustomerInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6992347838952272503L;
	 /**客户主键ID*/
	  private Integer cus_id;
	  /**客户的姓名*/
	  private String cus_name;
	  /**性别:男 女 未知*/
	  private String cus_sex;
	  /**客户的联系电话*/
	  private String cus_tel;
	  /**客户的家庭地址*/
	  private String cus_address;
	  /**客户的工作单位*/
	  private String cus_work_address;
	  /**客户的身份证号码*/
	  private String cus_id_card;
	  /**客户的驾驶证号码*/
	  private String cus_driver_code;
	  /**担保人姓名*/
	  private String gua_name;
	  /**担保人性别*/
	  private String gua_sex;
	  /**担保人电话*/
	  private String gua_tel;
	  /**担保人身份证号码*/
	  private String gua_id_card;
	  /**担保人家庭地址*/
	  private String gua_address;
	  /**担保人工作单位*/
	  private String gua_work_address;
	  /**担保人与客户关系*/
	  private String relation;
	  /**客户的类型*/
	  private CustomerType customer_type;
	  /**审核是否通过 默认通过，因为没有网页*/
	  private String verify="1";
	  /***/
	  private String cus_status = "1";
	public Integer getCus_id() {
		return cus_id;
	}
	public void setCus_id(Integer cusId) {
		cus_id = cusId;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cusName) {
		cus_name = cusName;
	}
	public String getCus_sex() {
		return cus_sex;
	}
	public void setCus_sex(String cusSex) {
		cus_sex = cusSex;
	}
	public String getCus_tel() {
		return cus_tel;
	}
	public void setCus_tel(String cusTel) {
		cus_tel = cusTel;
	}
	public String getCus_address() {
		return cus_address;
	}
	public void setCus_address(String cusAddress) {
		cus_address = cusAddress;
	}
	public String getCus_work_address() {
		return cus_work_address;
	}
	public void setCus_work_address(String cusWorkAddress) {
		cus_work_address = cusWorkAddress;
	}
	public String getCus_id_card() {
		return cus_id_card;
	}
	public void setCus_id_card(String cusIdCard) {
		cus_id_card = cusIdCard;
	}
	public String getCus_driver_code() {
		return cus_driver_code;
	}
	public void setCus_driver_code(String cusDriverCode) {
		cus_driver_code = cusDriverCode;
	}
	public String getGua_name() {
		return gua_name;
	}
	public void setGua_name(String guaName) {
		gua_name = guaName;
	}
	public String getGua_sex() {
		return gua_sex;
	}
	public void setGua_sex(String guaSex) {
		gua_sex = guaSex;
	}
	public String getGua_tel() {
		return gua_tel;
	}
	public void setGua_tel(String guaTel) {
		gua_tel = guaTel;
	}
	public String getGua_id_card() {
		return gua_id_card;
	}
	public void setGua_id_card(String guaIdCard) {
		gua_id_card = guaIdCard;
	}
	public String getGua_address() {
		return gua_address;
	}
	public void setGua_address(String guaAddress) {
		gua_address = guaAddress;
	}
	public String getGua_work_address() {
		return gua_work_address;
	}
	public void setGua_work_address(String guaWorkAddress) {
		gua_work_address = guaWorkAddress;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public String getCus_status() {
		return cus_status;
	}
	public void setCus_status(String cusStatus) {
		cus_status = cusStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public CustomerType getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(CustomerType customerType) {
		customer_type = customerType;
	}

	

}
