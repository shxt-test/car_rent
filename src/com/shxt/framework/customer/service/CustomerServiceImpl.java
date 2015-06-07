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
	
	public void add(CustomerInfo cusInfo) {
		this.baseDao.add(cusInfo);
		
		
	}
	

	public void delete(Integer cus_id) {
		
		
		
	}





	public List<CustomerInfo> list() {
		return null;
		
	}


	public CustomerInfo toUpdate(Integer cusId) {
		
		return (CustomerInfo) this.baseDao.load(CustomerInfo.class, cusId);
	}


	public void update(CustomerInfo cusInfo) {
		System.out.println(cusInfo.getCus_id());
		CustomerInfo oldCusInfo = (CustomerInfo) this.baseDao.load(CustomerInfo.class, cusInfo.getCus_id());
		
		oldCusInfo.setCus_address(cusInfo.getCus_address());
		oldCusInfo.setCus_driver_code(cusInfo.getCus_driver_code());
		oldCusInfo.setCus_id_card(cusInfo.getCus_id_card());
		oldCusInfo.setCus_name(cusInfo.getCus_name());
		oldCusInfo.setCus_sex(cusInfo.getCus_sex());
		oldCusInfo.setCus_tel(cusInfo.getCus_tel());
		oldCusInfo.setCus_work_address(cusInfo.getCus_work_address());
		oldCusInfo.setGua_address(cusInfo.getGua_address());
		oldCusInfo.setGua_id_card(cusInfo.getGua_id_card());
		oldCusInfo.setGua_name(cusInfo.getGua_name());
		oldCusInfo.setGua_sex(cusInfo.getGua_sex());
		oldCusInfo.setGua_tel(cusInfo.getGua_tel());
		oldCusInfo.setGua_work_address(cusInfo.getGua_work_address());
		
		this.baseDao.update(oldCusInfo);
		
	}




}
