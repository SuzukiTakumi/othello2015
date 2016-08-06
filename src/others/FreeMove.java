package others;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FreeMove extends Applet implements Runnable {
	
	int screen_width = 500, screen_height = 500;
	Player player;
	Image image;
	
	public void init(){
		setSize(screen_width, screen_height);
		image = createImage(screen_width, screen_height);
		player = new Player(screen_width / 2, screen_height);
		
		addKeyListener(player);
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void update(Graphics g){
		paint(g);
	}
	
	public void paint(Graphics g){
		Graphics ig = image.getGraphics();
		ig.clearRect(0, 0, screen_width, screen_height);
		player.draw(ig);
		g.drawImage(image, 0, 0, this);
		requestFocusInWindow();
	}
	
	public void run(){
		while(true){
			repaint();
			try{
				Thread.sleep(10);
			}catch(InterruptedException ie){
				
			}
		}
	}
	
	private class Player implements KeyListener {
		float x, y, vx, vy, gv = 9.8f, jv = -50;
		int width = 10, height = 20;
		Color color = Color.BLUE;
		boolean left = false, right = false, up = false;
		
		Player(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		private void draw(Graphics g){
			move();
			g.setColor(color);
			g.fillRect((int)(x - width / 2) % screen_width, (int)y - height, width, height);
		}
		
		private void move(){
			if(left) vx = -1;
			else if(right) vx = 1f;
			else vx = 0;
			x += vx;
			
			if(y + vy + gv <= screen_height){
				vy += gv;
			}else {
				vy = 0;
				y = screen_height;
				if(up) jump();
			}
			
			y += vy;
			
		}
		
		private void jump(){
			if(onGround()) vy += jv;
		}
		
		private boolean onGround(){
			if(y < screen_height) y = screen_height;
			return y == screen_height;
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			setKeyState(e, true);
			if(!onGround()) up = false;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			setKeyState(e, false);
		}
		
		private void setKeyState(KeyEvent ke, boolean state){
			int key = ke.getKeyCode();
			switch(key){
			case KeyEvent.VK_LEFT:
				left = state;
				break;
			case KeyEvent.VK_RIGHT:
				right = state;
				break;
			case KeyEvent.VK_UP:
				up = state;
				break;
			}
		}
	}
}