package com.newcoder.community.dao;
import com.newcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    //根据 id、username、email查询
    User selectById(int id);
    User selectByName(String username);
    User selectByEmail(String email);
    //增加用户（返回插入的行数） 方法名nsertUser 参数是整个user所包含的数据，用User对象封装
    int insertUser(User user);
    //修改状态，以id为条件修改状态，返回行数
    int updateStatus(int id, int status);
    int updateHeader(int id, String headerUrl);
    int updatePassword(int id, String password);
}
