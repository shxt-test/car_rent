package com.shxt.framework.role.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.shxt.base.action.BaseAction;
import com.shxt.base.exception.RbacException;
import com.shxt.framework.menu.model.Menu;
import com.shxt.framework.menu.service.IMenuService;
import com.shxt.framework.role.model.Role;
import com.shxt.framework.role.service.IRoleService;

public class RoleAction extends BaseAction {



	private List<Role> roleList;
	
	private File photo;
	private String photoFileName;
	private String photoContentType;
	
	private Role role;
	
	private String role_name;
	
	private Integer role_id;
	
	private Integer[] menuIds;
	/**已拥有的菜单*/
	private List<Menu> selectMenuList;
	/**未拥有的菜单*/
	private List<Menu> unSelectMenuList;
	
	IRoleService roleService;
	
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	IMenuService menuService ;

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public String list() {
		
		roleList = roleService.getRoleAllList();
		this.toJsp = "role/list";
		return DISPATCHER;
	}

	public String toAuthorize(){
		//获取该角色下拥有的菜单
		selectMenuList = menuService.getSelectMenuListByRoleId(role_id);
		//获取该角色下未拥有的菜单
		unSelectMenuList = menuService.getUnSelectMenuListByRoleId(role_id);
		
		role = roleService.getRoleById(role_id);
		
		this.toJsp = "role/authorize";
		return DISPATCHER;
	}
	public String updateAuthorize(){
		try{
			
			this.roleService.updateAuthorize(role.getRole_id(), menuIds);
		this.message="更新菜单成功,谢谢合作!";
		this.flag = "success";

		} catch (Exception e) {
		e.printStackTrace();
		this.message="更新菜单失败,异常信息为:"+e.getMessage();
		this.flag = "error";
		}
		this.toJsp = "message";
		return DISPATCHER;
	}
	
	public String toCheck(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		Long count = roleService.getCheckRoleName(role_name);
		
		if(count>0){
			map.put("flag","error");
			map.put("message", "该角色已拥有");
		}else{
			map.put("flag","success");
		}
		
		this.jsonResult = map;
		return JSON;
	}
	
	public String add(){
		try {
			if(photo!=null){
				String path = ServletActionContext.getServletContext().getRealPath("/upload/role");

				String ext = FilenameUtils.getExtension(photoFileName);
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(10000)+"."+ext;

				File newFile = new File(path+"/"+saveName);
				newFile.createNewFile();

				FileUtils.copyFile(photo, newFile);

				role.setPhoto(saveName);
			}
		
			roleService.add(role);
		
			this.message="角色添加成功,谢谢合作!";
			this.flag = "success";

		} catch (Exception e) {
			e.printStackTrace();
			this.message="角色添加失败,异常信息为:"+e.getMessage();
			this.flag = "error";

		}

		this.toJsp = "message";

		return DISPATCHER;
	}
	
	public String toUpdate(){
		role = this.roleService.getRoleById(role_id);
		this.toJsp="role/update";
		return DISPATCHER;
	}
	public String update(){
		try {
			if(photo!=null){
				String path = ServletActionContext.getServletContext().getRealPath("/upload/role");
				String ext = FilenameUtils.getExtension(photoFileName);
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(10000)+"."+ext;
				File newFile = new File(path+"/"+saveName);
				newFile.createNewFile();

				FileUtils.copyFile(photo, newFile);
				role.setPhoto(saveName);
			}
			roleService.update(role);
		
			this.message="角色添加成功,谢谢合作!";
			this.flag = "success";

		} catch (Exception e) {
			e.printStackTrace();
			this.message="角色添加失败,异常信息为:"+e.getMessage();
			this.flag = "error";

		}
		this.toJsp = "message";
		return DISPATCHER;
	}
	public String toAdd(){
		this.toJsp="role/add";
		return REDIRECT;
	}
	
	public String delete(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			this.roleService.delete(role_id);
			map.put("flag", "success");
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message", e.getMessage());
		}
		this.jsonResult=map;
		return JSON;
		
	}

	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}



	public Integer getRole_id() {
		return role_id;
	}



	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}

	public Integer[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(Integer[] menuIds) {
		this.menuIds = menuIds;
	}

	public List<Menu> getSelectMenuList() {
		return selectMenuList;
	}

	public void setSelectMenuList(List<Menu> selectMenuList) {
		this.selectMenuList = selectMenuList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Menu> getUnSelectMenuList() {
		return unSelectMenuList;
	}

	public void setUnSelectMenuList(List<Menu> unSelectMenuList) {
		this.unSelectMenuList = unSelectMenuList;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String roleName) {
		role_name = roleName;
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

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

}
