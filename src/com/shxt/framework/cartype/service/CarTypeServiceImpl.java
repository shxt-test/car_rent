package com.shxt.framework.cartype.service;

import java.util.List;

import com.shxt.base.dao.IBaseDao;
import com.shxt.base.exception.RbacException;
import com.shxt.base.model.CharDatas;
import com.shxt.framework.cartype.model.CarType;
import com.shxt.framework.dto.CarTypeDTO;


public class CarTypeServiceImpl implements ICarTypeService {
	private IBaseDao baseDao;

	public List<CarType> getCarType(Integer typeId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public List<CarType> getParentNodeByCarTypeId() {
		String sql = "select mm.* from car_type mm ";
		return (List<CarType>) this.baseDao.listSQL(sql, null, CarType.class, true);
	}

	public List<CarType> getChildNodeByCarTypeId(Integer typeId) {
		String sql = "select mm.* from car_type mm where mm.parent_id =?";
		return (List<CarType>) this.baseDao.listSQL(sql, typeId, CarType.class, true);
	}
	
	public List<CarType> getSelectCarTypeListByTypeId(Integer typeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CarType> getUnSelectCarTypeListByTypeId(Integer typeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CarTypeDTO> getCarTypeListAll() {
		String sql = "select * from car_type where parent_id is null order by type_id asc";
		List<CarTypeDTO> parentMenuList = (List<CarTypeDTO>) this.baseDao.listSQL(sql, CarTypeDTO.class, false);
		
		if(parentMenuList!=null&&parentMenuList.size()>0){
			for (CarTypeDTO parentMenu : parentMenuList) {
				sql = "select * from car_type where parent_id="+parentMenu.getType_id();
				parentMenu.setCarTypeList((List<CarTypeDTO>)this.baseDao.listSQL(sql, CarTypeDTO.class, false));
			}
		}
		return parentMenuList;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	//添加品牌
	public void addBrand(CarType carType){
		String hql="select count(m.type_id) from CarType m where m.type_name =? and m.parent_id is null";
		Long  count=(Long) this.baseDao.query(hql, carType.getType_name().trim());
		if(count>0){
			throw new RbacException("该品牌已经存在，请重新添加");
		}else{
			this.baseDao.add(carType);
		}
	}
	/**
	 * 
	 * @param type_id
	 * @return
	 */
		public CarType find(Integer type_id) {
			String hql="from CarType u where 1=1 and u.type_id =?";
			return  (CarType) this.baseDao.query(hql,type_id);
		}
		//持久化状态
		public void update(CarType carType) {
			CarType oldCarType=(CarType)this.baseDao.load(CarType.class,carType.getType_id());
			if(carType.getIcon()!=null){
				oldCarType.setIcon(carType.getIcon());
			}

			oldCarType.setType_name(carType.getType_name());
		}

	
		//删除汽车品牌节点
		public void deleteParent(Integer type_id){
			String hql = "select count(type_id) from CarType where parent_id=? ";
			Long count = (Long) this.baseDao.query(hql, String.valueOf(type_id));
			if(count>0){
				throw new RbacException("该品牌下有["+count+"]个类型，清先删除汽车类型后再删品牌1");
			}else{
				hql = "delete from CarType where type_id=?";
				this.baseDao.updateByHql(hql, type_id);
			}
		}
		
		//删除汽车类型节点
		public void deleteChild(Integer type_id){
			String hql = "select count(type_id) from CarType where parent_id=? ";
			Long count = (Long) this.baseDao.query(hql, String.valueOf(type_id));
			if(count>0){
				hql = "delete from CarType where type_id=?";
				this.baseDao.updateByHql(hql, type_id);
				throw new RbacException("该品牌下有["+count+"]个类型，清先删除汽车类型后再删品牌");
			}else{
				hql = "delete from CarType where type_id=?";
				this.baseDao.updateByHql(hql, type_id);
			}
		}
		public List<CarType> getBrandNodeAll(){
			String hql = "from CarType m where m.parent_id is null ";
			return (List<CarType>)this.baseDao.list(hql);
		}
		
		//添加汽车类型
		public void addChild(CarType carType){
			String hql = "select count(type_id) from CarType where type_name=? and parent_id is not null";
			Long count = (Long) this.baseDao.query(hql,carType.getType_name());
			if(count>0){
				throw new RbacException("该类型已经拥有，请重新填写");
			}else{
				this.baseDao.add(carType);
			}
		}
		
		public Long getCheckCarTypeName(String typeName) {
			String hql="select count(r.type_id) from CarType r where r.type_name =?";
			return (Long) this.baseDao.query(hql,typeName.trim());
		}

		public List<CarType> getEnableList() {
			String sql = "select mm.* from car_type mm where mm.type_status=1 and mm.parent_id is null";
			return (List<CarType>) this.baseDao.listSQL(sql, null, CarType.class, true);
		}
		public List<CharDatas> getCharDatas(){
			String sql = "select IFNULL(r.type_name,'无品牌类型') lable ,count(u.car_id) value from car_info u LEFT JOIN car_type  r on u.fk_carType_id=r.type_id GROUP BY r.type_name";
			return (List<CharDatas>) this.baseDao.listSQL(sql, CharDatas.class, false);
		}


		public CarType getChildCarType(Integer typeId) {
			String sql = "select mm.* from car_type mm where mm.type_id =?";
			return (CarType) this.baseDao.querySQL(sql, typeId, CarType.class, true);
		}

		public List<CarType> getTypeList(Integer typeId) {
			String sql = "select mm.* from car_type mm where mm.type_status=1 and mm.parent_id=?";
			return (List<CarType>) this.baseDao.listSQL(sql, typeId, CarType.class, true);
		}
		// 更改状态
		public void  updatestatus(Integer type_id){
			CarType carType=(CarType) this.baseDao.load(CarType.class, type_id);
			if(carType.getType_status().equals("1")){
				carType.setType_status("2");
			}else{
				carType.setType_status("1");
			}
			this.baseDao.update(carType);
		}

	


		
}
