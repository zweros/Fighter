package cn.szxy.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���� ִ�� DML �������
 * ģ�� ת�˲���
 * @author wzer
 *
 */
public class JDBCTestTransaction {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "szxy";
		String password = "123";
		
		Connection conn = null;
		Statement stat1 = null; 
		Statement stat2 = null;
		
		try {
			//ע������
			Class.forName("oracle.jdbc.OracleDriver");
			//�������
			conn = DriverManager.getConnection(url, user, password);
			
			//���������Զ��ύ
			conn.setAutoCommit(false);
			//����������
			stat1 = conn.createStatement();
			stat2 = conn.createStatement();
			String sql1 = "update t_account set amoney = amoney -20 where aid = 1 ";
			String sql2 = "update t_account set amoney = amoney +20 where aid = 4 ";
			//ʹ�÷���������������Ӱ�������
			int rownum = stat1.executeUpdate(sql1);
			rownum += stat2.executeUpdate(sql2);
			//�ж�ģ��ת���Ƿ�ɹ�
			if(rownum == 2){
				System.out.println("ת�˳ɹ�");
				conn.commit();//�ύ����
				System.out.println("�����ύ�ɹ�");
			}else{
				System.out.println("ת��ʧ��");
				conn.rollback();//�ع�
				System.out.println("ת�˷������󣬻ع�");
			}
		} catch (Exception e) {
			if( conn!=null){
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					System.out.println("SQL ��䷢������"+e.getMessage());
			}
		}finally{
			if(conn!=null){//�ָ������ύ�ķ�ʽ
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stat1 != null){
				try {
					stat1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stat2 != null){
				try {
					stat2.close();
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
