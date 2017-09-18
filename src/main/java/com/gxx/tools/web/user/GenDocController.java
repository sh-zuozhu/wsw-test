package com.gxx.tools.web.user;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxx.tools.base.core.Datasource;
import com.gxx.tools.base.dao.CommonMapper;
import com.gxx.tools.base.interfaces.BaseInterface;
import com.gxx.tools.base.utils.BaseUtils;
import com.gxx.tools.base.utils.DateUtils;
import com.gxx.tools.base.utils.PropertyUtil;
import com.gxx.tools.base.vo.GenDbDocRequest;
import com.gxx.tools.dto.GenDbDocDto;
import com.gxx.tools.service.CommonService;
import com.gxx.tools.service.GenDbDocService;

/**
 * db文档生成类
 * @author gxx
 */
@Controller
@RequestMapping("/gendoc/")
public class GenDocController {
	/**
	 * 日志处理器
	 */
	private final Logger logger = Logger.getLogger(GenDocController.class);
	
	@Autowired
	GenDbDocService genDbDocService;
	
	@Autowired
	CommonService commonService;
	
	/**
	 * 生成文档
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gendoc", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody Map<String, Object> gendoc(HttpServletRequest request) throws Exception {
		/**
		 * 定义结果集
		 */
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String tableSchema = request.getParameter("tableSchema");
		String nickName = request.getParameter("nickName");
		logger.info("生成文档开始,数据库:[" + tableSchema + "][" + nickName + "]!");
		if(StringUtils.isBlank(tableSchema)) {
			resultMap.put("isSuccess", false);
			resultMap.put("message", "请先选择系统！");
			return resultMap;
		}
		if(StringUtils.isBlank(nickName)) {
			resultMap.put("isSuccess", false);
			resultMap.put("message", "请输入昵称！");
			return resultMap;
		}
		nickName = nickName.trim();
		/**
		 * 文件
		 */
		String fileName = tableSchema + "数据库文档-" + DateUtils.getCurrentDate() + "_" + DateUtils.getCurrentTime() + ".xls";
		String fileRoute = PropertyUtil.getInstance().getProperty(BaseInterface.DB_DOC_FILE_DIR) + fileName;
		String fileUrl = PropertyUtil.getInstance().getProperty(BaseInterface.DB_DOC_FILE_URL_PRE) + fileName;
		
		FileOutputStream fos = new FileOutputStream(fileRoute);
		HSSFWorkbook workBook = new HSSFWorkbook();
		/**
		 * 创建两个sheet
		 */
		workBook.createSheet();
		workBook.createSheet();
		workBook.setSheetName(0, "表", HSSFWorkbook.ENCODING_UTF_16);
		workBook.setSheetName(1, "表结构", HSSFWorkbook.ENCODING_UTF_16);
		/**
		 * 第一个sheet
		 */
		HSSFSheet sheet = workBook.getSheetAt(0);
		sheet.setColumnWidth((short)0, (short)(40 * 256));
		sheet.setColumnWidth((short)1, (short)(30 * 256));
		
		short rowIndex = 0;
		
		/**
		 * 首行
		 */
		HSSFRow headerRow = sheet.createRow((short) rowIndex++);
		
		HSSFCellStyle cellStyle = createHSSFCellStyle(workBook, "table_title");
		
        HSSFCell headerCell = headerRow.createCell((short) 0);
        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
        headerCell.setCellStyle(cellStyle);
        headerCell.setCellValue("表");
        headerCell = headerRow.createCell((short) 1);
        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
        headerCell.setCellStyle(cellStyle);
        headerCell.setCellValue("名称");
        
        /**
         * 查询所有表和表名
         */
		List<Map<String, String>> list = commonService.queryAllTables(Datasource.UAT, tableSchema);
		logger.info("表	名称");
		for(Map<String, String> map : list) {
			String tableName = map.get("table_name");
			String tableComment = map.get("table_comment");
			logger.info(tableName + "	" + tableComment);
			/**
			 * 内容行:所有表和表名
			 */
			headerRow = sheet.createRow((short) rowIndex++);
			
			HSSFCellStyle cellStyle2 = createHSSFCellStyle(workBook, "table");
			
	        headerCell = headerRow.createCell((short) 0);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setCellStyle(cellStyle2);
	        headerCell.setCellValue(tableName);
	        headerCell = headerRow.createCell((short) 1);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setCellStyle(cellStyle2);
	        headerCell.setCellValue(tableComment);
		}
		logger.info("");
		/**
		 * 第二个sheet
		 */
		sheet = workBook.getSheetAt(1);
		sheet.setColumnWidth((short)0, (short)(40 * 256));
		sheet.setColumnWidth((short)1, (short)(40 * 256));
		sheet.setColumnWidth((short)2, (short)(20 * 256));
		sheet.setColumnWidth((short)3, (short)(30 * 256));
		sheet.setColumnWidth((short)4, (short)(20 * 256));
		sheet.setColumnWidth((short)5, (short)(40 * 256));
		sheet.setColumnWidth((short)6, (short)(20 * 256));
		
