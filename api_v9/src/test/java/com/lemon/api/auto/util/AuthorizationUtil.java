package com.lemon.api.auto.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;

import com.lemon.api.auto.pojo.Api;
import com.lemon.api.auto.pojo.Case;
import com.lemon.api.auto.pojo.TestData;

public class AuthorizationUtil {
	public static Map<String,Object> cache=new HashMap<String,Object>();
	

	public static void storeAuthorization(List<Api> apis, List<Case> cases) {
		
		String apiId = "2";
		String isPositive="1";
		TestData testData=new TestData();
		//获得鉴权的接口对象保存到testData中
		for(Api api:apis){
			if(apiId.equals(api.getApiId())){
				testData.setUrl(api.getUrl());
				testData.setType(api.getType());
				testData.setApiType(api.getApiType());
				break;
			}
		}
		//获得鉴权的用例对象保存到testData中
		for(Case cs:cases){
			if(apiId.equals(cs.getApiId())&&isPositive.equals(cs.getIsPositive())){
				
				testData.setParams(cs.getParams());
			}
		}	
		//根据tesetData请求接口获得响应值
		Map<String,Object> mapResult = HttpUtil.Call(testData);
		//获得相应数据的鉴权
		Header[] heads= (Header[]) mapResult.get("headers");
		for (Header header : heads) {
			if("Set-Cookie".equals(header.getName())&&header.getValue().contains("JSESSIONID")){
				String cookieValue=header.getValue();
				String[] parts=cookieValue.split(";");
				for (String part : parts) {
					if(part.contains("JSESSIONID")){
						AuthorizationUtil.cache.put("auth", part);
					}
					
				}
			}
		}
	}
	
	public static void addAuthrizationInfoBeforeRequest(HttpRequest httpRequest) {
		String auth=AuthorizationUtil.cache.get("auth").toString();
		httpRequest.addHeader("Cookie",auth );
	}
	
	
}
