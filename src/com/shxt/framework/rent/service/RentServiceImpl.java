package com.shxt.framework.rent.service;

import java.util.HashMap;
import java.util.Map;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.rent.model.RentInfo;
import com.shxt.framework.rent.query.Query;

public class RentServiceImpl implements IRentService {
	
	IBaseDao baseDao;

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}
	/**
	 * 列出未归还的汽车
	 */
	public PageBean find(Query query,PageBean pageBean) {
		String hql = "from RentInfo where real_date=null";
		if(query!=null){
			hql +=" and car_code like '"+query.getCar_code().trim()+"%'";
		}
		hql +=" order by car_code desc";
		return this.baseDao.find(hql, pageBean);
	}
	
	public Map returnCar(String id_card,Integer rent_id) {
		Map<String,Object> map = new HashMap<String,Object>();
		String hql=" from CustomerInfo where cus_id_card=?";
		CustomerInfo customerInfo=(CustomerInfo) this.baseDao.query(hql, id_card);
		String sql=" select * from rent_record where rent_id=?";
		RentInfo rentInfo  = (RentInfo) this.baseDao.querySQL(sql,rent_id, RentInfo.class, true);
		map.put("customerInfo", customerInfo);
		map.put("rentInfo", rentInfo);
		return map;
	}
	

}
