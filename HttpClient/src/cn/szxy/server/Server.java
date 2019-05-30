package cn.szxy.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import cn.szxy.servlet.Servlet;
import cn.szxy.util.IOCloseUtil;



public class Server {//������
	private ServerSocket server;
	private boolean isShutdown = false;//Ĭ��û�г���
	public void start(){
		this.start(8888);//���ñ������в�start����
	}
	private void start(int port){
		//���� ServerSocket ����
		try {
			server = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void receive() {
		try {
			while(!isShutdown){
				// �����ͻ��˵�����
				Socket  client = server.accept();
				//�����߳������
				Dispatcher  dis = new Dispatcher(client);
				//���������ಢ�����߳�
				new Thread(dis).start();	
			}
		} catch (IOException e) {
			this.stop();//���ִ��󣬹رշ�����		
		}
	}
	private void stop(){
		isShutdown = true;
		IOCloseUtil.CloseALL(server);
	}
	//���� ����
	public static void main(String[] args) {
		System.out.println("����������...");
		Server server = new Server();
		server.start();
	}
}
