package com.shxt.framework.carinfo.service;

import java.util.List;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.carinfo.query.CarInfoQuery;

public class CarInfoServiceImpl implements ICarInfoService {
	
	private IBaseDao baseDao ;
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void add(CarInfo carInfoAction) {
		
	}
	
	public void update(CarInfo carInfoAction) {
		
	}

	public void delete(Integer carinfoId) {
		
	}

	public List<CarInfo> list() {
		
		return null;
	}

	public PageBean find(CarInfoQuery query, PageBean pageBean) {
		String hql = "from CarInfo c where 1=1 ";
		try {
			if(query!=null){
				if(query.getCar_name()!=null&&query.getCar_name().length()>0){
					hql += " and c.car_name like '&"+query.getCar_name().trim()+"%'";
				}
				if(query.getPrient_id()!=null&&query.getPrient_id().length()>0){
					//
					if(query.getChild_id()!=null&&query.getChild_id().trim().length()>0){
						hql += " and c.carType.type_id = "+query.getChild_id();
					}else{
						String prient_hql = "select ca.type_id from CarType where ca.parent_id = "+query.getPrient_id();
						List<Object> tempList = (List<Object>) this.baseDao.list(prient_hql);
						String str = "";
						if(tempList!=null&&tempList.size()>0){
							for (int i = 0; i < tempList.size(); i++) {
								str = str+tempList.get(i);
								
								if(i<tempList.size()-1){
									str = str+",";
								}
							}
							hql += " and c.carType.type_id in ("+str+")";
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		hql += " order by c.car_id desc";
		try {
			return this.baseDao.find(hql, pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
