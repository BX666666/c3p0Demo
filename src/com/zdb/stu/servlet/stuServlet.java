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

		// ��ҳ���л�ȡ����Value��ÿ��������Ӧһ������
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
    	
    	//���
    	public String add(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		//��װ�����ݵ�student������
    		student s = CommonUtils.toBean(request.getParameterMap(), student.class);
    		//s.setStuId(CommonUtils.uuid());//uuid��ȫid
    		teaService.add(s);
    		request.setAttribute("msg", "��ӳɹ�");
    		return "F:/teacher/succ.jsp";
    	}
    	
    	//��ѯȫ��ѧ����Ϣ
    	public String findAll(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		
    		int pc = getPc(request);//�õ�pc
    		int ps = 10;//����ps��ֵ����ҳ10�м�¼
    		PageBean<student> pb = teaService.findAll(pc, ps);//����pc, ps��Service���õ�PageBean
    		
    		// ����url
    		pb.setUrl(getUrl(request));
    		request.setAttribute("pb", pb);
    		return "F:/teacher/chengyuan.jsp";
    	}
    	
    	private int getPc(HttpServletRequest request) {
    		/*
    		 * 1. �õ�pc
    		 *   ���pc���������ڣ�˵��pc=1
    		 *   ���pc�������ڣ���Ҫת����int���ͼ���
    		 */
    		String value = request.getParameter("pc");
    		if(value == null || value.trim().isEmpty()) {
    			return 1;
    		}
    		return Integer.parseInt(value);
		}

    	/**
    	 * ��ȡurl
    	 *   /��Ŀ��/Servlet·��?�����ַ���
    	 */
    	private String getUrl(HttpServletRequest request) {
    		String contextPath = request.getContextPath();//��ȡ��Ŀ��
    		String servletPath = request.getServletPath();//��ȡservletPath����/stuServlet
    		String queryString = request.getQueryString();//��ȡ�ʺ�֮��Ĳ�������
    		
    		//  �жϲ����������Ƿ����pc��������������������Ҫ��ȡ��ȥ����Ҫ��һ���ݡ�
    		if(queryString.contains("&pc=")) {
    			int index = queryString.lastIndexOf("&pc=");
    			queryString = queryString.substring(0, index);
    		}
    		
    		return contextPath + servletPath + "?" + queryString;
    	}
    
    	
    	//����
    	public String preEdit(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    	
    	
    		String stuId = request.getParameter("stuId");
    		student stu = teaService.load(stuId);
    		request.setAttribute("stu", stu);//���浽request����
    		return "F:/teacher/write.jsp";
    	}
    	
    	

    	
		//�༭
    	public String edit(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
 
    		student s = CommonUtils.toBean(request.getParameterMap(), student.class);
    		teaService.edit(s);
    		request.setAttribute("msg", "�޸ĳɹ�");
    		return "F:/teacher/succ.jsp";
    	}
    	
    	//ɾ��
    	public String delete(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    	
    		String stuId = request.getParameter("stuId");
    		teaService.detele(stuId);
    		request.setAttribute("msg", "ɾ���ɹ�");
    		//request.getRequestDispatcher("/succ.jsp").forward(request, response);
    		return "F:/teacher/succ.jsp";
    	}
    	
    	//������ѯ
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
    		 * 0. ��������װ��student������
    		 * 1. �õ�pc
    		 * 2. ����ps
    		 * 3. ʹ��pc��ps���Լ��������󣬵���service�����õ�PageBean
    		 * 4. ��PageBean���浽request����
    		 * 5. ת����chengyuan.jsp
    		 */
    		// ��ȡ��ѯ����
    		student criteria = CommonUtils.toBean(request.getParameterMap(), student.class);
    		
    		/*
    		 * ����GET����ʽ�������⣡
    		 */
    		criteria = encoding(criteria);
    		
    		int pc = getPc(request);//�õ�pc
    		int ps = 10;//����ps��ֵ����ҳ10�м�¼
    		PageBean<student> pb = teaService.query(criteria, pc, ps);
    		
    		// �õ�url�����浽pb��
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

		