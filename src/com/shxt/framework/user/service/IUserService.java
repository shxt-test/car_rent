package com.shxt.framework.user.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.base.model.CharDatas;
import com.shxt.framework.user.model.User;
import com.shxt.framework.user.query.UserQuery;

public interface IUserService {
	public User login(User user);
	public PageBean find(UserQuery query,PageBean pageBean);
	
	public void add(User user);
	
	public User getUserById(Integer user_id);
	
	public void update(User user);
	public void UpdateStatus(Integer user_id);
	
	public void updateRole(User user);
	public List<CharDatas> getCharDatas();
}
