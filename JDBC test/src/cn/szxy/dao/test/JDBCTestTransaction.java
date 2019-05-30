package cn.szxy.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试 执行 DML 事务操作
 * 模拟 转账操作
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
			//注册驱动
			Class.forName("oracle.jdbc.OracleDriver");
			//获得连接
			conn = DriverManager.getConnection(url, user, password);
			
			//设置事务不自动提交
			conn.setAutoCommit(false);
			//创建发射器
			stat1 = conn.createStatement();
			stat2 = conn.createStatement();
			String sql1 = "update t_account set amoney = amoney -20 where aid = 1 ";
			String sql2 = "update t_account set amoney = amoney +20 where aid = 4 ";
			//使用发射器，并返回受影响的行数
			int rownum = stat1.executeUpdate(sql1);
			rownum += stat2.executeUpdate(sql2);
			//判断模拟转账是否成功
			if(rownum == 2){
				System.out.println("转账成功");
				conn.commit();//提交事务
				System.out.println("事务提交成功");
			}else{
				System.out.println("转账失败");
				conn.rollback();//回滚
				System.out.println("转账发生错误，回滚");
			}
		} catch (Exception e) {
			if( conn!=null){
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					System.out.println("SQL 语句发生错误"+e.getMessage());
			}
		}finally{
			if(conn!=null){//恢复事务提交的方式
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
