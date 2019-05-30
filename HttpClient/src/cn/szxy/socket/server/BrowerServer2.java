package cn.szxy.socket.server;



import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrowerServer2 {
	public static void main(String[] args) {
		String CRLF="\r\n";    //����
		String BLANK=" ";      //�ո�
		//(1)����ServerSocket����
		ServerSocket server=null;
		//(2)�����Ƿ��пͻ��˷�������
		Socket client=null;
		InputStream is=null;
		
		try {
			server = new ServerSocket(8888);
			client = server.accept();
			//��ȡ�����������������Ϣ
			is=client.getInputStream();
			byte [] buf=new byte[20480];
			int len=is.read(buf);
			System.out.println(new String(buf,0,len));
			/**��Web�����������������Ӧ*/
			StringBuilder sb=new StringBuilder();
			StringBuilder sbContent=new StringBuilder();//��Ӧ���ı�
			sbContent.append("<html><head><title>��Ӧ���</title></head>");
			sbContent.append("<body>��¼�ɹ�</body></html>");
			
			//(1)ƴ����Ӧͷ
			sb.append("HTTP/1.1").append(BLANK).append(200).append(BLANK).append("OK");
			sb.append(CRLF);//����
			sb.append("Content-Type: text/html;charset=utf-8");
			sb.append(CRLF);//����
			sb.append("Content-Length:").append(sbContent.toString().getBytes().length).append(CRLF);
			sb.append(CRLF);//����,������Ӧͷ����Ӧ�����Ĳ���֮��Ŀ���
			sb.append(sbContent);
			
			//ͨ������� 
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"utf-8"));
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		//(6)�ر���
			IOClose.CloseAll(is,client,server);
		}
		
	}
}
