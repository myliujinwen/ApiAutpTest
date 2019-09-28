package com.lemon.api.auto.pojo;

import java.util.Map;

public class QueryResult {
	private String order;
	private Map<String,Object> result;
	public QueryResult() {
		super();
	}
	public QueryResult(String order, Map<String, Object> result) {
		super();
		this.order = order;
		this.result = result;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	

}
