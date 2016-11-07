package org.alipay.dto;

import org.alipay.entity.Feepackage;
import org.alipay.enums.AlipayStatEnum;

public class AlipayExecution {
	
	private Integer feepackageId;
	//订单id
	private String orderNo;
	
	private Feepackage feepackage ; 
	
	private String form;
	
	//秒杀执行结果状态
	private int state;
	
	//秒杀表示
	private String stateInfo;
	
	public Feepackage getFeepackage() {
		return feepackage;
	}

	public void setFeepackage(Feepackage feepackage) {
		this.feepackage = feepackage;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public Integer getFeepackageId() {
		return feepackageId;
	}

	public void setFeepackageId(Integer feepackageId) {
		this.feepackageId = feepackageId;
	}


	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public AlipayExecution(Integer feepackageId, int state, String stateInfo) {
		super();
		this.feepackageId = feepackageId;
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public AlipayExecution(Feepackage feepackage, int state, String stateInfo) {
		super();
		this.feepackage = feepackage;
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public AlipayExecution(Feepackage feepackage, AlipayStatEnum alipayStatEnum) {
		super();
		this.feepackage = feepackage;
		this.state = alipayStatEnum.getState();
		this.stateInfo = alipayStatEnum.getStateInfo();
	}
	
	public AlipayExecution(String  form, AlipayStatEnum alipayStatEnum) {
		super();
		this.form = form;
		this.state = alipayStatEnum.getState();
		this.stateInfo = alipayStatEnum.getStateInfo();
	}
	
	
	public AlipayExecution(Feepackage feepackage,String orderNo, AlipayStatEnum alipayStatEnum) {
		super();
		this.orderNo = orderNo;
		this.state = alipayStatEnum.getState();
		this.stateInfo = alipayStatEnum.getStateInfo();
	}
	
	public AlipayExecution( AlipayStatEnum alipayStatEnum,String orderNo) {
		super();
		this.orderNo = orderNo;
		this.state = alipayStatEnum.getState();
		this.stateInfo = alipayStatEnum.getStateInfo();
	}

	@Override
	public String toString() {
		return "AlipayExecution [feepackageId=" + feepackageId + ", orderNo=" + orderNo + ", feepackage=" + feepackage
				+ ", form=" + form + ", state=" + state + ", stateInfo=" + stateInfo + "]";
	}

	

}
