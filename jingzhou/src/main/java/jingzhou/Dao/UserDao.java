package jingzhou.Dao;

import jingzhou.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import static com.fasterxml.jackson.databind.node.JsonNodeType.POJO;

@Mapper
public interface UserDao {

    @Select("select * from user")
    List<User> listUser();
}
