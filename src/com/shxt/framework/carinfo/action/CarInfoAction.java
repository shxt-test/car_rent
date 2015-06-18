package com.shxt.framework.carinfo.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shxt.base.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.base.exception.RbacException;
import com.shxt.base.utils.PropertiesConfigHelper;
import com.shxt.framework.carinfo.model.CarInfo;
import com.shxt.framework.carinfo.query.CarInfoQuery;
import com.shxt.framework.carinfo.service.ICarInfoSerivece;
import com.shxt.framework.cartype.model.CarType;
import com.shxt.framework.cartype.service.ICarTypeService;
import com.shxt.framework.user.model.User;

public class CarInfoAction extends BaseAction {

	private static final long serialVersionUID = -6496519079653063937L;
	/**
	 * 车辆信息
	 */
	private CarInfo carInfo;
	/**
	 * 车辆服务
	 */
	private ICarInfoSerivece carInfoSerivece;
	public void setCarInfoSerivece(ICarInfoSerivece carInfoSerivece) {
		this.carInfoSerivece = carInfoSerivece;
	}
	/**
	 * 车辆类型服务
	 */
	private ICarTypeService carTypeService;
	public void setCarTypeService(ICarTypeService carTypeService) {
		this.carTypeService = carTypeService;
	}
	/**
	 * 保存查询条件
	 */
	private CarInfoQuery query;
	/**
	 * 汽车的主键
	 */
	private Integer carInfo_id;
	/**
	 * 分页的工具类
	 */
	private PageBean pageBean;
	/**
	 * 查询车辆类型需要的ID
	 */
	private String bra_id;
	/**
	 * 文件上传
	 */
	private File photo;
	private String photoFileName;
	private String photoContentType;
	/**
	 * 汽车类型列表
	 */
	private List<CarType> carTypelist;
	
	/**
	 * 汽车列表分页
	 * @return
	 */
	public String find(){

		if(pageBean==null){
			pageBean = new PageBean();
		}

		this.pageBean = carInfoSerivece.find(query,pageBean);

		this.toJsp = "carinfo/list";

		return DISPATCHER;
	}
	/**
	 * 跳转到汽车信息添加页面--需要传递数据汽车类型列表
	 * @return
	 */
	public String toAdd(){
		try {
			//获取启用的汽车信息列表
			carTypelist = carTypeService.getEnableList();

			this.toJsp="carinfo/add";
		} catch (RbacException e) {
			e.printStackTrace();
		}

		return DISPATCHER;
	}
	
	/**
	 * 获取所有品牌
	 * @return
	 */
	public String toLoadBrand() {
		List<CarType> dataList = carTypeService.getEnableList();
		this.jsonResult = dataList;

		return JSON;
	}
	/**
	 * 获取品牌下的所有类型
	 * @return
	 */
	public String toLoadType() {
		List<CarType> dataList = carTypeService.getTypeList(Integer.parseInt(bra_id));
		this.jsonResult = dataList;
		return JSON;
	}
	
