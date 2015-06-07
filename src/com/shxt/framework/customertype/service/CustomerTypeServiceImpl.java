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
		return null;
	}

	public void update(CustomerType customerType) {
		// TODO Auto-generated method stub

	}

}
