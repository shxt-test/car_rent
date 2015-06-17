package com.shxt.framework.customer.query;

public class CustomerQuery{

	private static final long serialVersionUID = -7781910895473120151L;
	/**省份证号*/
	private String cus_id_card;
	/**名称*/
	private String cus_name;
	/**性别*/
	private String cus_sex;
	/**客户类型*/
	private String customer_type;

	public String getCus_id_card() {
		return cus_id_card;
	}

	public void setCus_id_card(String cusIdCard) {
		cus_id_card = cusIdCard;
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

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customerType) {
		customer_type = customerType;
	}
}
