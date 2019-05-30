package cn.szxy.dao.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 异常处理和关闭资源
 * @author wzer
 *
 */
public class JDBCTestQuery2 {
	public static void main(String[] args) {
		//声明变量
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user = "scott";
		String password = "tiger";
		//声明
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//1 注册驱动
			Class.forName("oracle.jdbc.OracleDriver");
			//2 获得数据库连接    小技巧   Ctrl+2，L   创建对应的接受变量
		    conn = DriverManager.getConnection(url, user, password);
			//3 定义 SQL 语句
			String sql = "select ename,sal,hiredate from emp";
			//4 创建发射器
			stat = conn.createStatement();
			//5 使用发射器并执行 SQL语句,并返回结果集
			rs = stat.executeQuery(sql);
			//6 处理结果集
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
		}finally{//7 关闭顺序，从后往前关
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
