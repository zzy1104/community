package com.newcoder.community.service;

import com.newcoder.community.dao.UserMapper;
import com.newcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //因为提到的都是userId，实际使用时应该返回用户姓名，所以写一个通过userId返回User的业务逻
    public User findUserById(int id){
        return userMapper.selectById(id);
    }
}
