package cn.szxy.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Test{
	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection();
		
		System.out.println(conn);
		
	}
}
