package cn.szxy.num;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.szxy.Util.DBUtil;

public class TestNum2 {
	public static void main(String[] args) {
		
		//setType(647);
		
		Map<String,Integer> map = new HashMap<>();//ʡ�Ժ���ķ��ͣ�JDK 1.7 ����
		map = CountNum();
		Set<String> keys= map.keySet();
		// ���� Map ����
		for (String key : keys) {
			System.out.println(((key.equals("ZS")?"ZS":"HS")+"��"+map.get(key)+"��"));
		}
		
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println((entry.getKey().equals("ZS")?"ZS":"HS")+"��"+entry.getValue()+"��");
		}
		
		boolean row = deleteNumAll();
		if(row){
			System.out.println("���³ɹ�");
		}else{
			System.out.println("����ʧ��");
		}
		
		
	}

	/**
	 *  ɾ�����е�  num ��Ϣ
	 *  �� ��ɾ�� ���ṹ
	 */
	private static boolean deleteNumAll() {
		String sql = "truncate table T_num";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
	
		int row = 0;
		try {
			row = pstat.executeUpdate();
			System.out.println(row);
			return true;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, pstat, conn);
		}
		return false;
	}

	private static Map<String, Integer> CountNum() {
		Map<String,Integer> map = new HashMap<>();
		// ���� SQL ���
		String sql = "select type,count(*) from T_num group by type";
		// ������ݿ�����
		Connection conn = DBUtil.getConnection();
		// ����������
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		//  �����
		ResultSet rs = null;
		try {
			rs = pstat.executeQuery();
			while(rs.next()){
				String type = rs.getString("Type");
				int counter = rs.getInt(2);
				map.put(type, counter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(rs, pstat, conn);
		}
				
		return map;
	}

	/**
	 *  ͳһ �����ͺ����ĸ���
	 */
	

	/**
	 *  ��ѯĳ���� �������Ǻ���
	 */
	private static void setType(int num) {
		// ���� SQL ���
		String sql = "select type from T_num where num = ?";
		// ��ȡ���ݿ�����
		Connection conn = DBUtil.getConnection();
		// ��������������
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		// �󶨲���
		DBUtil.bindParam(pstat, num);
		ResultSet rs = null;
		try {
			rs = pstat.executeQuery();
			if(rs.next()){
				String type = rs.getString(1);
				System.out.println(num +"��һ����" +(type.equals("HS")?"HS":"ZS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(rs, pstat, conn);
		}
	}
}
