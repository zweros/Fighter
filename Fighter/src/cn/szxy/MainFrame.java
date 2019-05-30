package cn.szxy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import cn.szxy.Explode;




public class MainFrame extends Frame{
		//加载图片
		Image bg = GameUtil.getImage("Images/img_bg_level_3.jpg");
		Image myPlane_Img = GameUtil.getImage("Images/MY_plane.png");
		Image shell_Img = GameUtil.getImage("Images/My_zidan.png");
		
		Image craft_img0= GameUtil.getImage("Images/small_5.png");
		MyPlane myPlane = new MyPlane(myPlane_Img, 250, 400, 6);
		
		
		//创建敌机
		EnemyAircraft[] crafts = {
				new EnemyAircraft(craft_img0,Constant.CRAFT1_x,Constant.CRAFT_Y,5),
				new EnemyAircraft(craft_img0,Constant.CRAFT2_x,Constant.CRAFT_Y,5),
				new EnemyAircraft(craft_img0,Constant.CRAFT3_x,Constant.CRAFT_Y,5)
		};
		
		int  shellX = myPlane.getMyPlaneX()+38;//确定导弹的 Y 位置
		int shellY = myPlane.getMyPlanexY(); //确定导弹的 x 轴位置
		Shell shell = new Shell(shell_Img,shellX,shellY,30);//创建导弹的对象
	
//		Explode boom=null;//爆炸
		boolean GameOver = false;
		int Count=0; //飞机爆炸所需图片的轮播的次数

	@Override
	public void paint(Graphics g) {//g 是画笔
		Color c = g.getColor();//获得颜色
		
		
		g.drawImage(bg, 0, 0, null);//背景图
		myPlane.drawSelf(g);//画飞机
		crafts[0].drawSelf(g); //
		crafts[1].drawSelf(g);
		crafts[2].drawSelf(g);
		
		shell.drawSelf(g);
		
		
		
		
		//碰撞检测
		for(int i=0;i<crafts.length;i++){
			//导弹与敌机的碰撞
			boolean flag = shell.getRect().intersects(crafts[i].getRect());
			if(flag){
				//System.out.println("爆炸了");
				crafts[i].live = false;
				crafts[i].y = Constant.CRAFT_Y;//敌机回到初始位置
			
				
				if(i==0){//加分
					Constant.score +=1;
				}else if(i==1){
					Constant.score +=2;
				}else{
					Constant.score +=10;
				}
				if(Explode.boom==null){
					Explode.boom = new Explode(crafts[i].x, crafts[i].y);
				}
				Explode.boom.draw(g);
				
			}else{
				crafts[i].live = true;
			}
			
		  boolean flag2 = myPlane.getRect().intersects(crafts[i].getRect());
		  if(flag2){
			  GameOver = true;
			  myPlane.live = false;
			  shell.live = false;
			  if(Count==15){//等到爆炸图片全部轮播完成后，结束
				   myPlane.x = 700;
				   myPlane.y = 700;
				   crafts[i].y = Constant.CRAFT_Y;//敌机回到初始位置
			  }else{
				  Count++;
			  }
			  crafts[i].live = false;
			  
			  if(Explode.boom==null){
				   Explode.boom = new Explode(myPlane.x, myPlane.y);
			  }
			  Explode.boom.draw(g);
			  
			 
			  
		  }
		}
		if(GameOver){
			  g.setColor(c.RED);
			  g.setFont(new Font("宋体",Font.BOLD,50));
			  g.drawString("游戏结束", 120, 250);
		}
		
		
		g.setColor(c.RED);
		g.setFont(new Font("宋体",Font.BOLD,30));
		g.drawString("成绩为："+Constant.score, 20, 70);
		
		//g.setColor(c);
		
		
		
	}
	
	class PrintThread extends  Thread{//刷新界面
		@Override
		public void run() {
			while(true){
				repaint();
				
				try {
					Thread.sleep(20);//休息 40ms  
					//每秒刷新 1000/24=50 次
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	void lunchFrame(){//启动窗口
		this.setTitle("Demo");
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(700, 10);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){//关闭窗口
			public void windowClosing(WindowEvent e) {System.exit(0);}});
		
		//this.addKeyListener(new KeyMintor());
		this.addKeyListener(new KeyAdapter() {//监控键盘的移动
			 public void keyPressed(KeyEvent e) {
				 //System.out.println("按下"+e.getKeyCode());
				 myPlane.addDirction(e);
			 }
			 public void keyReleased(KeyEvent e) {
				 //System.out.println("松开"+e.getKeyCode());
				 myPlane.minussDirction(e);
			 }
		});
		
		this.addMouseListener(new MouseAdapter() {//监控鼠标移动
			/*public void mousePressed(MouseEvent e){
				System.out.println("坐标："+e.getX()+"\t"+e.getY());
			}
			@Override*/
			public void mouseMoved(MouseEvent e) {
				System.out.println("坐标："+e.getX()+"\t"+e.getY());
			}
		});
		
		new PrintThread().start();//重画窗口
	}
	/**
	 * main方法
	 * @param args
	 */
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
		f.lunchFrame();
	}
	
	//解决闪烁问题
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(500,500);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
}
