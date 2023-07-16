package cn.lut.se.forum.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext application=httpSessionEvent.getSession().getServletContext();
        Integer Online_number=(Integer)application.getAttribute("Online_number");
        if(null==Online_number)
            Online_number=0;

        Online_number++;
        application.setAttribute("Online_number",Online_number);

        System.out.println("新增一位在线用户！");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext application =httpSessionEvent.getSession().getServletContext();
        Integer Online_number=(Integer)application.getAttribute("Online_number");//要转换为数字时 要默认用（Integer）强转
        if(null==Online_number)
        {
            Online_number=0;
        }
        else
            Online_number--;
        application.setAttribute("Online_number",Online_number);
        System.out.println("一位用户离线");
    }
}
