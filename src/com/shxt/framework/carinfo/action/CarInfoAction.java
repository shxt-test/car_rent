package com.shxt.framework.carinfo.action;

import java.util.List;

import com.shxt.base.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.carinfo.query.CarInfoQuery;
import com.shxt.framework.carinfo.service.ICarInfoService;

public class CarInfoAction extends BaseAction {

	private static final long serialVersionUID = -1691111684105707999L;
	/**
	 * 汽车服务
	 */
	private ICarInfoService carInfoService;
	public void setCarInfoService(ICarInfoService carInfoService) {
		this.carInfoService = carInfoService;
	}
	/**
	 * 汽车信息 
	 */
	private CarInfo car;
	/**
	 * 汽车信息列表
	 */
	private List<CarInfo> carList;
	/**
	 * 
	 */
	private PageBean pageBean;
	/**
	 * 查询条件
	 */
	private CarInfoQuery query;
	/**
	 * 有条件的查询
	 * @return
	 */
	public String find(){

		if(pageBean==null){
			pageBean = new PageBean();
		}

		this.pageBean = carInfoService.find(query,pageBean);
		
		System.out.println(pageBean.getDatas().get(0));
		
		this.toJsp = "carinfo/list";

		return DISPATCHER;
	}
	/**
	 * 跳转到汽车信息添加页面
	 * @return
	 */
	public String toAdd(){
		
		this.toJsp = "carinfo/add";
		
		return DISPATCHER;
	}
	
	public PageBean getPageBean() {
		return pageBean;
	}


	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}


	public CarInfoQuery getQuery() {
		return query;
	}


	public void setQuery(CarInfoQuery query) {
		this.query = query;
	}


	public CarInfo getCar() {
		return car;
	}


	public void setCar(CarInfo car) {
		this.car = car;
	}


	public List<CarInfo> getCarList() {
		return carList;
	}


	public void setCarList(List<CarInfo> carList) {
		this.carList = carList;
	}
}
