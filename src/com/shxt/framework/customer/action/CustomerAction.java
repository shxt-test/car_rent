package com.shxt.framework.customer.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shxt.base.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.customer.query.CustomerQuery;
import com.shxt.framework.customer.service.ICustomerService;
import com.shxt.framework.customertype.model.CustomerType;
import com.shxt.framework.customertype.service.ICustomerTypeService;

public class CustomerAction extends BaseAction {

	private static final long serialVersionUID = -6496519079653063937L;

	private PageBean pageBean;

	private List<CustomerType> cusTypeList;

	private CustomerInfo cusInfo;

	private CustomerQuery query;
	
	private Integer cus_id;
	
	private String id_card; 

	private ICustomerService customerService;
		public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
		
	private ICustomerTypeService customerTypeService;
	public void setCustomerTypeService(ICustomerTypeService customerTypeService) {
		this.customerTypeService = customerTypeService;
	}

	public String find(){

		if(pageBean==null){
			pageBean = new PageBean();
		}
		cusTypeList = customerTypeService.list();
		this.pageBean = customerService.find(query, pageBean);
		this.toJsp="customer/list";
		return DISPATCHER;
	}
	/**
	 * 添加跳转
	 * @return
	 */
	public String toAdd(){
		try {
			cusTypeList = customerTypeService.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.toJsp="customer/add";
		return DISPATCHER;
	}
	/**
	 * 添加客户
	 */
	public String add(){
		try{
			
			if(cusInfo.getCustomer_type().getC_type_id()==null){
				cusInfo.setCustomer_type(null);
			}
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
	/**
	 * 更新跳转
	 * @return
	 */
	public String toUpdate(){
		
		cusTypeList = customerTypeService.list();
		cusInfo = customerService.toUpdate(cus_id);
		this.toJsp="customer/update";
		return DISPATCHER;
	}
	/**
	 * 更新客户
	 * @return
	 */
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
	/**
	 * 更新状态
	 * @return
	 */
	public String toUpdateStatus(){

		Map<String , Object> map = new HashMap<String, Object>();
		try {
			customerService.updateStatus(cus_id);
			map.put("flag", "success");
			map.put("message", "变更成功");
		} catch (Exception e) {
			map.put("flag", "error");
			map.put("message", "变更失败请联系管理员");
			e.printStackTrace();
		}
		this.jsonResult = map;
		return JSON;
	}
	/**
	 * 查看担保人信息
	 * @return
	 */
	public String toLook(){
		cusTypeList = customerTypeService.list();
		cusInfo = customerService.toUpdate(cus_id);
		this.toJsp="customer/lookGua";
		return DISPATCHER;
		
	}
	
	public String test(){
		Map<String,Object> map = new HashMap<String,Object>();
			Long count = customerService.getTest(id_card);
			if(count>0){
				map.put("flag","error");
				map.put("message", "该省份证已拥被注册");
			}else{
				map.put("flag","success");
			}
		
	
		jsonResult=map;
		return JSON;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
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

	public List<CustomerType> getCusTypeList() {
		return cusTypeList;
	}

	public void setCusTypeList(List<CustomerType> cusTypeList) {
		this.cusTypeList = cusTypeList;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String idCard) {
		id_card = idCard;
	}

	
	





}
