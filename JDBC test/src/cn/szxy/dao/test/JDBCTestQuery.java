package cn.szxy.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * 用于连接数据库测试
 * @author wzer
 *
 */
public class JDBCTestQuery {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//声明
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "tiger";
		//注册驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//建立数据库连接,需要用到驱动管理器
		Connection conn = DriverManager.getConnection(url, user, password);
		//System.out.println(conn);
		//定义 SQL 语句
		String sql = "select empno,ename,hiredate from emp";
		//建立 发送器
		Statement state = conn.createStatement();
		// 发送 sql 语句，并返回结果集
		ResultSet result = state.executeQuery(sql);
		// 处理 结果集
		System.out.println("empno\tename\thiredate");
		while(result.next()){
			int empno = result.getInt(1);//数据库列的索引从 1 开始
			String ename = result.getString("ename"); //根据列名索引
			Date hiredate = result.getDate(3);
			System.out.println(empno+"\t"+ename+"\t"+hiredate.toLocaleString());
		}
		//关闭资源
		result.close();
		state.close();
		conn.close();	
	}
}
