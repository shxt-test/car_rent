package com.shxt.framework.customer.action;

import com.shxt.base.action.BaseAction;

public class CustomerAction extends BaseAction {

	private static final long serialVersionUID = -6496519079653063937L;
	
	public String find(){
		System.out.println(1);
		
		this.toJsp="customer/list";
		return DISPATCHER;
	}

}
