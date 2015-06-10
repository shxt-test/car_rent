package com.shxt.framework.customertype.action;

import java.io.File;
import java.util.List;

import com.shxt.base.action.BaseAction;
import com.shxt.framework.customertype.model.CustomerType;
import com.shxt.framework.customertype.service.ICustomerTypeService;

public class CustomerTypeAction extends BaseAction{
	/**ID*/
	private Integer c_type_id;
	/**名称*/
	private String c_type_name;
	
	private List<CustomerType> typeList;
	
	private CustomerType customerType; 
	
	private File photo;
	
	private String photoFileName;
	
	private String photoCountentType;
	
	private ICustomerTypeService customerTypeService;
	
	public String list(){
		try {
			System.out.println("123");
			typeList = this.customerTypeService.list();
			this.toJsp = "customertype/list";
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
		
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

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoCountentType() {
		return photoCountentType;
	}

	public void setPhotoCountentType(String photoCountentType) {
		this.photoCountentType = photoCountentType;
	}

	public void setCustomerTypeService(ICustomerTypeService customerTypeService) {
		this.customerTypeService = customerTypeService;
	}
	
}
