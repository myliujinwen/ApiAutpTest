package com.lemon.api.auto.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.lemon.api.auto.pojo.TestData;

public class HttpUtil {


	public static Map<String,Object> Call(TestData testData) {
		String method=testData.getType();
		String result=null;
		if("get".equals(method)){
			return HttpUtil.doGet(testData);

		}else if("post".equals(method)){
			return HttpUtil.doPost(testData);
		}
		return null;

	}
	
	public static Map<String,Object> doGet(TestData testData){
		 Map<String,Object> resultMap=new HashMap<String,Object>();
		 String url=testData.getUrl();
		 String jsonString = testData.getParams();
		 String params=generateQueryParam(jsonString);
		 HttpGet httpGet = new HttpGet(url+"?"+params);
		 CloseableHttpClient httpClient = HttpClients.createDefault();
			if("1".equals(testData.getIsAccessControled())){
				AuthorizationUtil.addAuthrizationInfoBeforeRequest(httpGet);
			}
		 String result=null;
		 try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int code = response.getStatusLine().getStatusCode();
			Header[] allHeaders = response.getAllHeaders();
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			resultMap.put("code", code);
			resultMap.put("headers", allHeaders);
			resultMap.put("result",result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
		 return resultMap;
	}


	

	private static Map<String,Object>  doPost(TestData testData) {
		 Map<String,Object> resultMap=new HashMap<String,Object>();
		//1、获得httpposet并增加url
		String url=testData.getUrl();
		String jsonString = testData.getParams();
		String apiType=testData.getApiType();
		HttpPost httpPost=new HttpPost(url);

		//2、增加请求头
		if("form".equals(apiType)){
			String params=generateQueryParam(jsonString);
			httpPost.addHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8"));
			httpPost.setEntity(new StringEntity(params,"UTF-8"));
		}else if("json".equals(apiType)){
			AuthorizationUtil.addAuthrizationInfoBeforeRequest(httpPost);;
		}
			//增加参数
		
		//获得Http客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		if("1".equals(testData.getIsAccessControled())){
			String auth=AuthorizationUtil.cache.get("auth").toString();
			httpPost.addHeader("Cookie",auth );
		}
		String result=null;
		try {
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int code = response.getStatusLine().getStatusCode();
			Header[] allHeaders = response.getAllHeaders();
			result = EntityUtils.toString(response.getEntity());
			resultMap.put("code", code);
			resultMap.put("headers", allHeaders);
			resultMap.put("result",result);
			
			System.out.println("result:"+result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resultMap;		
	}



	public static String generateQueryParam(String jsonString) {
		// 通过fastJson框架把json字符串反系列化成一个map对象
		Map<String,Object> map = (Map<String, Object>) JSONObject.parse(jsonString);
		Set<String> keySet = map.keySet();
		StringBuilder sBuffer=new StringBuilder();
		String [] keyArr=new String[keySet.size()];
		keySet.toArray(keyArr);
		for(int i=0;i<keyArr.length;i++){
			sBuffer.append(keyArr[i]).append("=").append(map.get(keyArr[i])).append("&");			
		}		
		return sBuffer.substring(0, sBuffer.lastIndexOf("&"));
	}
	
	
}
