package com.zdb.stu.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class UserCountListener implements HttpSessionListener {

	private int userCount=0;
    public UserCountListener() {
        
    }

    public void sessionCreated(HttpSessionEvent hse)  { 
    	HttpSession session = hse.getSession();
		ServletContext servletContext = session.getServletContext();
		Object object = servletContext.getAttribute("userCount");
		if (object == null) {
			servletContext.setAttribute("userCount", 1);
		} else {
			int num =(int)object;
			servletContext.setAttribute("userCount", num + 1);
		}
    }

    public void sessionDestroyed(HttpSessionEvent hse)  { 
    	HttpSession session = hse.getSession();// 获得Session对象
		ServletContext servletContext = session.getServletContext();

		Object object = servletContext.getAttribute("userCount");
		if (object != null){
			int num = (int) object;
			servletContext.setAttribute("userCount", num - 1);
		}
    }
	
}
