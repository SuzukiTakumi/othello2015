package summer2015;

public class Hoge {
	int[][] origin={{127,100,82,99,70},{10,38,42,49,71},
			{19,21,39,207,208},{53,57,180,209,210},{197,243,222,61,180}};
	int[][] insert={{120,110,92,109,80},{19,39,42,42,61},
			{29,29,42,200,215},{62,90,107,211,211},{200,230,230,50,175}};
	double mse=0,psnr=0;
	
	public static void main(String[] args){
		new Hoge().start();
	}
	
	void start(){
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				//mse=mse+(origin[i][j]-insert[i][j])*(origin[i][j]-insert[i][j]);
				mse=mse+Math.pow((origin[i][j]-insert[i][j]), 2);
			}
		}
		System.out.println("sum="+mse);
		mse=mse/(5*5);
		System.out.println("MSE="+mse);
		
		psnr=10*Math.log10((255*255)/mse);
		System.out.println("PSNR="+psnr);
		//MSE=313.72
		//PSNR=23.165381545298573

	}
}