		rowIndex = 0;
		for(Map<String, String> map : list) {
			String tableName = map.get("table_name");
			String tableComment = map.get("table_comment");
			logger.info("表:[" + tableName + "],表名称:[" + tableComment + "]");
			
			HSSFCellStyle cellStyle1 = createHSSFCellStyle(workBook, "table_title");
			HSSFCellStyle cellStyle2 = createHSSFCellStyle(workBook, "table");
			HSSFCellStyle cellStyle3 = createHSSFCellStyle(workBook, "column_title");
			HSSFCellStyle cellStyle4 = createHSSFCellStyle(workBook, "column");
			HSSFCellStyle cellStyle5 = createHSSFCellStyle(workBook, "column_detail");
			
			/**
			 * 该表的第一行
			 */
			headerRow = sheet.createRow((short) rowIndex++);
			
	        headerCell = headerRow.createCell((short) 0);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle1);
	        headerCell.setCellValue("表:");
	        headerCell = headerRow.createCell((short) 1);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle2);
	        headerCell.setCellValue(tableName);
	        headerCell = headerRow.createCell((short) 2);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle1);
	        headerCell.setCellValue("表名称:");
	        headerCell = headerRow.createCell((short) 3);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle2);
	        headerCell.setCellValue(tableComment);
	        
			logger.info("==================");
			/**
			 * 该表的第二行
			 */
			headerRow = sheet.createRow((short) rowIndex++);
			
	        headerCell = headerRow.createCell((short) 0);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle3);
	        headerCell.setCellValue("列");
	        headerCell = headerRow.createCell((short) 1);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle3);
	        headerCell.setCellValue("列名称");
	        headerCell = headerRow.createCell((short) 2);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle3);
	        headerCell.setCellValue("类型");
	        headerCell = headerRow.createCell((short) 3);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle3);
	        headerCell.setCellValue("可空");
	        headerCell = headerRow.createCell((short) 4);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle3);
	        headerCell.setCellValue("默认");
	        headerCell = headerRow.createCell((short) 5);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle3);
	        headerCell.setCellValue("附加");
	        headerCell = headerRow.createCell((short) 6);
	        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        headerCell.setCellStyle(cellStyle3);
	        headerCell.setCellValue("索引");
	        
			List<Map<String, String>> columns = commonService.queryAllColumns(Datasource.UAT, tableSchema, tableName);
			for(Map<String, String> columnMap : columns) {
				String field = columnMap.get("Field");
				String comment = columnMap.get("Comment");
				String type = columnMap.get("Type");
				String nullEle = columnMap.get("Null");
				String defaultValue = columnMap.get("Default");
				String extra = columnMap.get("Extra");
				String key = columnMap.get("Key");
				logger.info("列:[" + field + "],列名称:[" + comment + "],类型:[" + type + "],可空:[" + nullEle + "],"
						+ "默认:[" + defaultValue + "],附加:[" + extra + "],索引:[" + key + "]");
				/**
				 * 该表第三行开始的内容行
				 */
				headerRow = sheet.createRow((short) rowIndex++);
				
		        headerCell = headerRow.createCell((short) 0);
		        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        headerCell.setCellStyle(cellStyle4);
		        headerCell.setCellValue(field);
		        headerCell = headerRow.createCell((short) 1);
		        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        headerCell.setCellStyle(cellStyle5);
		        headerCell.setCellValue(comment);
		        headerCell = headerRow.createCell((short) 2);
		        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        headerCell.setCellStyle(cellStyle5);
		        headerCell.setCellValue(type);
		        headerCell = headerRow.createCell((short) 3);
		        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        headerCell.setCellStyle(cellStyle5);
		        headerCell.setCellValue(nullEle);
		        headerCell = headerRow.createCell((short) 4);
		        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        headerCell.setCellStyle(cellStyle5);
		        headerCell.setCellValue(defaultValue);
		        headerCell = headerRow.createCell((short) 5);
		        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        headerCell.setCellStyle(cellStyle5);
		        headerCell.setCellValue(extra);
		        headerCell = headerRow.createCell((short) 6);
		        headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
		        headerCell.setCellStyle(cellStyle5);
		        headerCell.setCellValue(key);
			}
			logger.info("==================");
	        
	        /**
	         * 新建空行
	         */
	        sheet.createRow((short) rowIndex++);
		}
		workBook.write(fos);
		fos.flush();
		fos.close();
		logger.info("文件生成结束:[" + fileName + "]");
		
		/**
		 * 新增生成db文档请求
		 */
		GenDbDocRequest genDbDocRequest = new GenDbDocRequest();
		genDbDocRequest.setSysDate(DateUtils.getCurrentDate());
		genDbDocRequest.setSysTime(DateUtils.getCurrentTime());
		genDbDocRequest.setNickName(nickName);
		genDbDocRequest.setIp(BaseUtils.getRemoteAddr(request));
		genDbDocRequest.setDatabaseName(tableSchema);
		genDbDocRequest.setFileRoute(fileRoute);
		genDbDocRequest.setFileUrl(fileUrl);
		genDbDocRequest.setIsDelete(0);
		genDbDocRequest.setCreatedAt(DateUtils.getNow());
		genDbDocRequest.setUpdatedAt(DateUtils.getNow());
		genDbDocService.doSaveGenDbDocRequest(Datasource.TOOLS, genDbDocRequest);
		
		/**
		 * 返回结果
		 */
		resultMap.put("isSuccess", true);
		resultMap.put("message", "db文档生成成功:[<a href='" + fileUrl + "' target='_blank'>" + fileName + "</>]");
		return resultMap;
	}
	
	/**
	 * 创建格子样式
	 * @param workBook
	 * @param type
	 * @return
	 */
	private HSSFCellStyle createHSSFCellStyle(HSSFWorkbook workBook, String type) {
		HSSFPalette customPalette = workBook.getCustomPalette();
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		if("table_title".equals(type)) {
			customPalette.setColorAtIndex(HSSFColor.PALE_BLUE.index, (byte) 156, (byte) 195, (byte) 228);
			cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);//设置背景色
		} else if("table".equals(type)) {
			cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);//设置背景色
		} else if("column_title".equals(type)) {
			customPalette.setColorAtIndex(HSSFColor.GOLD.index, (byte) 254, (byte) 229, (byte) 157);
			cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);//设置背景色
		} else if("column".equals(type)) {
			customPalette.setColorAtIndex(HSSFColor.ORANGE.index, (byte) 247, (byte) 203, (byte) 175);
			cellStyle.setFillForegroundColor(HSSFColor.ORANGE.index);//设置背景色
		} else if("column_detail".equals(type)) {
			cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);//设置背景色
		}
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
		
		HSSFFont font = workBook.createFont();
		font.setFontName("黑体-简");
		font.setFontHeightInPoints((short) 12);//设置字体大小
		cellStyle.setFont(font);//选择需要用到的字体格式
		
		//sheet.setColumnWidth((short) 0, (short) 3766);
		return cellStyle;
	}
	
	/**
	 * 生成db文档历史查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody GenDbDocDto query(HttpServletRequest request, GenDbDocDto dto) throws Exception {
		logger.info("生成db文档历史查询,数据库:[" + dto.getTableSchema() + "],"
				+ "页码:[" + dto.getActivePage() + "],页大小:[" + dto.getPageSize() + "]!");
		
		dto.setSuccess(true);
		dto.setMessage("查询成功！");
		
		List<GenDbDocRequest> list = genDbDocService.doQueryGenDbDocRequest(Datasource.TOOLS, dto);
		dto.setList(list);
		
	    long totalCount = genDbDocService.doCountGenDbDocRequest(Datasource.TOOLS, dto);
	    long totalPage = 0;
	    if(dto.getPageSize() > 0 && totalCount > 0){
		    totalPage = (totalCount-1)/dto.getPageSize()+1;
	    }
	    dto.setTotalCount(totalCount);
	    dto.setTotalPage(totalPage);
		return dto;
	}
}