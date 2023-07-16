package cn.lut.se.forum.controller;

/**
 * @author vincent
 * @create 2022-11-04 18:13
 */
import cn.lut.se.forum.domain.Sign_in;
import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.service.Sign_inService;
import cn.lut.se.forum.service.UserService;
import cn.lut.se.forum.service.impl.Sign_inServiceImpl;
import cn.lut.se.forum.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Sign_inServlet" ,urlPatterns ="/sign")
public class Sign_inServlet  extends BaseServlet {
    private Sign_inService sin = new Sign_inServiceImpl();

    /*
     * 获取签到页集合
     * */

    public void list(HttpServletRequest request, HttpServletResponse response)  {
        List<Sign_in> list = sin.list();
        System.out.println("sin.list() ==============" + list);
        System.out.println(list.toString());
        request.setAttribute("sign_inList",list);
        System.out.println("session设置完毕");
//        request.getRequestDispatcher("").forward(request,response);
    }


    /**
     * http://localhost:8080/user?method=signin
     * @param request
     * @param response
     */
    public void sign_in(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("签到");
        User user = (User) request.getSession(true ).getAttribute("loginUser");//获取到了已登录的user对象
        //执行签到函数
        sin.signin(user);
        //跳转到签到页面


//        System.out.println("后面的还没写 先测试一下");
        list(request,response);
        System.out.println("签到完成");
//        request .getRequestDispatcher(request.getContextPath()+"/user/login.jsp").forward(request,response);
        request.getRequestDispatcher("/signIn.jsp").forward(request,response);

    }


}
