package cn.szxy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyGame2 extends JFrame{

	Image ball  = Toolkit.getDefaultToolkit().getImage("images/ball.png");
	Image desk = Toolkit.getDefaultToolkit().getImage("images/desk.jpg");
	
	double x = 100;
	double y = 100;
	
	double degree = 3.14/3;
	
	public void paint(Graphics g){
		g.drawImage(desk, 0, 0, null);
		g.drawImage(ball,(int)x,(int)y,null);
		
		 x = x + 10*Math.cos(degree);
         y = y + 10*Math.sin(degree);

        if( (y>500-40) || y<40+40){
            degree = - degree;
        }

        if(x<40||x>856-40-30){
            degree = 3.14-degree;
        }
			
	}
	

	void launchFrame(){
		this.setSize(856, 500);
		this.setLocation(500, 500);
		this.setVisible(true);
		
		while(true){
			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyGame2 game = new MyGame2();
		game.launchFrame();
	}

}
