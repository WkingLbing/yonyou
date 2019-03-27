/**
 * Copyright 2018-2020 yonyou.com.
 * All rights reserved.
 */
package com.yonyou.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yonyou.util.PosPayment;

/**
 * pos签名工具类
 * 
 * @Author:zhangyu
 * @version $id:PaymentUtils.java,v0.1 2018年6月9日 下午2:58:37 zhangyu Exp$
 */
public class PosSignUtils {

	public static Logger logger = LoggerFactory.getLogger(PosSignUtils.class);

	/**
	 * 生成签名
	 * 
	 * @param parm 参数map
	 * @param channelPrivateKey 渠道私钥
	 * @return :签名字符串
	 * @author: zhangyu v0.1 2018年6月11日
	 */
	private static String generatePosPaymentSign(Map<String, Object> parm, String channelPrivateKey) {

		String sign = "";
		if (null != parm) {
			Collection<String> keyset = parm.keySet();
			ArrayList<String> list = new ArrayList<String>(keyset);
			Collections.sort(list);
			for (int i = 0; i < list.size(); i++) {
				sign += list.get(i) + "=" + parm.get(list.get(i));
			}
			sign = bytesToMD5((bytesToMD5(sign.getBytes()) + channelPrivateKey).getBytes());
		}
		return sign;
	}

	private static String bytesToMD5(byte[] input) {

		String md5str = null;
		try {
			// 创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算后获得字节数组
			byte[] buff = md.digest(input);
			// 把数组每一字节换成16进制连成md5字符串
			md5str = bytesToHex(buff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}

	private static String bytesToHex(byte[] bytes) {

		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];
			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString();
	}

	public static String generateSign(Object obj, String channelPrivateKey) {

		Map<String, Object> map = null;
		try {
			map = PropertyUtils.describe(obj);
		} catch (Exception e) {
			logger.error("payment - pos generateSign error!");
		}
		map.remove("class");
		map.remove(PosPayment.PAYMENT_POS_SIGN_KEY);
		logger.info("posPayment - to sign param [{}]", map);
		return generatePosPaymentSign(map, channelPrivateKey);
	}

}
