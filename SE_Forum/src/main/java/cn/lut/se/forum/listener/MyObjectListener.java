package cn.lut.se.forum.listener;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyObjectListener implements HttpSessionListener,ServletContextListener,ServletRequestListener{
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println("新创建一个session, ID为: " + session.getId());
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println("销毁一个session, ID为: " + session.getId());
	}

}
