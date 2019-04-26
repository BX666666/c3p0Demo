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
			if(tea == null) throw new UserException("�û���������");
			if(!tea.gettPassword().equals(form.gettPassword()))
				throw new UserException("�������");
			return tea;
		}
		
		//���ѧ����Ϣ

		public void add(student s) {
			TeaDaoimpl.add(s);
		}
		
		 // ��ѯ����ѧ����Ϣ
		
		public PageBean<student> findAll(int pc, int ps) {
			return TeaDaoimpl.findAll(pc,ps);
		}


		// ����ѧ����Ϣ
		
		public student load(String stuId) {
			return TeaDaoimpl.load(stuId);
		}

		 //�༭ѧ����Ϣ

		public void edit(student s) {
			TeaDaoimpl.edit(s);
		}

		//ɾ��ѧ����Ϣ
		public void detele(String stuId) {
			TeaDaoimpl.detele(stuId);
		}
	
		 // ��������ѯ
		/*public List<student> query(student criteria) {
			return stuDao.query(criteria);
		}*/

		public PageBean<student> query(student criteria, int pc, int ps) {
			return TeaDaoimpl.query(criteria, pc, ps);
		}

	}

