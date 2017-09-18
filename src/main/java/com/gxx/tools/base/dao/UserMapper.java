package com.gxx.tools.base.dao;

import org.springframework.stereotype.Repository;

import com.gxx.tools.base.vo.User;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User tools);

    int insertSelective(User tools);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User tools);

    int updateByPrimaryKey(User tools);

    /**
     * 根据姓名查用户
     * @param name
     * @return
     */
    User getUserByName(String name);
}