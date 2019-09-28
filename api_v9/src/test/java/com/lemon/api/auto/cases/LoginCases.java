package com.lemon.api.auto.cases;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.lemon.api.auto.util.DataUtil;


public class LoginCases extends BaseCase{
	
	@DataProvider
	public Object [][] datas(ITestContext context){
		//根据及接口号，上下文缓存，获得测试数据
		Object[][] providerDatas = DataUtil.getProviderDatas("2",context);
		
		return providerDatas;		
	}
	

}
