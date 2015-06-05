package com.shxt.framework.rent.model;

import java.io.Serializable;
import java.util.Date;

import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.customer.model.CustomerInfo;

public class RentInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8679107027211567715L;
	
	 /**记录表主键ID*/
	  private Integer rent_id;
	  /**车辆的车牌号*/
	  private String car_code;
	  /**实际的保证金*/
	  private Integer deposit;
	  /**实际的租赁金额*/
	  private Integer rent_price;
	  /**租赁天数*/
	  private Integer days_number;
	  /**开始日期*/
	  private Date start_date;
	  /**应归还日期*/
	  private Date end_date;
	  /**实际归还日期*/
	  private Date real_date;
	  /**应收金额*/
	  private String receivable_price;
	  /**实际金额*/
	  private String real_price;
	  /**意见*/
	  private String rent_desc;
	  /**车辆信息*/
	  private CarInfo carInfo;
	  /**客户信息*/
	  private CustomerInfo customerinfo;
	public Integer getRent_id() {
		return rent_id;
	}
	public void setRent_id(Integer rentId) {
		rent_id = rentId;
	}
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String carCode) {
		car_code = carCode;
	}
	public Integer getDeposit() {
		return deposit;
	}
	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}
	public Integer getRent_price() {
		return rent_price;
	}
	public void setRent_price(Integer rentPrice) {
		rent_price = rentPrice;
	}
	public Integer getDays_number() {
		return days_number;
	}
	public void setDays_number(Integer daysNumber) {
		days_number = daysNumber;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date startDate) {
		start_date = startDate;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date endDate) {
		end_date = endDate;
	}
	public Date getReal_date() {
		return real_date;
	}
	public void setReal_date(Date realDate) {
		real_date = realDate;
	}
	public String getReceivable_price() {
		return receivable_price;
	}
	public void setReceivable_price(String receivablePrice) {
		receivable_price = receivablePrice;
	}
	public String getReal_price() {
		return real_price;
	}
	public void setReal_price(String realPrice) {
		real_price = realPrice;
	}
	public String getRent_desc() {
		return rent_desc;
	}
	public void setRent_desc(String rentDesc) {
		rent_desc = rentDesc;
	}
	public CarInfo getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public CustomerInfo getCustomerinfo() {
		return customerinfo;
	}
	public void setCustomerinfo(CustomerInfo customerinfo) {
		this.customerinfo = customerinfo;
	}
	

}
