package com.shxt.framework.carinfo.query;

import com.shxt.framework.cartype.model.CarType;

public class CarInfoQuery {
	
	private String prient_id;
	
	private String child_id;
	
	private String car_name;

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String carName) {
		car_name = carName;
	}

	public String getPrient_id() {
		return prient_id;
	}

	public void setPrient_id(String prientId) {
		prient_id = prientId;
	}

	public String getChild_id() {
		return child_id;
	}

	public void setChild_id(String childId) {
		child_id = childId;
	}
	

}
