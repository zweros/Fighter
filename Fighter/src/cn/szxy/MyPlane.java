package cn.szxy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyPlane extends GameObject{
	//Image planeImg = GameUtil.getImage("Images/My_plane.png");
	boolean up,down,left,right;//上下左右
	boolean live = true; //默认飞机是活的
	
	public void drawSelf(Graphics g){
		if(live){
			g.drawImage(image,(int)x,(int)y,null);
			//x++;
			//this.speed =3;
			if(left){
				x-=speed;
			}
			if(right){
				x+=speed;
			}
			if(up){
				y-=speed;
			}
			if(down){
				y+=speed;
			}
			Constant.myPlane_x = (int)x;
			Constant.myPlane_y = (int)y;
		}else{
			//System.out.println("death");
		}
		
		
	}
	public MyPlane(Image img,double planeX,double planeY,int speed) {
		// TODO Auto-generated constructor stub
		this.image = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		
		this.x = planeX;//飞机的横坐标
		this.y = planeY;//飞机的纵坐标

		this.speed = speed;
		
	}
	
	public void lunchBullet(){
		
	}
	
	public void addDirction(KeyEvent e){//按下方向键
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		default:
			break;
		}
	}
	
	public void minussDirction(KeyEvent e){//松开方向键
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		default:
			break;
		}
	}
	
	public  int getMyPlaneX(){
		return (int)this.x;
	}
	public int getMyPlanexY(){
		return (int)this.y;
	}
}
