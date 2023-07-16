package cn.lut.se.forum.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "firstFilter",urlPatterns = "/*")
public class CharsetFilter implements Filter {


    @Override
    //
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;



        //request的乱码解决
        request.setCharacterEncoding("utf-8");

        //resp的乱码解决
        response.setContentType("text/html;charset=utf-8");


        //PrintWriter out = response.getWriter();
        //目的是让过滤器继续通行   随时等待过滤请求
        filterChain.doFilter(request,response);
    }




    @Override
    //web服务器启动时就进行初始化
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}