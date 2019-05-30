package cn.szxy.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import cn.szxy.Util.DBUtil;

/**
 * ʹ�� Statement �� PreparedStatement �ӽӿ�
 * ���� ռλ�� ��
 * ִ�� SQL Ч�ʸ� ��ȫ�Ժ�
 * @author wzer
 *
 */
public class Login2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("�������û�����");
		String user = input.next();
		System.out.println("����������");
		String password = input.next();
		
		Connection conn = DBUtil.getConnection();
		
		// ? ռλ��
		String sql = "select count(*) from T_user where tuser=? and tpassword = ?";
		System.out.println(sql);
		
		PreparedStatement pstat = null;
		ResultSet rs = null;
	    try {
	    	// Ԥ��������
			pstat = DBUtil.getPreparedStatement(conn, sql);
			//�� ռλ��
			DBUtil.bindParam(pstat,user,password);
	    	rs = pstat.executeQuery();
			if(rs.next()){
		    	if(rs.getInt(1) == 0){
		    		System.out.println("�û������������");
		    	}else{
		    		System.out.println("��ӭ�û�  "+user+"  ��¼�ɹ�");
		    	}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//  pstat ����ת�� ������ӿ�����ӿ�ת��  --ʹ�ö�̬
			DBUtil.CloseAll(rs, pstat, conn);
		}
	    input.close();
	   
	    
	}
}
