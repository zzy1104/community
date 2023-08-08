package com.newcoder.community.controller;

import com.newcoder.community.entity.DiscussPost;
import com.newcoder.community.entity.Page;
import com.newcoder.community.entity.User;
import com.newcoder.community.service.DiscussPostService;
import com.newcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    //可以不加访问路径/alpha，直接访问方法
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    @RequestMapping(path="/index",method = RequestMethod.GET)
    public String getIndexPage(Model model , Page page){
        //方法调用之前，SpringMvc会自动实例化Model 和Page，并将Page注入Model
        //多以，在thymelead中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        //先查到的是第一页数据
        List<DiscussPost> list=discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        //需要将查到的数据里的UserId对应到用户名
        //遍历list集合，找到每个User，把对应的值装到一个新的数组里面
        List<Map<String,Object>> discussPosts= new ArrayList<>();
        if(list!=null){
            for (DiscussPost post:list){
                Map<String,Object> map=new HashMap<>();
                map.put("post",post);
                User user =userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        //前面是给页面返回数据取得名字 对应的值是这个集合
        model.addAttribute("discussPosts",discussPosts);
        //返回的是模板的路径
        return "index";
    }

}
