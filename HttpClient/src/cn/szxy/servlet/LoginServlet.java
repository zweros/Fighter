package cn.szxy.servlet;

import cn.szxy.server.Request;
import cn.szxy.server.Response;
/**
 * ��¼��֤
 * @author wzer
 *
 */
public class LoginServlet extends Servlet{
	@Override
	public void doGet(Request req, Response rep) throws Exception {
		//��ȡ�������
		String name=req.getParameter("username");
		String pwd=req.getParameter("pwd");
		
		if(this.login(name, pwd)){
			//������Ӧ�еĹ������ݵķ���
			rep.println(name+"��¼�ɹ�");
		}else{
			rep.println(name+"��¼ʧ�ܣ��Բ����˺Ż����벻��ȷ");
		}
		
	}
	private boolean login(String name,String pwd){
		if ("szxy".equals(name)&&"123".equals(pwd)) {
			return true;
		}
		return false;
	}

	@Override
	public void doPost(Request req, Response rep) throws Exception {
		// TODO Auto-generated method stub
		//this.doGet(req, rep);
	}
}
