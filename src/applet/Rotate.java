package applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class Rotate extends Applet {

	Thread thread = null;
	int width = 500, height = 500;
	int x, y;
	int diameterW = 100,diameterH = 100;
	int d,n;
	
	Color[] blackOrWhite = {Color.BLACK, Color.WHITE};
	
	public void init(){
		setSize(500,500);
		x = width / 2 - diameterW / 2;
		y = height / 2 - diameterW / 2;
		d = -1;
		n = 0;
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(blackOrWhite[n]);
		g.fillOval(x,y,diameterW,diameterH);
		g.setColor(blackOrWhite[1 - n]);
		g.drawOval(x,y,diameterW,diameterH);
	}
	
	public void run(){
		while(true){
			if(diameterW < 0 || diameterW > diameterH){
				d *= -1;
				if(diameterW < 0) n = 1 - n;
			}
			
			diameterW += d;
			x = width / 2 - diameterW / 2;
			y = height / 2 - diameterH / 2;
			
			repaint();
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				
			}
		}
	}
}
