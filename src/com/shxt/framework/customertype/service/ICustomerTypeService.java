package com.shxt.framework.customertype.service;

import java.util.List;

import com.shxt.framework.customertype.model.CustomerType;

public interface ICustomerTypeService {
	/**
	 * 汽车类型添加管理
	 * @param customerType
	 */
	public void add(CustomerType customerType);
	
	public void update(CustomerType customerType);
	
	public void delete(Integer type_id);
	
	public List<CustomerType> list();
	
	public CustomerType getCustomerTypeById(Integer id);
}
