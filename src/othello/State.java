package othello;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class State implements Runnable, MouseListener {
	
	Main main;
	Player[] player;
	Data[] data;
	Spread sprs_order, sprs_amount;
	Thread thread = null;
	
	final int N = 8;
	final int STONE_NONE = 0, STONE_BLACK = 1, STONE_WHITE = 2;
	int turn;
	int[][] board_state;
	int this_i, this_j;
	int mouse_x, mouse_y;
	int[] point;
	
	boolean hittable_flag = true , lock = false;
	
	int stone_player = STONE_BLACK, stone_computer = STONE_WHITE;
	int win_player = 0, win_computer = 0;
	int point_player, point_computer;
	String name_player = "YOU", name_computer = "COM";
	
	int[][] usable = {{1,1,1,1,1,1,1,1},
					  {1,1,1,1,1,1,1,1},
					  {1,1,1,1,1,1,1,1},
					  {1,1,1,1,1,1,1,1},
					  {1,1,1,1,1,1,1,1},
					  {1,1,1,1,1,1,1,1},
					  {1,1,1,1,1,1,1,1},
					  {1,1,1,1,1,1,1,1}};
	
	State(Main ma){
		main = ma;
		player = new Player[2];
		player[0] = new You();
		player[1] = new Com();
		data = new Data[2];
		data[0] = new Data("YOU", STONE_BLACK);
		data[1] = new Data("COM", STONE_WHITE);
		sprs_order = new Spread();
		sprs_amount = new Spread();
	}
	
	boolean continue_select(){
		String message = result() + "\nもう一回やりますか？";
		int answer = JOptionPane.showConfirmDialog(null, message, "Othello", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return (answer == JOptionPane.YES_OPTION) ? true : false;
	}
	
	void init_game(){
		turn = STONE_BLACK;
		
		board_state = new int[N][N];
		board_state[3][4] = STONE_BLACK;
		board_state[4][3] = STONE_BLACK;
		board_state[3][3] = STONE_WHITE;
		board_state[4][4] = STONE_WHITE;
		
		point = new int[3];
		
		updatePoints(STONE_BLACK, 2);
		updatePoints(STONE_WHITE, 2);
	}
	
	void start(){
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	void stop(){
		thread = null;
		lock = false;
	}
	
	boolean hit(int[][] boast){
		return false;
	}
	
	void hit(int i, int j){
		sprs_order.reset();
		sprs_order.add(i, j, 1);
		for(int di = -1; di <= 1; di++){
			for(int dj = -1; dj <= 1; dj++){
				if(di == 0 && dj == 0) continue;
				if(hittable_count(i, j, di, dj) > 0){
					hit_spread(i, j, di, dj);
				}
			}
		}
	}
	
	void hit_spread(int i, int j, int di, int dj){
		int count = 1, state;
		while(true){
			i += di;
			j += dj;
			count++;
			state = board_state[i][j];
			if(state == 3 - turn){
				sprs_order.add(i, j, count);
			}else{
				break;
			}
		}
	}
	
	boolean hittable(){
		sprs_amount.reset();
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(board_state[i][j] != STONE_NONE) continue;
				hittable_around(i, j, true);
			}
		}
		hittable_flag = (sprs_amount.getSize() != 0) ? true : false;
		return hittable_flag;
	}
	
	boolean hittable(int i, int j){
		return (board_state[i][j] == STONE_NONE && hittable_around(i, j, false) > 0) ? true : false;
	}
	
	int hittable_around(int i, int j, boolean register){
		int spr = 0;
		for(int di = -1; di <= 1; di++){
			for(int dj = -1; dj <= 1; dj++){
				if(di == 0 && dj == 0) continue;
				spr += hittable_count(i, j, di, dj);
			}
		}
		if(spr != 0 && register) sprs_amount.add(i, j, spr);
		return spr;
	}
	
	int hittable_count(int i, int j, int di, int dj){
		int count = 0, state;
		while(true){
			i += di;
			j += dj;
			
			if(!onBoard(i, j)) return 0;
			
			state = board_state[i][j];
			
			if(state == 3 - turn){
				count++;
				continue;
			}else if(state == STONE_NONE){
				return 0;
			}
			break;
		}
		return count;
	}
	
	boolean onBoard(int i, int j){
		return (0 <= i && i < N && 0 <= j && j < N);
	}
	
	void updatePoints(int t, int p){
		point[t] += p;
		point[0] += p;
	}
	
	void update(int sp){
		int i,j;
		for(int k = 0; k < sprs_order.getSize(); k++){
			if(sprs_order.getSp(k) == sp){
				i = sprs_order.getI(k);
				j = sprs_order.getJ(k);
				updatePoints(turn, 1);
				if(board_state[i][j] != STONE_NONE){
					updatePoints(3 - turn, -1);
				}
				board_state[i][j] = turn;
			}
		}
		main.repaint();
	}
	
	boolean change_turn(){
		turn = 3 - turn;
		System.out.println("Black : " + point[1] + ", White : " + point[2] + ", Total : " + point[0]);
		boolean lasting = !game_ended();
		if(lasting) hittable();
		return lasting;
	}
	
	void change_players(){
		player_color = 3 - player_color;
	}
	
	void computer(){
		for(int i = 0; i < sprs_amount.getSize(); i++){
			if(sprs_amount.getSp(i) == sprs_amount.getMS()){
				hit(sprs_amount.getI(i), sprs_amount.getJ(i));
			}
		}
	}
	
	boolean game_ended(){
		return !(point[0] < N * N);
	}
	
	String result(){
		String result = "";
		if(point[STONE_BLACK] > point[STONE_WHITE]){
			result = "Winner is Player";
		}else if(point[STONE_BLACK] < point[STONE_WHITE]){
			result = "Winner is Computer";
		}else{
			result = "draw";
		}
		return result;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(thread == null && !lock){
			lock = true;
			mouse_x = e.getX();
			mouse_y = e.getY();
			this_i = ( mouse_x - main.MARGIN_LeftRight ) / main.SQUARE_SIZE;
			this_j = ( mouse_y - main.MARGIN_TopBottom ) / main.SQUARE_SIZE;
			if(hittable(this_i, this_j)){
				hit(this_i, this_j);
				start();
			}else lock = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		
		while(true){
			if(turn == STONE_WHITE){
				computer();
			}
			for(int m = 1; m <= sprs_order.getMS(); m++){
				update(m);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					continue;
				}
			}
			if(!change_turn()) {
				if(continue_select()) init_game();
				else break;
			}else if(!hittable_flag) change_turn();
			if(turn == STONE_BLACK) break;
		}
		
		stop();
	}
}

class Data{
	String name;
	int stone;
	int win, point;
	
	Data(String n, int sc){
		name = n;
		stone = sc;
		win = 0;
		point = 0;
	}
	
	void set_stone(int s){
		stone = s;
	}
	
	void win(){
		win++;
	}
	
	void update_point(int p){
		point += p;
	}
}