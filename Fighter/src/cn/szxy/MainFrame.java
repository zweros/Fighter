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
		//����ͼƬ
		Image bg = GameUtil.getImage("Images/img_bg_level_3.jpg");
		Image myPlane_Img = GameUtil.getImage("Images/MY_plane.png");
		Image shell_Img = GameUtil.getImage("Images/My_zidan.png");
		
		Image craft_img0= GameUtil.getImage("Images/small_5.png");
		MyPlane myPlane = new MyPlane(myPlane_Img, 250, 400, 6);
		
		
		//�����л�
		EnemyAircraft[] crafts = {
				new EnemyAircraft(craft_img0,Constant.CRAFT1_x,Constant.CRAFT_Y,5),
				new EnemyAircraft(craft_img0,Constant.CRAFT2_x,Constant.CRAFT_Y,5),
				new EnemyAircraft(craft_img0,Constant.CRAFT3_x,Constant.CRAFT_Y,5)
		};
		
		int  shellX = myPlane.getMyPlaneX()+38;//ȷ�������� Y λ��
		int shellY = myPlane.getMyPlanexY(); //ȷ�������� x ��λ��
		Shell shell = new Shell(shell_Img,shellX,shellY,30);//���������Ķ���
	
//		Explode boom=null;//��ը
		boolean GameOver = false;
		int Count=0; //�ɻ���ը����ͼƬ���ֲ��Ĵ���

	@Override
	public void paint(Graphics g) {//g �ǻ���
		Color c = g.getColor();//�����ɫ
		
		
		g.drawImage(bg, 0, 0, null);//����ͼ
		myPlane.drawSelf(g);//���ɻ�
		crafts[0].drawSelf(g); //
		crafts[1].drawSelf(g);
		crafts[2].drawSelf(g);
		
		shell.drawSelf(g);
		
		
		
		
		//��ײ���
		for(int i=0;i<crafts.length;i++){
			//������л�����ײ
			boolean flag = shell.getRect().intersects(crafts[i].getRect());
			if(flag){
				//System.out.println("��ը��");
				crafts[i].live = false;
				crafts[i].y = Constant.CRAFT_Y;//�л��ص���ʼλ��
			
				
				if(i==0){//�ӷ�
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
			  if(Count==15){//�ȵ���ըͼƬȫ���ֲ���ɺ󣬽���
				   myPlane.x = 700;
				   myPlane.y = 700;
				   crafts[i].y = Constant.CRAFT_Y;//�л��ص���ʼλ��
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
			  g.setFont(new Font("����",Font.BOLD,50));
			  g.drawString("��Ϸ����", 120, 250);
		}
		
		
		g.setColor(c.RED);
		g.setFont(new Font("����",Font.BOLD,30));
		g.drawString("�ɼ�Ϊ��"+Constant.score, 20, 70);
		
		//g.setColor(c);
		
		
		
	}
	
	class PrintThread extends  Thread{//ˢ�½���
		@Override
		public void run() {
			while(true){
				repaint();
				
				try {
					Thread.sleep(20);//��Ϣ 40ms  
					//ÿ��ˢ�� 1000/24=50 ��
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	void lunchFrame(){//��������
		this.setTitle("Demo");
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(700, 10);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){//�رմ���
			public void windowClosing(WindowEvent e) {System.exit(0);}});
		
		//this.addKeyListener(new KeyMintor());
		this.addKeyListener(new KeyAdapter() {//��ؼ��̵��ƶ�
			 public void keyPressed(KeyEvent e) {
				 //System.out.println("����"+e.getKeyCode());
				 myPlane.addDirction(e);
			 }
			 public void keyReleased(KeyEvent e) {
				 //System.out.println("�ɿ�"+e.getKeyCode());
				 myPlane.minussDirction(e);
			 }
		});
		
		this.addMouseListener(new MouseAdapter() {//�������ƶ�
			/*public void mousePressed(MouseEvent e){
				System.out.println("���꣺"+e.getX()+"\t"+e.getY());
			}
			@Override*/
			public void mouseMoved(MouseEvent e) {
				System.out.println("���꣺"+e.getX()+"\t"+e.getY());
			}
		});
		
		new PrintThread().start();//�ػ�����
	}
	/**
	 * main����
	 * @param args
	 */
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
		f.lunchFrame();
	}
	
	//�����˸����
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(500,500);//������Ϸ���ڵĿ�Ⱥ͸߶�
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
}
