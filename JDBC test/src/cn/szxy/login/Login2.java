package cn.szxy.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import cn.szxy.Util.DBUtil;

/**
 * 使用 Statement 的 PreparedStatement 子接口
 * 采用 占位符 ？
 * 执行 SQL 效率高 安全性好
 * @author wzer
 *
 */
public class Login2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String user = input.next();
		System.out.println("请输入密码");
		String password = input.next();
		
		Connection conn = DBUtil.getConnection();
		
		// ? 占位符
		String sql = "select count(*) from T_user where tuser=? and tpassword = ?";
		System.out.println(sql);
		
		PreparedStatement pstat = null;
		ResultSet rs = null;
	    try {
	    	// 预处理发射器
			pstat = DBUtil.getPreparedStatement(conn, sql);
			//填 占位符
			DBUtil.bindParam(pstat,user,password);
	    	rs = pstat.executeQuery();
			if(rs.next()){
		    	if(rs.getInt(1) == 0){
		    		System.out.println("用户名或密码错误");
		    	}else{
		    		System.out.println("欢迎用户  "+user+"  登录成功");
		    	}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//  pstat 向上转型 从子类接口向父类接口转变  --使用多态
			DBUtil.CloseAll(rs, pstat, conn);
		}
	    input.close();
	   
	    
	}
}
