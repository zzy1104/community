package com.newcoder.community;

import com.newcoder.community.dao.DiscussPostMapper;
import com.newcoder.community.dao.UserMapper;
import com.newcoder.community.entity.DiscussPost;
import com.newcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    //为了注入Mapper
    private UserMapper userMapper;
    @Test
    public void testSelectUser(){
        User user= userMapper.selectById(101);
        System.out.println(user);
        user= userMapper.selectByName("liubei");
        System.out.println(user);
        user= userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        //需要给这个方法传入一个User对象，那么需要实例化一个User
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser(){
        int rows=userMapper.updateStatus(150,1);
        System.out.println(rows);
        rows=userMapper.updateHeader(150,"http://www.nowcoder.com/102.png");
        System.out.println(rows);
        rows=userMapper.updatePassword(150,"hello");
        System.out.println(rows);
    }

    @Autowired
    //为了注入Mapper
    private DiscussPostMapper discussPostMapper;
    @Test
    public void testSelectPosts(){
        //查第1页的数据，所以offset行号从0开始，一页最多有10行
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for(DiscussPost post : list) {
            System.out.println(post);
        }
        int rows =discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);

    }

}
