package com.gxx.tools.base.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	none
 *    </dd>
 *    <dt><b>Description:时间工具类</b></dt>
 *    <dd>
 *    	<p>none
 *    </dd>
 * </dl>
 *
 * @author Gxx
 * @version 1.0, 2015年12月23日
 * @since config_center
 *
 */
public class DateUtils {
	
	public static final Pattern DATE_PATTERN_1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}$");
	public static final Pattern DATE_PATTERN_2 = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
	public static final Pattern TIME_PATTERN_1 = Pattern.compile("\\d{2}:\\d{2}:\\d{2}$");
	public static final Pattern TIME_PATTERN_2 = Pattern.compile("\\d{2}:\\d{2}$");
	
	public static final Map<String,String> DIGITAL_TO_CHINESES = new HashMap<String,String>();

	static {
		DIGITAL_TO_CHINESES.put("0", "零");
		DIGITAL_TO_CHINESES.put("1", "一");
		DIGITAL_TO_CHINESES.put("2", "二");
		DIGITAL_TO_CHINESES.put("3", "三");
		DIGITAL_TO_CHINESES.put("4", "四");
		DIGITAL_TO_CHINESES.put("5", "五");
		DIGITAL_TO_CHINESES.put("6", "六");
		DIGITAL_TO_CHINESES.put("7", "七");
		DIGITAL_TO_CHINESES.put("8", "八");
		DIGITAL_TO_CHINESES.put("9", "九");
	}
	
	//============================0.获取当前时间====================================
	    
	/**
	 * 获取当前日期类型时间
	 */
	public static Date getNow(){
		return new Date();
	}
	
	
	/**
	 * 获取当前时间戳
	 */
	public static long getNowTimestamp(){
		return getNow().getTime();		
	}
	
	/**
	 * 获取当前日期 yyyyMMdd
	 */
	public static String getCurrentDate(){
		return toMailDateDtPartString(getNow());		
	}
	
	/**
	 * 获取当期时间HHmmss
	 * @return
	 */
	public static String getCurrentTime(){
		return toMailTimeTmPartString(getNow());
	}
	
	/**
	 * 获取当期时间Yyyy年MM月dd日HH:mm
	 * @return
	 */
	public static String getCurrentYyyyMmDdHm(){
		return toMailDtm(getNow());
	}
	
	/**
	 * 获取当期时间MM月dd日HH:mm
	 * @return
	 */
	public static String getCurrentMmDdHm(){
		return toMailDtmPart(getNow());
	}
	
	/**
	 * 获取当期时间yyyyMMddHHmmss
	 * @return
	 */
	public static String getCurrentDateTime(){
		return toMailDateString(getNow());
	}


	public static String getCurrentGBKDateTime(){
		return DateUtils.toLongDateGBKString(DateUtils.getNow());
	}
	
	//============================1.Date2String=====================================
	
	/**
	 * 将一个日期型转换为指定格式字串
	 * @param aDate
	 * @param formatStr
	 * @return
	 */
    public static final String toFormatDateString(Date aDate, String formatStr) {
    	if (aDate == null) return StringUtils.EMPTY;
        return new SimpleDateFormat(formatStr).format(aDate);

    }

    /**
     * 将一个日期型转换为'yyyy-MM-dd'格式字串 
     * @param aDate
     * @return
     */
    public static final String toShortDateString(Date aDate) {
        return toFormatDateString(aDate,SHORT_DATE_FORMAT);
    }
    
	/**
	 * 将一个日期型转换为'yyyyMMdd'格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toMailDateDtPartString(Date aDate) {
        return toFormatDateString(aDate,MAIL_DATE_DT_PART_FORMAT);
    }

	/**
	 * 将一个日期型转换为'HHmmss'格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toMailTimeTmPartString(Date aDate) {
        return toFormatDateString(aDate,MAIL_TIME_TM_PART_FORMAT);
    }
    
    
	/**
	 * 将一个日期型转换为'yyyyMMddHHmmss'格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toMailDateString(Date aDate) {
        return toFormatDateString(aDate,MAIL_DATE_FORMAT);
    }
    
    /**
	 * 将一个日期型转换为MM月dd日HH:mm格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toMailDtmPart(Date aDate) {
        return toFormatDateString(aDate,MAIL_DATA_DTM_PART_FORMAT);
    }
    
    /**
	 * 将一个日期型转换为yyyy年MM月dd日HH:mm格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toMailDtm(Date aDate) {
        return toFormatDateString(aDate,MAIL_DATA_DTM_FORMAT);
    }
    
    
    /**
     * 
     */
    /**
	 * 将一个日期型转换为yyyy.MM.dd格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toPointDtmPart(Date aDate) {
        return toFormatDateString(aDate,POINT_DATA_DTM_PART_FORMAT);
    }
    
    
    /**
     * 将一个日期型转换为'yyyy-MM-dd HH:mm:ss'格式字串
     * @param aDate
     * @return
     */
    public static final String toLongDateString(Date aDate) {
        return toFormatDateString(aDate,LONG_DATE_FORMAT);
    }

	/**
	 * 将一个日期型转换为'HH:mm:ss'格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toLongDateTmPartString(Date aDate) {
        return toFormatDateString(aDate,LONG_DATE_TM_PART_FORMAT);
    }
    
    /**
     * 将一个日期型转换为'yyyy年MM月dd日'格式字串
     * @param aDate
     * @return
     */
    public static final String toShortDateGBKString(Date aDate) {
        return toFormatDateString(aDate,SHORT_DATE_GBK_FORMAT);
    }
    
    
    /**
     * 将一个日期型转换为'yyyy年MM月dd日 HH时mm分'格式字串
     * @param aDate
     * @return
     */
    public static final String toDateGBKString(Date aDate) {
        return toFormatDateString(aDate,DATE_GBK_FORMAT);
    }
    
    /**
     * 将一个日期型转换为'yyyy年MM月dd日 HH时mm分ss秒'格式字串
     * @param aDate
     * @return
     */
    public static final String toLongDateGBKString(Date aDate) {
        return toFormatDateString(aDate,LONG_DATE_GBK_FORMAT);
    }
    
	/**
	 * 将一个日期型转换为'HH时mm分ss秒'格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toLongDateTmPartGBKString(Date aDate) {
        return toFormatDateString(aDate,Long_DATE_TM_PART_GBK_FORMAT);
    }    

    /** 
     * 将一个日期型转换为'yyyy-MM-dd HH:mm:ss:SSS'格式字串
     * @param aDate
     * @return
     */
    public static final String toFullDateString (Date aDate){
    	return toFormatDateString(aDate,FULL_DATE_FORMAT);
    }
    
    /**
     * 将一个日期型转换为'yyyy年MM月dd日 HH时mm分ss秒SSS毫秒'格式字串
     * @param aDate
     * @return
     */
    public static final String toFullDateGBKString (Date aDate){
    	return toFormatDateString(aDate,FULL_DATE_GBK_FORMAT);
    }
    
    /**
     * 将一个日期型转换为'yyyyMMddHHmmssSSS'格式字串
     * @param aDate
     * @return
     */
    public static final String toFullDateCompactString (Date aDate){
    	return toFormatDateString(aDate,FULL_DATE_COMPACT_FORMAT);
    }
    
    /**
     * 将一个日期型转换为LDAP格式字串
     * @param aDate
     * @return
     */
    public static final String toLDAPDateString(Date aDate) {
        return toFormatDateString(aDate, LDAP_DATE_FORMAT);
    }
    
    
	//============================2.String2Date=====================================    
    
    /**
     * 将一个符合指定格式的字串解析成日期型
     * @param aDateStr
     * @param formatter
     * @return
     * @throws ParseException 
     */
    public static final Date parser(String aDateStr, String formatter) throws ParseException{
    	if (StringUtils.isBlank(aDateStr)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.parse(aDateStr);

    }
    
    /**
     * 获取日期格式
     * @param date 日期字符串
     * @return 日期格式
     * @throws Exception 非法参数或不支持格式错误信息
     */
    public static String getDatePattern(String date) throws Exception{
    	if( date==null || "".equals(date) ){
    		throw new java.lang.IllegalArgumentException("非法日期参数，无法解析日期");
    	}
    	if( DATE_PATTERN_1.matcher(date).find() ){
			return "yyyy-MM-dd";
		}else if( DATE_PATTERN_2.matcher(date).find() ){
			return "yyyy-MM-dd HH:mm:ss";
		}else if( TIME_PATTERN_1.matcher(date).find()){
			return "HH:mm:ss";
		}else if( TIME_PATTERN_2.matcher(date).find()){
			return "HH:mm";
		}else{
			throw new Exception("未知日期格式，无法解析日期"); 
		}
    }
    
    /**
     * 解析日期字符串至日期类型内容,返回java.sql.Date日期类型数据内容
     * @param date 日期字符串
     * @param format 与日期字符串格式匹配的格式
     * @return 解析后返回的日期
     */
    public static java.sql.Date parseSqlDate(String date, String format) {
        try {
            SimpleDateFormat formatter;
            if (null == format)  throw new IllegalArgumentException("错误的日期格式");
            formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            java.util.Date utilDate = formatter.parse(date, pos);
            return new java.sql.Date( utilDate.getTime() );
        } catch (Exception e) {
            throw new IllegalArgumentException("错误的日期:" + date , e);
        }
    }

    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss'格式的字串解析成日期型
     * 
     * @param aDateStr
     * @return
     */
    public static final Date parseLongDateString(String aDateStr) throws ParseException{
    	return parser(aDateStr,LONG_DATE_FORMAT);
 
    }
    
    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss'格式的字串解析成日期型
     * 
     * @param aDateStr
     * @return
     */
    public static final Date parseLongDateDtPartString(String aDateStr) throws ParseException{
    	return parser(aDateStr,LONG_DATE_FORMAT);
 
    }
    
    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss'格式的字串解析成日期型
     * 
     * @param aDateStr
     * @return
     */
    public static final Date parseLongDateTmPartString(String aDateStr) throws ParseException{
    	return parser(aDateStr,LONG_DATE_FORMAT);
 
    }
    
    /**
     * 将一个符合'yyyy-MM-dd'格式的字串解析成日期型
     * @param aDateStr
     * @return
     */
    public static final Date parseShortDateString(String aDateStr) throws ParseException{
    	return parser(aDateStr,SHORT_DATE_FORMAT);
 
    }
    
    /**
     * 将一个符合'yyyyMMddHHmmss'格式的字串解析成日期型
     * @param aDateStr
     * @return
     */
    public static final Date parseMailDateString(String aDateStr) throws ParseException{
    	return parser(aDateStr,MAIL_DATE_FORMAT);
 
    }
    
    /**
     * 将一个符合'yyyyMMdd'格式的字串解析成日期型
     * @param aDateStr
     * @return
     */
    public static final Date parseMailDateDtPartString(String aDateStr) throws ParseException{
    	return parser(aDateStr,MAIL_DATE_DT_PART_FORMAT);
    }
    
    /**
     * 将一个符合'HHmmss'格式的字串解析成日期型
     * @param aDateStr
     * @return
     */
    public static final Date parseMailDateTmPartString(String aDateStr) throws ParseException{
    	return parser(aDateStr,MAIL_TIME_TM_PART_FORMAT);
    }
    
   
    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss:SSS'格式的字串解析成日期型
     * @param strDate
     * @return
     */
    public static final Date parseFullDateString(String aDateStr) throws ParseException{
    	return parser(aDateStr,FULL_DATE_FORMAT);
 
    }    
    
    
    
    /**
     * 解析日期字符串至日期类型内容
     * @param date 日期字符串
     * @param format 与日期字符串格式匹配的格式
     * @return 解析后返回的日期
     */
    public static java.util.Date parseDate(String date, String format) {
        try {
            SimpleDateFormat formatter;
            if (null == format)
                throw new IllegalArgumentException("错误的日期格式");
            formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            return formatter.parse(date, pos);
        } catch (Exception e) {
            throw new IllegalArgumentException("错误的日期:" + date + " " + e);
        }
    }
    
    
    
  //============================3.String2String===================================== 
    
    
    

    
	//============================4.时间加减=====================================    
    
    
    /**
     * 为一个日期加上指定年数
     * @param aDate
     * @param amount 年数
     * @return
     */
    public static final Date addYears(Date aDate, int amount) {
		return addTime(aDate,Calendar.YEAR,amount);    
    }

    /**
     * 为一个日期加上指定月数
     * @param aDate
     * @param amount 月数
     * @return
     */
	public static final Date addMonths(Date aDate, int amount) {
		return addTime(aDate,Calendar.MONTH,amount);  
	}	

    /**
     * 为一个日期加上指定天数
     * @param aDate
     * @param amount 天数
     * @return
     */
    public static final Date addDays(Date aDate, int amount) {
		return addTime(aDate,Calendar.DAY_OF_YEAR,amount);        
    }
    
    /**
     * 为一个日期加上指定天数
     * @param aDate yyyyMMdd格式字串
     * @param amount 天数
     * @return
     */
    public static final String addDays(String aDate, int amount) {
    	try {
    		return DateUtils.toMailDateDtPartString(addTime(DateUtils.parseMailDateDtPartString(aDate),Calendar.DAY_OF_YEAR,amount));  
    	} catch (ParseException e) {
    		throw new RuntimeException(e);
		} 
    }

    /**
     * 为一个日期加上指定小时数
     * @param aDate 
     * @param amount 小时数
     * @return
     */
	public static final Date addHours(Date aDate,int amount) {	
		return addTime(aDate,Calendar.HOUR,amount);

	}
	
	/**
	 * 为一个日期加上指定分钟数
	 * @param aDate
	 * @param amount 分钟数
	 * @return
	 */
	public static final Date addMinutes(Date aDate,int amount) {	
		return addTime(aDate,Calendar.MINUTE,amount);
	}
	
	/**
	 * 为一个日期加上指定秒数 
	 * @param aDate
	 * @param amount 秒数
	 * @return
	 */
	public static final Date addSeconds(Date aDate,int amount) {	
		return addTime(aDate,Calendar.SECOND,amount);

	}

	private static final Date addTime(Date aDate,int timeType,int amount){
		if (aDate == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();		
		cal.setTime(aDate);
		cal.add(timeType, amount);
		return cal.getTime();
	}
	
	
	
	//======================================5.时间国际化=================================
	
	
	
	/**
	 * 检查字串是否为YYYYMMDD格式
	 * @param mailDateStr
	 * @return
	 */
	public static boolean isMailDateStr(String mailDateStr){
		boolean ret = true;
		try {
			parseMailDateDtPartString(mailDateStr);
		} catch (ParseException e) {
			ret = false;
		}
		return ret;
	}
	
	/**
	 * 检查字串是否为HHMISS格式
	 * @param mailTimeStr
	 * @return
	 */
	public static boolean isMailTimeStr(String mailTimeStr){
		boolean ret = true;
		try {
			parseMailDateTmPartString(mailTimeStr);
		} catch (ParseException e) {
			ret = false;
		}
		return ret;
	}
    

   public static final String getPrevDay(){
	   Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);    //得到前一天
		Date date = calendar.getTime();
		return toFormatDateString(date,MAIL_DATE_DT_PART_FORMAT);
   }
   
   /**
    * 获取当前日期 MM月dd日
    */
   public static String getCurrentDateMD(){
   	return toMailDateMDPartString(getNow());		
   }

   /**
    * 获取当前时间 HH时mm分
    */
   public static String getCurrentDateHM(){
   		return toMailDateHMPartString(getNow());		
   }

   /**
    * 将一个日期型转换为'MM月dd日'格式字串
    * @param aDate
    * @return
    */
   public static final String toMailDateMDPartString(Date aDate) {
	   return toFormatDateString(aDate,MAIL_DATE_MD_FORMAT);
   }

   /**
    * 将一个日期型转换为'HH时mm分'格式字串
    * @param aDate
    * @return
    */
   public static final String toMailDateHMPartString(Date aDate) {
	   return toFormatDateString(aDate,MAIL_DATE_HM_FORMAT);
   }
   
   public static final String MAIL_DATE_MD_FORMAT = "MM月dd日";
   public static final String MAIL_DATE_HM_FORMAT = "HH时mm分";
	
    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SHORT_DATE_GBK_FORMAT = "yyyy年MM月dd日";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分";       
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒";  
    public static final String MAIL_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String MAIL_DATE_HHMM_FORMAT = "HH:mm";
    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String FULL_DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";
    public static final String FULL_DATE_COMPACT_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String LDAP_DATE_FORMAT = "yyyyMMddHHmm'Z'";
    public static final String US_LOCALE_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";
    
    public static final String MAIL_DATE_DT_PART_FORMAT = "yyyyMMdd";    
    public static final String MAIL_TIME_TM_PART_FORMAT = "HHmmss";  
    public static final String LONG_DATE_TM_PART_FORMAT = "HH:mm:ss";
    public static final String Long_DATE_TM_PART_GBK_FORMAT = "HH时mm分ss秒";
    public static final String MAIL_DATA_DTM_FORMAT="yyyy年MM月dd日HH:mm";
    public static final String MAIL_DATA_DTM_PART_FORMAT="MM月dd日HH:mm";
    public static final String POINT_DATA_DTM_PART_FORMAT="yyyy.MM.dd";
    
    public static final String DEFAULT_DATE_FORMAT = US_LOCALE_DATE_FORMAT;

    public static long NANO_ONE_SECOND = 1000;
    public static long NANO_ONE_MINUTE = 60*NANO_ONE_SECOND;
    public static long NANO_ONE_HOUR = 60*NANO_ONE_MINUTE;
    public static long NANO_ONE_DAY = 24*NANO_ONE_HOUR;
    

    private DateUtils(){}
}
