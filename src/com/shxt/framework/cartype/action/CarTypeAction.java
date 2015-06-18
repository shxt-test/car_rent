package com.shxt.framework.cartype.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.shxt.base.action.BaseAction;
import com.shxt.base.exception.RbacException;
import com.shxt.base.utils.PropertiesConfigHelper;
import com.shxt.framework.cartype.model.CarType;
import com.shxt.framework.cartype.service.ICarTypeService;
import com.shxt.framework.dto.CarTypeDTO;
import com.shxt.framework.user.model.User;


public class CarTypeAction extends BaseAction {
	private static final long serialVersionUID = -3243344985662707874L;

	private List<CarType> parentNodeList;
	
	private List<CarType> childNodeList;
	private String type_name;
	private File photo;
	private String photoFileName;
	private String photoContentType;
	
	private List<CarTypeDTO> carTypeList;
	
	private CarType carType;
	
	private Integer type_id;
	
	private ICarTypeService carTypeService;
	
	//获得汽车类型
	public String findCarKnowledge(){
		carTypeList = this.carTypeService.getCarTypeListAll();
		this.toJsp="cartype/list";
		return DISPATCHER;
	}
	
	//添加品牌到jsp页面
	public String toAdd(){
		this.toJsp="cartype/brand_add";
		return REDIRECT;
	}
	
	//添加品牌
	public String addbrand(){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			this.carTypeService.addBrand(carType);
			map.put("message", "汽车品牌添加成功");
			map.put("flag", "success");
		} catch (RbacException e) {
			map.put("message", e.getMessage());
			map.put("flag", "error");
		}
		this.jsonResult=map;
		return JSON;
	}
	
	//添加类型
	public String toAddChild(){
		parentNodeList=this.carTypeService.getBrandNodeAll();
		this.toJsp="cartype/type_add";
		return DISPATCHER;
		
	}
	
	
	public String toUpdateBrand(){
		carType=carTypeService.find(type_id);
		this.toJsp="cartype/brand_update";
		return DISPATCHER;
	}
	
	//品牌修改
	public String toUpdate(){
		
		try {
			System.out.println("action"+photo);
			if(photo!=null){
				String path = ServletActionContext.getServletContext().getRealPath("/upload/cartype");
				
				String ext = FilenameUtils.getExtension(photoFileName);
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(10000)+"."+ext;
				
				File newFile = new File(path+"/"+saveName);
				newFile.createNewFile();
				
				FileUtils.copyFile(photo, newFile);
				
				carType.setIcon(saveName);
			}
			carTypeService.update(carType);
			this.message="用户修改成功";
			this.flag="success";
		} catch (Exception e) {
			this.flag="error";
			this.message="您修改失败错误为"+	e.getMessage();
		
		}
		this.toJsp="message";
		return DISPATCHER;
	}
	
	public String toCheck(){
		Map<String,Object> map=new HashMap<String,Object>();
		Long count=this.carTypeService.getCheckCarTypeName(type_name);
		if(count>0){
			map.put("flag", "error");
			map.put("message", "该汽车品牌已经拥有");
		}else{
			map.put("flag", "success");
		}
		this.jsonResult=map;
		return JSON;
	}
	
	//核对汽车品牌类型
	public String toCheckType(){
		Map<String,Object> map=new HashMap<String,Object>();
		System.out.println(type_id);
		carType=this.carTypeService.getChildCarType(type_id);
		System.out.println(carType.getType_name());
		map.put("flag", carType);
		this.jsonResult=map;
		return JSON;
	}
	/**
	 * 删除汽车类型
	 * @return
	 */
	public String deleteParent(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			this.carTypeService.deleteChild(type_id);
			map.put("flag", "success");
			map.put("message", "删除成功");
			
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message",e.getMessage());
		}
		this.jsonResult = map;
		return JSON;
	}
	
	/**
	 * 变更状态
	 * @return
	 */
	public String toUpdateStatus(){
			Map<String,Object> map=new HashMap<String,Object>();
		try {
			carTypeService.updatestatus(type_id);
			map.put("flag", "success");
			map.put("message", "您的变更成功");
		}catch (Exception e) {
			map.put("flag", "error");
			map.put("message", "您的变更失败，请联系管理员");
			e.getMessage();
		}
		this.jsonResult=map;
		return JSON;
	}
	
	//添加汽车品牌
	public String toAddParent(){
		carTypeList =  carTypeService.getCarTypeListAll();
		System.out.println(carTypeList.get(0).getParent_id()+"==="+carTypeList.get(0).getType_name()+"=="+carTypeList.get(0).getType_name());
		this.toJsp = "cartype/type_add";
		return REDIRECT;
	}
	
	public String addChild(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			this.carTypeService.addChild(carType);
			map.put("flag", "success");
			map.put("message", "添加成功");
			
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message", "添加失败"+e.getMessage());
		}
		this.jsonResult = map;
		return JSON;
	}
	
	//删除品牌类型
	public String deleteChild(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			this.carTypeService.deleteChild(type_id);
			map.put("flag", "success");
			map.put("message", "删除成功");
			
		} catch (RbacException e) {
			map.put("flag", "error");
			map.put("message",e.getMessage());
		}
		this.jsonResult = map;
		return JSON;
	}
	
	/**
	 * 统计测试
	 * @return
	 */
	public String tjTest(){
		this.toJsp = "cartype/tj_test";
		return REDIRECT;
	}
	
	public String tjDataJSON(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("test/html;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			
			Map<String , Object> mapsx = new HashMap<String , Object>();
			
			mapsx.put("bgcolor", "FFFFFF");
			mapsx.put("bgalpha", "100");
			mapsx.put("caption", "汽车统计");
			mapsx.put("subcaption", "副标题");
			mapsx.put("numberprefix", "总共");
			mapsx.put("numberSuffix", "辆");
			mapsx.put("is2d", "1");
			mapsx.put("issliced", "1");
			mapsx.put("showplotborder", "1");
			mapsx.put("plotborderthickness", "1");
			mapsx.put("plotborderalpha", "100");
			mapsx.put("plotbordercolor", "FFFFFF");
			mapsx.put("enablesmartlabels", "1");
			mapsx.put("showborder", "0");
			
			Map<String, Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("chart", mapsx);
			jsonMap.put("data",this.carTypeService.getCharDatas());
			Gson gson = new Gson();
			out.write(gson.toJson(jsonMap));
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}
	public List<CarType> getParentNodeList() {
		return parentNodeList;
	}

	public void setParentNodeList(List<CarType> parentNodeList) {
		this.parentNodeList = parentNodeList;
	}

	public List<CarType> getChildNodeList() {
		return childNodeList;
	}

	public void setChildNodeList(List<CarType> childNodeList) {
		this.childNodeList = childNodeList;
	}

	
	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer typeId) {
		type_id = typeId;
	}
	public void setCarTypeService(ICarTypeService carTypeService) {
		this.carTypeService = carTypeService;
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

	public List<CarTypeDTO> getCarTypeList() {
		return carTypeList;
	}

	public void setCarTypeList(List<CarTypeDTO> carTypeList) {
		this.carTypeList = carTypeList;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String typeName) {
		type_name = typeName;
	}


}
