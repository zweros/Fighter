package cn.szxy.socket.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrowerServer {
	public static void main(String[] args) {
		System.out.println("Server start...");
		//���� ServerSocket ����
		ServerSocket server=null;
		//�����ͻ��˵����󣬲����������
		Socket client=null;
		BufferedReader br =null;
		//��������
		BufferedWriter bw =null;
		try {
			server = new ServerSocket(8888);
			client = server.accept();
			
			br = new BufferedReader(new InputStreamReader(client.getInputStream(),"utf-8"));
			String str=null;
			// br.readLine().length
			while((str=br.readLine()).length()>0){
				System.out.println(str);
			}
			
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			//�ر�
			IOClose.CloseAll(bw,br,client,server);
		}
		
	}
}	
