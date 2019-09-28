package com.lemon.api.auto.cases;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.lemon.api.auto.util.DataUtil;

public class Register extends BaseCase{


	
	@DataProvider
	public Object [][] datas(ITestContext context){
		Object[][] providerDatas = DataUtil.getProviderDatas("1",context);
		
		return providerDatas;		
	}


}
