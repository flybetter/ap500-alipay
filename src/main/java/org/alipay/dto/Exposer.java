package org.alipay.dto;

public class Exposer {
	
	public String md5;
	
	public Integer feepackageId;

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Integer getFeepackageId() {
		return feepackageId;
	}

	public void setFeepackageId(Integer feepackageId) {
		this.feepackageId = feepackageId;
	}

	public Exposer(String md5, Integer feepackageId) {
		super();
		this.md5 = md5;
		this.feepackageId = feepackageId;
	}

	@Override
	public String toString() {
		return "Exposer [md5=" + md5 + ", feepackageId=" + feepackageId + "]";
	}
	
	

}
