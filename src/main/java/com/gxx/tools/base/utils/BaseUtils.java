package com.gxx.tools.base.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/** 
 * 基础工具类
 * @author Gxx
 */
public class BaseUtils {


	/**
	 * 得到远程访问地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {

		String ret = StringUtils.EMPTY;

		String xForwardedForIp = request.getHeader("x-forwarded-for");
		String proxyClientIp = request.getHeader("Proxy-Client-IP");
		String wlProxyClientIp = request.getHeader("WL-Proxy-Client-IP");
		String remoteAddr = request.getRemoteAddr();

		ret = xForwardedForIp;
		if (StringUtils.isNotEmpty(ret)) {
			ret = ret.trim();
		}
		if (StringUtils.isBlank(ret)) {
			ret = proxyClientIp;
			if (StringUtils.isNotEmpty(ret)) {
				ret = ret.trim();
			}
		}
		if (StringUtils.isBlank(ret)) {
			ret = wlProxyClientIp;
			if (StringUtils.isNotEmpty(ret)) {
				ret = ret.trim();
			}
		}
		if (StringUtils.isBlank(ret)) {
			ret = remoteAddr;
			if (StringUtils.isNotEmpty(ret)) {
				ret = ret.trim();
			}
		}
		return ret;
	}
}
