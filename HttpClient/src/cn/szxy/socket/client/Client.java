package cn.szxy.socket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �ͻ���
 * @author wzer
 *
 */
public class Client {
	public static void main(String[] args) {
		//����Socket����
		Socket client=null;
		//��������
		DataOutputStream dos=null;
		//���������
		DataInputStream dis=null;
		try {
			client = new  Socket("localhost", 8888);
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF("Hello Server");
			dis = new DataInputStream(client.getInputStream());
			System.out.println(dis.readUTF());
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			//�ر�
			IOClose.CloseAll(dis,dos,client);
		}
		
	}
}
