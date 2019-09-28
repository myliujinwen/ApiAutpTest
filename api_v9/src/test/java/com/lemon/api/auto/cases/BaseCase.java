package com.lemon.api.auto.cases;





import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.lemon.api.auto.pojo.Api;
import com.lemon.api.auto.pojo.Case;
import com.lemon.api.auto.pojo.DBValidator;
import com.lemon.api.auto.pojo.QueryResult;
import com.lemon.api.auto.pojo.TestData;
import com.lemon.api.auto.util.AuthorizationUtil;
import com.lemon.api.auto.util.DBUtil;
import com.lemon.api.auto.util.ExcelUtil;
import com.lemon.api.auto.util.HttpUtil;
import com.lemon.api.auto.util.ResponseValidateUtil;



public class BaseCase {
	@BeforeSuite
	public void initData(ITestContext context){
		//获得接口对象集合
		List<Api> apis=ExcelUtil.readPojos(Api.class,0);
		//获得用例对象集合
		List<Case> cases=ExcelUtil.readPojos(Case.class,1);
		//保存对象集合到testng上下文缓存中
		context.setAttribute("apis", apis);
		context.setAttribute("cases", cases);
		
		//保存鉴权数据
		AuthorizationUtil.storeAuthorization(apis, cases);
		
	}


	
	
	@Test(dataProvider="datas")
	public void test1(TestData testData){
		//用例执行前进行表数据查询
		String dbQueryResult = DBUtil.queryValidateFields(testData);
		if(dbQueryResult!=null){
			//输入不为空写时，存取查询结果的数据
			ExcelUtil.saveWriteBackData(testData.getCaseId(), "QueryResultBefore", dbQueryResult);
		}
		//获得响应结果
		Map<String, Object> resultMap = HttpUtil.Call(testData);
		//获得响应的校验结果
		String  validatorDateResult = ResponseValidateUtil.validateResponseData(testData, resultMap);
		System.out.println(validatorDateResult);
		String cellName="ResponseValidationResult";
		String caseId=testData.getCaseId();		
		//保存校验结果
		ExcelUtil.saveWriteBackData(caseId,cellName,validatorDateResult);
		
		
		String cellName2="ActualResponseData";
		String result=(String) resultMap.get("result");
		ExcelUtil.saveWriteBackData(caseId,cellName2,result);
		//请求后获得查询结果
		String afterDBQueryResult =DBUtil.queryValidateFields(testData);
		if(afterDBQueryResult!=null){
			//保存查询结果
			ExcelUtil.saveWriteBackData(testData.getCaseId(), "QueryResultAfter", afterDBQueryResult);
		}
	}



	@AfterSuite
	public void finish(){
		System.out.println("开始");
		
		ExcelUtil.batchWriteBackDatas();

	}
	

}
