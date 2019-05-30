package cn.szxy.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import cn.szxy.servlet.Servlet;
import cn.szxy.util.IOCloseUtil;



public class Server {//服务器
	private ServerSocket server;
	private boolean isShutdown = false;//默认没有出错
	public void start(){
		this.start(8888);//调用本类中有参start方法
	}
	private void start(int port){
		//创建 ServerSocket 对象
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
				// 监听客户端的请求
				Socket  client = server.accept();
				//创建线程类对象
				Dispatcher  dis = new Dispatcher(client);
				//创建代理类并启动线程
				new Thread(dis).start();	
			}
		} catch (IOException e) {
			this.stop();//出现错误，关闭服务器		
		}
	}
	private void stop(){
		isShutdown = true;
		IOCloseUtil.CloseALL(server);
	}
	//测试 方法
	public static void main(String[] args) {
		System.out.println("启动服务器...");
		Server server = new Server();
		server.start();
	}
}
