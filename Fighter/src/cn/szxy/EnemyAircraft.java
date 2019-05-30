package cn.szxy;

import java.awt.Graphics;
import java.awt.Image;
/**
 * 敌机
 * @author wzer
 *
 */
public class EnemyAircraft extends GameObject{
	Image craft_img0= GameUtil.getImage("Images/small_5.png");
	Image craft_img1 = GameUtil.getImage("Images/big_1.png");
	Image craft_img2 = GameUtil.getImage("Images/hit_3.png");
	Image[]  craft_images = {craft_img0,craft_img1,craft_img2};
	boolean live = true;
	int craft_Img_type = (int)(Math.random()*3);//(Math.random*3)外面要有圆括号，不然(int)Math.random()=0
	long start_time = 0;
	
	void drawSelf(Graphics g){
		if(live){
			
			g.drawImage(craft_images[craft_Img_type],(int)x,(int)y, null);
	

			this.y+=speed;
			
			
			if(y>Constant.GAME_HEIGHT){//大于游戏边框，回到起始位置
				
				craft_Img_type = (int)(Math.random()*3);
				this.y = Constant.CRAFT_Y+(int)(Math.random()*20);
			
			}
			
		}
		
	}
	//构造函数，初始化敌机
	public EnemyAircraft(Image img,double craftX,double craftY,int speed) {
		this.image = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		
		//增加趣味性，改变战机的位置
		this.x = craftX+(int)(Math.random()*20);
		this.y = craftY+(int)(Math.random()*20);
		
		this.speed = speed;
		
	}
	
}
