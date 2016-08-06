package summer2015;

public class ConflictionBall {
	MovingBall[] data;
	double[] xs,ys,vxs,vys,dias;
	
	ConflictionBall(MovingBall[] mvs){
		data=mvs;
		xs=new double[data.length];
		ys=new double[data.length];
		vxs=new double[data.length];
		vys=new double[data.length];
		dias=new double[data.length];
		for(int i=0;i<data.length;i++){
			dias[i]=data[i].dia;
		}
	}
	
	void conflicting(){
		setData();
		for(int i=0;i<data.length;i++){
			for(int j=i+1;j<data.length;j++){
				analysis(i,j);
			}
		}
	}
	
	void setData(){
		for(int i=0;i<data.length;i++){
			xs[i]=data[i].x;
			ys[i]=data[i].y;
			vxs[i]=data[i].vx;
			vys[i]=data[i].vy;
		}
	}
	
	void analysis(int i,int j){
		double disX=Math.abs(xs[i]-xs[j]);
		double disY=Math.abs(ys[i]-ys[j]);
		double dis2=Math.pow(disX, 2)+Math.pow(disY, 2);
		double dia=dias[i]/2+dias[j]/2;
		double dia2=Math.pow(dia, 2);
		if(dis2<=dia2){
			vxs[i]*=-1;
			vys[j]*=-1;
			vxs[i]*=-1;
			vys[j]*=-1;
		}
	}
	
	void returnDataX(String str,int i){
		if(str.equals("x")){
			
		}else if(str.equals("y")){
			
		}else if(str.equals("y")){
			
		}else if(str.equals("y")){
			
		}
	}
}
