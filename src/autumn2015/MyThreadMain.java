package autumn2015;

public class MyThreadMain {
	public static void main(String[] args){
		new MyThreadMain().start();
	}
	
	void start(){
		MyThreadSub sub=new MyThreadSub(5);
		Thread subThread=new Thread(sub);
		subThread.start();
		
		while(sub.confirmRunning()){
			System.out.println("Main");
		}
	}
}

class MyThreadSub implements Runnable{
	int times;
	boolean running=true;
	
	MyThreadSub(int i){
		times=i;
	}
	
	public void run(){
		for(int i=0;i<times;i++){
			System.out.println("Sub start Sleeping");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			System.out.println("Sub stop Sleeping");
		}
		running=false;
	}
	
	boolean confirmRunning(){
		return running;
	}
}
