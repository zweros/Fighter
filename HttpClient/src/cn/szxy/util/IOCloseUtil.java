package cn.szxy.util;

import java.io.Closeable;
import java.io.IOException;

public class IOCloseUtil {//�ر���
	public static void CloseALL(Closeable...c){//�ɱ�����б�
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
