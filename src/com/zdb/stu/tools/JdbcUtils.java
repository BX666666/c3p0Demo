package com.zdb.stu.tools;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	// �����ļ���Ĭ�����ã�Ҫ����������c3p0-config.xml������
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	// ��������ר�����ӣ�
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	/**
	 * ʹ�����ӳط���һ�����Ӷ���
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection con = tl.get();
		// ��con������null��˵���Ѿ����ù�beginTransaction()����ʾ����������
		if(con != null) return con;
		return dataSource.getConnection();
	}
	
	/**
	 * �������ӳض���
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * ��������
	 * 1. ��ȡһ��Connection����������setAutoComnmit(false)
	 * 2. ��Ҫ��֤dao��ʹ�õ����������Ǹոմ����ģ�
	 * --------------
	 * 1. ����һ��Connection������Ϊ�ֶ��ύ
	 * 2. �����Connection��dao�ã�
	 * 3. ��Ҫ��commitTransaction��rollbackTransaction���Ի�ȡ����
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException {
		Connection con = tl.get();
		if(con != null) throw new SQLException("�Ѿ����������񣬾Ͳ�Ҫ�ظ������ˣ�");
		/*
		 * 1. ��con��ֵ��
		 * 2. ��con����Ϊ�ֶ��ύ��
		 */
		con = getConnection();//��con��ֵ����ʾ�����Ѿ���ʼ��
		con.setAutoCommit(false);
		
		tl.set(con);//�ѵ�ǰ�̵߳����ӱ���������
	}
	
	/**
	 * �ύ����
	 * 1. ��ȡbeginTransaction�ṩ��Connection��Ȼ�����commit����
	 * @throws SQLException 
	 */
	public static void commitTransaction() throws SQLException {
		Connection con = tl.get();//��ȡ��ǰ�̵߳�ר������
		if(con == null) throw new SQLException("��û�п������񣬲����ύ��");
		/*
		 * 1. ֱ��ʹ��con.commit()
		 */
		con.commit();
		con.close();
		// ��������Ϊnull����ʾ�����Ѿ������ˣ��´���ȥ����getConnection()���صľͲ���con��
		tl.remove();//��tl���Ƴ�����
	}
	
	/**
	 * �ύ����
	 * 1. ��ȡbeginTransaction�ṩ��Connection��Ȼ�����rollback����
	 * @throws SQLException 
	 */
	public static void rollbackTransaction() throws SQLException {
		Connection con = tl.get();
		if(con == null) throw new SQLException("��û�п������񣬲��ܻع���");
		/*
		 * 1. ֱ��ʹ��con.rollback()
		 */
		con.rollback();
		con.close();
		tl.remove();
	}
	
	/**
	 * �ͷ����ӡ�
	 * @param connection
	 * @throws SQLException 
	 */
	public static void releaseConnection(Connection connection) throws SQLException {
		Connection con = tl.get();
		/*
		 * �ж����ǲ�������ר�ã�����ǣ��Ͳ��رգ�
		 * �����������ר�ã���ô��Ҫ�رգ�
		 */
		// ���con == null��˵������û��������ôconnectionһ����������ר�õģ�
		if(con == null) connection.close();
		// ���con != null��˵����������ô��Ҫ�жϲ��������Ƿ���con��ȣ������ȣ�˵���������Ӳ�������ר������
		if(con != connection) connection.close();
	}
}
