package com.lemon.api.auto.util;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lemon.api.auto.pojo.ResponseValidator;
import com.lemon.api.auto.pojo.TestData;

public class ResponseValidateUtil {
	//响应结果进行数据验证；通过/不通过
	public static String validateResponseData(TestData testData, Map<String, Object> resultMap) {
		//实际响应结果
		String result =(String) resultMap.get("result");
		//响应数据验证规则
		String responseValidators=testData.getResponseValidators();
		//验证规则对象集合
		List<ResponseValidator> validators = JSONObject.parseArray(responseValidators, ResponseValidator.class);
		//默认验证结果通过
		String validatorDateResult="通过";
		//根据每个规则验证，对实际结果进行验证
		for (ResponseValidator responseValidator : validators) {
			//获得验证的json表达式i
			String jsonPathExpression = responseValidator.getJsonPath();
			//根据表达式获得实际结果
			String actual=(String) JSONPath.read(result, jsonPathExpression);
			//获得期望结果
			String expected = responseValidator.getExpected();
			
			try {
				Assert.assertEquals(actual, expected);
			} catch (Error e) {
				validatorDateResult="不通过";
				break;
			}	
		}
		return validatorDateResult;
	}



}
