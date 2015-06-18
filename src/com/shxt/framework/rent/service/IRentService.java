package com.shxt.framework.rent.service;

import java.util.Map;

import com.shxt.base.dao.PageBean;
import com.shxt.framework.rent.query.Query;

public interface IRentService {
	
	public Map returnCar(String id_card);
	
	public PageBean find(Query query,PageBean pageBean);
	

}
