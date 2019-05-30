package cn.szxy.test;

import java.sql.Date;
import java.util.List;

import cn.szxy.dao.EmpDao;
import cn.szxy.dao.Impl.EmpDaoImpl;
import cn.szxy.pojo.Emp;
import cn.szxy.view.MainView;

/**
 * ������
 *
 */
public class Test {
	public static void test1(){//��������
		EmpDao empDao = new EmpDaoImpl();
		List<Emp> emps = empDao.findAll();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
	}
	public static void test2(){//���ҵ���
		EmpDaoImpl empdao = new EmpDaoImpl();
		Emp emp = empdao.findByIdEmp(7369);
		System.out.println(emp);
	}
	public static void test3(){//����
		EmpDaoImpl empdao = new EmpDaoImpl();
		Emp emp = new Emp(7379,"SMITH2","CLEAK","7903",new Date(2008-12-2),8000.00,0.0,20);
		boolean flag = empdao.insertEmp(emp);
		if(flag){
			System.out.println("ִ�гɹ�");
		}else{
			System.out.println("ִ��ʧ��");
		}
		System.out.println(emp);
	}
	
	public static void test4(){//����
		EmpDaoImpl empdao = new EmpDaoImpl();
		boolean flag = empdao.updateBySalEmp(7379,7777);
		if(flag){
			System.out.println("ִ�гɹ�");
		}else{
			System.out.println("ִ��ʧ��");
		}

	}
	
	public static void test5(){//ɾ��
		EmpDaoImpl empdao = new EmpDaoImpl();
		boolean flag = empdao.deleteEmp(7379);
		if(flag){
			System.out.println("ִ�гɹ�");
		}else{
			System.out.println("ִ��ʧ��");
		}
	}
	
	/**
	 *  Ա����ѯϵͳ 
	 */
	public static void test6(){
		MainView mainView = new MainView();
		mainView.View();
	}
	public static void main(String[] args) {
		/*test1();
		System.out.println("-------");
        test2()*/;
        test6();
        
		
	}
}
