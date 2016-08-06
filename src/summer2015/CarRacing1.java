package summer2015;

import java.awt.*;
import java.applet.*;

public class CarRacing1 extends Applet implements Runnable {
	
	Thread thread=null;
	int width,height;
	
	public void init(){
		thread=new Thread(this);
		thread.start();
		Dimension size=getSize();
		width=size.width;
		height=size.height;
	}
	
	public void start(){
		
	}
	
	public void stop(){
		
	}
	
	public void destroy(){
		
	}
	
	public void paint(Graphics g){
		g.setColor(Color.blue);
		g.drawString("HeyWorld!!", width/2,height/2);
		g.setColor(new Color(255,0,0));
		g.drawLine(0, 60, 200, 60);
		g.setColor(Color.black);
		g.fillArc(100, 100, 50, 50, 10, 50);
		int xs[]={100,125,150,125,150,100, 50, 75, 50, 75,100};
		int ys[]={ 50, 75, 75,100,125,100,125,100, 75, 75, 50};
		g.drawPolyline(xs, ys, xs.length);
		g.drawPolygon(xs, ys, xs.length);
	}
	
	public void repaint(){
		
	}
	
	public void update(Graphics g){
		
	}
	
	public void run(){
		
	}
}
