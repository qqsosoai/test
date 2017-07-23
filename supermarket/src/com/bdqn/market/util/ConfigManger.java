package com.bdqn.market.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManger {
	private Properties properties;
	private static class Config{
		private static ConfigManger manger=new ConfigManger();
	}
	private ConfigManger(){
		properties=new Properties();
		InputStream is=ConfigManger.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//获取对象接口
	public static synchronized ConfigManger getInstance(){
		return Config.manger;
	}
	//获取配置文件的值
	public String getValue(String key){
		return properties.getProperty(key);
	}
}
