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
 * 数据库连接工具
 * 软编码： 将配置信息提取生成一个配置文件， 然后让程序在执行过程中， 读取配置信息
 * 好处： 可以动态的获取配置信息， 有助于后续代码的维护
 * 
 * Java中， 提供了一个类， 叫Properties类， 用于读取properties配置文件
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
			//创建 Properties 对象
			Properties prop = new Properties();
			//加载 db.properties 配置文件
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			// 读取信息并进行初始化
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
	 * 建立数据库连接
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
	 * 创建 发射器
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
	 * 预处理编译器
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
	 * 绑定参数
	 * @param pstat
	 * @param params
	 */
	public static void bindParam(PreparedStatement pstat,Object ...params){
		// i从 1 开始是因为数据库的索引从 1 开始
		// params 是一个数组，下标从 0 开始
		for(int i=1;i<=params.length;i++){
			try {
				pstat.setObject(i, params[i-1]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 统一关闭资源
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
	
