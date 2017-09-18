package com.gxx.tools.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxx.tools.base.core.Datasource;
import com.gxx.tools.base.dao.CommonMapper;
import com.gxx.tools.service.CommonService;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	基础服务实现类
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
@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Autowired
	CommonMapper commonMapper;
    
    /**
     * 查询所有库表
     * @param tableSchema
     * @return
     */
    public List<Map<String, String>> queryAllTables(Datasource datasource, String tableSchema) {
    	return commonMapper.queryAllTables(tableSchema);
    }
    
    /**
     * 查询所有库表
     * @param tableSchema
     * @param tableName
     * @return
     */
    public List<Map<String, String>> queryAllColumns(Datasource datasource, String tableSchema, String tableName) {
    	return commonMapper.queryAllColumns(tableSchema, tableName);
    }
    
    /**
     * 查询所有库
     * @return
     */
    public List<String> queryAllDatabases(Datasource datasource) {
    	return commonMapper.queryAllDatabases();
    }
}
