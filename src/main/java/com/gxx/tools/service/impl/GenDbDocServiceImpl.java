package com.gxx.tools.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxx.tools.base.core.Datasource;
import com.gxx.tools.base.dao.GenDbDocRequestMapper;
import com.gxx.tools.base.vo.GenDbDocRequest;
import com.gxx.tools.dto.GenDbDocDto;
import com.gxx.tools.service.GenDbDocService;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	生成db文档服务实现类
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
@Service("genDbDocService")
public class GenDbDocServiceImpl implements GenDbDocService {

	@Autowired
	private GenDbDocRequestMapper genDbDocRequestMapper;

	/**
	 * 新增生成db文档请求
	 * @param datasource
	 * @param request
	 */
	public void doSaveGenDbDocRequest(Datasource datasource, GenDbDocRequest request) {
		genDbDocRequestMapper.insert(request);
	}
	
	/**
	 * 查询生成db文档请求
	 * @param datasource
	 * @param dto
	 */
	public List<GenDbDocRequest> doQueryGenDbDocRequest(Datasource datasource, GenDbDocDto genDbDocDto) {
		return genDbDocRequestMapper.queryGenDbDocRequest(genDbDocDto);
	}
	
	/**
	 * 新增生成db文档请求总数
	 * @param datasource
	 * @param dto
	 */
	public long doCountGenDbDocRequest(Datasource datasource, GenDbDocDto genDbDocDto) {
		return genDbDocRequestMapper.countGenDbDocRequest(genDbDocDto);
	}
}
