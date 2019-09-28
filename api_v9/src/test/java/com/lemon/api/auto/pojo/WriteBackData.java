package com.lemon.api.auto.pojo;

public class WriteBackData {
	private String caseId;
	private String cellName;
	private String content;
	public WriteBackData() {
		super();
	}
	public WriteBackData(String caseId, String cellName, String content) {
		super();
		this.caseId = caseId;
		this.cellName = cellName;
		this.content = content;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "WriteBackData [caseId=" + caseId + ", cellName=" + cellName + ", content=" + content + "]";
	}
	
	

}
