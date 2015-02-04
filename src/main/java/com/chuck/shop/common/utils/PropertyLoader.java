/**
 * PropertyLoader.java 
 * Copyright © 2009-2015, 杭州跃度网络科技有限公司
 * 
 * @author Liu Tao
 * @create 2014年10月14日
 */
package com.chuck.shop.common.utils;

import java.util.Properties;

/**
 * Properties文件读取
 */
public class PropertyLoader {

	private static String location = null;
	
	public static void setPropLocation(String location) {
		PropertyLoader.location = location;
	}
	
	public static Properties getProperties(String fileName){
		return PropertiesUtils.loadProperties(location + fileName);
	}
	
}
