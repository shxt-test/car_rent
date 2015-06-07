package com.shxt.framework.customer.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.customer.query.CustomerQuery;

public interface ICustomerService {
	/**
	 * 添加车辆信息
	 * @param carInfo
	 */
	
	public void add(CarInfo carInfo);
	
	
	public void update(CarInfo carInfo);
	
	
	public void delete(Integer type_id);
	
	public List<CustomerInfo> list();
	
	public PageBean find(CustomerQuery query ,PageBean pageBean);

}
