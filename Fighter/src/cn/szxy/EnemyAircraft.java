package cn.szxy;

import java.awt.Graphics;
import java.awt.Image;
/**
 * �л�
 * @author wzer
 *
 */
public class EnemyAircraft extends GameObject{
	Image craft_img0= GameUtil.getImage("Images/small_5.png");
	Image craft_img1 = GameUtil.getImage("Images/big_1.png");
	Image craft_img2 = GameUtil.getImage("Images/hit_3.png");
	Image[]  craft_images = {craft_img0,craft_img1,craft_img2};
	boolean live = true;
	int craft_Img_type = (int)(Math.random()*3);//(Math.random*3)����Ҫ��Բ���ţ���Ȼ(int)Math.random()=0
	long start_time = 0;
	
	void drawSelf(Graphics g){
		if(live){
			
			g.drawImage(craft_images[craft_Img_type],(int)x,(int)y, null);
	

			this.y+=speed;
			
			
			if(y>Constant.GAME_HEIGHT){//������Ϸ�߿򣬻ص���ʼλ��
				
				craft_Img_type = (int)(Math.random()*3);
				this.y = Constant.CRAFT_Y+(int)(Math.random()*20);
			
			}
			
		}
		
	}
	//���캯������ʼ���л�
	public EnemyAircraft(Image img,double craftX,double craftY,int speed) {
		this.image = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		
		//����Ȥζ�ԣ��ı�ս����λ��
		this.x = craftX+(int)(Math.random()*20);
		this.y = craftY+(int)(Math.random()*20);
		
		this.speed = speed;
		
	}
	
}
