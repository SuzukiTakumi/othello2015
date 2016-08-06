package othello;

import java.awt.Point;
import java.util.ArrayList;

class Spread{
	ArrayList<Spread_hand> handList;
	int moSp;//mostSpread
	
	Spread(){
		handList = new ArrayList<Spread_hand>();
		moSp = 0;
	}
	
	void add(int i, int j, int sp){
		handList.add(new Spread_hand(i, j, sp));
		if(moSp < sp) moSp = sp;
	}
	
	void reset(){
		handList = new ArrayList<Spread_hand>();
		moSp = 0;
	}
	
	boolean exist(int i, int j){
		for(int t = 0; t < getSize(); t++){
			if(getI(t) == i && getJ(t) == j) return true;
		}
		return false;
	}
	
	int getNum(int i, int j){
		int t;
		for(t = 0; t < getSize(); t++){
			if(getI(t) == i && getJ(t) == j) break;
		}
		return t;
	}
	
	int getI(int i){
		return handList.get(i).pos.x;
	}
	
	int getJ(int j){
		return handList.get(j).pos.y;
	}
	
	int getSp(int sp){
		return handList.get(sp).spr;
	}
	
	int getMS(){
		return moSp;
	}
	
	int getSize(){
		return handList.size();
	}
	
	int getDiSp(int disp, int di, int dj){
		return handList.get(disp).sprs_arr[1 + di][1 + dj];
	}
	
	int getDiSp(int i, int j, int di, int dj){
		int disp = getNum(i,j);
		return handList.get(disp).sprs_arr[1 + di][1 + dj];
	}
	
	boolean spreadable(int i, int j, int di, int dj){
		return (getDiSp(i, j, di, dj) != 0);
	}
	
	void setDiSp(int di, int dj, int sp){
		handList.get(getSize() - 1).sprs_arr[1 + di][1 + dj] = sp;
		handList.get(getSize() - 1).sprs_arr[1][1] += sp;
	}
	
	void setDisp(int[][] disp){
		handList.get(getSize() - 1).sprs_arr = disp;
	}
}

class Spread_hand{
	Point pos;//position [i,j]
	int spr;//spread
	int[][] sprs_arr;
	
	Spread_hand(int i, int j, int sp){
		pos = new Point(i, j);
		spr = sp;
		sprs_arr = new int[3][3];
	}
}
