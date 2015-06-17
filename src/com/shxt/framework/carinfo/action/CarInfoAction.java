package com.shxt.framework.carinfo.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shxt.base.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.base.exception.RbacException;
import com.shxt.base.utils.PropertiesConfigHelper;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.carinfo.query.CarInfoQuery;
import com.shxt.framework.carinfo.service.ICarInfoSerivece;
import com.shxt.framework.cartype.model.CarType;
import com.shxt.framework.cartype.service.ICarTypeService;
import com.shxt.framework.user.model.User;

public class CarInfoAction extends BaseAction {

	private static final long serialVersionUID = -6496519079653063937L;
	
	private CarInfo carInfo;
	
	private ICarInfoSerivece carInfoSerivece;
	public void setCarInfoSerivece(ICarInfoSerivece carInfoSerivece) {
		this.carInfoSerivece = carInfoSerivece;
	}
	private ICarTypeService carTypeService;
	public void setCarTypeService(ICarTypeService carTypeService) {
		this.carTypeService = carTypeService;
	}
	
	private CarInfoQuery query;
	private Integer carInfo_id;
	private PageBean pageBean;
	
	private List<CarType> carTypelist;
	
	public String find(){

		if(pageBean==null){
			pageBean = new PageBean();
		}

		this.pageBean = carInfoSerivece.find(query,pageBean);

		this.toJsp = "carinfo/list";

		return DISPATCHER;
	}
	/**
	 * 跳转到汽车信息添加页面--需要传递数据角色列表
	 * @return
	 */
	public String toAdd(){
		try {
			//TODO:获取启用的汽车信息列表
			//carTypelist = carTypeService.

			this.toJsp="carinfo/add";
		} catch (RbacException e) {
			e.printStackTrace();
		}

		return DISPATCHER;
	}
	/***
	 * 汽车添加操作，有图片的上传哟
	 * @return
	 */
	public String add(){
		
		return DISPATCHER;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public CarInfoQuery getQuery() {
		return query;
	}

	public void setQuery(CarInfoQuery query) {
		this.query = query;
	}

	public Integer getCarInfo_id() {
		return carInfo_id;
	}

	public void setCarInfo_id(Integer carInfoId) {
		carInfo_id = carInfoId;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<CarType> getCarTypelist() {
		return carTypelist;
	}
	public void setCarTypelist(List<CarType> carTypelist) {
		this.carTypelist = carTypelist;
	}
	

}
