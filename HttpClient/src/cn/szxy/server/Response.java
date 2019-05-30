package cn.szxy.server;



import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import cn.szxy.util.IOCloseUtil;

/**
 * ��Ӧ
 * @author wzer
 *
 */
public class Response {//��Ӧ
	private StringBuilder headInfo;//��Ӧͷ
	private StringBuilder content;//��Ӧ����
	private int length;//��Ӧ���ݵĳ���
	//��
	private BufferedWriter bw;
	
	//�������������кͿո�
	private static final String CRLF="\r\n";//����
	private static final String BLANK=" ";//�ո�
	
	//���췽��
	public Response() {//��ʼ��
		headInfo=new StringBuilder();
		content=new StringBuilder();
		
	}
	//���ι��췽��
	public Response(OutputStream os){
		this();//���ñ�����޲ι��췽��
		try {
			bw=new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			headInfo=null;
		}
		
	}
	//�������Ĳ���
	public Response print(String info){
		content.append(info);
		try {
			length+=info.getBytes("utf-8").length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	public Response println(String info){
		content.append(info).append(CRLF);
		try {
			length+=(info+CRLF).getBytes("utf-8").length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	//������Ӧͷ
	private void createHeadInfo(int code){
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch (code) {
		case 200:
			headInfo.append("OK");
			break;
		case 500:
			headInfo.append("SERVER ERROR");
			break;
		default:
			headInfo.append("NOT FOUND");
			break;
		}
		headInfo.append(CRLF);
		headInfo.append("Content-Type:text/html;charset=utf-8").append(CRLF);
		headInfo.append("Content-Length:"+length).append(CRLF);
		headInfo.append(CRLF);
	}
	/**
	 * ���͵��ͻ����������
	 * @param code
	 */
	public void pushToClient(int code){
		if (headInfo==null) {
			code=500;
		}
		try {
			//���ñ����еĹ�����Ӧͷ
			this.createHeadInfo(code);
			bw.write(headInfo.toString());
			bw.write(content.toString());
			bw.flush();
			this.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(){
		IOCloseUtil.CloseALL(bw);
	}
}
