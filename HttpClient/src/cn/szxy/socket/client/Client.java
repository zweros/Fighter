package cn.szxy.socket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 * @author wzer
 *
 */
public class Client {
	public static void main(String[] args) {
		//创建Socket对象
		Socket client=null;
		//获得输出流
		DataOutputStream dos=null;
		//获得输入流
		DataInputStream dis=null;
		try {
			client = new  Socket("localhost", 8888);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF("Hello Server");
			dis = new DataInputStream(client.getInputStream());
			System.out.println(dis.readUTF());
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			//关闭
			IOClose.CloseAll(dis,dos,client);
		}
		
	}
}
