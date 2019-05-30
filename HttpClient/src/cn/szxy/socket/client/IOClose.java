package cn.szxy.socket.client;

import java.io.Closeable;
import java.io.IOException;

public class IOClose {
	public static void CloseAll(Closeable...c){
		for(Closeable closeable:c){
			if(closeable!=null){
				try {
					closeable.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		}
	}
}
