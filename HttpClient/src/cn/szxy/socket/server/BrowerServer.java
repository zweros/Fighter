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
		//创建 ServerSocket 对象
		ServerSocket server=null;
		//监听客户端的请求，并获得输入流
		Socket client=null;
		BufferedReader br =null;
		//获得输出流
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			//关闭
			IOClose.CloseAll(bw,br,client,server);
		}
		
	}
}	
