package cn.lut.se.forum.listener;

import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.service.UserService;
import cn.lut.se.forum.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "userloginlistener",urlPatterns = {"/reply.jsp","/publish.jsp"})
public class UserLoginListenner implements Filter {

    UserService userService = new UserServiceImpl();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;


        User user = (User) request.getSession(true).getAttribute("loginUser");
        if( user ==null || user.getPhone() == null || user.getPwd() == null ){
            user = null;
        }else{
            user = userService.login(user.getPhone(),user.getPwd(),true);
        }

        if(user != null){
            filterChain.doFilter(request,response);
        }else {
            request.setAttribute("msg","用户没有登录");
            request.getRequestDispatcher("/user/login.jsp").forward(request,response);
        }
    }
}
