package summer2015;

import java.awt.*;

import javax.swing.*;

public class ST11Tab01 {
	
	int width,height;
	int lwidth,lheight;
	int rwidth,rheight;
	int bwidth,bheight;
	int pwidth,pheight;
	int proNum=10;
	
	JPanel mainPanel;
	JPanel listPanel,resultPanel,bottomPanel;
	JScrollPane listSPane;
	JButton[] buttons;
	
	ST11Tab01(int width,int height){
		this.width=width;
		this.height=height;
		setSize(width,height);
	}
	
	void setSize(int w,int h){
		lwidth=(w*3/5);
		lheight=h*2/3;
		rwidth=(w*2/5)-55;
		rheight=h*2/3;
		bwidth=w-50;
		bheight=h/3-100;
		pwidth=(w*3/5)-30;
		pheight=100;
	}
	
	//JPanel 
	
	void make(){
		initTab();
		makeListPanel();
		makeResultPanel();
		makeButtomPanel();
	}
	
	void initTab(){
		mainPanel=new JPanel();
		mainPanel.setBackground(Color.black);
		listPanel=new JPanel();
		listPanel.setBackground(Color.white);
		resultPanel=new JPanel();
		listPanel.setBackground(Color.white);
		bottomPanel=new JPanel();
		bottomPanel.setBackground(Color.white);
	}
	
	void makeListPanel(){
		listPanel.setBackground(Color.white);
		listPanel.setPreferredSize(new Dimension(lwidth-20,pheight*(proNum+1)));
		
		listSPane=new JScrollPane(listPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listSPane.setPreferredSize(new Dimension(lwidth,lheight));
		
		buttons=new JButton[proNum];
		for(int i=0;i<proNum;i++){
			buttons[i]=new JButton("Pro - "+(i+1));
			buttons[i].setPreferredSize(new Dimension(pwidth,pheight));
			listPanel.add(buttons[i]);
		}
		mainPanel.add(listSPane);
	}
	
	void makeResultPanel(){
		resultPanel.setPreferredSize(new Dimension(rwidth,rheight));
		resultPanel.setBackground(Color.white);
		mainPanel.add(resultPanel);
	}
	
	void makeButtomPanel(){
		bottomPanel.setPreferredSize(new Dimension(bwidth,bheight));
		bottomPanel.setBackground(Color.white);
		mainPanel.add(bottomPanel);
	}
}
