package cn.szxy.dao.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �쳣����͹ر���Դ
 * @author wzer
 *
 */
public class JDBCTestQuery2 {
	public static void main(String[] args) {
		//��������
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "tiger";
		//����
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//1 ע������
			Class.forName("oracle.jdbc.OracleDriver");
			//2 ������ݿ�����    С����   Ctrl+2��L   ������Ӧ�Ľ��ܱ���
		    conn = DriverManager.getConnection(url, user, password);
			//3 ���� SQL ���
			String sql = "select ename,sal,hiredate from emp";
			//4 ����������
			stat = conn.createStatement();
			//5 ʹ�÷�������ִ�� SQL���,�����ؽ����
			rs = stat.executeQuery(sql);
			//6 ��������
			System.out.println("ename"+"\t"+"sal"+"\t"+"hiredate");
			while(rs.next()){
				String ename = rs.getString(1);
				double sal = rs.getDouble("sal");
				Date hiredate = rs.getDate("hiredate");
				System.out.println(ename+"\t"+sal+"\t"+hiredate);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//7 �ر�˳�򣬴Ӻ���ǰ��
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stat != null){
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
}
