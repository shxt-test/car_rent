package com.shxt.framework.rent.action;

import java.util.HashMap;
import java.util.Map;

import com.shxt.base.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.rent.model.RentInfo;
import com.shxt.framework.rent.query.Query;
import com.shxt.framework.rent.service.IRentService;

public class RentAction extends BaseAction {
	private static final long serialVersionUID = -4695121257889278672L;
	
	private String id_card;
	
	private Integer rent_id;
	
	private Query query;
	
	private CustomerInfo customerInfo ;
	
	private PageBean pageBean;
	
	private RentInfo rentInfo;
	
	private String car_code;
	
	
	private IRentService rentService;
	public void setRentService(IRentService rentService) {
		this.rentService = rentService;
	}
	
	public String find(){
		if(pageBean==null){
			pageBean = new PageBean();
		}
		this.pageBean = rentService.find(query,pageBean);
		this.toJsp="rent/list";
		return DISPATCHER;
	}
	
	public String returnCar(){
		this.toJsp="rent/returnCar";
		return DISPATCHER;
	}
	/**
	 * 
	 * @return
	 */
	public String toCheck(){
		Map<String,Object> map ;
		
		map = rentService.returnCar(id_card,rent_id);
		jsonResult=map;
		return JSON;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String idCard) {
		id_card = idCard;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public RentInfo getRentInfo() {
		return rentInfo;
	}

	public void setRentInfo(RentInfo rentInfo) {
		this.rentInfo = rentInfo;
	}

	public String getCar_code() {
		return car_code;
	}

	public void setCar_code(String carCode) {
		car_code = carCode;
	}

	public Integer getRent_id() {
		return rent_id;
	}

	public void setRent_id(Integer rentId) {
		rent_id = rentId;
	}

	
}
