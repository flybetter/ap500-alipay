package org.alipay.enums;

public enum AlipayStatEnum {
	PRESUCCESS(0,"支付前入库成功"),
	SUCCESS(1,"支付成功"),
	FAIL(-1,"支付失败"),
	INNER_ERROR(-2,"系统异常"),
	DATA_REWRITE(-3,"数据重复");
	
	public int state;
	
	public String stateInfo;

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

	private AlipayStatEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	
	
}
