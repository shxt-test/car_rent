package com.shxt.framework.customer.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private Integer cus_id;

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
	/**
	 * 添加跳转
	 * @return
	 */
	public String toAdd(){

		this.toJsp="customer/add";
		return DISPATCHER;
	}
	/**
	 * 添加客户
	 */
	public String add(){
		try{
			customerService.add(cusInfo);
			this.message="客户添加成功,谢谢合作!";
			this.flag = "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.message="客户添加失败,异常信息为:"+e.getMessage();
			this.flag = "error";

		}
		this.toJsp="message";
		return DISPATCHER;

	}
	public String toUpdate(){
		cusInfo = customerService.toUpdate(cus_id);
		this.toJsp="customer/update";
		return DISPATCHER;
	}
	public String update(){
		try{
			customerService.update(cusInfo);
			this.message="客户修改成功,谢谢合作!";
			this.flag = "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.message="客户修改失败,异常信息为:"+e.getMessage();
			this.flag = "error";

		}
		this.toJsp="message";
		return DISPATCHER;
	}
	
	public String deleteCusInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		
		this.jsonResult=map;
		return JSON;
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

	public Integer getCus_id() {
		return cus_id;
	}

	public void setCus_id(Integer cusId) {
		cus_id = cusId;
	}





}
