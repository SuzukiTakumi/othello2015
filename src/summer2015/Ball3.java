package summer2015;

import gpjava.Canvas;

public class Ball3 {
	int width=500,height=500;
	public static void main(String[] args){
		new Ball3().start();
	}
	
	void start(){
		MovingBalls balls=new MovingBalls(3,width,height);
		
		Canvas.show(width,height);
		
		while(true){
			balls.setBackground(100, 100, 200);
			balls.drawBall();
			balls.move();
			balls.clear();
		}
	}
}
