package com.shxt.framework.rent.service;

import com.shxt.base.dao.PageBean;
import com.shxt.framework.customer.model.CustomerInfo;

public interface IRentService {
	
	public CustomerInfo returnCar(String id_card);
	
	public PageBean find(PageBean pageBean);

}
