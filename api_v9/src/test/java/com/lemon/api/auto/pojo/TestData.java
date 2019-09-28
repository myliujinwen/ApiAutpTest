package com.lemon.api.auto.pojo;

public class TestData {
	private String url;
	private String type;
	private String apiType;
	private String params;
	private String isAccessControled;
	private String responseValidators;
	private String caseId;
	private String dbValidators;
	
	public TestData() {
		super();
	}
	
	
	
	public TestData(String caseId,String url, String type, String apiType, String params, String isAccessControled,
			String responseValidators, String dbValidators) {
		super();
		this.url = url;
		this.caseId = caseId;
		this.type = type;
		this.apiType = apiType;
		this.params = params;
		this.isAccessControled = isAccessControled;
		this.responseValidators = responseValidators;
		this.dbValidators = dbValidators;
	}

	
	
	public String getDbValidators() {
		return dbValidators;
	}



	public void setDbValidators(String dbValidators) {
		this.dbValidators = dbValidators;
	}



	public String getCaseId() {
		return caseId;
	}



	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}



	public String getResponseValidators() {
		return responseValidators;
	}
	public void setResponseValidators(String responseValidators) {
		this.responseValidators = responseValidators;
	}
	public String getIsAccessControled() {
		return isAccessControled;
	}
	public void setIsAccessControled(String isAccessControled) {
		this.isAccessControled = isAccessControled;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApiType() {
		return apiType;
	}
	public void setApiType(String apiType) {
		this.apiType = apiType;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	@Override
	public String toString() {
		return "TestData [url=" + url + ", type=" + type + ", apiType=" + apiType + ", params=" + params + "]";
	}
	

}
