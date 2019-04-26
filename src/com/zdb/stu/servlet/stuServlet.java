package com.zdb.stu.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.commons.CommonUtils;

import com.zdb.stu.domain.PageBean;
import com.zdb.stu.domain.student;
import com.zdb.stu.service.TeaService;
import com.zdb.stu.tools.BaseServlet;


@WebServlet("/stuServlet")
public class stuServlet extends BaseServlet {

/*	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 从页面中获取参数Value，每个参数对应一个方法
		String value = request.getParameter("method");
		if (value.equals("add")) {
			add(request, response);
		} else if (value.equals("findAll")) {
			findAll(request, response);
		} else if (value.equals("preEdit")) {
			preEdit(request, response);
		}else if (value.equals("edit")) {
			edit(request, response);
		}else if (value.equals("delete")) {
			delete(request, response);
		}else if (value.equals("query")) {
			query(request, response);}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}*/
	
    	private TeaService teaService = new TeaService();
    	
    	//添加
    	public String add(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		//封装表单数据到student对象中
    		student s = CommonUtils.toBean(request.getParameterMap(), student.class);
    		//s.setStuId(CommonUtils.uuid());//uuid补全id
    		teaService.add(s);
    		request.setAttribute("msg", "添加成功");
    		return "F:/teacher/succ.jsp";
    	}
    	
    	//查询全部学生信息
    	public String findAll(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		
    		int pc = getPc(request);//得到pc
    		int ps = 10;//给定ps的值，第页10行记录
    		PageBean<student> pb = teaService.findAll(pc, ps);//传递pc, ps给Service，得到PageBean
    		
    		// 设置url
    		pb.setUrl(getUrl(request));
    		request.setAttribute("pb", pb);
    		return "F:/teacher/chengyuan.jsp";
    	}
    	
    	private int getPc(HttpServletRequest request) {
    		/*
    		 * 1. 得到pc
    		 *   如果pc参数不存在，说明pc=1
    		 *   如果pc参数存在，需要转换成int类型即可
    		 */
    		String value = request.getParameter("pc");
    		if(value == null || value.trim().isEmpty()) {
    			return 1;
    		}
    		return Integer.parseInt(value);
		}

    	/**
    	 * 截取url
    	 *   /项目名/Servlet路径?参数字符串
    	 */
    	private String getUrl(HttpServletRequest request) {
    		String contextPath = request.getContextPath();//获取项目名
    		String servletPath = request.getServletPath();//获取servletPath，即/stuServlet
    		String queryString = request.getQueryString();//获取问号之后的参数部份
    		
    		//  判断参数部份中是否包含pc这个参数，如果包含，需要截取下去，不要这一部份。
    		if(queryString.contains("&pc=")) {
    			int index = queryString.lastIndexOf("&pc=");
    			queryString = queryString.substring(0, index);
    		}
    		
    		return contextPath + servletPath + "?" + queryString;
    	}
    
    	
    	//加载
    	public String preEdit(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    	
    	
    		String stuId = request.getParameter("stuId");
    		student stu = teaService.load(stuId);
    		request.setAttribute("stu", stu);//保存到request域中
    		return "F:/teacher/write.jsp";
    	}
    	
    	

    	
		//编辑
    	public String edit(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
 
    		student s = CommonUtils.toBean(request.getParameterMap(), student.class);
    		teaService.edit(s);
    		request.setAttribute("msg", "修改成功");
    		return "F:/teacher/succ.jsp";
    	}
    	
    	//删除
    	public String delete(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    	
    		String stuId = request.getParameter("stuId");
    		teaService.detele(stuId);
    		request.setAttribute("msg", "删除成功");
    		//request.getRequestDispatcher("/succ.jsp").forward(request, response);
    		return "F:/teacher/succ.jsp";
    	}
    	
    	//条件查询
    	/*public String query(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
   
    		student criteria = CommonUtils.toBean(request.getParameterMap(), student.class);
    		List<student> stuList = stuService.query(criteria);
    		request.setAttribute("stuList", stuList);
    		return "F:/chengyuan.jsp";
    	}*/
    	
    	public String query(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
//    		System.out.println(getUrl(request));
    		/*
    		 * 0. 把条件封装到student对象中
    		 * 1. 得到pc
    		 * 2. 给定ps
    		 * 3. 使用pc和ps，以及条件对象，调用service方法得到PageBean
    		 * 4. 把PageBean保存到request域中
    		 * 5. 转发到chengyuan.jsp
    		 */
    		// 获取查询条件
    		student criteria = CommonUtils.toBean(request.getParameterMap(), student.class);
    		
    		/*
    		 * 处理GET请求方式编码问题！
    		 */
    		criteria = encoding(criteria);
    		
    		int pc = getPc(request);//得到pc
    		int ps = 10;//给定ps的值，第页10行记录
    		PageBean<student> pb = teaService.query(criteria, pc, ps);
    		
    		// 得到url，保存到pb中
    		pb.setUrl(getUrl(request));
    		
    		request.setAttribute("pb", pb);
    		return "F:/teacher/chengyuan.jsp";
    	}
    	
    	
    	private student encoding(student criteria) throws UnsupportedEncodingException {
    		String stuName = criteria.getStuName();
    		String stuSex = criteria.getStuSex();
    		String stuAge = criteria.getStuAge();
    		String stuNum = criteria.getStuNum();
    		
    		if(stuName != null && !stuName.trim().isEmpty()) {
    			stuName = new String(stuName.getBytes("ISO-8859-1"), "utf-8");
    			criteria.setStuName(stuName);
    		}
    		
    		if(stuSex != null && !stuSex.trim().isEmpty()) {
    			stuSex = new String(stuSex.getBytes("ISO-8859-1"), "utf-8");
    			criteria.setStuSex(stuSex);
    		}
    		
    		if(stuAge != null && !stuAge.trim().isEmpty()) {
    			stuAge = new String(stuAge.getBytes("ISO-8859-1"), "utf-8");
    			criteria.setStuAge(stuAge);
    		}
    		
    		if(stuNum != null && !stuNum.trim().isEmpty()) {
    			stuNum = new String(stuNum.getBytes("ISO-8859-1"), "utf-8");
    			criteria.setStuNum(stuNum);
    		}
    		return criteria;
    	}
    }

		