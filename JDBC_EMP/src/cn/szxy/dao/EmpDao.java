package cn.szxy.dao;

import java.util.List;

import cn.szxy.pojo.Emp;

public interface EmpDao {
	
	/**
	 * ��ѯ���й�Ա��Ϣ
	 * @return
	 */
	List<Emp> findAll();
	
	/**
	 * ���ݹ�Ա�� ID ��ѯ��Ա��Ϣ
	 * @param empno
	 * @return
	 */
	Emp  findByIdEmp(int empno);
	
	/**
	 * ��ӹ�Ա
	 * @param emp
	 * @return
	 */
	boolean insertEmp(Emp emp);
	
	/**
	 * ���¹�Ա��Ϣ
	 * @param emp
	 * @return
	 */
	boolean updateBySalEmp(int empno,double sal);
	
	/**
	 * ɾ����Ա
	 * @param empno
	 * @return
	 */
	boolean deleteEmp(int empno);
}
