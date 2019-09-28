package com.lemon.api.auto.util;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;

import com.lemon.api.auto.pojo.Api;
import com.lemon.api.auto.pojo.Case;
import com.lemon.api.auto.pojo.TestData;

public class DataUtil {

	public static Object[][] getProviderDatas(String apiId, ITestContext context) {
		//根据用例编号，上下文缓存获得用例对象集合
		List<Case> cases = getWantedCaseList(apiId, context);			
		//获得接口
		Api api = getWantedApi(apiId, context);
		//把用例及接口数据保存到测试数据
		String url= api.getUrl();
		String type=api.getType();
		String apiType=api.getApiType();
		String isAccessControled = api.getIsAccessControled();
		Object[][] datas = new Object[cases.size()][1];
		for(int i=0;i<cases.size();i++){			
			Case cs = cases.get(i);
			String caseId=cs.getCaseId();
			String params=cs.getParams();
			String responseValidators=cs.getResponseValidators();
			String dbValidators = cs.getDbValidators();
			TestData testData=new TestData(caseId,url,type,apiType,params,isAccessControled,responseValidators,dbValidators);
			datas[i][0]=testData;
			
		}
		return datas;
	}

	public static List<Case> getWantedCaseList(String apiId, ITestContext context) {
		List<Case> cases = (List<Case>) context.getAttribute("cases");
		List<Case> wantedCaseList= new ArrayList();

		for(Case cs:cases){
			if(apiId.equals(cs.getApiId())){
				wantedCaseList.add(cs);
			}
			
		}
		return wantedCaseList;
	}

	public static Api getWantedApi(String apiId, ITestContext context) {
		List<Api> apis = (List<Api>) context.getAttribute("apis");
		for(Api api:apis){
			if(apiId.equals(api.getApiId())){
				return api;
			}
		}
		return null;
	}
	

}
