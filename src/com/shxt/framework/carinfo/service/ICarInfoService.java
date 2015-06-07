package com.shxt.framework.carinfo.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.carinfo.query.CarInfoQuery;

public interface ICarInfoService {

	/**
	 * 汽车信息添加
	 * @param CarInfo
	 */
	public void add(CarInfo CarInfo);
	/**
	 * 汽车信息修改
	 * @param CarInfo
	 */
	public void update(CarInfo CarInfo);
	/**
	 * 汽车信息删除
	 * @param CarInfo
	 */
	public void delete(Integer carinfo_id);
	/**
	 * 全部的汽车信息列表
	 * @param CarInfo
	 */
	public List<CarInfo> list();
	/**
	 * 汽车信息分页查询
	 * @param CarInfo
	 */
	public PageBean find(CarInfoQuery query,PageBean pageBean);
	
}
