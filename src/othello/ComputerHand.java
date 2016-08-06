package othello;

public class ComputerHand extends Hand {
	
	final int n = 8, S_N = 0, S_B = 1, S_W = 2;
	int ti, tj;
	int t, stState[][];
	Spread sprs;
	boolean det;
	
	ComputerHand(){
		sprs = new Spread();
	}
	
	int getI(){
		return ti;
	}
	
	int getJ(){
		return tj;
	}
	
	boolean hit(int[][] state, int turn){//there is(are) hittable place(s) -> true
		stState = state;
		t = turn;
		det = false;
		puttable_All();
		
		if(sprs.getSize() !=0 ){
			edge();
			mostGettable();
		}
		return det;
	}
	
	int puttable(int i, int j){
		int get = 0;
		if(stState[i][j] == S_N){
			for(int k = -1; k <= 1; k++){
				for(int l = -1; l <= 1; l++){
					get += puttable_search(i, j, k, l);
				}
			}
		}
		return get;
	}
	
	int puttable_search(int i, int j, int di, int dj){
		int state, stock = 0;
		
		while(true){
			i += di;
			j += dj;
			if(onBoard(i, j)){
				state = stState[i][j];
				if(state == t) break;
				else if(state != S_N) {
					stock++;
					continue;
				}
			}
			stock = 0;
			break;
		}
		
		return stock;
	}
	
	void puttable_All(){
		sprs.reset();
		int aGet = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				aGet = puttable(i, j);
				if(aGet != 0) {
					sprs.add(i, j, aGet);
				}
			}
		}
	}
	
	boolean onBoard(int i, int j){
		if(0 <= i && i < n && 0 <= j && j < n) return true;
		else return false;
	}
	
	void register(int i){
		ti = sprs.getI(i);
		tj = sprs.getJ(i);
		det = true;
	}
	
	void edge(){
		int i;
		if(det) return;
		for(i = 0; i < sprs.getSize(); i++){
			if((sprs.getI(i) == 0 || sprs.getI(i) == n - 1)&&(sprs.getJ(i) == 0 || sprs.getJ(i) == n - 1)){
				register(i);
				return;
			}
		}
		for(i = 0; i < sprs.getSize(); i++){
			if(sprs.getI(i) == 0 || sprs.getI(i) == n - 1 || sprs.getJ(i) == 0 || sprs.getJ(i) == n - 1){
				register(i);
				return;
			}
		}
	}
	
	void mostGettable(){//pattern1
		if(det) return;
		int mn = sprs.getSize();
		if(mn == 0) return;
		for(int i = 0; i < mn; i++){
			if(sprs.getSp(i) == sprs.getMS()){
				register(i);
			}
		}
	}
	
	void analyze(Spread spread){
		
	}
}