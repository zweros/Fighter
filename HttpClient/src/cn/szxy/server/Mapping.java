package cn.szxy.server;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * <servlet-mapping>
		<servlet-name>login</servlet-name>
		<url-pattern>/LoginServlet</url-pattern>
	</servlet-mapping>
	<servlet-mapping>
		<servlet-name>login</servlet-name>
		<url-pattern>/Log</url-pattern>
	</servlet-mapping>
 * @author wzer
 *
 */
public class Mapping {//映射关系，多路径访问
		private String name; //servlet-name
		private List<String> urlPattern; //url-pattern
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<String> getUrlPattern() {
			return urlPattern;
		}
		public void setUrlPattern(List<String> urlPattern) {
			this.urlPattern = urlPattern;
		}
		public Mapping(String name, List<String> urlPattern) {
			super();
			this.name = name;
			this.urlPattern = urlPattern;
		}
		public Mapping() {
			this.urlPattern = new ArrayList<String>();
		}
		
		
}
