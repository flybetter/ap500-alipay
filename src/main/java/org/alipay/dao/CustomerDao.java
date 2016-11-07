package org.alipay.dao;

import org.alipay.entity.Customer;

public interface CustomerDao {
	
	/**
	 * 
	* @Title: queryByDeviceAccountId 
	* @Description: 通过设备账号的id获得客户信息
	* @param @param userId
	* @param @return    设定文件 
	* @return Customer    返回类型 
	* @throws
	 */
	Customer queryByDeviceAccountId(Integer userId);

}
