package cn.szxy.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.szxy.Util.DBUtil;
import cn.szxy.dao.BaseDao;
import cn.szxy.dao.EmpDao;
import cn.szxy.pojo.Emp;

public class EmpDaoImpl extends BaseDao implements EmpDao {

	@Override
	public List<Emp> findAll() {
		String sql = "select * from emp";
		return queryAll(Emp.class, sql);
	}

	@Override
	public Emp findByIdEmp(int empno) {
		String sql = "select * from emp where empno = ?";
		return queryOne(Emp.class, sql,empno);
	}

	@Override
	public boolean insertEmp(Emp emp){
		String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
		Object[] obj = {
		            emp.getEmpno(),
		            emp.getEname(),
		            emp.getJob(),
		            emp.getMgr(),
		            emp.getHirdate(),
		            emp.getSal(),
		            emp.getComm(),
		            emp.getDeptno()
		};
		return update(sql,obj);
	}
	
	@Override
	public boolean deleteEmp(int empno) {
		String sql = "delete from emp where empno = ?";
		return update(sql,empno);
	}

	@Override
	public boolean updateBySalEmp(int empno , double sal) {
		String sql = "update emp set sal=? where empno=?	";
		return update(sql,sal,empno);
	}

	
	
}
