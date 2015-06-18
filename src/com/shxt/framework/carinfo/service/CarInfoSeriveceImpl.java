package com.shxt.framework.carinfo.service;

import java.util.List;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.carinfo.query.CarInfoQuery;
import com.shxt.framework.cartype.model.CarType;

public class CarInfoSeriveceImpl implements ICarInfoSerivece {

	private IBaseDao baseDao;
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void add(CarInfo carInfo) {
		this.baseDao.add(carInfo);
	}

	public void update(Integer carInfoId) {
		CarInfo carInfo = (CarInfo) this.baseDao.load(CarInfo.class, carInfoId);
		
		if(carInfo.getCar_status().equals("2")){
			carInfo.setCar_status("1");
			carInfo.setReserve_date(null);
			carInfo.setReserve_tel(null);
			carInfo.setReserve_user_name(null);
		}
		
		this.baseDao.update(carInfo);
	}
	
	public void updateAll(CarInfo carInfo) {
		CarInfo old_car = (CarInfo) this.baseDao.load(CarInfo.class, carInfo.getCar_id());
		CarType carType = (CarType) this.baseDao.load(CarType.class, carInfo.getCarType().getType_id());
		old_car.setCar_code(carInfo.getCar_code());
		old_car.setCar_name(carInfo.getCar_name());
		old_car.setRent_price(carInfo.getRent_price());
		old_car.setCar_color(carInfo.getCar_color());
		old_car.setBuy_price(carInfo.getBuy_price());
		old_car.setDeposit(carInfo.getDeposit());
		old_car.setKm(carInfo.getKm());
		old_car.setCarType(carType);
		if(carInfo.getPhoto()!=null){
			old_car.setPhoto(carInfo.getPhoto());
		}
		old_car.setCar_desc(carInfo.getCar_desc());
		this.baseDao.update(old_car);
	}
	
	public void updateStatus(Integer carInfoId) {
		CarInfo carInfo = (CarInfo) this.baseDao.load(CarInfo.class, carInfoId);
		
		if(carInfo.getCar_status().equals("1")){
			carInfo.setCar_status("3");
		}else if(carInfo.getCar_status().equals("2")){
			carInfo.setCar_status("3");
		}else if(carInfo.getCar_status().equals("4")){
			carInfo.setCar_status("3");
		}else{
			carInfo.setCar_status("1");
		}
		
		this.baseDao.update(carInfo);
		
	}
	
	public PageBean find(CarInfoQuery query, PageBean pageBean) {
		String hql = "from CarInfo c where 1=1 ";
		if(query!=null){
			if(query.getCarinfo_name()!=null&&query.getCarinfo_name().trim().length()>0){
				hql += " and c.car_name like '%"+query.getCarinfo_name().trim()+"%'";
			}
			if(query.getParent_id()!=null&&query.getParent_id().length()>0){
				if(query.getChild_id()!=null&&query.getChild_id().length()>0){
					hql += " and c.carType.type_id = "+query.getChild_id();
				}else{
					String parent_hql = "select ca.type_id from CarType ca where ca.parent_id = "+query.getParent_id();
					List<Object> tempList = (List<Object>) this.baseDao.list(parent_hql);
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
		
		hql += " order by c.car_id desc";
		
		return this.baseDao.find(hql, pageBean);
		
	}

	public CarInfo getCarInfoById(Integer carInfo_id) {
		return (CarInfo) this.baseDao.load(CarInfo.class, carInfo_id);
	}

	public PageBean findByReserve(PageBean pageBean) {
		String hql = " from CarInfo c where c.car_status=1 order by c.car_id desc";
		return this.baseDao.find(hql, pageBean);
	}

	public void addReserve(CarInfo carInfo) {
		CarInfo old_car = (CarInfo) this.baseDao.load(CarInfo.class, carInfo.getCar_id());
		old_car.setReserve_user_name(carInfo.getReserve_user_name());
		old_car.setReserve_tel(carInfo.getReserve_tel());
		old_car.setReserve_date(carInfo.getReserve_date());
		old_car.setCar_status("2");
		this.baseDao.update(old_car);
	}

	public PageBean findByCancelReserve(PageBean pageBean) {
		String hql = " from CarInfo c where c.car_status=2 order by c.car_id desc";
		return this.baseDao.find(hql, pageBean);
	}
	
}
