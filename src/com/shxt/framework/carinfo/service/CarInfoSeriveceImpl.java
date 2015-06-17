package com.shxt.framework.carinfo.service;

import java.util.List;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.carinfo.query.CarInfoQuery;

public class CarInfoSeriveceImpl implements ICarInfoSerivece {

	private IBaseDao baseDao;
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void add(CarInfo carInfo) {
		this.baseDao.add(carInfo);
	}

	public void update(Integer carInfoId) {
		
	}
	
	public void UpdateStatus(Integer carInfoId) {
		
	}
	
	public PageBean find(CarInfoQuery query, PageBean pageBean) {
		String hql = "from CarInfo c where 1=1 ";
		if(query!=null){
			if(query.getCarinfo_name()!=null&&query.getCarinfo_name().trim().length()>0){
				hql += " and c.car_name like '"+query.getCarinfo_name().trim()+"%'";
			}
		}
		
		hql += " order by c.car_id desc";
		
		System.out.println(hql);
		
		return this.baseDao.find(hql, pageBean);
		
	}

	public CarInfo getCarInfoById(Integer carInfoId) {
		return null;
	}

}
