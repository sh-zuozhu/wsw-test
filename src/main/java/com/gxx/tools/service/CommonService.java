package com.gxx.tools.service;

import java.util.List;
import java.util.Map;

import com.gxx.tools.base.core.Datasource;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	生成db文档服务
 *    </dd>
 *    <dt><b>Description:</b></dt>
 *    <dd>
 *    	<p>none
 *    </dd>
 * </dl>
 *
 * @author Administrator
 * @version 1.0, 2017年9月14日
 * @since tools
 *
 */
public interface CommonService {
    
    /**
     * 查询所有库表
     * @param tableSchema
     * @return
     */
    List<Map<String, String>> queryAllTables(Datasource datasource, String tableSchema);
    
    /**
     * 查询所有库表
     * @param tableSchema
     * @param tableName
     * @return
     */
    List<Map<String, String>> queryAllColumns(Datasource datasource, String tableSchema, String tableName);
    
    /**
     * 查询所有库
     * @return
     */
    List<String> queryAllDatabases(Datasource datasource);
}
