package org.alipay.dao;

import java.util.Date;
import java.util.List;

import org.alipay.entity.Mypackage;
import org.apache.ibatis.annotations.Param;

public interface MypackageDao {

	/**
	 * 
	* @Title: queryByUserIdAndPackageType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @param packageType
	* @param @return    设定文件 
	* @return List<Mypackage>    返回类型 
	* @throws
	 */
	List<Mypackage> queryByUserIdAndPackageType(@Param("userId") Integer userId,
			@Param("packageType") Integer packageType);

	/**
	 * 
	* @Title: insertMypackage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param feepackageId
	* @param @param startTime
	* @param @param endTime
	* @param @param packageType
	* @param @param chargingWay
	* @param @param userId
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws
	 */
	Integer insertMypackage(@Param("feepackageId") Integer feepackageId, @Param("startTime") Date startTime,
			@Param("endTime") Date endTime, @Param("packageType") Integer packageType,
			@Param("chargingWay") Integer chargingWay, @Param("userId") Integer userId);
	
	
	void deleteMypackage();

}
