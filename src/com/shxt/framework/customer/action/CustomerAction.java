package com.shxt.framework.customer.action;

import java.util.List;

import com.shxt.base.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.customer.query.CustomerQuery;
import com.shxt.framework.customer.service.ICustomerService;

public class CustomerAction extends BaseAction {

	private static final long serialVersionUID = -6496519079653063937L;
	
	private PageBean pageBean;
	
	private List<CustomerInfo> cusList;
	
	private CustomerInfo cusInfo;
	
	private CustomerQuery query;
	
	private ICustomerService customerService;
	
	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
	
	public String find(){
		
		if(pageBean==null){
			pageBean = new PageBean();
		}
		this.pageBean = customerService.find(query, pageBean);
		this.toJsp="customer/list";
		return DISPATCHER;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<CustomerInfo> getCusList() {
		return cusList;
	}

	public void setCusList(List<CustomerInfo> cusList) {
		this.cusList = cusList;
	}

	public CustomerInfo getCusInfo() {
		return cusInfo;
	}

	public void setCusInfo(CustomerInfo cusInfo) {
		this.cusInfo = cusInfo;
	}

	public CustomerQuery getQuery() {
		return query;
	}

	public void setQuery(CustomerQuery query) {
		this.query = query;
	}



}
