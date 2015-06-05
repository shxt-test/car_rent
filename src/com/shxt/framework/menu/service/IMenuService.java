package com.shxt.framework.menu.service;

import java.util.List;

import com.shxt.framework.dto.MenuDTO;
import com.shxt.framework.menu.model.Menu;
import com.shxt.framework.user.model.User;

public interface IMenuService {
	/**
	 * 通过角色获取该角色下对应的菜单信息
	 * @param role_id
	 * @return
	 */
	public List<Menu> getChildNodeByRoleId(Integer role_id); 
	/**
	 * 通过角色获取拥有菜单信息对应的父节点信息
	 * @param role_id
	 * @return
	 */
	public List<Menu> getParentNodeByRoleId(Integer role_id); 
	
	/**
	 * 通过角色获取该角色下已拥有的菜单信息
	 * @param role_id
	 * @return
	 */
	public List<Menu> getSelectMenuListByRoleId(Integer role_id); 
	/**
	 * 通过角色获取该角色下未拥有的菜单信息
	 * @param role_id
	 * @return
	 */
	public List<Menu> getUnSelectMenuListByRoleId(Integer role_id); 
	

	
	public List<MenuDTO> getMenuListAll();
	public void add(Menu menu);
	public void deleteParent(Integer menu_id);
	public void deleteChild(Integer menu_id);
	
	public List<Menu> getMenuList();
	public void addChild(Menu menu);
}
