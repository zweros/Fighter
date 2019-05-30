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
		System.out.println("********员工信息系统中心*********");
		System.out.println("****************************");
		while(true){
			System.out.println("1. 新增员工信息");
			System.out.println("2. 更新员工薪资信息");
			System.out.println("3. 删除员工信息");
			System.out.println("4. 查询所有员工信息");
			System.out.println("5. 查询单个员工信息");
			System.out.println("6. 退出");
			
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
					System.out.println("输入有误,请重新输出！");
					continue;
			}
			break; //退出 while 循环
		}
	}

	private void fidone() {
		System.out.print("请输入员工编号: ");
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
		System.out.print("请输入员工编号: ");
		int empno = input.nextInt();
		boolean flag = dao.deleteEmp(empno);
		if(flag){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
		
	}

	private void updEmp() {
		System.out.print("请输入员工编号: ");
		int empno = input.nextInt();
		System.out.print("请输入员工薪水: ");
		Double sal = input.nextDouble();
		boolean flag = dao.updateBySalEmp(empno, sal);
		if(flag){
			System.out.println("更新成功");
		}else{
			System.out.println("更新失败");
		}
	}

	private void addEmp() {
		System.out.print("请输入员工编号: ");
		int empno = input.nextInt();
		System.out.print("请输入员工姓名: ");
		String ename = input.next();
		System.out.print("请输入员工职位: ");
		String job = input.next();
		System.out.print("请输入员工领导编号: ");
		String mgr = input.next();
		System.out.print("请输入员工入职日期:");
		Date hirdate = Date.valueOf(input.next());
		System.out.print("请输入员工薪水: ");
		Double sal = input.nextDouble();
		System.out.print("请输入员工提成: ");
		Double comm = input.nextDouble();
		System.out.print("请输入员工部门编号: ");
		int deptno = input.nextInt();
		Emp emp = new Emp(empno, ename, job, mgr, hirdate, sal, comm, deptno);
		boolean flag = dao.insertEmp(emp);
		if(flag){
			System.out.println("插入成功");
		}else{
			System.out.println("插入失败");
		}
	}
	public static void main(String[] args) {
		new MainView().View();
	}
}
