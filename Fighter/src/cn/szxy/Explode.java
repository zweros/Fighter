package cn.szxy;

import java.awt.Graphics;
import java.awt.Image;

 
/*
 * ��ը��
 */
public class Explode {
    double x,y;
    static Explode boom=null;
    static Image[] imgs = new Image[16];
    static {
        for(int i=0;i<16;i++){//��ñ�ըͼƬ
            imgs[i] = GameUtil.getImage("Images/explode/e"+(i+1)+".gif");
            imgs[i].getWidth(null);
        }
    }
     
    int count=0;// ��¼�ֲ��Ĵ���
    boolean flag =false;//��֤15��ͼƬȫ���ֲ����
    //�ֲ�16��ͼƬ  �γɶ���Ч��
    public void draw(Graphics g){
        if(count<=15){
            g.drawImage(imgs[count], (int)x, (int)y, null);
            count++;
            if(count==15){
            	flag=true;
            }
        }
        if(flag){
        	count =0;
        	flag=false;
        	Explode.boom=null;
        	
        }
    }
     
    public Explode(double x,double y){
        this.x = x;
        this.y = y;
    }
}