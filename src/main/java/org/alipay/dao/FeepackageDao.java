package org.alipay.dao;

import java.util.List;

import org.alipay.entity.Feepackage;
import org.apache.ibatis.annotations.Param;

public interface FeepackageDao {

	/**
	 * 
	* @Title: reduceStockCount 
	* @Description: 减少库存数量
	* @param @param feepackageId
	* @param @param leastPurchase
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws
	 */
	Integer reduceStockCount(@Param("feepackageId") Integer feepackageId,
			@Param("leastPurchase") Integer leastPurchase);

	/**
	 * 
	* @Title: queryAll 
	* @Description: 查询所有套餐
	* @param @param type
	* @param @param offset
	* @param @param limit
	* @param @return    设定文件 
	* @return List<Feepackage>    返回类型 
	* @throws
	 */
	List<Feepackage> queryAll(@Param("type") Integer type, 
			@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 
    * @Title: queryById 
    * @Description: 根据id查询套餐
    * @param @param feepackageId
    * @param @return    设定文件 
    * @return Feepackage    返回类型 
    * @throws
     */
	Feepackage queryById(@Param("feepackageId") Integer feepackageId);

	/**
	 * 
	* @Title: addSoldCount 
	* @Description: 添加已销售库存
	* @param @param feepackageId
	* @param @param leastPurchase
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws
	 */
	Integer addSoldCount(@Param("feepackageId") Integer feepackageId, @Param("leastPurchase") Integer leastPurchase);

	/**
	 * 
	* @Title: addStockCount 
	* @Description: 添加库存数量
	* @param @param feepackageId
	* @param @param leastPurchase
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws
	 */
	Integer addStockCount(@Param("feepackageId") Integer feepackageId, @Param("leastPurchase") Integer leastPurchase);

}
