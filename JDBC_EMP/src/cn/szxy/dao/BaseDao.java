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
	 * ִ�� DML ���(��ɾ��)
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
			int row = pstat.executeUpdate(); //ִ�и������
			if(row > 0){
				return true;  //ִ�и������ɹ�
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.CloseAll(null, pstat, conn);
		}
		return false;
	}
	
	/**
	 * ��ѯ�������еļ�¼
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
			ResultSetMetaData metaData = rs.getMetaData(); // ���Ԫ���ݶ���
			while(rs.next()){
				T bean =  cls.newInstance(); // ͨ����������������
				for(int i=0;i<metaData.getColumnCount();i++){
					// ���� BeanUtil ��������������
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
	 * ��ѯ������¼
	 * @param cls    �࣬ʹ�÷����ȡ
	 * @param sql    SQL ���
	 * @param params Object ����
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
