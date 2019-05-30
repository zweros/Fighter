package cn.szxy.url;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ��������
 * ��ȡ�ٶȽ���
 * @author wzer
 *
 */
public class TestURL2 {
	public static void main(String[] args) throws IOException {
		//��ȡURL
		URL url = new URL("https://baidu.com");
		//��ȡ��ҳ�е���Ϣ
		InputStream is = url.openStream();
		//����������
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		//����������
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("index.html"),"utf-8"));
		//�߶���д
		String line = null;
		if((line=br.readLine())!=null){
			bw.write(line);
			bw.newLine();
		}
		//�ر���
		bw.close();
		bw.close();
	}
}
