package com.lemon.api.auto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.lemon.api.auto.pojo.DBValidator;
import com.lemon.api.auto.pojo.QueryResult;
import com.lemon.api.auto.pojo.TestData;

public class DBUtil {
	
	//
	public static String queryValidateFields(TestData testData) {
		//获得表数据验证查询信息
		String dbValidators = testData.getDbValidators();
		//不为空进入表数据查询
		if(dbValidators!=null && dbValidators.trim().length()>0){
			//把表数据的json数据反系列化为DBValidator集合
			List<DBValidator> validators = JSONObject.parseArray(dbValidators, DBValidator.class);
			//创建一个查询结果集合;
			List<QueryResult> queryResults=new ArrayList<QueryResult>();
			//遍历DBValidator数据进行sql查询
			for (DBValidator dbValidator : validators) {
				//获得查询号
				String order=dbValidator.getOrder();
				//获得sql查询语句
				String sql=dbValidator.getSql();
				
				String url="jdbc:mysql://test.lemonban.com:3306/future?useUnicode=true&characterEncoding=utf-8";
				String user="test";
				String password="test";
				//创建一个map对象
				Map<String,Object> map=new HashMap<String,Object>();
				//1、创建连接
				try {
					//创建数据库连接
					Connection connection = DriverManager.getConnection(url, user, password);
					//获得PreparedStatement对象
					PreparedStatement statement = connection.prepareStatement(sql);
					//执行sql,获得结果集合
					ResultSet resultSet = statement.executeQuery();
					//获取元数据，（查询信息：查询字段名，查询字段的个数）
					ResultSetMetaData metaData = resultSet.getMetaData();
					//获得列数量
					int columnCount = metaData.getColumnCount();
					while(resultSet.next()){
						for(int i=1;i<=columnCount;i++){
							//获得查询结果的列名
							String columnName = metaData.getColumnName(i);
							//获得查询结果值
							Object value=resultSet.getObject(i);
							//把列名对象的值出入map中
							map.put(columnName, value);
						}

					}					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//2、获得statement对象
				//3
				//创建一个QuryResult对象
				QueryResult result=new QueryResult(order,map);
				//加入集合
				queryResults.add(result);
			}
			//系列化成json字符串
			String jsonArrayString=JSONObject.toJSONString(queryResults);
			//返回字符串
			return jsonArrayString;
		}
		return null;
	}


}
