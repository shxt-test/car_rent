package com.shxt.framework.role.service;

import java.util.List;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.exception.RbacException;
import com.shxt.framework.menu.model.Menu;
import com.shxt.framework.role.model.Role;

@SuppressWarnings("unchecked")
public class RoleServiceImpl implements IRoleService {

	private IBaseDao baseDao ;

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Role> getStartRoleList() {
		return this.getRoleList("1");
	}

	public List<Role> getRoleAllList() {
		return this.getRoleList(null);
	}

	private List<Role> getRoleList(String role_status) {
		String hql = "from Role r where 1=1 ";
		if (role_status != null) {
			hql += " and r.role_status='" + role_status + "'";
		}

		return (List<Role>) this.baseDao.list(hql);
	}
	
	public void updateAuthorize(Integer role_id,Integer[] menuIds){
		
		Role role = (Role) this.baseDao.load(Role.class, role_id);
		role.getMenuSet().clear();
		if(menuIds!=null&&menuIds.length>0){
			for (Integer menu_id : menuIds) {
				Menu menu = (Menu) this.baseDao.load(Menu.class, menu_id);
				role.getMenuSet().add(menu);
				
			}
		
		}
		
		this.baseDao.update(role);
		
	}

	public Role getRoleById(Integer role_id) {
		
		return (Role) this.baseDao.load(Role.class,role_id);
	}
	
	public Long getCheckRoleName(String role_name){
		
		String hql = "select count(r.role_id) from Role r where role_name=?";
		
		return (Long) this.baseDao.query(hql, role_name);
	}
	
	public void add(Role role){
		
		this.baseDao.add(role);
		
		
	}
	
	public void update(Role role){
		Role old_role = (Role) this.baseDao.load(Role.class, role.getRole_id());
		old_role.setRole_name(role.getRole_name());
		old_role.setRole_desc(role.getRole_desc());
		if(role.getPhoto()!=null){
			old_role.setPhoto(role.getPhoto());
		}
		
	}
	
	public void delete(Integer role_id){
		Role role = (Role) this.baseDao.load(Role.class, role_id);
		//判断你是否允许删除
			if(role.getFlag().equals("2")){
				throw new RbacException("该角色已被锁定，无法进行注销操作");
			}else{
				String sql = "delete from role_link_menu where fk_role_id=?";
				this.baseDao.updateBySQL(sql, role_id);
				sql = "update web_sys_user set fk_role_id = null where fk_role_id=?";
				this.baseDao.updateBySQL(sql, role_id);
				sql = "delete from web_sys_role where role_id=?";
				this.baseDao.updateBySQL(sql, role_id);
			}
	}

}
