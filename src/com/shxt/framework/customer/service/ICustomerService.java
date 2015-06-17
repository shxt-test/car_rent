package com.shxt.framework.customer.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.customer.model.CustomerInfo;
import com.shxt.framework.customer.query.CustomerQuery;

public interface ICustomerService {
	/**
	 * 添加客户信息
	 * @param customer
	 */
	
	public void add(CustomerInfo customer);
	/**
	 * 查询所有客户信息并分页
	 * @param query 查询的条件
	 * @param pageBean 
	 * @return
	 */
	
	public PageBean find(CustomerQuery query ,PageBean pageBean);
	
	/**
	 * 通过id查询要更新的记录
	 * @param cus_id
	 * @return
	 */
	public CustomerInfo toUpdate(Integer  cus_id);
	
	/**
	 * 更新客户信息
	 * @param customer
	 */
	public void update(CustomerInfo cusInfo);
	/**
	 * 删除客户信息
	 * @param type_id
	 */
	public void delete(Integer cus_id);
	/**
	 * 更该客户状态
	 * @param cus_id
	 */
	public void updateStatus(Integer cus_id);
	/**
	 * 验证省份证号码
	 * @param id_card
	 */
	public Long getTest(String id_card);
	
	public List<CustomerInfo> list();
	

}
