/**
 * StringToDateConverter.java 
 * Copyright © 2009-2015, 杭州跃度网络科技有限公司
 * 
 * @author liao
 * @create 2015年2月3日
 */
package com.chuck.shop.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 *  日期编辑器 根据日期字符串长度判断是长日期还是短日期。支持yyyy-MM-dd，yyyy-MM-dd HH:mm:ss,yyyy-MM-dd HH:mm,yyyy-MM-dd HH格式。
 */
public class StringToDateConverter implements Converter<String, Date>{

	/**
	 * 短类型日期长度
	 */
	public static final int SHORT_DATE = 10;

	public static final int YYYYMM_DATE = 7;

	@Override
	public Date convert(String source) {
		if (StringUtils.isBlank(source)) {
			return null;
		}

		source = source.trim();
		if (!StringUtils.hasText(source)) {
			return null;
		}

		Date date = null;
		try {
			if (source.length() <= YYYYMM_DATE) {
				date = new java.sql.Date(DF_YYYYMM.parse(source).getTime());
			} else if (source.length() <= SHORT_DATE) {
				date = new java.sql.Date(DF_SHORT.parse(source).getTime());
			} else if (source.length() == 16) {
				date = new java.sql.Timestamp(DF_MIN.parse(source).getTime());
			} else if (source.length() == 13) {
				date = new java.sql.Timestamp(DF_HOUR.parse(source).getTime());
			} else {
				date = new java.sql.Timestamp(DF_LONG.parse(source).getTime());
			}
		} catch (ParseException ex) {
			IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());
			iae.initCause(ex);
			throw iae;
		}

		return date;
	}

	public static final DateFormat DF_HOUR = new SimpleDateFormat("yyyy-MM-dd HH");
	public static final DateFormat DF_MIN = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final DateFormat DF_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat DF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat DF_YYYYMM = new SimpleDateFormat("yyyy-MM");
}
