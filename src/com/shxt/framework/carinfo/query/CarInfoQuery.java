package com.shxt.framework.carinfo.query;

public class CarInfoQuery {

	/**
	 * 车辆类型的父节点ID
	 */
	private String parent_id;
	/**
	 * 车辆类型的子节点ID
	 */
	private String child_id;
	/**
	 * 车辆名称
	 */
	private String carinfo_name;
	
	public String getCarinfo_name() {
		return carinfo_name;
	}

	public void setCarinfo_name(String carinfoName) {
		carinfo_name = carinfoName;
	}

	public String getChild_id() {
		return child_id;
	}

	public void setChild_id(String childId) {
		child_id = childId;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parentId) {
		parent_id = parentId;
	}
	
	
}
