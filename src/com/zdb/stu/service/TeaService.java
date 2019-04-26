package com.zdb.stu.service;

import java.util.List;

import com.zdb.stu.daoimpl.TeaDaoimpl;
import com.zdb.stu.domain.PageBean;
import com.zdb.stu.domain.student;
import com.zdb.stu.domain.Teacher;

	
	public class TeaService {
		private TeaDaoimpl TeaDaoimpl = new TeaDaoimpl();
		
		public Teacher login(Teacher form) throws UserException {
			Teacher tea=TeaDaoimpl.findBytName(form.gettName());
			if(tea == null) throw new UserException("用户名不存在");
			if(!tea.gettPassword().equals(form.gettPassword()))
				throw new UserException("密码错误");
			return tea;
		}
		
		//添加学生信息

		public void add(student s) {
			TeaDaoimpl.add(s);
		}
		
		 // 查询所有学生信息
		
		public PageBean<student> findAll(int pc, int ps) {
			return TeaDaoimpl.findAll(pc,ps);
		}


		// 加载学生信息
		
		public student load(String stuId) {
			return TeaDaoimpl.load(stuId);
		}

		 //编辑学生信息

		public void edit(student s) {
			TeaDaoimpl.edit(s);
		}

		//删除学生信息
		public void detele(String stuId) {
			TeaDaoimpl.detele(stuId);
		}
	
		 // 多条件查询
		/*public List<student> query(student criteria) {
			return stuDao.query(criteria);
		}*/

		public PageBean<student> query(student criteria, int pc, int ps) {
			return TeaDaoimpl.query(criteria, pc, ps);
		}

	}

