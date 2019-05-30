package cn.szxy;

import java.awt.Graphics;
import java.awt.Image;

public class Shell extends GameObject{
	Image shell_Img = GameUtil.getImage("Images/My_zidan.png");
	boolean live = true;
	void drawSelf(Graphics g){
		if(live){
			g.drawImage(shell_Img,(int)this.x,(int)this.y, null);
			  this.y -=20;
			//this.y-=this.speed;
			if( y < 0){//子弹达到边界不在,
				//System.out.println("子弹消失");
				this.y = Constant.myPlane_y;
				this.x = Constant.myPlane_x+38;
			}
			
		}	
	}
	 
	
	public Shell(Image img,double shellX,double shellY,int spend) {
		this.image = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.x = shellX;
		this.y = shellY;
		this.speed = speed;
	}
}
