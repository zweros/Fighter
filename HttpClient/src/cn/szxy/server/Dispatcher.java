package cn.szxy.server;

import java.io.IOException;
import java.net.Socket;

import cn.szxy.servlet.Servlet;
import cn.szxy.util.IOCloseUtil;

/**
 * һ������һ����Ӧ	
 * @author wzer
 *
 */
public class Dispatcher implements Runnable{
	private Request req;   // ����������������������
	private Response resp; // ����������������ص���Ӧ
	private Socket client; //���������ܵ� socket ����
	private int code;//״̬��
	//���캯��  ��ʼ��
	public Dispatcher(Socket client) {
		// �Ѿֲ�������ֵ������Ա����
		this.client = client;
		try {
			this.req = new Request(this.client.getInputStream());
			this.resp = new Response(this.client.getOutputStream());
		} catch (IOException e) {
			this.code = 500;
			return ;
		}
	}
	@Override
	public void run() {
		//���ݲ�ͬ�� URL����·�� ����ָ���� Servlet
		//System.out.println(req.getUrl());
		Servlet servlet = WebApp.getServlet(req.getUrl());
		if(servlet==null){
			this.code = 404;
		}else{
			//���� servlet ��Ӧ�� service ����
			try {
				servlet.service(req, resp);
			} catch (Exception e) {
				this.code =500;
			} 
		}
		resp.pushToClient(code);
		IOCloseUtil.CloseALL(client);  
	}
}
