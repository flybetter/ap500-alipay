package org.alipay.enums;

public enum FeepackageTypeEnum {
	
	//0主体 1增值 2活动 3补充 4服务
	
	SUBJECT(0,"主体"),
	ADDEDVALUE(1,"增值 "),
	ACTIVITY(2,"活动"),
	SUPPLEMENT(3,"补充"),
	SERVICE(4,"服务");
	
	public Integer value;
	
	public String name;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private FeepackageTypeEnum(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	
	
}
