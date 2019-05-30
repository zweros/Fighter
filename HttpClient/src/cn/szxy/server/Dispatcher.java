package cn.szxy.server;

import java.io.IOException;
import java.net.Socket;

import cn.szxy.servlet.Servlet;
import cn.szxy.util.IOCloseUtil;

/**
 * 一个请求一个响应	
 * @author wzer
 *
 */
public class Dispatcher implements Runnable{
	private Request req;   // 浏览器向服务器发出的请求
	private Response resp; // 服务器对浏览器发回的响应
	private Socket client; //服务器接受的 socket 对象
	private int code;//状态码
	//构造函数  初始化
	public Dispatcher(Socket client) {
		// 把局部变量的值赋给成员变量
		this.client = client;
		try {
			this.req = new Request(this.client.getInputStream());
			this.resp = new Response(this.client.getOutputStream());
		} catch (IOException e) {
			this.code = 500;
			return ;
		}
	}
	@Override
	public void run() {
		//根据不同的 URL拦截路径 创建指定的 Servlet
		//System.out.println(req.getUrl());
		Servlet servlet = WebApp.getServlet(req.getUrl());
		if(servlet==null){
			this.code = 404;
		}else{
			//调用 servlet 相应的 service 方法
			try {
				servlet.service(req, resp);
			} catch (Exception e) {
				this.code =500;
			} 
		}
		resp.pushToClient(code);
		IOCloseUtil.CloseALL(client);  
	}
}
