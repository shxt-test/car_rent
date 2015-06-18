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
	 * 根据ID修改汽车信息
	 * @param carInfo
	 */
	public void update(Integer carInfo_id);
	/**
	 * 修改汽车信息
	 * @param carInfo
	 */
	public void updateAll(CarInfo carInfo);
	/**
	 * 根据ID得到汽车信息
	 * @param carInfo
	 */
	public CarInfo getCarInfoById(Integer carInfo_id);
	/**
	 * 汽车分页
	 * @param query
	 * @param pageBean
	 */
	public PageBean find(CarInfoQuery query,PageBean pageBean);
	/**
	 * 根据ID修改汽车的状态
	 * @param carInfo_id
	 */
	public void updateStatus(Integer carInfo_id);
	/**
	 * 可预定车辆的信息分页
	 */
	public PageBean findByReserve(PageBean pageBean);
	/**
	 * 预定操作
	 * @param carInfo
	 */
	public void addReserve(CarInfo carInfo);
	/**
	 * 取消预定车辆的信息分页
	 */
	public PageBean findByCancelReserve(PageBean pageBean);
	
}
