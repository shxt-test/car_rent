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
		this.baseDao.add(customerType);

	}

	public void delete(Integer typeId) {
		String sql = "update customer_info set fk_custype_id=null where fk_custype_id="+typeId;
		this.baseDao.updateBySQL(sql);
		this.baseDao.delete(CustomerType.class, typeId);
	}

	public List<CustomerType> list() {
		
		String sql = "select * from customer_type";
		return (List<CustomerType>) this.baseDao.listSQL(sql, CustomerType.class, false);

	}
	
	public void update(CustomerType customerType) {
		CustomerType oldCustomerType = (CustomerType) this.baseDao.load(CustomerType.class, customerType.getC_type_id());
		oldCustomerType.setC_type_name(customerType.getC_type_name());
		oldCustomerType.setC_discount(customerType.getC_discount());
		oldCustomerType.setPhoto(customerType.getPhoto());
		this.baseDao.update(oldCustomerType);
	}
	public CustomerType getCustomerTypeById(Integer id){
		return (CustomerType) this.baseDao.load(CustomerType.class, id);
	}
}
