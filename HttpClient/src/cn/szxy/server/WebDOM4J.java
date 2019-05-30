package cn.szxy.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class WebDOM4J {// 解析 XML 文档
	private List<Entity> entity;
	private List<Mapping> mapping;
	
	public List<Entity> getEntity() {
		return entity;
	}

	public void setEntity(List<Entity> entity) {
		this.entity = entity;
	}
	public List<Mapping> getMapping() {
		return mapping;
	}

	public void setMapping(List<Mapping> mapping) {
		this.mapping = mapping;
	}
	
	public WebDOM4J() {
		this.entity = new  ArrayList<Entity>();
		this.mapping = new ArrayList<Mapping>();
	}

	public WebDOM4J(List<Entity> entity, List<Mapping> mapping) {
		super();
		this.entity = entity;
		this.mapping = mapping;
	}
	
	public Document getDocuement(){
		Document doc=null;
		try {
			//创建 SAXReader 对象
			SAXReader reader = new SAXReader();
			//调用 read 方法
			return reader.read("src/cn/szxy/WEB_INFO/web.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;	
	}
	public void parse(Document doc){
		//获得 根节点 root
		Element root = doc.getRootElement();
		//遍历节点
		try {
			Class c = Class.forName("cn.szxy.server.Entity");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		for(Iterator<Element> iter = root.elementIterator("servlet");iter.hasNext();){
			Element subEle = iter.next(); //获得每个serlvet
			//System.out.println(subEle);
			Entity t  = new Entity(); // 存储 servlet-name和 servlet-class
			for(Iterator<Element> subIter = subEle.elementIterator();subIter.hasNext();){
				Element ele = subIter.next();
				//System.out.println(ele.getName()+"\t"+ele.getText());
				if("servlet-name".equals(ele.getName())){
					t.setName(ele.getText());
				}else if("servlet-class".equals(ele.getName())){
					t.setClazz(ele.getText());
				}
			}
			entity.add(t);
		}	
		//测试
		/*for (Entity list : entity) {
			System.out.println(list.getName()+"\t"+list.getClazz());
		}*/
		// servlet-mapping
		for(Iterator<Element> iter = root.elementIterator("servlet-mapping");iter.hasNext();){
			Element subEle = iter.next(); //获得每个serlvet
			//System.out.println(subEle);
			Mapping m = new Mapping();
			for(Iterator<Element> subIter = subEle.elementIterator();subIter.hasNext();){
				Element ele = subIter.next();
				//System.out.println(ele.getName()+"\t"+ele.getText());
				if("servlet-name".equals(ele.getName())){
					m.setName(ele.getText());
				}else if("url-pattern".equals(ele.getName())){
					//获得集合对象，调用集合对象的添加方法  ---------
					m.getUrlPattern().add(ele.getText());
				}
			}
			mapping.add(m);
		}	
		/*for(Mapping m:mapping){ //测试
			//System.out.println(m.getName()+"----");
			for(String s:m.getUrlPattern()){
				System.out.println(s);
			}
		}*/
	}	
	
	public static void main(String[] args) {
		WebDOM4J dom4j = new WebDOM4J();
		dom4j.parse(dom4j.getDocuement());
		
	}
	
}
