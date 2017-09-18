package com.gxx.tools.service;

import java.util.List;

import com.gxx.tools.base.core.Datasource;
import com.gxx.tools.base.vo.GenDbDocRequest;
import com.gxx.tools.dto.GenDbDocDto;

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
public interface GenDbDocService {
	/**
	 * 新增生成db文档请求
	 * @param datasource
	 * @param request
	 */
	public void doSaveGenDbDocRequest(Datasource datasource, GenDbDocRequest request);
	/**
	 * 查询生成db文档请求
	 * @param datasource
	 * @param dto
	 */
	public List<GenDbDocRequest> doQueryGenDbDocRequest(Datasource datasource, GenDbDocDto dto);
	/**
	 * 新增生成db文档请求总数
	 * @param datasource
	 * @param dto
	 */
	public long doCountGenDbDocRequest(Datasource datasource, GenDbDocDto dto);
}
