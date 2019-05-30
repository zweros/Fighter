package cn.szxy.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Eclipse С����  alt+ ���� �����ƶ�һ�е�λ��
 * JDBC ִ�� DML ����
 * @author wzer
 *
 */
public class JDBCTestUpdate {
	public static void main(String[] args) {
		//��������
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "tiger";
		//����
		Connection conn = null;
		Statement stat = null;
		try {
			//ע������
			Class.forName("oracle.jdbc.OracleDriver");
			//������ݿ�����
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
			//���� SQL ���
			//String sql = "insert into emp values('1001','С��','CLEAK',7902,sysdate,500,200,20)";
			//String sql = "update emp set sal = sal*2+100 where empno= 1001";
			String sql = "delete from emp where empno = '1001'";
			//����������
			stat = conn.createStatement();
			//ʹ�÷�����ִ�� SQL�������ؽ��
			//rowcount ָ���ݱ�����Ӱ�������
			int rowcount = stat.executeUpdate(sql);
			if(rowcount > 0){
				System.out.println("�����ɹ�");
			}else{
				System.out.println("����ʧ��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
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
