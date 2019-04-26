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
	 * 添加学生信息
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
	 * 查询所有学生信息
	 */
	public PageBean<student> findAll(int pc, int ps) {
		try {
			
			PageBean<student> pb = new PageBean<student>();
			pb.setPc(pc);
			pb.setPs(ps);

			 //得到tr

			String sql = "select count(*) from student";
			Number num = (Number)qr.query(sql, new ScalarHandler());
			int tr = num.intValue();
			pb.setTr(tr);
			/*
			 * 得到beanList
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
	 * 加载学生信息
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
	 * 修改学生信息
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
	 *删除学生信息 
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
	 * 多条件查询
	 */
	/*public List<student> query(student criteria) {
		try {
			//给出sql语句前半部
			StringBuilder sql = new StringBuilder("select * from student where 1=1");
	
			//判断条件，完成向sql中追加where语句
			List<Object> params = new ArrayList<Object>();//创建一个ArrayList装载参数
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
			
			//执行
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
			 * 1. 创建PageBean对象　
			 * 2. 设置已有的属性，pc和ps
			 * 3. 得到tr
			 * 4. 得到beanList
			 */
			/*
			 * 创建pb，设置已有属性
			 */
			PageBean<student> pb = new PageBean<student>();
			pb.setPc(pc);
			pb.setPs(ps);
			
			/*
			 * 得到tr
			 */
			
			/*
			 * 1. 给出一个sql语句前半部
			 */
			StringBuilder cntSql = new StringBuilder("select count(*) from student");
			StringBuilder whereSql = new StringBuilder(" where 1=1");
			/*
			 * 2. 判断条件，完成向sql中追加where子句
			 */
			/*
			 * 3. 创建一个ArrayList，用来装载参数值
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
			 * select count(*) .. + where子句
			 * 执行之
			 */
			Number num = (Number)qr.query(cntSql.append(whereSql).toString(), 
					new ScalarHandler(), params.toArray());
			int tr = num.intValue();
			pb.setTr(tr);
			
			/*
			 * 得到beanList
			 */
			StringBuilder sql = new StringBuilder("select * from student");
			// 我们查询beanList这一步，还需要给出limit子句
			StringBuilder limitSql = new StringBuilder(" limit ?,?");
			// params中需要给出limit后两个问号对应的值
			params.add((pc-1)*ps);
			params.add(ps);
			// 执行之
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
