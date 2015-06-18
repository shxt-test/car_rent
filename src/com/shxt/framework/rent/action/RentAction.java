package com.shxt.framework.rent.action;

import java.util.HashMap;
import java.util.Map;

import com.shxt.base.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.rent.service.IRentService;

public class RentAction extends BaseAction {
	private static final long serialVersionUID = -4695121257889278672L;
	
	private String id_card;
	
	private CustomerInfo customerInfo ;
	
	private PageBean pageBean;
	
	private IRentService rentService;
	public void setRentService(IRentService rentService) {
		this.rentService = rentService;
	}
	
	public String find(){
		if(pageBean==null){
			pageBean = new PageBean();
		}
		this.pageBean = rentService.find(pageBean);
		this.toJsp="rent/list";
		return DISPATCHER;
	}
	
	public String returnCar(){
		
		this.toJsp="rent/returnCar";
		return DISPATCHER;
	}
	
	public String toCheck(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			customerInfo = rentService.returnCar(id_card);
			System.out.println(customerInfo.getCus_name());
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("cus", customerInfo);
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

	
}
