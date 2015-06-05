package com.shxt.framework.carinfo.model;

import java.io.Serializable;
import java.util.Date;

import com.shxt.framework.cartype.model.CarType;

public class CarInfo implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1697685073413309618L;
	/**车辆主键ID*/
	  private Integer car_id;
	  /**车牌号码，唯一的*/
	  private String car_code;
	  /**车辆名称*/
	  private String car_name;
	  /**租借金额，一般情况下都是整数*/
	  private Integer rent_price;
	  /**车辆颜色*/
	  private String car_color;
	  /**购买价格*/
	  private String buy_price;
	  /**车辆保证金*/
	  private Integer deposit=5000;
	  /**汽车行驶公里数*/
	  private String km;
	  /**登记人的姓名*/
	  private String create_user_name;
	  /**车辆状态1:可租 2:预定 3:维修 4:报废 5:租赁*/
	  private String car_status = "1";
	  /**预定日期*/
	  private Date reserve_date;
	  /**预定人姓名*/
	  private String reserve_user_name;
	  /**预定人联系方式*/
	  private String reserve_tel;
	  /**车辆类型*/
	  private CarType carType;
	  /**车辆封页，上传操作*/
	  private String photo;
	  /**车辆描述，注意事项*/
	  private String car_desc;
	public Integer getCar_id() {
		return car_id;
	}
	public void setCar_id(Integer carId) {
		car_id = carId;
	}
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String carCode) {
		car_code = carCode;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String carName) {
		car_name = carName;
	}
	public Integer getRent_price() {
		return rent_price;
	}
	public void setRent_price(Integer rentPrice) {
		rent_price = rentPrice;
	}
	public String getCar_color() {
		return car_color;
	}
	public void setCar_color(String carColor) {
		car_color = carColor;
	}
	public String getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(String buyPrice) {
		buy_price = buyPrice;
	}
	public Integer getDeposit() {
		return deposit;
	}
	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}
	public String getKm() {
		return km;
	}
	public void setKm(String km) {
		this.km = km;
	}
	public String getCreate_user_name() {
		return create_user_name;
	}
	public void setCreate_user_name(String createUserName) {
		create_user_name = createUserName;
	}
	public String getCar_status() {
		return car_status;
	}
	public void setCar_status(String carStatus) {
		car_status = carStatus;
	}
	public Date getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(Date reserveDate) {
		reserve_date = reserveDate;
	}
	public String getReserve_user_name() {
		return reserve_user_name;
	}
	public void setReserve_user_name(String reserveUserName) {
		reserve_user_name = reserveUserName;
	}
	public String getReserve_tel() {
		return reserve_tel;
	}
	public void setReserve_tel(String reserveTel) {
		reserve_tel = reserveTel;
	}
	public CarType getCarType() {
		return carType;
	}
	public void setCarType(CarType carType) {
		this.carType = carType;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCar_desc() {
		return car_desc;
	}
	public void setCar_desc(String carDesc) {
		car_desc = carDesc;
	}

}
