package com.gxx.tools.base.core;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	none
 *    </dd>
 *    <dt><b>Description:数据源切面拦截器</b></dt>
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
@Component  
public class DataSourceInterceptor {
	/**
	 * 设置数据源KEy
	 * @param jp
	 */
    public void setDataSource(JoinPoint jp) {
    	/**
    	 * 清空数据源KEY
    	 */
    	DatabaseContextHolder.clearCustomerType();
    	/**
    	 * 这里不知道为啥，使用注册方式注解方法或者入参，这里取不到注解，才改成入参送指定枚举的方式
    	 */
    	for(Object param : jp.getArgs()){
    		if(param instanceof Datasource){
    			Datasource datasource = (Datasource)param;
    			DatabaseContextHolder.setCustomerType(datasource.name());
    		}
    	}
    }
}