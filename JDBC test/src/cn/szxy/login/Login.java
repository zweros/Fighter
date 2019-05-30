package cn.szxy.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import cn.szxy.Util.DBUtil;

public class Login {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String user = input.next();
		System.out.println("请输入密码");
		String password = input.next();
		
		Connection conn = DBUtil.getConnection();
		Statement stat = DBUtil.getStatement(conn);
		String sql = 
	"select count(*) from T_user where tuser='"+user+"' and tpassword = '" +password+ "'";
		System.out.println(sql);
		ResultSet rs = null;
	    try {
			rs = stat.executeQuery(sql);
			if(rs.next()){
		    	if(rs.getInt(1) == 0){
		    		System.out.println("用户名或密码错误");
		    	}else{
		    		System.out.println("欢迎用户  "+user+"  登录成功");
		    	}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(rs, stat, conn);
		}
	    input.close();
	   
	    
	}
}
