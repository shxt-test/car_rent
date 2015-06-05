package com.shxt.framework.menu.service;

import java.util.List;

import com.shxt.base.dao.BaseDaoImpl;
import com.shxt.base.dao.IBaseDao;
import com.shxt.base.exception.RbacException;
import com.shxt.framework.dto.MenuDTO;
import com.shxt.framework.menu.model.Menu;
@SuppressWarnings("unchecked")
public class MenuServiceImpl implements IMenuService {
	private IBaseDao baseDao;

	
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Menu> getChildNodeByRoleId(Integer roleId) {
		String sql = "select m.* from web_sys_menu m,role_link_menu rlm where rlm.fk_menu_id=m.menu_id and rlm.fk_role_id=?";
		return (List<Menu>) this.baseDao.listSQL(sql, roleId, Menu.class, true);
	}

	public List<Menu> getParentNodeByRoleId(Integer roleId) {
		String sql = "select mm.* from web_sys_menu mm where mm.menu_id in( select DISTINCT m.parent_id from web_sys_menu m,role_link_menu rlm where rlm.fk_menu_id=m.menu_id and rlm.fk_role_id=?)";
		return (List<Menu>) this.baseDao.listSQL(sql, roleId, Menu.class, true);
	}

	public List<Menu> getSelectMenuListByRoleId(Integer roleId) {
		String sql = "select m.* from web_sys_menu m,role_link_menu rlm where rlm.fk_menu_id=m.menu_id and rlm.fk_role_id=?";
		return (List<Menu>) this.baseDao.listSQL(sql, roleId, Menu.class, true);
	}

	public List<Menu> getUnSelectMenuListByRoleId(Integer roleId) {
		String sql = "select mm.* from web_sys_menu mm where mm.menu_id not in " +
				" (select m.menu_id from web_sys_menu m,role_link_menu rlm where rlm.fk_menu_id=m.menu_id and rlm.fk_role_id=?)" +
				" and mm.parent_id is not null";
		return (List<Menu>) this.baseDao.listSQL(sql, roleId, Menu.class, true);
	}
	

	public List<MenuDTO> getMenuListAll(){
		String sql = "select * from web_sys_menu where parent_id is null order by menu_id asc";
		List<MenuDTO> parentMenuList = (List<MenuDTO>) this.baseDao.listSQL(sql, MenuDTO.class, false);
		
		if(parentMenuList!=null&&parentMenuList.size()>0){
			for (MenuDTO parentMenu : parentMenuList) {
				sql = "select * from web_sys_menu where parent_id="+parentMenu.getMenu_id();
				parentMenu.setMenuList((List<MenuDTO>)this.baseDao.listSQL(sql, MenuDTO.class, false));
			}
		}
		return parentMenuList;
	}
	
	//递归调用
	
	public void add(Menu menu){
		String hql = "select count(menu_id) from Menu where menu_name=? and parent_id is null";
		Long count = (Long) this.baseDao.query(hql, menu.getMenu_name());
		
		if(count>0){
			throw new RbacException("该菜单已经拥有，请重新填写");
		}else{
			this.baseDao.add(menu);
		}
	}
	public void addChild(Menu menu){
		String hql = "select count(menu_id) from Menu where menu_name=? and parent_id is not null";
		Long count = (Long) this.baseDao.query(hql,menu.getMenu_name());
		if(count>0){
			throw new RbacException("该菜单已经拥有，请重新填写");
		}else{
			this.baseDao.add(menu);
		}
	}
	
	public void deleteParent(Integer menu_id){
		String hql = "select count(menu_id) from Menu where parent_id=? ";
		Long count = (Long) this.baseDao.query(hql, String.valueOf(menu_id));
		if(count>0){
			throw new RbacException("该菜单下有["+count+"]个子节点，清先删除子节点后再删父节点");
		}else{
			hql = "delete from Menu where menu_id=?";
			this.baseDao.updateByHql(hql, menu_id);
		}
	}
	public void deleteChild(Integer menu_id){
		String sql = "delete from role_link_menu where fk_menu_id=?";
		this.baseDao.updateBySQL(sql, menu_id);
		sql ="delete from web_sys_menu where menu_id=?";
		this.baseDao.updateBySQL(sql,menu_id);
	}
	
	public List<Menu> getMenuList(){
		String sql = "select * from web_sys_menu where parent_id is null order by menu_id asc";
		return (List<Menu>) this.baseDao.listSQL(sql, Menu.class, false);
	}
}
