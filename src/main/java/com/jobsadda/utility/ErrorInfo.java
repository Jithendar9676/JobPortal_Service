package com.jobsadda.utility;

import java.time.LocalDateTime;

public class ErrorInfo {

	private String message;
	private Integer code;
	private LocalDateTime timestamp;
	public ErrorInfo(String message, Integer code, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.code = code;
		this.timestamp = timestamp;
	}
	public ErrorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "ErrorInfo [message=" + message + ", code=" + code + ", timestamp=" + timestamp + "]";
	}
	
	

}
