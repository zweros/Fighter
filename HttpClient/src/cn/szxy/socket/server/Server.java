package cn.szxy.socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		System.out.println("Server start...");
		//���� ServerSocket ����
		ServerSocket server=null;
		//�����ͻ��˵����󣬲����������
		Socket client=null;
		DataInputStream dis=null;
		//��������
		DataOutputStream dos=null;
		try {
			server = new ServerSocket(8888);
			client = server.accept();
			dis = new DataInputStream(client.getInputStream());
			System.out.println(dis.readUTF());
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF("Hello Client!");
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			//�ر�
			IOClose.CloseAll(dos,dis,client,server);
		}
		
	}
}	
