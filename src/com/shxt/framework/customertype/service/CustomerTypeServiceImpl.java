package com.shxt.framework.customertype.service;

import java.util.List;

import com.shxt.base.dao.IBaseDao;
import com.shxt.framework.customertype.model.CustomerType;

public class CustomerTypeServiceImpl implements ICustomerTypeService {
	
	private IBaseDao baseDao;

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void add(CustomerType customerType) {
		// TODO Auto-generated method stub

	}

	public void delete(Integer typeId) {
		// TODO Auto-generated method stub

	}

	public List<CustomerType> list() {
		String sql = "select * from car_customer_type";
		return (List<CustomerType>) this.baseDao.listSQL(sql, CustomerType.class, false);
	}

	public void update(CustomerType customerType) {
		// TODO Auto-generated method stub

	}

}
