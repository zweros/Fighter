package cn.szxy.view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import cn.szxy.dao.EmpDao;
import cn.szxy.dao.Impl.EmpDaoImpl;
import cn.szxy.pojo.Emp;

public class MainView {
	Scanner input = new Scanner(System.in);
	EmpDao dao = new EmpDaoImpl();
	
	public void View(){
		System.out.println("****************************");
		System.out.println("********Ա����Ϣϵͳ����*********");
		System.out.println("****************************");
		while(true){
			System.out.println("1. ����Ա����Ϣ");
			System.out.println("2. ����Ա��н����Ϣ");
			System.out.println("3. ɾ��Ա����Ϣ");
			System.out.println("4. ��ѯ����Ա����Ϣ");
			System.out.println("5. ��ѯ����Ա����Ϣ");
			System.out.println("6. �˳�");
			
			switch(input.nextInt()){
				case 1:
					addEmp();
					continue;
				case 2:
					updEmp();
					continue;
				case 3:
					delEmp();
					continue;
				case 4:
					FidAll();
					continue;
				case 5:
					fidone();
					continue;
				case 6:
					break;
				default:
					System.out.println("��������,�����������");
					continue;
			}
			break; //�˳� while ѭ��
		}
	}

	private void fidone() {
		System.out.print("������Ա�����: ");
		int empno = input.nextInt();
		Emp emp = dao.findByIdEmp(empno);
		System.out.println(emp);
	}

	private void FidAll() {
		 List<Emp> emps = dao.findAll();
		for (Emp emp : emps) {
			System.out.println(emp);
		}
	}

	private void delEmp() {
		System.out.print("������Ա�����: ");
		int empno = input.nextInt();
		boolean flag = dao.deleteEmp(empno);
		if(flag){
			System.out.println("ɾ���ɹ�");
		}else{
			System.out.println("ɾ��ʧ��");
		}
		
	}

	private void updEmp() {
		System.out.print("������Ա�����: ");
		int empno = input.nextInt();
		System.out.print("������Ա��нˮ: ");
		Double sal = input.nextDouble();
		boolean flag = dao.updateBySalEmp(empno, sal);
		if(flag){
			System.out.println("���³ɹ�");
		}else{
			System.out.println("����ʧ��");
		}
	}

	private void addEmp() {
		System.out.print("������Ա�����: ");
		int empno = input.nextInt();
		System.out.print("������Ա������: ");
		String ename = input.next();
		System.out.print("������Ա��ְλ: ");
		String job = input.next();
		System.out.print("������Ա���쵼���: ");
		String mgr = input.next();
		System.out.print("������Ա����ְ����:");
		Date hirdate = Date.valueOf(input.next());
		System.out.print("������Ա��нˮ: ");
		Double sal = input.nextDouble();
		System.out.print("������Ա�����: ");
		Double comm = input.nextDouble();
		System.out.print("������Ա�����ű��: ");
		int deptno = input.nextInt();
		Emp emp = new Emp(empno, ename, job, mgr, hirdate, sal, comm, deptno);
		boolean flag = dao.insertEmp(emp);
		if(flag){
			System.out.println("����ɹ�");
		}else{
			System.out.println("����ʧ��");
		}
	}
	public static void main(String[] args) {
		new MainView().View();
	}
}
