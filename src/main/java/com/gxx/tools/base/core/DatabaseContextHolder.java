package com.gxx.tools.base.core;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	none
 *    </dd>
 *    <dt><b>Description:使用ThreadLocal设置和获取当前线程的数据源KEY</b></dt>
 *    <dd>
 *    	<p>none
 *    </dd>
 * </dl>
 *
 * @author Gxx
 * @version 1.0, 2016年1月12日
 * @since record
 *
 */
public class DatabaseContextHolder {
	/**
	 * ThreadLocal
	 */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 设置数据源KEY
     * @param customerType
     */
    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    /**
     * 获取数据源KEY
     * @return
     */
    public static String getCustomerType() {
        return contextHolder.get();  
    }

    /**
     * 清空数据源KEY
     */
    public static void clearCustomerType() {
        contextHolder.remove();
    }
}  