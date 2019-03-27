/**
 * Copyright 2018-2020 yonyou.com.
 * All rights reserved.
 */
package com.yonyou.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * TODO
 * 
 * @Author:xiongcz
 * @version $id:BeanUtils.java,v0.1 2018年6月14日 下午2:42:56 xiongcz Exp$
 */
public class BeanUtil {

	public static Map<String, Object> bean2Map(Object obj){

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());// 在 Java Bean 上进行内省，了解其所有属性、公开的方法和事件
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  // 获得 beans PropertyDescriptor
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName(); // 获得所有对象属性值得名字
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);//调用方法
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String, String> bean2StringMap(Object obj){

		Map<String, Object> map = bean2Map(obj);
		Map<String, String> smap = new HashMap<>();

		Iterator<Entry<String, Object>> it = map.entrySet().iterator();//map数组转化为set数组，并进行迭代遍历
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			if (entry.getValue() != null) {
				String value = "";
				value = (String) entry.getValue();
				smap.put(entry.getKey(), value);
			}
		}
		return smap;
	}
}
