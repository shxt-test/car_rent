package com.shxt.framework.customer.service;

import java.util.List;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.customer.query.CustomerQuery;

public class CustomerServiceImpl implements ICustomerService {
	IBaseDao baseDao;

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public PageBean find(CustomerQuery query, PageBean pageBean) {
		String hql = "from CustomerInfo cus where 1=1 ";
		if(query!=null){
			if(query.getCus_name()!=null&&query.getCus_name().trim().length()>0){
				hql +=" and cus.cus_name like '"+query.getCus_name().trim()+"%'";
			}
			if(query.getCus_id_card()!=null&&query.getCus_id_card().trim().length()>0){
				hql += " and cus.cus_id_card='"+query.getCus_id_card()+"' ";
			}
		}
		hql +=" order by cus.cus_id desc ";
		return this.baseDao.find(hql, pageBean);
	}
	
	public void add(CarInfo carInfo) {
		// TODO Auto-generated method stub
		
	}
	

	public void delete(Integer typeId) {
		// TODO Auto-generated method stub
		
	}


	public void update(CarInfo carInfo) {
		// TODO Auto-generated method stub
		
	}


	public List<CustomerInfo> list() {
		return null;
		
	}




}
