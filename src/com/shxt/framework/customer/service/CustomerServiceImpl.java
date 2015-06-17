package com.shxt.framework.customer.service;

import java.util.List;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.customer.query.CustomerQuery;
import com.shxt.framework.user.model.User;

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
			
			if(query.getCus_sex()!=null&&query.getCus_sex().length()>0){
				hql += " and cus.cus_sex='"+query.getCus_sex()+"'";
			}
			
			if(query.getCustomer_type()!=null&&query.getCustomer_type().length()>0){
				hql += " and cus.customer_type='"+query.getCustomer_type()+"'";
			}
		}
		hql +=" order by cus.cus_status asc ";
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
		oldCusInfo.setCustomer_type(cusInfo.getCustomer_type());
		
		this.baseDao.update(oldCusInfo);
		
	}


	public void updateStatus(Integer cusId) {
		CustomerInfo cusInfo =  (CustomerInfo) this.baseDao.load(CustomerInfo.class, cusId);
		if(cusInfo.getCus_status().equals("1")){
			cusInfo.setCus_status("2");
			
		}else{
			cusInfo.setCus_status("1");
			
		}
		this.baseDao.update(cusInfo);
		
	}




}
