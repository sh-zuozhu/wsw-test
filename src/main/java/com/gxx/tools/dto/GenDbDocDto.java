package com.gxx.tools.dto;

import java.util.List;

import com.gxx.tools.base.vo.GenDbDocRequest;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	db文档生成对象
 *    </dd>
 *    <dt><b>Description:</b></dt>
 *    <dd>
 *    	<p>none
 *    </dd>
 * </dl>
 *
 * @author Gxx
 * @version 1.0, 2017年9月14日
 * @since tools
 */
public class GenDbDocDto extends BaseDto {
    private String tableSchema;//库
    
    private int pageSize;//每页个数
    private int activePage;//当前页数
    
    private List<GenDbDocRequest> list;//数据集合
    private long totalCount;//总条数
    private long totalPage;//总页数
    
	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getActivePage() {
		return activePage;
	}
	public void setActivePage(int activePage) {
		this.activePage = activePage;
	}
	public List<GenDbDocRequest> getList() {
		return list;
	}
	public void setList(List<GenDbDocRequest> list) {
		this.list = list;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
}