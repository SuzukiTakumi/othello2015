package autumn2015;

import gpjava.Canvas;

import java.util.*;

public class BallConflict {
	BallList ballList;
	int ballNumber=10;
	int screenWidth=500,screenHeight=500;
	
	public static void main(String[] args){
		new BallConflict().start();
	}
	
	void start(){
		ballList=new BallList(ballNumber);
		Canvas.show(screenWidth,screenHeight);
//		while(true){
//			Canvas.clear();
//			ballList.conflict();
//			ballList.draw();
//		}
		Thread thread=new Thread(ballList);
		thread.start();
	}
}

class BallList implements Runnable{
	Thread thread;
	ArrayList<Ball> ballList;
	int ballNumber=0;
	double e=0.8;
	double d=0.99;
	int screenWidth=500,screenHeight=500;
	
	BallList(int n){
		ballList=new ArrayList<Ball>();
		for(int i=0;i<n;i++){add();}
	}
	
	void add(){
		ballList.add(new Ball());
		ballNumber++;
	}
	
	void draw(){
		Canvas.clear();
		for(int i=0;i<ballNumber;i++){ballList.get(i).draw();}
	}
	
	public void run(){
		while(true){
			Canvas.clear();
			conflict();
			draw();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("例外です : " + e.getMessage());
			}
		}
	}
	
	void conflict(){
		for(int i=0;i<ballNumber;i++){
			for(int j=0;j<ballNumber;j++){
				if(i>=j)continue;
				Ball a=ballList.get(i);
				Ball b=ballList.get(j);
				double ab_x=b.x-a.x;
				double ab_y=b.y-a.y;
				double tr=a.radius+b.radius;
				
				if(ab_x*ab_x + ab_y*ab_y < tr*tr){
					double len=(double)Math.sqrt(ab_x*ab_x+ab_y*ab_y);
					double distance=(a.radius+b.radius)-len;
					if(len>0)len=1/len;
					ab_x*=len;
					ab_y*=len;
					
					distance/=2.0;
					a.x-=ab_x*distance;
					a.y-=ab_y*distance;
					b.x+=ab_x*distance;
					b.y+=ab_y*distance;
					
					double ma=(double) ((b.m/(a.m+b.m))*(1+e)*((b.vx-a.vx)*ab_x+(b.vy-a.vy)*ab_y));
					double mb=(double) ((a.m/(a.m+b.m))*(1+e)*((a.vx-b.vx)*ab_x+(a.vy-b.vy)*ab_y));
					a.vx+=ma*ab_x;
					a.vy+=ma*ab_y;
					b.vx+=mb*ab_x;
					b.vy+=mb*ab_y;
				}
			}
		}
		
		for(int k=0;k<ballNumber;k++){
			Ball c=ballList.get(k);
			//c.vx*=d;
			//c.vy+=d;
			c.x+=c.vx;
			c.y+=c.vy;
			
			if(c.x<c.radius){
				c.x=c.radius;
				c.vx*=-1;
			}
			if(c.y<c.radius){
				c.y=c.radius;
				c.vy*=-1;
			}
			if(c.x>screenWidth-c.radius){
				c.x=screenWidth-c.radius;
				c.vx*=-1;
			}
			if(c.y>screenHeight-c.radius){
				c.y=screenHeight-c.radius;
				c.vy*=-1;
			}
		}
	}
	
	void update(int i,Ball b){
		ballList.get(i).x=b.x;
		ballList.get(i).y=b.y;
		ballList.get(i).vx=b.vx;
		ballList.get(i).vy=b.vy;
	}
}

class Ball{
	double x,y;
	double vx,vy;
	double ax,ay;
	double radius=15;//半径
	double m=1;
	int screenWidth=500,screenHeight=500;
	
	Ball(){
		x=radius*2+Math.random()*(screenWidth-radius*4);
		y=radius*2+Math.random()*(screenHeight-radius*4);
		vx=Math.random()*6-3;
		vy=Math.random()*6-3;
		ax=ay=0;
		m=Math.random()*5+1;
		radius+=m;
	}
	
	void move(){
		vx+=ax;
		vy+=ay;
		x+=vx;
		y+=vy;
	}
	
	void draw(){
		//Canvas.setColor(0, 0, 0);
		Canvas.fillOval(x-radius, y-radius, radius*2, radius*2);
	}
}