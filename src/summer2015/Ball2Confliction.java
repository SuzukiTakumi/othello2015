package summer2015;

import gpjava.Canvas;

public class Ball2Confliction {
	int width=500,height=500,dia=30;
	public static void main(String[] args){
		new Ball2Confliction().start();
	}
	
	void start(){
		MovingBall[] mvs=new MovingBall[2];
		mvs[0] = new MovingBall(width, height);
		mvs[1] = new MovingBall(width, height);
		ConflictionBall cb=new ConflictionBall(mvs);
		Canvas.show(width, height);
		int i;
		while (true) {
			for(i=0;i<mvs.length;i++){
				mvs[i].drawBall();
				mvs[i].move();
			}
			cb.conflicting();
			mvs[0].clear();
		}
	}
}
