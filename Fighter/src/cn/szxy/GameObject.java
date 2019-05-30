package cn.szxy;

import java.awt.Image;
import java.awt.Rectangle;
/**
 * 游戏物体的父类
 * @author wzer
 *
 */
public class GameObject {
	Image image;
	double x,y;
	int speed;
	int width,height;
	public GameObject(Image image, double x, double y, int speed, int width,
			int height) {
		super();
		this.image = image;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}
	public GameObject() {
		// TODO Auto-generated constructor stub
	}
	//返回物理所在的矩形，便于后续的碰撞检测
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,width,height);
	}
}		
