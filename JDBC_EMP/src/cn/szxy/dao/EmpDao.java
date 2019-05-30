package cn.szxy.dao;

import java.util.List;

import cn.szxy.pojo.Emp;

public interface EmpDao {
	
	/**
	 * 查询所有雇员信息
	 * @return
	 */
	List<Emp> findAll();
	
	/**
	 * 根据雇员的 ID 查询雇员信息
	 * @param empno
	 * @return
	 */
	Emp  findByIdEmp(int empno);
	
	/**
	 * 添加雇员
	 * @param emp
	 * @return
	 */
	boolean insertEmp(Emp emp);
	
	/**
	 * 更新雇员信息
	 * @param emp
	 * @return
	 */
	boolean updateBySalEmp(int empno,double sal);
	
	/**
	 * 删除雇员
	 * @param empno
	 * @return
	 */
	boolean deleteEmp(int empno);
}
