package com.shxt.framework.cartype.service;

import java.util.List;

import com.shxt.framework.cartype.model.CarType;
import com.shxt.framework.dto.CarTypeDTO;
import com.shxt.framework.dto.MenuDTO;


public interface ICarTypeService {
	/**
	 * 通过汽车类型ID获取该汽车类型下对应的菜单信息
	 * @param role_id
	 * @return
	 */
	public List<CarType> getCarType(Integer type_id); 
	/**
//	 * 获取拥有汽车信息对应的父节点信息
	 * @param role_id
	 * @return
	 */
	public List<CarType> getParentNodeByCarTypeId(); 
	
	/**
	 * 得到启用的列表
	 * @return
	 */
	
	public List<CarType> getEnableList(); 
	/**
	 * 得到启用子类型列表
	 * @return
	 */
	
	public List<CarType> getTypeList(Integer typeId); 

	
	/**
	 * 获取该品牌下的所有类型
	 * @param typeId
	 * @return
	 */
	public List<CarType> getChildNodeByCarTypeId(Integer typeId);
	/**
	 * 通过角色获取该角色下已拥有的菜单信息
	 * @param role_id
	 * @return
	 */
	public List<CarType> getSelectCarTypeListByTypeId(Integer type_id); 
	/**
	 * 通过角色获取该角色下未拥有的菜单信息
	 * @param role_id
	 * @return
	 */
	public List<CarType> getUnSelectCarTypeListByTypeId(Integer type_id); 
	public List<CarTypeDTO> getCarTypeListAll();
	
	/**
	 * 添加品牌
	 * @param carType
	 */
	
	public void addBrand(CarType carType);
	
	/**
	 * 通过ID查询汽车类型
	 * @param type_id
	 * @return
	 */
	public CarType find(Integer type_id);
	
	public void update(CarType carType);
	public void deleteParent(Integer type_id);
	public void addChild(CarType carType);
	public void deleteChild(Integer type_id);
	public List<CarType> getBrandNodeAll();
	public Long getCheckCarTypeName(String typeName);
}
