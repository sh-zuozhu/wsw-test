package com.gxx.tools.base.dao;

import org.apache.commons.lang3.StringUtils;

import com.gxx.tools.dto.GenDbDocDto;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	none
 *    </dd>
 *    <dt><b>Description:生成db文档请求查询SQL提供类</b></dt>
 *    <dd>
 *    	<p>none
 *    </dd>
 * </dl>
 *
 * @author Gxx
 * @version 1.0, 2017年9月14日
 * @since tools
 *
 */
public class GenDbDocRequestSqlProvider {
	/**
	 * 查询生成db文档请求
	 * @param genDbDocDto
	 * @return
	 */
	public String queryGenDbDocRequest(GenDbDocDto genDbDocDto){
		String sql = "SELECT * FROM gen_db_doc_request WHERE 1=1";
		//方法路径
		if(StringUtils.isNotBlank(genDbDocDto.getTableSchema())){
			sql += " AND DATABASE_NAME = '" + (genDbDocDto.getTableSchema()) + "'";
		}
		//id倒序
		sql += " ORDER BY ID DESC";
		//分页
		if(genDbDocDto.getActivePage() > 0 && genDbDocDto.getPageSize() > 0){
			sql += " LIMIT " + (genDbDocDto.getActivePage()-1)*genDbDocDto.getPageSize() + "," + genDbDocDto.getPageSize();
		}
		return sql;
	}
	
	/**
	 * 查询生成db文档请求总数
	 * @param monitorDto
	 * @return
	 */
	public String countGenDbDocRequest(GenDbDocDto genDbDocDto){
		String sql = "SELECT COUNT(1) FROM gen_db_doc_request WHERE 1=1";
		//方法路径
		if(StringUtils.isNotBlank(genDbDocDto.getTableSchema())){
			sql += " AND DATABASE_NAME = '" + (genDbDocDto.getTableSchema()) + "'";
		}
		return sql;
	}
}