package com.shxt.framework.customertype.model;

import java.io.Serializable;

public class CustomerType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8252609900591900127L;
	
	 /**用户类型主键ID*/
	  private Integer c_type_id;
	  /**客户类型的名称*/
	  private String c_type_name;
	  /**折扣率在0-1之间的小数*/
	  private String c_discount;
	  /**1-可用 2-禁用*/
	  private String c_type_status="1";
	  private String photo;
	public Integer getC_type_id() {
		return c_type_id;
	}
	public void setC_type_id(Integer cTypeId) {
		c_type_id = cTypeId;
	}
	public String getC_type_name() {
		return c_type_name;
	}
	public void setC_type_name(String cTypeName) {
		c_type_name = cTypeName;
	}
	public String getC_discount() {
		return c_discount;
	}
	public void setC_discount(String cDiscount) {
		c_discount = cDiscount;
	}
	public String getC_type_status() {
		return c_type_status;
	}
	public void setC_type_status(String cTypeStatus) {
		c_type_status = cTypeStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
