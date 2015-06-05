package com.shxt.framework.cartype.model;

import java.io.Serializable;

public class CarType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7889048020898801604L;
	/** 车辆类型主键 */
	private Integer type_id;
	/** 名称 */
	private String type_name;
	/** 状态:1-可用 2-禁用状态 */
	private String type_status = "1";// 1 代表可用 2代表禁用状态
	/** 父节点ID */
	private String parent_id;
	
	private String icon;

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer typeId) {
		type_id = typeId;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String typeName) {
		type_name = typeName;
	}

	public String getType_status() {
		return type_status;
	}

	public void setType_status(String typeStatus) {
		type_status = typeStatus;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parentId) {
		parent_id = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
