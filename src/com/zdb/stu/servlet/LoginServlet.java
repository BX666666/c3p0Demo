package com.zdb.stu.servlet;

import com.zdb.stu.daoimpl.TeaDaoimpl;
import com.zdb.stu.domain.Teacher;
import com.zdb.stu.service.TeaService;
import com.zdb.stu.service.UserException;
import com.zdb.stu.tools.BaseServlet;

import cn.itcast.commons.CommonUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	private TeaService teaService = new TeaService();
       public String login(HttpServletRequest request,HttpServletResponse responce) throws ServletException, IOException {
    	  // String tName=request.getParameter("tName");
    	   //String password=request.getParameter("password");
    	   Teacher form= CommonUtils.toBean(request.getParameterMap(), Teacher.class);
			try {
				Teacher tea = teaService.login(form);
				request.getSession().setAttribute("session_tea", tea);
	   			return "R:/teacher/main.jsp";
			} catch (UserException e) {
				request.setAttribute("msg", e.getMessage());
				request.setAttribute("form", form);
				return "F:/login.jsp";
   		}
    }
 }
