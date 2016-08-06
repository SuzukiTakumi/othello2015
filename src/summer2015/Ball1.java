package summer2015;

import gpjava.Canvas;

public class Ball1 {
	int width = 500, height = 500;

	public static void main(String[] args) {
		new Ball1().start();
	}

	void start() {
		MovingBall mv1 = new MovingBall(width, height);
		Canvas.show(width, height);
		while (true) {
			mv1.drawBall();
			mv1.move();
			mv1.reflect();
			mv1.clear();
		}
	}
}
