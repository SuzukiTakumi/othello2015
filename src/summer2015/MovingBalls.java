package summer2015;

import gpjava.Canvas;

public class MovingBalls {
	int width,height;
	int ballNum;
	double realDis,origDis;
	MovingBall[] balls;
	
	MovingBalls(int bn,int width,int height){
		this.width=width;
		this.height=height;
		this.ballNum=bn;
		balls=new MovingBall[ballNum];
		for(int i=0;i<ballNum;i++){
			balls[i]=new MovingBall(width,height);
		}
	}
	
	void setBackground(int r,int g,int b){
		Canvas.setColor(r, g, b);
		Canvas.fillRect(0, 0, width, height);
		Canvas.setColor(0, 0, 0);
	}
	
	void drawBall(){
		for(int i=0;i<ballNum;i++){
			balls[i].drawBall();
		}
	}
	
	void move(){
		for(int i=0;i<ballNum;i++){
			balls[i].move();
		}
		conflict();
		for(int i=0;i<ballNum;i++){
			balls[i].reflect();
		}
	}
	
	void conflict(){
		int i,j;
		for(i=0;i<ballNum;i++){
			for(j=i+1;j<ballNum;j++){
				if(isHit(i,j)){
					conflict(i,j);
				}
			}
		}
	}
	
	void conflict(int i,int j){
		double disDiff=origDis-realDis;
	}
	
	boolean isHit(int i,int j){
		realDis=Math.sqrt((balls[i].x-balls[j].x)*(balls[i].x-balls[j].x)+(balls[i].y-balls[j].y)*(balls[i].y-balls[j].y));
		origDis=balls[i].rad+balls[j].rad;
		if(realDis<origDis){
			return true;
		}else{
			return false;
		}
	}
	
	void clear(){
		balls[0].clear();
	}
}
