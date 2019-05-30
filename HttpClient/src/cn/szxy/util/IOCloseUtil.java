package cn.szxy.util;

import java.io.Closeable;
import java.io.IOException;

public class IOCloseUtil {//关闭流
	public static void CloseALL(Closeable...c){//可变参数列表
		for(Closeable closeable:c){
			if(closeable!=null){
				try {
					closeable.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}
	
}
