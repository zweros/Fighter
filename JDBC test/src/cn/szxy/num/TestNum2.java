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
		
		Map<String,Integer> map = new HashMap<>();//省略后面的泛型，JDK 1.7 声明
		map = CountNum();
		Set<String> keys= map.keySet();
		// 遍历 Map 集合
		for (String key : keys) {
			System.out.println(((key.equals("ZS")?"ZS":"HS")+"有"+map.get(key)+"个"));
		}
		
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println((entry.getKey().equals("ZS")?"ZS":"HS")+"有"+entry.getValue()+"个");
		}
		
		boolean row = deleteNumAll();
		if(row){
			System.out.println("更新成功");
		}else{
			System.out.println("更新失败");
		}
		
		
	}

	/**
	 *  删除所有的  num 信息
	 *  但 不删除 表格结构
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
		// 定义 SQL 语句
		String sql = "select type,count(*) from T_num group by type";
		// 获得数据库连接
		Connection conn = DBUtil.getConnection();
		// 创建发射器
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		//  结果集
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
	 *  统一 质数和合数的个数
	 */
	

	/**
	 *  查询某个数 质数还是合数
	 */
	private static void setType(int num) {
		// 定义 SQL 语句
		String sql = "select type from T_num where num = ?";
		// 获取数据库连接
		Connection conn = DBUtil.getConnection();
		// 创建发射器对象
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		// 绑定参数
		DBUtil.bindParam(pstat, num);
		ResultSet rs = null;
		try {
			rs = pstat.executeQuery();
			if(rs.next()){
				String type = rs.getString(1);
				System.out.println(num +"是一个：" +(type.equals("HS")?"HS":"ZS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(rs, pstat, conn);
		}
	}
}
