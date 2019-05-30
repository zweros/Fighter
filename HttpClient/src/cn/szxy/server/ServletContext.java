package cn.szxy.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Servlet������   	����һ������
 * @author wzer
 *
 */
public class ServletContext {//serlvet ������ ���� ����
		private Map<String,String> 	servlet;//key �� servlet-name    value �� servlet-class
		private Map<String,String> mapping;// key �� url-pattern  value �� servlet-class
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
			// TODO �Զ����ɵĹ��캯�����
			servlet = new HashMap<String,String>();
			mapping = new HashMap<String,String>();
		}
		
}
