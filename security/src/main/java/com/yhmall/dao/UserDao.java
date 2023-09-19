package com.yhmall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhmall.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao extends BaseMapper<User> {

    @Select("select username from user ")
    List<String> findAllUseruame();

}
