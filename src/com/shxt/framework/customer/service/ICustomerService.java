package com.shxt.framework.customer.service;

import java.util.List;

import com.shxt.framework.carinfo.model.CarInfo;

public interface ICustomerService {
	/**
	 * 添加车辆信息
	 * @param carInfo
	 */
	
	public void add(CarInfo carInfo);
	
	
	public void update(CarInfo carInfo);
	
	
	public void delete(Integer type_id);
	
	public List<CarInfo> list();

}
