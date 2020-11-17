package com.example.jingzhou.Dao;

import com.example.jingzhou.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from user")
    List<User> listUser();
}
