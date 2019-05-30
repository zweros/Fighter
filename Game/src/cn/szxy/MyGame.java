package cn.szxy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyGame extends JFrame{

	Image ball  = Toolkit.getDefaultToolkit().getImage("images/ball.png");
	Image desk = Toolkit.getDefaultToolkit().getImage("images/desk.jpg");
	
	double x = 100;
	double y = 100;
	boolean right= true;
	
	public void paint(Graphics g){//自动调用	
		g.drawImage(desk, 0, 0, null);
		g.drawImage(ball,(int)x,(int)y,null);
		
		 if (right) {
	            x = x + 10;
	        } else {
	            x = x - 10;
	        }

	        if (x > 856 - 40 - 30) {
	            right = false;
	        }
	        if (x < 0) {
	            right = true;
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
		MyGame game = new MyGame();
		game.launchFrame();
	}

}
