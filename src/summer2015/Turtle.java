package summer2015;

import gpjava.Canvas;

public class Turtle {
	
	int swidth=500,sheight=500;
	int wspeed=10;
	double x=250,y=250;
	double dir=0;
	boolean penDown=true;
	String mode="";
	
	public static void main(String[] args){
		new Turtle().start();
	}
	
	void start(){
		Canvas.show(swidth,sheight);
		test();
		//wheel();
	}
	
	void forward(double dis){
		double prex,prey;
		double nexx=x,nexy=y;
		for(int i=0;i<dis;i++){
			prex=nexx;
			prey=nexy;
			nexx=nexx+Math.cos(dir);
			nexy=nexy-Math.sin(dir);
			Canvas.drawLine(prex, prey, nexx, nexy);
			Canvas.waitForCountdown(wspeed);
		}
		nexx=x+dis*Math.cos(dir);
		nexy=y-dis*Math.sin(dir);
		Canvas.drawLine(x, y, nexx, nexy);
		x=nexx;
		y=nexy;
	}
	
	void back(double dis){
		double prex,prey;
		double nexx=x,nexy=y;
		for(int i=0;i<dis;i++){
			prex=nexx;
			prey=nexy;
			nexx=nexx+Math.cos(dir+Math.PI);
			nexy=nexy-Math.sin(dir+Math.PI);
			Canvas.drawLine(prex, prey, nexx, nexy);
			Canvas.waitForCountdown(wspeed);
		}
		nexx=x+dis*Math.cos(dir+Math.PI);
		nexy=y-dis*Math.sin(dir+Math.PI);
		Canvas.drawLine(x, y, nexx, nexy);
		x=nexx;
		y=nexy;
	}
	
	void left(double deg) {
		dir += Math.PI * (deg / 180);
	}

	void right(double deg) {
		dir -= Math.PI * (deg / 180);
	}
	
	void penDownMode() {
		penDown = true;
		Canvas.setColor(0, 0, 0);
	}

	void penUpMode() {
		penDown = false;
		Canvas.setColor(255, 255, 255);
	}
	
	void slide(double d,String lr) {
		if(lr.equals("l")){
			penUpMode();
			left(90);
			forward(d);
			right(90);
			penDownMode();
		}else{
			penUpMode();
			right(90);
			forward(d);
			left(90);
			penDownMode();
		}
	}
	
	void setPosition(double nx,double ny){
		
	}
	
	
	void test(){
		left(30);
		forward(100);
		left(90);
		forward(50);
		circle(100);
		circle(200);
	}
	
	void circle(double dist) {
		if(mode.equals("circle")){
			for (int i = 0; i < 360; i++) {
				forward(dist*Math.PI/360);
				left(1);
			}
		}else{
			mode="circle";
			for (int i = 0; i < 360; i++) {
				forward(dist*Math.PI/360);
				right(1);
			}
		}
	}

	void qcircle(double dist) {
		if(mode.equals("circle")){
			for (int i = 0; i < 360/4; i++) {
				forward(dist*Math.PI/360);
				left(1);
			}
		}else{
			mode="circle";
			for (int i = 0; i < 360/4; i++) {
				forward(dist*Math.PI/360);
				right(1);
			}
		}
	}
	
	void wheel(){
		mode="wheel";
		wspeed=5;
		for(int i=0;i<12;i++){
			forward(100);
			back(100);
			right(360/12);
		}
	}
}
