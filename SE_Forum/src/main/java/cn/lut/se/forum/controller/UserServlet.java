package cn.lut.se.forum.controller;


import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.service.UserService;
import cn.lut.se.forum.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "userServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     *
     * http://localhost:8080/user?method=login
     *
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd"); 


        User user = userService.login(phone,pwd);

        if(user != null){
            //用户存在
            request.getSession().setAttribute("loginUser",user);


            //跳转页面
            response.sendRedirect(request.getContextPath()+"/topic?method=list&c_id=1");
//            request.getRequestDispatcher(request.getContextPath()+"/topic?method=list&c_id=1").forward(request,response);

        }else {
            //用户不存在或者密码错误，重新转发到登录界面
            request.setAttribute("msg","用户名或者密码不正确");
            request.getRequestDispatcher("/user/login.jsp").forward(request,response);
        }
    }
    /**
     *  http://localhost:8080/user?method=logout
     * 退出登录
     * @param request
     * @param response
     */
    public void  logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        User user = (User) request.getSession(true ).getAttribute("loginUser");



        userService.cancellation(user);

        request.getSession().invalidate();



        response.sendRedirect(request.getContextPath()+"/topic?method=list&c_id=1");
    }



    /**
     * http://localhost:8080/user?method=register
     * @param request
     * @param response
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();

        Map<String,String[]> map = request.getParameterMap();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String x  = request.getParameter("pwd"), y = request.getParameter("pwd2") ;
        if(x != null && y != null && x.equals(y)){
                int i = userService.register(user);
                if(i>0){
                    System.out.println("注册成功");
                    request.getRequestDispatcher("/user/login.jsp").forward(request,response);
                }else {
                    request.getRequestDispatcher("/user/register.jsp").forward(request,response);
                }
        }else{
            System.out.println("两次密码有一次为空或者密码不相同 注册失败！！！");
            request.getRequestDispatcher("/user/register.jsp").forward(request,response);
        }
    }
}
