package org.alipay.entity;
// Generated 2016-7-22 15:39:21 by Hibernate Tools 3.6.0.Final

import java.util.Date;

/**
 * Deviceaccount generated by hbm2java
 */
public class DeviceAccount {

	private Long id;
	private String userName;
	private String simNum;
	private String serialNo;
	private Date createTime;
	private String creator;
	private Date lastUpdateTime;
	private Long customerId;
	private Character createWay;

	public DeviceAccount() {
	}

	public DeviceAccount(String simNum, String serialNo) {
		this.simNum = simNum;
		this.serialNo = serialNo;
	}

	public DeviceAccount(String userName, String simNum, String serialNo, Date createTime, String creator,
			Date lastUpdateTime, Long customerId, Character createWay) {
		this.userName = userName;
		this.simNum = simNum;
		this.serialNo = serialNo;
		this.createTime = createTime;
		this.creator = creator;
		this.lastUpdateTime = lastUpdateTime;
		this.customerId = customerId;
		this.createWay = createWay;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSimNum() {
		return this.simNum;
	}

	public void setSimNum(String simNum) {
		this.simNum = simNum;
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Character getCreateWay() {
		return this.createWay;
	}

	public void setCreateWay(Character createWay) {
		this.createWay = createWay;
	}

}