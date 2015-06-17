package com.shxt.framework.customertype.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shxt.base.action.BaseAction;
import com.shxt.framework.customertype.model.CustomerType;
import com.shxt.framework.customertype.service.ICustomerTypeService;

public class CustomerTypeAction extends BaseAction{
	/**ID*/
	private Integer c_type_id;
	/**名称*/
	private String c_type_name;
	/**列表*/
	private List<CustomerType> typeList;
	
	private CustomerType customerType; 
	
	
	private ICustomerTypeService customerTypeService;
	
	public String add(){
		System.out.println(customerType.getC_type_name());
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			
			customerType.setPhoto(this.getSaveName());
			customerTypeService.add(customerType);
			this.message = "客户类型添加成功";
			this.flag = "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.message = "客户类型添加失败"+e.getMessage();
			this.flag = "error";
		}
		this.toJsp = "message";
		return DISPATCHER;
	}
	public String toUpdate(){
		System.out.println(c_type_id);
		customerType = this.customerTypeService.getCustomerTypeById(c_type_id);
		this.toJsp = "customertype/update";
		
		return DISPATCHER;
	}
	public String update(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println(this.getSaveName());
			customerType.setPhoto(this.getSaveName());
			this.customerTypeService.update(customerType);
			this.message="客户类型修改成功";
			this.flag="success";
		} catch (Exception e) {
			e.printStackTrace();
			this.message="客户类型修改失败,异常信息为:"+e.getMessage();
			this.flag="error";
		}
		this.toJsp = "message";
		return DISPATCHER;
	}
	public String toAdd(){
		
		this.toJsp = "customertype/add";
		
		return REDIRECT;
	}
	
	public String list(){
		try {
			typeList = this.customerTypeService.list();
			this.toJsp = "customertype/list";
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
		
		return DISPATCHER;
	}
	public String delete(){
		try {
		customerTypeService.delete(c_type_id);
		
			this.message = "删除成功";
			this.flag = "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.message = "删除失败，异常信息为"+e.getMessage();
			this.flag = "error";
		}
		this.toJsp = "message";
		
		return DISPATCHER;
	}
	public Integer getC_type_id() {
		return c_type_id;
	}

	public void setC_type_id(Integer cTypeId) {
		c_type_id = cTypeId;
	}

	public String getC_type_name() {
		return c_type_name;
	}

	public void setC_type_name(String cTypeName) {
		c_type_name = cTypeName;
	}

	public List<CustomerType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<CustomerType> typeList) {
		this.typeList = typeList;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public void setCustomerTypeService(ICustomerTypeService customerTypeService) {
		this.customerTypeService = customerTypeService;
	}
	
}
