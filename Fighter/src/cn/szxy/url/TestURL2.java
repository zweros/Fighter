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
 * 网络爬虫
 * 爬取百度界面
 * @author wzer
 *
 */
public class TestURL2 {
	public static void main(String[] args) throws IOException {
		//获取URL
		URL url = new URL("https://baidu.com");
		//获取网页中的信息
		InputStream is = url.openStream();
		//缓冲输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		//缓冲输入流
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("index.html"),"utf-8"));
		//边读变写
		String line = null;
		if((line=br.readLine())!=null){
			bw.write(line);
			bw.newLine();
		}
		//关闭流
		bw.close();
		bw.close();
	}
}
