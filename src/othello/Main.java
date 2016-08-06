package othello;

import java.applet.Applet;
import java.awt.*;

@SuppressWarnings("serial")
public class Main extends Applet {
	
	State state;
	
	final int N = 8;
	final int SQUARE_SIZE = 55;
	final int MARGIN_TopBottom = 80, MARGIN_LeftRight = 20;
	final int SCREEN_WIDTH = SQUARE_SIZE * N + MARGIN_LeftRight * 2, SCREEN_HEIGHT = SQUARE_SIZE * N + MARGIN_TopBottom * 2;
	
	Color[] colors = {new Color(0, 200, 10), Color.BLACK, Color.WHITE};
	
	public void init(){
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		state = new State(this);
		addMouseListener(state);
		state.init_game();
	}
	
	public void paint(Graphics g){
		draw_background(g);
		draw_board(g);
		draw_stones(g);
		draw_status(g);
	}
	
	void draw_background(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	void draw_board(Graphics g){
		int sx, sy;
		for(int i = 0; i < N; i++){
			sx = MARGIN_LeftRight + SQUARE_SIZE * i;
			for(int j = 0; j < N; j++){
				sy = MARGIN_TopBottom + SQUARE_SIZE * j;
				
				g.setColor(colors[0]);
				if(state.usable[i][j] == 0) g.setColor(Color.LIGHT_GRAY);
				g.fillRect(sx, sy, SQUARE_SIZE, SQUARE_SIZE);
				
				g.setColor(Color.BLACK);
				g.drawRect(sx, sy, SQUARE_SIZE, SQUARE_SIZE);
				if( (i == 2 || i == 6) && (j == 2 || j == 6) ) g.fillOval(sx - 4, sy - 4, 8, 8);
			}
		}
	}
	
	void draw_stones(Graphics g){
		int ix,iy;
		for(int i = 0; i < N; i++){
			ix = MARGIN_LeftRight + SQUARE_SIZE * i + SQUARE_SIZE / 10;
			for(int j = 0; j < N; j++){
				iy = MARGIN_TopBottom + SQUARE_SIZE * j + SQUARE_SIZE / 10;
				if(state.board_state[i][j] != state.STONE_NONE){
					g.setColor(colors[state.board_state[i][j]]);
					g.fillOval(ix, iy, SQUARE_SIZE * 4 / 5, SQUARE_SIZE * 4 / 5);
					g.setColor(colors[3 - state.board_state[i][j]]);
					g.drawOval(ix, iy, SQUARE_SIZE * 4 / 5, SQUARE_SIZE * 4 / 5);
				}else{
					g.setColor(colors[0]);
					if(state.usable[i][j] == 0) g.setColor(Color.LIGHT_GRAY);
					g.fillOval(ix, iy, SQUARE_SIZE * 4 / 5, SQUARE_SIZE * 4 / 5);
				}
			}
		}
	}
	
	void draw_status(Graphics g){
		int ix, iy, h = MARGIN_TopBottom / 2 - 2;
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
		
		//Black
		if(state.data[0].stone == state.STONE_BLACK){
			iy = MARGIN_TopBottom * 5 / 4 + SQUARE_SIZE * N + 1;
		}else{
			iy = MARGIN_TopBottom / 4 + 1;
		}
		
		for(int i = 0; i < state.point[state.STONE_BLACK]; i++){//black
			ix = MARGIN_LeftRight + 5 * i;
			
			g.setColor(Color.WHITE);
			g.drawRoundRect(ix, iy, 5, h, 4, 4);
		}
		
		//White
		if(state.data[0].stone == state.STONE_WHITE){
			iy = MARGIN_TopBottom * 5 / 4 + SQUARE_SIZE * N + 1;
		}else{
			iy = MARGIN_TopBottom / 4 + 1;
		}
		
		for(int i = 0; i < state.point[state.STONE_WHITE]; i++){//white
			ix = MARGIN_LeftRight + 5 * i;
						
			g.setColor(Color.WHITE);
			g.fillRoundRect(ix, iy, 5, h, 4, 4);
			
			g.setColor(Color.BLACK);
			g.drawRoundRect(ix, iy, 5, h, 4, 4);
		}
		
		if(state.turn == state.STONE_BLACK) g.setColor(Color.RED);
		else g.setColor(Color.WHITE);
		
		
		g.setColor(Color.WHITE);
		g.drawString("COM : " + state.point[state.STONE_WHITE], MARGIN_LeftRight + 5 * ( N * N + 3), MARGIN_TopBottom * 5 / 8);
		g.setColor(Color.WHITE);
		g.drawString("YOU : " + state.point[state.STONE_BLACK], MARGIN_LeftRight + 5 * ( N * N + 3), MARGIN_TopBottom * 13 / 8 + SQUARE_SIZE * N);
	}
	
	void draw_status(Graphics g, int t){
		int x = MARGIN_LeftRight, y, ys[] = {MARGIN_TopBottom * 5 / 4 + SQUARE_SIZE * N + 1, MARGIN_TopBottom / 4 + 1};
		
		//Black
		
		
		//White
	}
}
