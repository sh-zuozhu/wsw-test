package com.gxx.tools.base.core;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	none
 *    </dd>
 *    <dt><b>Description:动态获取数据源KEY，继承父类AbstractRoutingDataSource</b></dt>
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
public class DynamicDataSource extends AbstractRoutingDataSource{  
  
	/**
	 * 获取数据源值KEY
	 */
    @Override  
    protected Object determineCurrentLookupKey() { 
        return DatabaseContextHolder.getCustomerType();   
    }  
  
}