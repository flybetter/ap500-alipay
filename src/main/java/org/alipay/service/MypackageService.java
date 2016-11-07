package org.alipay.service;

import java.util.List;

import org.alipay.entity.Mypackage;

public interface MypackageService {
	
	/**
	 * 
	* @Title: getMypackageListByType 
	* @Description: 通过type获得所有的我的套餐
	* @param @param userId
	* @param @param type
	* @param @return    设定文件 
	* @return List<Mypackage>    返回类型 
	* @throws
	 */
	List<Mypackage> getMypackageListByType(Integer userId,Integer type);
	
	
	/**
	 * 
	* @Title: insertMypackage 
	* @Description: 通过userId和feepackageId插入我的套餐
	* @param @param userId
	* @param @param feepackageId
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws
	 */
	Integer insertMypackage(Integer userId,Integer feepackageId);

}
