package cn.szxy.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Servlet上下文   	就是一个容器
 * @author wzer
 *
 */
public class ServletContext {//serlvet 上下文 就是 容器
		private Map<String,String> 	servlet;//key 是 servlet-name    value 是 servlet-class
		private Map<String,String> mapping;// key 是 url-pattern  value 是 servlet-class
		public Map<String, String> getServlet() {
			return servlet;
		}
		public void setServlet(Map<String, String> servlet) {
			this.servlet = servlet;
		}
		public Map<String, String> getMapping() {
			return mapping;
		}
		public void setMapping(Map<String, String> mapping) {
			this.mapping = mapping;
		}
		public ServletContext() {
			// TODO 自动生成的构造函数存根
			servlet = new HashMap<String,String>();
			mapping = new HashMap<String,String>();
		}
		
}
