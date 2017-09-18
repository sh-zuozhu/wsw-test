package com.gxx.tools.web.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gxx.tools.base.core.Datasource;
import com.gxx.tools.service.CommonService;

/**
 * 索引
 * @author gxx
 */
@Controller
@RequestMapping("/tools/")
public class IndexController {
	
	@Autowired
	CommonService commonService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		/**
		 * 查询所有库表
		 */
		List<String> databases = commonService.queryAllDatabases(Datasource.UAT);
		
		/**
		 * 将系统列表放入session
		 */
		request.getSession().setAttribute("sys_list", databases.toArray());
		return "index";
	}
	
	/**
	 * 生成db文档历史
	 * @return
	 */
	@RequestMapping(value = "/genDbDocHis", method = RequestMethod.GET)
	public String genDbDocHis(HttpServletRequest request) {
		/**
		 * 查询所有库表
		 */
		List<String> databases = commonService.queryAllDatabases(Datasource.UAT);
		
		/**
		 * 将系统列表放入session
		 */
		request.getSession().setAttribute("sys_list", databases.toArray());
		return "genDbDocHis";
	}
}