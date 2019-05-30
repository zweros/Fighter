package cn.szxy.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Eclipse 小技巧  alt+ 上下 可以移动一行的位置
 * JDBC 执行 DML 操作
 * @author wzer
 *
 */
public class JDBCTestUpdate {
	public static void main(String[] args) {
		//声明变量
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "tiger";
		//声明
		Connection conn = null;
		Statement stat = null;
		try {
			//注册驱动
			Class.forName("oracle.jdbc.OracleDriver");
			//获得数据库连接
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
			//定义 SQL 语句
			//String sql = "insert into emp values('1001','小名','CLEAK',7902,sysdate,500,200,20)";
			//String sql = "update emp set sal = sal*2+100 where empno= 1001";
			String sql = "delete from emp where empno = '1001'";
			//创建发射器
			stat = conn.createStatement();
			//使用发射器执行 SQL，并返回结果
			//rowcount 指数据表中受影响的行数
			int rowcount = stat.executeUpdate(sql);
			if(rowcount > 0){
				System.out.println("操作成功");
			}else{
				System.out.println("操作失败");
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
