package cn.szxy.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * �����������ݿ����
 * @author wzer
 *
 */
public class JDBCTestQuery {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//����
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "tiger";
		//ע������
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//�������ݿ�����,��Ҫ�õ�����������
		Connection conn = DriverManager.getConnection(url, user, password);
		//System.out.println(conn);
		//���� SQL ���
		String sql = "select empno,ename,hiredate from emp";
		//���� ������
		Statement state = conn.createStatement();
		// ���� sql ��䣬�����ؽ����
		ResultSet result = state.executeQuery(sql);
		// ���� �����
		System.out.println("empno\tename\thiredate");
		while(result.next()){
			int empno = result.getInt(1);//���ݿ��е������� 1 ��ʼ
			String ename = result.getString("ename"); //������������
			Date hiredate = result.getDate(3);
			System.out.println(empno+"\t"+ename+"\t"+hiredate.toLocaleString());
		}
		//�ر���Դ
		result.close();
		state.close();
		conn.close();	
	}
}
