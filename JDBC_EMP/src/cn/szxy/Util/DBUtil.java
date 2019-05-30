package cn.szxy.Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * ���ݿ����ӹ���
 * ����룺 ��������Ϣ��ȡ����һ�������ļ��� Ȼ���ó�����ִ�й����У� ��ȡ������Ϣ
 * �ô��� ���Զ�̬�Ļ�ȡ������Ϣ�� �����ں��������ά��
 * 
 * Java�У� �ṩ��һ���࣬ ��Properties�࣬ ���ڶ�ȡproperties�����ļ�
 * @author wzer
 *
 */
public class DBUtil {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	
	static{
		try {
			//���� Properties ����
			Properties prop = new Properties();
			//���� db.properties �����ļ�
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			// ��ȡ��Ϣ�����г�ʼ��
			driver = prop.getProperty("jdbc.driver").trim();
			url = prop.getProperty("jdbc.url").trim();
			user = prop.getProperty("jdbc.user").trim();
			password = prop.getProperty("jdbc.password").trim();
			
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �������ݿ�����
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.out.println(
					"DBUtil.getConnection([URL:]"+url+"[USER:]"+user+"[PASSWORD:]"+password+"");
		}
		return conn;
	}
	/**
	 * ���� ������
	 * @param conn
	 * @return
	 */
	public static Statement getStatement(Connection conn){
		Statement stat = null;
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stat;
	}
	/**
	 * Ԥ���������
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static PreparedStatement  getPreparedStatement(Connection conn,String sql){
		PreparedStatement pstat = null;
		try {
			pstat = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstat;
	}
	/**
	 * �󶨲���
	 * @param pstat
	 * @param params
	 */
	public static void bindParam(PreparedStatement pstat,Object ...params){
		// i�� 1 ��ʼ����Ϊ���ݿ�������� 1 ��ʼ
		// params ��һ�����飬�±�� 0 ��ʼ
		for(int i=1;i<=params.length;i++){
			try {
				pstat.setObject(i, params[i-1]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ͳһ�ر���Դ
	 * @param rs
	 * @param stat
	 * @param conn
	 */
	public static void CloseAll(ResultSet rs,Statement stat,Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stat!= null){
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
	
