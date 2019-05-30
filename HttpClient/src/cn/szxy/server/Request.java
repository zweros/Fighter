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
	
	public class Request {//���ܿͻ��˵�����  
		private InputStream is; 
		private String requstInfo; //������Ϣ
		private String method; //���󷽷�
		private String url; //·��
		 
		private Map<String,List<String>> parametermapValues;// �������
		private static final String CRLF = "\r\n"; //����
		private static final String BLANK = " "; //�հ�
		
		public String getUrl() {
			return url;
		}
		//���캯������ʼ������
		public Request(){
			parametermapValues=new HashMap<String,List<String>>();
			requstInfo = "";
			method = "";
			url = "";
		}
		public Request(InputStream is) {
			this();//�����޲ι��캯��
			try {
				this.is = is;
				byte[] buf = new byte[20480];
				int len = is.read(buf);
				requstInfo = new String(buf,0,len);
			} catch (IOException e) {
				return;	
			}
			//���ñ����еķֽ�������Ϣ�ķ���
			this.parseRequestInfo();
		}
		//�ֽ�������Ϣ�ķ���
		private void parseRequestInfo() {
			String paraString="";//���ڴ洢�������
			//��õ�һ��
			String firstLine = requstInfo.substring(0,requstInfo.indexOf(CRLF)).trim(); 
			int index = firstLine.indexOf("/"); //��ȡ����һ����������
			this.method = firstLine.substring(0,index).trim();//����ĵ�һ������ ���󷽷�    ��ͷ����β
			String urlString = firstLine.substring(index, firstLine.indexOf("HTTP/")).trim();//����ļ�·��
			//�ж� get���� ���� post����
			if(method.equalsIgnoreCase(this.method)){
				if(urlString.contains("?")){
					String[] urlArray = urlString.split("\\?");
					this.url = urlArray[0];
					paraString = urlArray[1];	
				}else{
					this.url = urlString;
				}
			}else{//post�������������
				this.url = urlString;
				paraString =requstInfo.substring(requstInfo.lastIndexOf(CRLF));
			}
			if(paraString.equals("")){
				return;
			}
			//���÷ֽ� ��������ķ���
			this.parseParam(paraString);
			//System.out.println(paraString);
		}
		/**
		 *  �ֽ� �������
		 */
		private void parseParam(String paraString){
			String[] token = paraString.split("&");
			for (int i = 0; i < token.length; i++) {
				String keyValues = token[i];
				String[] keyValue = keyValues.split("=");
				if(keyValue.length ==1){// ֻ�в�������û�в���ֵ
					keyValue = Arrays.copyOf(keyValue, 2);
					keyValue[1] = null;
				}
				//�Ѳ������Ͳ���ֵ���뼯����
				String key = keyValue[0];
				String value= keyValue[1]==null?null:decode(keyValue[1].trim(),"utf-8");
				
				//�ŵ����ϴ洢
				if(!parametermapValues.containsKey(key)){
					parametermapValues.put(key, new ArrayList<String>());
				}
				List<String>  values = parametermapValues.get(key);
				values.add(value);
			}
		}
		//���ݱ� Ԫ�ص�name��ȡ���ֵ
		public String [] getParametermapValues(String key){
			List<String> values = parametermapValues.get(key);
			if(values==null){
				return null;
			}else{
				return values.toArray(new String[0]);//������ת�� ����
			}
		}
		//���ݱ�Ԫ�ص�name��ȡ����ֵ
		public String getParameter(String name){
			String[] values = this.getParametermapValues(name);
			if(values==null){
				return null;
			}else{
				return values[0];
			}
		}
		
		//�������� ����Ϊ����������Ľ��б��룬������Ҫ����
		private String decode(String value,String code){
			try {
				return URLDecoder.decode(value, code);
			} catch (UnsupportedEncodingException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			return null;
		}
		
		//���ڲ���
			/*public void show(){
				System.out.println(this.url);
				System.out.println(this.method);
			}*/
			
		public static void main(String[] args) {
			Request req = new Request();
			req.parseParam("usr=a%20a&pwd=&hoby=foot&hoby=baket");
			System.out.println(req.parametermapValues);
			
			//���û�ö��ֵ
			String[] str = req.getParametermapValues("hoby");
			for (String string : str) {
				System.out.println(string);
			}
			//���û��һ��ֵ
			System.out.println(req.getParameter("usr"));
		}
		
	}
