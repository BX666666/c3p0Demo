package com.zdb.stu.daoimpl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zdb.stu.domain.PageBean;
import com.zdb.stu.domain.Teacher;
import com.zdb.stu.domain.student;
import cn.itcast.jdbc.TxQueryRunner;


public class TeaDaoimpl {
	private QueryRunner qr = new TxQueryRunner();

	public Teacher findBytName(String tName) {
		String sql="select * from teacher where name=?";
		try {
			return  qr.query(sql, new BeanHandler<Teacher>(Teacher.class),tName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ���ѧ����Ϣ
	 */
	public void add(student s) {
		try {
			String sql = "insert into student values(?,?,?,?,?,?)";
			Object[] params = { s.getStuId(), s.getStuName(), s.getStuSex(),
					s.getStuAge(), s.getStuNum(), s.getStuScore()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��ѯ����ѧ����Ϣ
	 */
	public PageBean<student> findAll(int pc, int ps) {
		try {
			
			PageBean<student> pb = new PageBean<student>();
			pb.setPc(pc);
			pb.setPs(ps);

			 //�õ�tr

			String sql = "select count(*) from student";
			Number num = (Number)qr.query(sql, new ScalarHandler());
			int tr = num.intValue();
			pb.setTr(tr);
			/*
			 * �õ�beanList
			 */
			sql = "select * from student limit ?,?";
			List<student> beanList = qr.query(sql, 
					new BeanListHandler<student>(student.class), 
					(pc-1)*ps, ps);
			pb.setBeanList(beanList);
			return pb;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ����ѧ����Ϣ
	 */
	public student load(String stuId) {
		try {
			String sql = "select * from student where stuId=?";
			return qr.query(sql, new BeanHandler<student>(student.class), stuId);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * �޸�ѧ����Ϣ
	 */
	public void edit(student s) {
		try {
			String sql = "update student set stuId=?,stuName=?,stuSex=?," +
					"stuAge=?,stuNum=?,stuScore=? where stuId=? ";
			Object[] params = {s.getStuId(), s.getStuName(), s.getStuSex(),
					s.getStuAge(), s.getStuNum(), s.getStuScore(),s.getStuId()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 *ɾ��ѧ����Ϣ 
	 */
	public void detele(String stuId) {
		try {
			String sql = "delete from student where stuId=?";
			qr.update(sql, stuId);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
		
	

	/**
	 * ��������ѯ
	 */
	/*public List<student> query(student criteria) {
		try {
			//����sql���ǰ�벿
			StringBuilder sql = new StringBuilder("select * from student where 1=1");
	
			//�ж������������sql��׷��where���
			List<Object> params = new ArrayList<Object>();//����һ��ArrayListװ�ز���
			String stuName = criteria.getStuName();
			if(stuName != null && !stuName.trim().isEmpty()) {
				sql.append(" and stuName like ?");
				params.add("%" + stuName + "%");
			}
			
			String stuSex = criteria.getStuSex();
			if(stuSex != null && !stuSex.trim().isEmpty()) {
				sql.append(" and stuSex=?");
				params.add(stuSex);
			}
			
			String stuAge = criteria.getStuAge();
			if(stuAge != null && !stuAge.trim().isEmpty()) {
				sql.append(" and stuAge like ?");
				params.add("%" + stuAge + "%");
			}
			
			String stuNum = criteria.getStuNum();
			if(stuNum != null && !stuNum.trim().isEmpty()) {
				sql.append(" and stuNum like ?");
				params.add("%" + stuNum + "%");
			}
			
			//ִ��
			return qr.query(sql.toString(), 
					new BeanListHandler<student>(student.class), 
					params.toArray());
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}*/

	public PageBean<student> query(student criteria, int pc, int ps) {
		try {
			/*
			 * 1. ����PageBean����
			 * 2. �������е����ԣ�pc��ps
			 * 3. �õ�tr
			 * 4. �õ�beanList
			 */
			/*
			 * ����pb��������������
			 */
			PageBean<student> pb = new PageBean<student>();
			pb.setPc(pc);
			pb.setPs(ps);
			
			/*
			 * �õ�tr
			 */
			
			/*
			 * 1. ����һ��sql���ǰ�벿
			 */
			StringBuilder cntSql = new StringBuilder("select count(*) from student");
			StringBuilder whereSql = new StringBuilder(" where 1=1");
			/*
			 * 2. �ж������������sql��׷��where�Ӿ�
			 */
			/*
			 * 3. ����һ��ArrayList������װ�ز���ֵ
			 */
			List<Object> params = new ArrayList<Object>();
			String stuName = criteria.getStuName();
			if(stuName != null && !stuName.trim().isEmpty()) {
				whereSql.append(" and stuName like ?");
				params.add("%" + stuName + "%");
			}
			
			String stuSex = criteria.getStuSex();
			if(stuSex != null && !stuSex.trim().isEmpty()) {
				whereSql.append(" and stuSex=?");
				params.add(stuSex);
			}
			
			String stuAge = criteria.getStuAge();
			if(stuAge != null && !stuAge.trim().isEmpty()) {
				whereSql.append(" and stuAge like ?");
				params.add("%" + stuAge + "%");
			}
			
			String stuNum = criteria.getStuNum();
			if(stuNum != null && !stuNum.trim().isEmpty()) {
				whereSql.append(" and stuNum like ?");
				params.add("%" + stuNum + "%");
			}
			
			/*
			 * select count(*) .. + where�Ӿ�
			 * ִ��֮
			 */
			Number num = (Number)qr.query(cntSql.append(whereSql).toString(), 
					new ScalarHandler(), params.toArray());
			int tr = num.intValue();
			pb.setTr(tr);
			
			/*
			 * �õ�beanList
			 */
			StringBuilder sql = new StringBuilder("select * from student");
			// ���ǲ�ѯbeanList��һ��������Ҫ����limit�Ӿ�
			StringBuilder limitSql = new StringBuilder(" limit ?,?");
			// params����Ҫ����limit�������ʺŶ�Ӧ��ֵ
			params.add((pc-1)*ps);
			params.add(ps);
			// ִ��֮
			List<student> beanList = qr.query(sql.append(whereSql).append(limitSql).toString(), 
					new BeanListHandler<student>(student.class), 
					params.toArray());
			pb.setBeanList(beanList);
			
			return pb;
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
