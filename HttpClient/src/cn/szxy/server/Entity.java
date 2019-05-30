package cn.szxy.server;
/**
 * <servlet>
		<servlet-name>login</servlet-name>
		<servlet-class>cn.szxy.servlet.LoginServlet</servlet-class>
	</servlet>
 * @author wzer
 *
 */
public class Entity {//一个 servlet-name 对应 一个 实体类
	private String name;// servlet-name
	private String clazz;// servlet-class
	public String getName() {
		return name;	
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public Entity(String name, String clazz) {
		super();
		this.name = name;
		this.clazz = clazz;
	}
	public Entity() {
		super();
	}
	
}
