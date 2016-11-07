package org.alipay.dto;

public class AlipayResult<T> {
	
	private boolean success;
	
	private T data;
	
	private String error;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public AlipayResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}

	public AlipayResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}
	
	

}
