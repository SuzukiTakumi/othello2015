package summer2015;

import gpjava.Canvas;

public class MovingBall {
	double x, y;// location,centerOfBall
	double vx, vy, fvy;// speed
	double ax, ay;// acceleration
	int rad, dia;// diameter
	int width, height;
	int hWidth, hHeight;
	int br, bg, bb;

	MovingBall(int width, int height) {
		this.width = width;
		this.height = height;
		this.hWidth = width / 2;
		this.hHeight = height / 2;

		this.rad = (int) (Math.random() * (width / 40)) + (width / 40);
		this.dia = rad * 2;

		this.x = dia + Math.random() * (width - dia * 2);
		this.y = dia + Math.random() * (height - dia * 2);
		this.vx = Math.random() * 20 - 10;
		this.vy = Math.random() * 20 - 10;
		this.fvy = this.vy;
		this.ax = 0;
		this.ay = 0;

		this.br = 0;
		this.bg = 0;
		this.bb = 0;
	}

	MovingBall(double x, double y, double vx, double vy, int rad, int wi, int he) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.fvy = vy;
		this.ax = 0;
		this.ay = 0;
		this.rad = rad;
		this.dia = rad * 2;
		width = wi;
		height = he;
		this.hWidth = width / 2;
		this.hHeight = height / 2;
		this.br = 0;
		this.bg = 0;
		this.bb = 0;
	}

	void drawBall() {
		Canvas.setColor(255 - br, 255 - bg, 255 - bb);
		Canvas.fillOval(x - rad - 1, y - rad - 1, dia + 2, dia + 2);
		Canvas.setColor(br, bg, bb);
		Canvas.fillOval(x - rad, y - rad, dia, dia);
	}

	void move() {
		vx += ax;
		vy += ay;
		x += vx;
		y += vy;
	}

	void reflect() {
		double nx = Math.abs(x - hWidth) + rad;
		if (nx >= width / 2) {
			vx *= -1;
		}

		double ny = Math.abs(y - hHeight) + rad;
		if (ny >= height / 2) {
			vy *= -1;
		}
	}

	void clear() {
		Canvas.waitForCountdown(10);
		Canvas.clear();
	}
}
