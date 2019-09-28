package com.lemon.api.auto.pojo;

public class ResponseValidator {
	private String jsonPath;
	private String expected;
	
	
	public ResponseValidator() {
		super();
	}
	public ResponseValidator(String jsonPath, String expected) {
		super();
		this.jsonPath = jsonPath;
		this.expected = expected;
	}
	public String getJsonPath() {
		return jsonPath;
	}
	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}
	public String getExpected() {
		return expected;
	}
	public void setExpected(String expected) {
		this.expected = expected;
	}
	
	
}
