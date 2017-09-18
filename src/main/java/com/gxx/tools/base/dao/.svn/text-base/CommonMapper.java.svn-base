package com.gxx.tools.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/** 
 * 通用数据操作磊
 * @author Gxx
 */
@Repository
public interface CommonMapper {
    
    /**
     * 查询所有库表
     * @param tableSchema
     * @return
     */
    @Select("select table_name,table_comment from information_schema.tables where table_schema=#{tableSchema} and table_type='BASE TABLE'")
    List<Map<String, String>> queryAllTables(@Param("tableSchema") String tableSchema);
    
    /**
     * 查询所有库表
     * @param tableSchema
     * @param tableName
     * @return
     */
    @Select("SHOW FULL COLUMNS FROM ${tableSchema}.${tableName}")
    List<Map<String, String>> queryAllColumns(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);
    
    /**
     * 查询所有库
     * @return
     */
    @Select("Select SCHEMA_NAME FROM information_schema.SCHEMATA where SCHEMA_NAME not in ('information_schema','performance_schema')")
    List<String> queryAllDatabases();
}
