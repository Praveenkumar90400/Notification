package com.gts.notification.model.response;

public class JsonResponseModel {

	private String success;
	
	private String message;
	
	private String status_code;
	

	public JsonResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JsonResponseModel(String message, String success, String status_code) {
		super();
		
		this.message = message;
		this.success = success;
		this.status_code = status_code;	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}



      
	
	
	
	
	
}
