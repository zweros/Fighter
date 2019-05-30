package cn.szxy.server;

import java.util.List;
import java.util.Map;

import cn.szxy.servlet.Servlet;

/**
 * ��ʼ�� �������е�����
 * 
 * @author wzer
 *
 */
public class WebApp {
	private static ServletContext context;
	static{
		context = new ServletContext();
		//�ֱ��ö�Ӧ�Ĺ�ϵ��map����
		Map<String,String> servlet = context.getServlet();
		Map<String,String> mapping = context.getMapping();
		//���� ������ XML �ĵ�
		WebDOM4J dom4j = new WebDOM4J();
		dom4j.parse(dom4j.getDocuement());
		//��ȡ����֮���List����
		List<Entity> entityList = dom4j.getEntity();
		List<Mapping> mappingList = dom4j.getMapping();
		//�� List�����ݷ��� Map��
		for (Entity  entity : entityList) {
			servlet.put(entity.getName(),entity.getClazz());
		}
		//System.out.println(servlet);
		for (Mapping map : mappingList) {
			//���� url-patterns ����
			List<String> urlpatterns = map.getUrlPattern();
			for (String str: urlpatterns) {
				mapping.put(str, map.getName());
			}
		}
		//System.out.println(mapping);
	}
	/**
	 * ���� ӳ���ϵ��������servlet-class
	 * 
	 * @return
	 */
	public static Servlet getServlet(String url) {
		//�ж� url ��ʽ�Ƿ���ȷ
		if(url==null||url.trim().equals("")){
			return null;
		}
		//��� url ��ȷ
		//���� url ��key ȥ��ȡ servlet-name ��ֵ
		String name = context.getMapping().get(url);
		//���� servlet-name��key ȥ��ȡ servlet-class ��value
		String servletclass = context.getServlet().get(name);
		try {
			//���ݷ��䣬���servlet-class����
			Class clazz = Class.forName(servletclass);
			//�����޲ι��췽�� ���� Servlet ����
			Servlet servlet = (Servlet)clazz.newInstance();
			return servlet;
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(getServlet("/Log"));
	}
}
