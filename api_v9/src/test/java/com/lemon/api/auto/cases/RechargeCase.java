package com.lemon.api.auto.cases;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.lemon.api.auto.util.DataUtil;

public class RechargeCase extends BaseCase{
	@DataProvider
	public Object [][] datas(ITestContext context){
		Object[][] providerDatas = DataUtil.getProviderDatas("3",context);
		
		return providerDatas;		
	}

}
