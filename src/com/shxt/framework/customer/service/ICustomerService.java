package com.shxt.framework.customer.service;

import java.util.List;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.customer.query.CustomerQuery;

public interface ICustomerService {

	public abstract void setBaseDao(IBaseDao baseDao);

	public abstract PageBean find(CustomerQuery query, PageBean pageBean);

	public abstract void add(CustomerInfo cusInfo);

	public abstract void delete(Integer cus_id);

	public abstract List<CustomerInfo> list();

	public abstract CustomerInfo toUpdate(Integer cusId);

	public abstract void update(CustomerInfo cusInfo);

	public abstract void updateStatus(Integer cusId);

	public abstract void add(CarInfo carInfo);

	public abstract void update(CarInfo carInfo);

}