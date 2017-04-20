package com.spring.boot.curd.client.pojo;

public class Response {
	private boolean isSuccess;
	private String status;

	public Response(boolean isSuccess, String status) {
		super();
		this.isSuccess = isSuccess;
		this.status = status;
	}

	public Response() {
		// TODO Auto-generated constructor stub
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
