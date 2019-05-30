package cn.szxy.servlet;

import cn.szxy.server.Request;
import cn.szxy.server.Response;

public abstract class Servlet {//�����е������Servlet�ĸ���
	public void service(Request req,Response resp)throws Exception{
		this.doGet(req, resp);
		this.doPost(req, resp);
	}
	public abstract void doGet(Request req,Response resp) throws Exception;
	public abstract void doPost(Request req,Response resp)throws Exception;
}
