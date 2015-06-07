package com.shxt.framework.customer.service;

import java.util.List;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.customer.query.CustomerQuery;

import com.shxt.framework.user.model.User;

public class CustomerServiceImpl implements  ICustomerService {
	IBaseDao baseDao;

	/* (non-Javadoc)
	 * @see com.shxt.framework.customer.service.ICustomerService#setBaseDao(com.shxt.base.dao.IBaseDao)
	 */
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}


	/* (non-Javadoc)
	 * @see com.shxt.framework.customer.service.ICustomerService#find(com.shxt.framework.customer.query.CustomerQuery, com.shxt.base.dao.PageBean)
	 */
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
	
	/* (non-Javadoc)
	 * @see com.shxt.framework.customer.service.ICustomerService#add(com.shxt.framework.customer.model.CustomerInfo)
	 */
	public void add(CustomerInfo cusInfo) {
		this.baseDao.add(cusInfo);
		
		
	}
	

	/* (non-Javadoc)
	 * @see com.shxt.framework.customer.service.ICustomerService#delete(java.lang.Integer)
	 */
	public void delete(Integer cus_id) {
		
		
		
	}





	/* (non-Javadoc)
	 * @see com.shxt.framework.customer.service.ICustomerService#list()
	 */
	public List<CustomerInfo> list() {
		return null;
		
	}


	/* (non-Javadoc)
	 * @see com.shxt.framework.customer.service.ICustomerService#toUpdate(java.lang.Integer)
	 */
	public CustomerInfo toUpdate(Integer cusId) {
		
		return (CustomerInfo) this.baseDao.load(CustomerInfo.class, cusId);
	}


	/* (non-Javadoc)
	 * @see com.shxt.framework.customer.service.ICustomerService#update(com.shxt.framework.customer.model.CustomerInfo)
	 */
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


	/* (non-Javadoc)
	 * @see com.shxt.framework.customer.service.ICustomerService#updateStatus(java.lang.Integer)
	 */
	public void updateStatus(Integer cusId) {
		CustomerInfo cusInfo =  (CustomerInfo) this.baseDao.load(CustomerInfo.class, cusId);
		if(cusInfo.getCus_status().equals("1")){
			cusInfo.setCus_status("2");
			
		}else{
			cusInfo.setCus_status("1");
			
		}
		this.baseDao.update(cusInfo);
		
	}


	/* (non-Javadoc)
	 * @see com.shxt.framework.customer.service.ICustomerService#add(com.shxt.framework.carinfo.model.CarInfo)
	 */
	public void add(CarInfo carInfo) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.shxt.framework.customer.service.ICustomerService#update(com.shxt.framework.carinfo.model.CarInfo)
	 */
	public void update(CarInfo carInfo) {
		// TODO Auto-generated method stub
		
	}




}
