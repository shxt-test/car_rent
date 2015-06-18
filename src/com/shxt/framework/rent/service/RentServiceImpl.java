package com.shxt.framework.rent.service;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.customer.model.CustomerInfo;

public class RentServiceImpl implements IRentService {
	
	IBaseDao baseDao;

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public CustomerInfo returnCar(String id_card) {
		String hql=" from CustomerInfo where cus_id_card=?";
		return (CustomerInfo)this.baseDao.query(hql, id_card);
	}

	public PageBean find(PageBean pageBean) {
		String hql = "from RentInfo where real_date=null";
		return this.baseDao.find(hql, pageBean);
	}

}
