	package cn.szxy.server;
	
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.UnsupportedEncodingException;
	import java.net.URLDecoder;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	
	public class Request {//接受客户端的请求  
		private InputStream is; 
		private String requstInfo; //请求信息
		private String method; //请求方法
		private String url; //路径
		 
		private Map<String,List<String>> parametermapValues;// 请求参数
		private static final String CRLF = "\r\n"; //换行
		private static final String BLANK = " "; //空白
		
		public String getUrl() {
			return url;
		}
		//构造函数，初始化属性
		public Request(){
			parametermapValues=new HashMap<String,List<String>>();
			requstInfo = "";
			method = "";
			url = "";
		}
		public Request(InputStream is) {
			this();//调用无参构造函数
			try {
				this.is = is;
				byte[] buf = new byte[20480];
				int len = is.read(buf);
				requstInfo = new String(buf,0,len);
			} catch (IOException e) {
				return;	
			}
			//调用本类中的分解请求信息的方法
			this.parseRequestInfo();
		}
		//分解请求信息的方法
		private void parseRequestInfo() {
			String paraString="";//用于存储请求参数
			//获得第一行
			String firstLine = requstInfo.substring(0,requstInfo.indexOf(CRLF)).trim(); 
			int index = firstLine.indexOf("/"); //截取到第一个参数结束
			this.method = firstLine.substring(0,index).trim();//请求的第一个参数 请求方法    含头不含尾
			String urlString = firstLine.substring(index, firstLine.indexOf("HTTP/")).trim();//获得文件路径
			//判断 get请求 还是 post请求
			if(method.equalsIgnoreCase(this.method)){
				if(urlString.contains("?")){
					String[] urlArray = urlString.split("\\?");
					this.url = urlArray[0];
					paraString = urlArray[1];	
				}else{
					this.url = urlString;
				}
			}else{//post不包含请求参数
				this.url = urlString;
				paraString =requstInfo.substring(requstInfo.lastIndexOf(CRLF));
			}
			if(paraString.equals("")){
				return;
			}
			//调用分解 请求参数的方法
			this.parseParam(paraString);
			//System.out.println(paraString);
		}
		/**
		 *  分解 请求参数
		 */
		private void parseParam(String paraString){
			String[] token = paraString.split("&");
			for (int i = 0; i < token.length; i++) {
				String keyValues = token[i];
				String[] keyValue = keyValues.split("=");
				if(keyValue.length ==1){// 只有参数名，没有参数值
					keyValue = Arrays.copyOf(keyValue, 2);
					keyValue[1] = null;
				}
				//把参数名和参数值放入集合中
				String key = keyValue[0];
				String value= keyValue[1]==null?null:decode(keyValue[1].trim(),"utf-8");
				
				//放到集合存储
				if(!parametermapValues.containsKey(key)){
					parametermapValues.put(key, new ArrayList<String>());
				}
				List<String>  values = parametermapValues.get(key);
				values.add(value);
			}
		}
		//根据表单 元素的name获取多个值
		public String [] getParametermapValues(String key){
			List<String> values = parametermapValues.get(key);
			if(values==null){
				return null;
			}else{
				return values.toArray(new String[0]);//将集合转成 数组
			}
		}
		//根据表单元素的name获取单个值
		public String getParameter(String name){
			String[] values = this.getParametermapValues(name);
			if(values==null){
				return null;
			}else{
				return values[0];
			}
		}
		
		//处理中文 ，因为浏览器对中文进行编码，所以需要解码
		private String decode(String value,String code){
			try {
				return URLDecoder.decode(value, code);
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return null;
		}
		
		//用于测试
			/*public void show(){
				System.out.println(this.url);
				System.out.println(this.method);
			}*/
			
		public static void main(String[] args) {
			Request req = new Request();
			req.parseParam("usr=a%20a&pwd=&hoby=foot&hoby=baket");
			System.out.println(req.parametermapValues);
			
			//调用获得多个值
			String[] str = req.getParametermapValues("hoby");
			for (String string : str) {
				System.out.println(string);
			}
			//调用获得一个值
			System.out.println(req.getParameter("usr"));
		}
		
	}
