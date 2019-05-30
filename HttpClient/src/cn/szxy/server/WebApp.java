package cn.szxy.server;

import java.util.List;
import java.util.Map;

import cn.szxy.servlet.Servlet;

/**
 * 初始化 程序运行的数据
 * 
 * @author wzer
 *
 */
public class WebApp {
	private static ServletContext context;
	static{
		context = new ServletContext();
		//分别获得对应的关系的map集合
		Map<String,String> servlet = context.getServlet();
		Map<String,String> mapping = context.getMapping();
		//创建 解析的 XML 文档
		WebDOM4J dom4j = new WebDOM4J();
		dom4j.parse(dom4j.getDocuement());
		//获取解析之后的List集合
		List<Entity> entityList = dom4j.getEntity();
		List<Mapping> mappingList = dom4j.getMapping();
		//将 List中数据放入 Map中
		for (Entity  entity : entityList) {
			servlet.put(entity.getName(),entity.getClazz());
		}
		//System.out.println(servlet);
		for (Mapping map : mappingList) {
			//遍历 url-patterns 集合
			List<String> urlpatterns = map.getUrlPattern();
			for (String str: urlpatterns) {
				mapping.put(str, map.getName());
			}
		}
		//System.out.println(mapping);
	}
	/**
	 * 根据 映射关系，反射获得servlet-class
	 * 
	 * @return
	 */
	public static Servlet getServlet(String url) {
		//判断 url 格式是否正确
		if(url==null||url.trim().equals("")){
			return null;
		}
		//如果 url 正确
		//根据 url 的key 去获取 servlet-name 的值
		String name = context.getMapping().get(url);
		//根据 servlet-name的key 去获取 servlet-class 的value
		String servletclass = context.getServlet().get(name);
		try {
			//根据反射，获得servlet-class对象
			Class clazz = Class.forName(servletclass);
			//调用无参构造方法 创建 Servlet 对象
			Servlet servlet = (Servlet)clazz.newInstance();
			return servlet;
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(getServlet("/Log"));
	}
}
