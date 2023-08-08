package com.newcoder.community.controller;

import com.newcoder.community.config.AlphaConfig;
import com.newcoder.community.service.AlphaService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.http11.filters.IdentityOutputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListenerMethodProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
//声明请求路径
@RequestMapping("/alpha")
public class Alphacontroller {
    @Autowired
    private AlphaService alphaService;
    @Autowired
    private AlphaConfig alphaConfig;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello,spring boot";
    }

    //springMVC http路径
    @RequestMapping("/http")
    //HttpServletRequest是接口 request是请求

    public void http(HttpServletRequest request, HttpServletResponse response){
        //通过request对象获取相关数据的办法
        //获取请求数据 42和44是请求行第一行的数据
        //获取请求方式
        System.out.println(request.getMethod());
        //获取请求路径
        System.out.println(request.getServletPath());
        //下面是请求的消息头
        //得到所有请求行的key 请求行是key-value 得到一个迭代器
        Enumeration<String> enumeration=request.getHeaderNames();
        //对迭代器对象，需要通过while进行遍历 是否有更多的值
        while(enumeration.hasMoreElements()){
            //请求行的key
            String name=enumeration.nextElement();
            //请求航对应的value
            String value =request.getHeader(name);
            System.out.println(name+":"+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据 首先设置返回数据类型  text/html 网页类型的文本
        response.setContentType("text/html;charset=utf-8");
        //输出流
        try (PrintWriter writer= response.getWriter();)
        {
            //通过writer向浏览器中打印一个网页
            writer.write("<h1>嘎嘎</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //简便方式处理浏览器的请求 1、基于request接收请求数据 2、基于response返回响应数据
    //GET请求 默认发送的请求是GET请求
    // /students？current=1 &limit=20
    //path 规定路径，method 声明请求方式，强制方法必须是GET才能访问到，（因为请求方式有很多种）
    @RequestMapping(path="/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            //加两个注解对传入参数做更详细的说明，吧request中名为current的参数赋值给 current，如果没有 默认值为1
            @RequestParam(name="current",required = false,defaultValue = "1") int current,
            @RequestParam(name="limit",required = false,defaultValue = "10")int limit)
    {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // /student/123   当参数不是通过传入，而是路径的一部分的时候怎么获取参数呢
    @RequestMapping(path="/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            //这个注解是为了在路径中获取名为“id”的变量 然后赋值给Newid
            @PathVariable("id") int Newid)
    {
        System.out.println(Newid);
        return "a student";
    }


    //POST请求 浏览器向服务器提交数据
    //因为需要提交 所以要输入，所以需要配置静态网页
    @RequestMapping(path ="/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }


    //基于Response响应一个数据
    @RequestMapping(path="/teacher",method =RequestMethod.GET)
    //@ResponseBody 这里不用谢这个注解，因为不是返回一个字符串 是返回一个html网页
    public ModelAndView getteacher(){
        ModelAndView mav =new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","25");
        mav.setViewName("/demo/view");
        return mav;
    }

    //基于Response响应一个数据的简单方法
    @RequestMapping(path="/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","xx大学");
        model.addAttribute("age","55");
        return "demo/view";
    }

    //响应JSON数据（异步请求）
    //Java对象 -> JSON字符串 —> Js对象 Json实现跨语言访问
    @RequestMapping(path="/emp", method = RequestMethod.GET)
    @ResponseBody
    //因为返回的是JSON字符串，所以需要responsebody注解
    //key-value结构
    public Map<String,Object> getEMP(){
        Map emp= new HashMap<>();
        emp.put("name","张三");
        emp.put("age",55);
        emp.put("slary",8000.00);
        return emp;
    }

    @RequestMapping(path="/emps", method = RequestMethod.GET)
    @ResponseBody
    //因为返回的是JSON字符串，所以需要responsebody注解
    //key-value结构
    public List<Map<String,Object>> getEMPS(){
        List<Map<String,Object>> list =new ArrayList<>();
        //张三
        Map emp= new HashMap<>();
        emp.put("name","张三");
        emp.put("age",55);
        emp.put("slary",8000.00);
        list.add(emp);
        //李四
        emp= new HashMap<>();
        emp.put("name","李四");
        emp.put("age",25);
        emp.put("slary",5000.00);
        list.add(emp);
        //小王
        emp= new HashMap<>();
        emp.put("name","小王");
        emp.put("age",20);
        emp.put("slary",4000.00);
        list.add(emp);
        return list;
    }

}
