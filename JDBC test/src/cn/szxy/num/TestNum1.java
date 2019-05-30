package cn.szxy.num;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import cn.szxy.Util.DBUtil;
	
public class TestNum1 {
	public static void main(String[] args) {
		// 往表 T_num 中 插入 1000条数据
		int num = 1000;
		long start = System.currentTimeMillis();
		/*for(int i=0;i<num;i++){
			insertNum1(i);	
		}*/
		//insertNum2(1000);  //速度较 insertNum2()快
		//insertNum3(1000);
		//insertNum4(num);
		insertNum5(10000);
		long end = System.currentTimeMillis();
		System.out.println("时间: "+(end-start)+"ms");
	
		
	}
	/**
	 * PreparedStatement 预处理发射器
	 * 使用 批处理
	 * 统一提交事务
	 * 
	 * @param num
	 */
	private static void insertNum5(int num) {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into T_num values(?,?,?)"; 
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		try {
				//关闭事务自动提交
				conn.setAutoCommit(false);
				for(int i=1;i<num;i++){
					
					DBUtil.bindParam(pstat, getUUID(),i,getType(i));
					//添加到批处理
					pstat.addBatch();
					//pstat.executeUpdate();
			}
				//执行批处理
				pstat.executeBatch();
				//提价事务
				conn.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, pstat, conn);
		}
	}
	
	/**
	 * PreparedStatement 预处理发射器
	 * 关闭 自动提交书屋
	 * 统一提交事务
	 * @param num
	 */
	private static void insertNum4(int num) {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into T_num values(?,?,?)"; 
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		try {
				//关闭事务自动提交
				conn.setAutoCommit(false);
				for(int i=1;i<num;i++){
				DBUtil.bindParam(pstat, getUUID(),i,getType(i));
				pstat.executeUpdate();
			}
				//提价事务
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, pstat, conn);
		}
	}
	
	/**
	 * 使用 PreparedStatement 预处理发射器
	 * @param num
	 */
	private static void insertNum3(int num) {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into T_num values(?,?,?)"; 
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		try {
				for(int i=1;i<num;i++){
				DBUtil.bindParam(pstat, getUUID(),i,getType(i));
				pstat.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, pstat, conn);
		}	
	}
	
	/**
	 * Statement 发射器
	 * @param num
	 */
	private static void insertNum2(int num) {
		Connection conn = DBUtil.getConnection();
		Statement stat = DBUtil.getStatement(conn);
		try {
			for(int i=0;i<num;i++){
				//定义 SQL 语句
				String sql = "insert into T_num values('"+getUUID()+"',"+i+",'"+getType(i)+"')";
				System.out.println(sql);
				stat.execute(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, stat, conn);
		}

	}
	
	/**
	 * 插入一条数据
	 * @param num
	 */
	private static void insertNum1(int num) {
		//定义 SQL 语句
		String sql = "insert into T_num values('"+getUUID()+"',"+num+",'"+getType(num)+"')";
		System.out.println(sql);
		Connection conn = DBUtil.getConnection();
		Statement stat = DBUtil.getStatement(conn);
		try {
			stat.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, stat, conn);
		}
	}
	
	/**
	 * 获得 num 的类型
	 * 是合数还是质数
	 * @param num
	 * @return
	 */
	private static String getType(int num) {
		if(num <4 ){
			return "ZS";
		}
		for(int i=2;i<=Math.sqrt(num);i++){
			if(i%2==0){
				return "HS";
			}
		}
		return "ZS";
	}	
	
	/**
	 * 生成 UUID
	 * @return
	 */
	private static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
