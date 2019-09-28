package com.lemon.api.auto.pojo;

public class DBValidator {
	private String order;
	private String sql;
	public DBValidator() {
		super();
	}
	public DBValidator(String order, String sql) {
		super();
		this.order = order;
		this.sql = sql;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	

}
