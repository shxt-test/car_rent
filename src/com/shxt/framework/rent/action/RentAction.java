package com.shxt.framework.rent.action;

import com.shxt.base.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.framework.rent.query.Query;

public class RentAction extends BaseAction {

	
	private static final long serialVersionUID = -4695121257889278672L;
	
	private PageBean pageBean;
	
	private Query query;
	
	
	public String find(){
		this.toJsp="rentInfo/list";
		return DISPATCHER;
	}

}