	/***
	 * 汽车添加操作，有图片的上传哟
	 * @return
	 */
	public String add(){
		try {
			if(photo!=null){
				String path = ServletActionContext.getServletContext().getRealPath("/upload/carinfo");

				String ext = FilenameUtils.getExtension(photoFileName);
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(10000)+"."+ext;

				File newFile = new File(path+"/"+saveName);
				newFile.createNewFile();

				FileUtils.copyFile(photo, newFile);

				carInfo.setPhoto(saveName);
			}
			//添加创建者名字
			Map<String ,Object> session = ActionContext.getContext().getSession();
			User loginUser  = (User) session.get(PropertiesConfigHelper.getStringValue("RBAC_SESSION"));
			carInfo.setCreate_user_name(loginUser.getUser_name());
			carInfoSerivece.add(carInfo);
			this.message="车辆信息添加成功,谢谢合作!";
			this.flag = "success";

		} catch (Exception e) {
			e.printStackTrace();
			this.message="车辆信息添加失败,异常信息为:"+e.getMessage();
			this.flag = "error";
		}
		this.toJsp = "message";
		return DISPATCHER;
		
	}
	/**
	 * 跳转到汽车修改页面
	 * @return
	 */
	public String toUpdate(){
		carInfo = carInfoSerivece.getCarInfoById(carInfo_id);
		this.toJsp = "carinfo/update";
		return DISPATCHER;
	}
	/**
	 * 汽车修改
	 * @return
	 */
	public String update(){
		
		try {
			if(photo!=null){
				String path = ServletActionContext.getServletContext().getRealPath("/upload/carinfo");

				String ext = FilenameUtils.getExtension(photoFileName);
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(10000)+"."+ext;

				File newFile = new File(path+"/"+saveName);
				newFile.createNewFile();

				FileUtils.copyFile(photo, newFile);

				carInfo.setPhoto(saveName);
			}
			carInfoSerivece.updateAll(carInfo);
			this.message="车辆信息更新成功,谢谢合作!";
			this.flag = "success";

		} catch (Exception e) {
			e.printStackTrace();
			this.message="车辆信息更新失败,异常信息为:"+e.getMessage();
			this.flag = "error";
		}
		this.toJsp = "message";
		return DISPATCHER;
	}
	/**
	 * 更改车的状态
	 * @return
	 */
	public String toUpdateStatus(){
		Map<String , Object> map = new HashMap<String, Object>();
		try {
			carInfoSerivece.updateStatus(carInfo_id);
			map.put("flag", "success");
			map.put("message", "变更成功");
		} catch (Exception e) {
			map.put("flag", "error");
			map.put("message", "变更失败请联系管理员");
			e.printStackTrace();
		}
		this.jsonResult = map;
		return JSON;
	}
	/**
	 * 预定列表
	 */
	public String findReserve(){
		if(pageBean==null){
			pageBean = new PageBean();
		}

		this.pageBean = carInfoSerivece.findByReserve(pageBean);

		this.toJsp = "carinfo/reserve_list";

		return DISPATCHER;
		
	}
	/**
	 * 跳转到预定页面
	 * @return
	 */
	public String toReserve(){
		
		carInfo = carInfoSerivece.getCarInfoById(carInfo_id);
		
		this.toJsp = "carinfo/reserve_add";
		
		return DISPATCHER;
	}
	/**
	 * 预定操作
	 * @return
	 */
	public String reserve(){
		try {
			
			System.out.println(carInfo.getCar_id());
			
			carInfoSerivece.addReserve(carInfo);
			this.message="预定成功,谢谢合作!";
			this.flag = "success";

		} catch (Exception e) {
			e.printStackTrace();
			this.message="预定失败,异常信息为:"+e.getMessage();
			this.flag = "error";

		}
		this.toJsp = "message";
		return DISPATCHER;
	}
	/**
	 * 可取消预定列表
	 * @return
	 */
	public String findByCancelReserve(){
		if(pageBean==null){
			pageBean = new PageBean();
		}

		this.pageBean = carInfoSerivece.findByCancelReserve(pageBean);

		this.toJsp = "carinfo/cancel_list";

		return DISPATCHER;
	}
	/**
	 * 取消预定
	 * @return
	 */
	public String toCancelReserve(){
		Map<String , Object> map = new HashMap<String, Object>();
		try {
			carInfoSerivece.update(carInfo_id);
			map.put("flag", "success");
			map.put("message", "取消预定成功");
		} catch (Exception e) {
			map.put("flag", "error");
			map.put("message", "取消预定失败请联系管理员");
			e.printStackTrace();
		}
		this.jsonResult = map;
		return JSON;
	}
	
	
	
	
	
	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public CarInfoQuery getQuery() {
		return query;
	}

	public void setQuery(CarInfoQuery query) {
		this.query = query;
	}

	public Integer getCarInfo_id() {
		return carInfo_id;
	}

	public void setCarInfo_id(Integer carInfoId) {
		carInfo_id = carInfoId;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<CarType> getCarTypelist() {
		return carTypelist;
	}
	public void setCarTypelist(List<CarType> carTypelist) {
		this.carTypelist = carTypelist;
	}
	public String getBra_id() {
		return bra_id;
	}
	public void setBra_id(String braId) {
		bra_id = braId;
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
