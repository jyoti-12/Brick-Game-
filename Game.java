import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JPanel;


public class Game extends JPanel implements ActionListener ,KeyListener{

	private boolean play=false;
	private int score=0;
	private Timer timer;
	private int totalbricks=21;
	private Bricks brick;
	Random ran=new Random();
	int n;
	private int delay=8,playerX=310,ballx=120,bally=350,balldirx=-1,balldiry=-2;
	public  Game() {
		brick=new Bricks(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
		
	}
	 
    public void paint(java.awt.Graphics g) {
    	g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		brick.draw((Graphics2D)g);
		g.setColor(Color.green);
		g.fillRect(0, 0, 3, 592);
		g.setColor(Color.white);
		g.setFont(new Font("Serif",Font.BOLD,24));
		g.drawString(""+score,590, 30);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(692, 0, 3, 592);
		g.setColor(Color.yellow);
		g.fillRect(playerX, 550, 100,8);
		g.setColor(Color.yellow);
		g.fillOval(ballx, bally, 20, 20);
		if(totalbricks<=0){
			play=false;
			balldirx=0;
			balldiry=0;
			g.setColor(Color.RED);
			g.setFont(new Font("Serif",Font.BOLD,30));
			g.drawString("You Won "+score,190, 300);
			g.setFont(new Font("Serif",Font.BOLD,20));
			g.drawString("Press Enter to start level-2 ",230, 350);
			delay-=4;
		}
		if(bally> 570){
			play=false;
			balldirx=0;
			balldiry=0;
			g.setColor(Color.RED);
			g.setFont(new Font("Serif",Font.BOLD,30));
			g.drawString("Game Over , Scores : "+score,190, 300);
			g.setFont(new Font("Serif",Font.BOLD,20));
			g.drawString("Press R to restart ",230, 350);
			delay=8;
		}
		g.dispose();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			if(playerX>=600)
				playerX=600;
			else
				moveright();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			if(playerX<10)
				playerX=10;
			else
				moveleft();
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(!play){
				play=true;
				ballx=120;
				bally=350;
				balldirx=-1;
				balldiry=-2;
				playerX=310;
				//score=0;
				totalbricks=32;
				brick=new Bricks(4,8);
				repaint();
			}
			if(e.getKeyCode()==KeyEvent.VK_R){
				if(!play){
					play=true;
					delay=8;
					ballx=120;
					bally=350;
					balldirx=-1;
					balldiry=-2;
					playerX=310;
					score=0;
					totalbricks=21;
					brick=new Bricks(3,7);
					repaint();
				}
		}
		}
		
	}

	private void moveleft() {
		play=true;
		playerX-=20;	
	}
	private void moveright() {
		play=true;
		playerX+=20;
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(play){
			if(new Rectangle(ballx, bally, 20, 20).intersects(new Rectangle(playerX,550,100,5))){
				balldiry= -balldiry;
			}
			outer:for (int i = 0; i < Bricks.array.length; i++) {
				for (int j = 0; j < Bricks.array[0].length; j++) {
					if(Bricks.array[i][j]>0){
						int brickwidth=Bricks.width;
						int brickheight=Bricks.height;
						int brickx=j*brickwidth+80;
						int bricky=i*brickheight+50;
						Rectangle r=new Rectangle(brickx, bricky, brickwidth, brickheight);
						Rectangle ball=new Rectangle(ballx,bally,20,20);
						Rectangle brick=r;
						if(ball.intersects(brick)){
							Bricks.setbrickvalue(0, i, j);
							totalbricks--;
							score+=10;
							if(ballx+19 <= brick.x || ballx+1>= brick.x+brick.width){
								balldirx= -balldirx;
							}else
								balldiry= -balldiry;
							break outer;
						}
					}
				}
			}
			ballx+=balldirx;
			bally+=balldiry;
			if(ballx<0)
				balldirx= -balldirx;
			if(bally<0)
				balldiry= -balldiry;
			if(ballx>670)
				balldirx= -balldirx;
		}
		repaint();
	}

}
