package com.shxt.framework.carinfo.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.carinfo.query.CarInfoQuery;

public interface ICarInfoSerivece {
	/**
	 * 添加汽车信息
	 * @param carInfo
	 */
	public void add(CarInfo carInfo);
	/**
	 * 修改汽车信息
	 * @param carInfo
	 */
	public void update(Integer carInfo_id);
	/**
	 * 根据ID得到汽车信息
	 * @param carInfo
	 */
	public CarInfo getCarInfoById(Integer carInfo_id);
	/**
	 * 汽车分页
	 * @param carInfo
	 */
	public PageBean find(CarInfoQuery query,PageBean pageBean);
	/**
	 * 根据ID修改汽车的状态
	 * @param carInfo
	 */
	public void UpdateStatus(Integer carInfo_id);
	
}
