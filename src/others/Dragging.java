package others;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Dragging extends Applet implements Runnable {
	
	Thread thread;
	Player player;
	int width = 500, height = 500;
	int x, y;
	boolean up, down, left, right;
	
	public void init(){
		setSize(width, height);
		addKeyListener(new MyKeyAdapter());
		player = new Player(width / 2,height);
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g){
		player.draw(g);
		requestFocusInWindow();
	}
	
	public void run(){
		while(true){
			
			if(left)player.moveLeft();
			else if(right)player.moveRight();
			else player.stopX();
			
			player.move();
			
			repaint();
			
			try{
				Thread.sleep(20);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}
	
	class Player{
		int x, y;
		int vx, vy;
		int width = 30, height = 30;
		
		Player(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		void draw(Graphics g){
			g.setColor(Color.BLUE);
			g.fillRect(x - width / 2, y - height, width, height);
		}
		
		void move(){
			x += vx;
			y += vy;
		}
		
		void moveLeft(){
			vx -= 1;
		}
		
		void moveRight(){
			vx += 1;
		}
		
		void stopX(){
			vx = 0;
		}
		
		void stopY(){
			vy = 0;
		}
	}
	
	class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent ke){
			stateChange(ke.getKeyCode(), true);
		}
		
		@Override
		public void keyReleased(KeyEvent ke){
			stateChange(ke.getKeyCode(), false);
		}
		
		private void stateChange(int keyCode, boolean state){
			switch(keyCode){
			case KeyEvent.VK_UP : up = state; break;
			case KeyEvent.VK_DOWN : down = state; break;
			case KeyEvent.VK_LEFT : left = state; break;
			case KeyEvent.VK_RIGHT : right = state; break;
			}
		}
	}
}
