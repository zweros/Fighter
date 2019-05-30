package cn.szxy;

import java.awt.Graphics;
import java.awt.Image;

 
/*
 * 爆炸类
 */
public class Explode {
    double x,y;
    static Explode boom=null;
    static Image[] imgs = new Image[16];
    static {
        for(int i=0;i<16;i++){//获得爆炸图片
            imgs[i] = GameUtil.getImage("Images/explode/e"+(i+1)+".gif");
            imgs[i].getWidth(null);
        }
    }
     
    int count=0;// 记录轮播的次数
    boolean flag =false;//保证15张图片全部轮播完成
    //轮播16张图片  形成动画效果
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