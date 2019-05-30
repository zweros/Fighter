package cn.szxy.num;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import cn.szxy.Util.DBUtil;
	
public class TestNum1 {
	public static void main(String[] args) {
		// ���� T_num �� ���� 1000������
		int num = 1000;
		long start = System.currentTimeMillis();
		/*for(int i=0;i<num;i++){
			insertNum1(i);	
		}*/
		//insertNum2(1000);  //�ٶȽ� insertNum2()��
		//insertNum3(1000);
		//insertNum4(num);
		insertNum5(10000);
		long end = System.currentTimeMillis();
		System.out.println("ʱ��: "+(end-start)+"ms");
	
		
	}
	/**
	 * PreparedStatement Ԥ��������
	 * ʹ�� ������
	 * ͳһ�ύ����
	 * 
	 * @param num
	 */
	private static void insertNum5(int num) {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into T_num values(?,?,?)"; 
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		try {
				//�ر������Զ��ύ
				conn.setAutoCommit(false);
				for(int i=1;i<num;i++){
					
					DBUtil.bindParam(pstat, getUUID(),i,getType(i));
					//��ӵ�������
					pstat.addBatch();
					//pstat.executeUpdate();
			}
				//ִ��������
				pstat.executeBatch();
				//�������
				conn.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, pstat, conn);
		}
	}
	
	/**
	 * PreparedStatement Ԥ��������
	 * �ر� �Զ��ύ����
	 * ͳһ�ύ����
	 * @param num
	 */
	private static void insertNum4(int num) {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into T_num values(?,?,?)"; 
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		try {
				//�ر������Զ��ύ
				conn.setAutoCommit(false);
				for(int i=1;i<num;i++){
				DBUtil.bindParam(pstat, getUUID(),i,getType(i));
				pstat.executeUpdate();
			}
				//�������
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, pstat, conn);
		}
	}
	
	/**
	 * ʹ�� PreparedStatement Ԥ��������
	 * @param num
	 */
	private static void insertNum3(int num) {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into T_num values(?,?,?)"; 
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		try {
				for(int i=1;i<num;i++){
				DBUtil.bindParam(pstat, getUUID(),i,getType(i));
				pstat.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, pstat, conn);
		}	
	}
	
	/**
	 * Statement ������
	 * @param num
	 */
	private static void insertNum2(int num) {
		Connection conn = DBUtil.getConnection();
		Statement stat = DBUtil.getStatement(conn);
		try {
			for(int i=0;i<num;i++){
				//���� SQL ���
				String sql = "insert into T_num values('"+getUUID()+"',"+i+",'"+getType(i)+"')";
				System.out.println(sql);
				stat.execute(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, stat, conn);
		}

	}
	
	/**
	 * ����һ������
	 * @param num
	 */
	private static void insertNum1(int num) {
		//���� SQL ���
		String sql = "insert into T_num values('"+getUUID()+"',"+num+",'"+getType(num)+"')";
		System.out.println(sql);
		Connection conn = DBUtil.getConnection();
		Statement stat = DBUtil.getStatement(conn);
		try {
			stat.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, stat, conn);
		}
	}
	
	/**
	 * ��� num ������
	 * �Ǻ�����������
	 * @param num
	 * @return
	 */
	private static String getType(int num) {
		if(num <4 ){
			return "ZS";
		}
		for(int i=2;i<=Math.sqrt(num);i++){
			if(i%2==0){
				return "HS";
			}
		}
		return "ZS";
	}	
	
	/**
	 * ���� UUID
	 * @return
	 */
	private static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
