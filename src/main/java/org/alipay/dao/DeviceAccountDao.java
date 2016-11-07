package org.alipay.dao;

import org.alipay.entity.DeviceAccount;
import org.apache.ibatis.annotations.Param;

public interface DeviceAccountDao {
	
	/**
	 * 
	* @Title: queryById 
	* @Description: 通过userId 获得设备账户
	* @param @param userId
	* @param @return    设定文件 
	* @return DeviceAccount    返回类型 
	* @throws
	 */
	DeviceAccount queryById(@Param("userId")Integer userId);

}
