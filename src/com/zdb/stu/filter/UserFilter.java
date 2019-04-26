/*package com.zdb.stu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.zdb.stu.domain.Teacher;

@WebFilter("/teacher/*")
public class UserFilter implements Filter {

	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		Teacher name =  (Teacher) req.getSession().getAttribute("session_tea");
		if(name!=null) {
			chain.doFilter(request, response);
		}else {
			req.setAttribute("msg", "Äú»¹Î´µÇÂ¼");
			req.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}*/
