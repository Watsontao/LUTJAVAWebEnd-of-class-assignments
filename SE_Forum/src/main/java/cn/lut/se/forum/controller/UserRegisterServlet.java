package cn.lut.se.forum.controller;

/**
 * @author vincent
 * @create 2022-11-04 21:34
 */
import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.service.TopicService;
import cn.lut.se.forum.service.UserService;
import cn.lut.se.forum.service.impl.TopicServiceImpl;
import cn.lut.se.forum.service.impl.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet("/userRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        String info = "";

        String phone = req.getParameter("phone");
        System.out.println("phone : " + phone);
        if(phone == null||" ".equals(phone))return;
        boolean flag = Pattern.matches("^1[3-9]\\d{9}$", phone);;
        if(flag){

            user = userService.findByPhone(phone);


            if(user==null){
                info = "手机号可以使用。";
            }else {
                info = "该手机号已经注册。";
            }
        }else {
            info = "手机号格式不正确";
        }

        resp.getWriter().println(info);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(2);
        String info = "";

        String username = req.getParameter("username");
        System.out.println("username : " + username);
        if(username == null||" ".equals(username))return;

            User user = userService.findByUsername(username);

            if(user==null&&!"".equals(username)){
                info = "用户名可以使用。";
            }else if("".equals(username)){
                info = "用户名不能为空";
            } else {
                info = "该用户名已经注册。";
            }


        resp.getWriter().println(info);
    }
}
