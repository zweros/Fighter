package cn.szxy.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 学习 URL类的方法
 * getProtocol 
 * getHost
 * getPath
 * 
 *
 */
public class TestURL {
	public static void main(String[] args) throws MalformedURLException {
		
		URL url = new URL("https://zwer.xyz");
		System.out.println(""+url.getProtocol());
		System.out.println(""+url.getHost());
		System.out.println("getPath: "+url.getPath());
		
	}
}
