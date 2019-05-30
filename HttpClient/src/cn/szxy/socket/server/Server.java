package cn.szxy.socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		System.out.println("Server start...");
		//创建 ServerSocket 对象
		ServerSocket server=null;
		//监听客户端的请求，并获得输入流
		Socket client=null;
		DataInputStream dis=null;
		//获得输出流
		DataOutputStream dos=null;
		try {
			server = new ServerSocket(8888);
			client = server.accept();
			dis = new DataInputStream(client.getInputStream());
			System.out.println(dis.readUTF());
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF("Hello Client!");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			//关闭
			IOClose.CloseAll(dos,dis,client,server);
		}
		
	}
}	
