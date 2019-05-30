package cn.szxy.socket.server;



import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrowerServer2 {
	public static void main(String[] args) {
		String CRLF="\r\n";    //换行
		String BLANK=" ";      //空格
		//(1)创建ServerSocket对象
		ServerSocket server=null;
		//(2)监听是否有客户端发送请求
		Socket client=null;
		InputStream is=null;
		
		try {
			server = new ServerSocket(8888);
			client = server.accept();
			//获取来自浏览器的请求信息
			is=client.getInputStream();
			byte [] buf=new byte[20480];
			int len=is.read(buf);
			System.out.println(new String(buf,0,len));
			/**对Web浏览器的请求作出响应*/
			StringBuilder sb=new StringBuilder();
			StringBuilder sbContent=new StringBuilder();//响应的文本
			sbContent.append("<html><head><title>响应结果</title></head>");
			sbContent.append("<body>登录成功</body></html>");
			
			//(1)拼接响应头
			sb.append("HTTP/1.1").append(BLANK).append(200).append(BLANK).append("OK");
			sb.append(CRLF);//换行
			sb.append("Content-Type: text/html;charset=utf-8");
			sb.append(CRLF);//换行
			sb.append("Content-Length:").append(sbContent.toString().getBytes().length).append(CRLF);
			sb.append(CRLF);//换行,代表响应头与响应的正文部门之间的空行
			sb.append(sbContent);
			
			//通过流输出 
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"utf-8"));
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		//(6)关闭流
			IOClose.CloseAll(is,client,server);
		}
		
	}
}
