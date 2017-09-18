package com.gxx.tools.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.gxx.tools.base.vo.GenDbDocRequest;
import com.gxx.tools.dto.GenDbDocDto;

@Repository
public interface GenDbDocRequestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GenDbDocRequest request);

    int insertSelective(GenDbDocRequest request);

    GenDbDocRequest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GenDbDocRequest request);

    int updateByPrimaryKey(GenDbDocRequest request);
    
    /**
     * 查询生成db文档请求
     * @param monitorDto
     * @return
     */
	@SelectProvider(type=GenDbDocRequestSqlProvider.class,method="queryGenDbDocRequest")
    public List<GenDbDocRequest> queryGenDbDocRequest(GenDbDocDto genDbDocDto);
    
    /**
     * 查询生成db文档请求总数
     * @param monitorDto
     * @return
     */
	@SelectProvider(type=GenDbDocRequestSqlProvider.class,method="countGenDbDocRequest")
    public long countGenDbDocRequest(GenDbDocDto genDbDocDto);
}