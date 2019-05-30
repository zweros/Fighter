package cn.szxy.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import cn.szxy.Util.DBUtil;

public class BaseDao{
	/**
	 * 执行 DML 语句(增删改)
	 * @param sql
	 * @param parmas
	 * @return
	 */
	public boolean update(String sql,Object ... params){
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = DBUtil.getConnection();
			pstat = DBUtil.getPreparedStatement(conn, sql);
			DBUtil.bindParam(pstat, params);
			int row = pstat.executeUpdate(); //执行更新语句
			if(row > 0){
				return true;  //执行更新语句成功
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, pstat, conn);
		}
		return false;
	}
	
	/**
	 * 查询表中所有的记录
	 * @param cls
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> List<T> queryAll(Class<T> cls,String sql,Object ... params){
		List<T> list = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstat = DBUtil.getPreparedStatement(conn, sql);
		DBUtil.bindParam(pstat, params);
		ResultSet rs = null;
		try {
			rs =  pstat.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData(); // 获得元数据对象
			while(rs.next()){
				T bean =  cls.newInstance(); // 通过反射来创建对象
				for(int i=0;i<metaData.getColumnCount();i++){
					// 利用 BeanUtil 工具类设置属性
					BeanUtils.setProperty(bean, metaData.getColumnLabel(i+1).toLowerCase(),rs.getObject(i+1));					
				}
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(rs, pstat, conn);
		}
		return list;
	}
	
	/**
	 * 查询单个记录
	 * @param cls    类，使用反射获取
	 * @param sql    SQL 语句
	 * @param params Object 数组
	 * @return
	 */
	protected <T> T queryOne(Class<T> cls, String sql, Object... params) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = DBUtil.getPreparedStatement(conn, sql);
		DBUtil.bindParam(pstmt, params);
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			if(rs.next()) {
				T bean = cls.newInstance();
				for(int i=0; i<metaData.getColumnCount(); i++) {
					BeanUtils.setProperty(bean, metaData.getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
				}
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.CloseAll(null, pstmt, conn);
		}
		return null;
	}
}
