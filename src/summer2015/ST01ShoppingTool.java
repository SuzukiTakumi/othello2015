package summer2015;

import java.awt.*;
import javax.swing.*;

public class ST01ShoppingTool extends JFrame {
	
	static ST01ShoppingTool frame;
	int width=900,height=600;
	
	JPanel mainPanel;
	JPanel panel1,panel2,panel3,panel4;
	JTabbedPane tabs;
	
	public static void main(String[] args){
		frame=new ST01ShoppingTool();
		frame.setTitle("お買い物ツール");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public ST01ShoppingTool(){
		
		mainPanel=new JPanel();
		tabs=new JTabbedPane();
		
		initScreen();
		
		ST11Tab01 tab01=new ST11Tab01(width,height);
		panel1=new JPanel();
		panel1.setBackground(Color.black);
		makeTab1();
		
		panel2=new JPanel();
		panel2.setBackground(Color.black);
		makeTab2();
		
		panel3=new JPanel();
		panel3.setBackground(Color.black);
		makeTab3();
		
		panel4=new JPanel();
		panel4.setBackground(Color.black);
		makeTab4();
		
		tabs.add("step － １　：　商品を選択する",panel1);
		tabs.add("step － ２　：　配送先を選択する",panel2);
		tabs.add("step － ３　：　選択した内容を確認する",panel3);
		tabs.add("step － ４　：　購入完了",panel4);
		mainPanel.add(tabs);
		
		getContentPane().add(mainPanel);
	}
	
	void initScreen(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (screenSize.width)*7/10;
		height = (screenSize.height)*4/5;
		
		setSize(width, height);
		tabs.setPreferredSize(new Dimension(width-30,height-50));
	}
	
	void makeTab1(){
		int lwidth=(width*3/5),lheight=height*2/3;
		int rwidth=(width*2/5)-55,rheight=height*2/3;
		int bwidth=width-50,bheight=height/3-100;
		int pwidth=(width*3/5)-30,pheight=100;
		int proNum=10;
		
		JPanel listPanel=new JPanel();
		listPanel.setBackground(Color.white);
		listPanel.setPreferredSize(new Dimension(lwidth-20,pheight*(proNum+1)));
		
		JScrollPane listSPane=new JScrollPane(listPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listSPane.setPreferredSize(new Dimension(lwidth,lheight));
		
		JButton[] buttons=new JButton[proNum];
		for(int i=0;i<proNum;i++){
			buttons[i]=new JButton("Pro - "+(i+1));
			buttons[i].setPreferredSize(new Dimension(pwidth,pheight));
			listPanel.add(buttons[i]);
		}
		panel1.add(listSPane);
		
		JPanel resultPanel=new JPanel();
		resultPanel.setPreferredSize(new Dimension(rwidth,rheight));
		resultPanel.setBackground(Color.white);
		panel1.add(resultPanel);
		
		JPanel bottomPanel=new JPanel();
		bottomPanel.setPreferredSize(new Dimension(bwidth,bheight));
		bottomPanel.setBackground(Color.white);
		panel1.add(bottomPanel);
	}
	
	void makeTab2(){
		int lwidth=(width*3/5),lheight=height*2/3;
		int rwidth=(width*2/5)-55,rheight=height*2/3;
		int bwidth=width-50,bheight=height/3-100;
		int pwidth=(width*3/5)-30,pheight=50;
		
		JPanel listPanel=new JPanel();
		listPanel.setPreferredSize(new Dimension(lwidth,lheight));
		listPanel.setBackground(Color.white);
		
		//HERE
		JRadioButton rbuttonHERE=new JRadioButton("ここの研究室",true);
		rbuttonHERE.setPreferredSize(new Dimension(pwidth,lheight/4));
		
		ButtonGroup group=new ButtonGroup();
		group.add(rbuttonHERE);
		
		listPanel.add(rbuttonHERE);
		
		//DM
		int dmlabNum=10;
		String[] dmlab=new String[dmlabNum];
		JRadioButton[] rbuttondm=new JRadioButton[dmlabNum];
		
		JPanel listdm=new JPanel();
		listdm.setPreferredSize(new Dimension(pwidth,lheight/3));
		
		for(int i=0;i<dmlabNum;i++){
			dmlab[i]="DMteacher-"+(i+1);
			rbuttondm[i]=new JRadioButton(dmlab[i]);
			group.add(rbuttondm[i]);
			listdm.add(rbuttondm[i]);
		}
		listPanel.add(listdm);
		
		//CS
		int cslabNum=10;
		String[] cslab=new String[cslabNum];
		JRadioButton[] rbuttoncs=new JRadioButton[cslabNum];
		
		JPanel listcs=new JPanel();
		listcs.setPreferredSize(new Dimension(pwidth,lheight/3));
		
		for(int i=0;i<cslabNum;i++){
			cslab[i]="CSteacher-"+(i+1);
			rbuttoncs[i]=new JRadioButton(cslab[i]);
			group.add(rbuttoncs[i]);
			listcs.add(rbuttoncs[i]);
		}
		listPanel.add(listcs);
		
		panel2.add(listPanel);
		
		JPanel resultPanel=new JPanel();
		resultPanel.setPreferredSize(new Dimension(rwidth,rheight));
		resultPanel.setBackground(Color.white);
		panel2.add(resultPanel);
		
		JPanel bottomPanel=new JPanel();
		bottomPanel.setPreferredSize(new Dimension(bwidth,bheight));
		bottomPanel.setBackground(Color.white);
		panel2.add(bottomPanel);
	}
	
	void makeTab3(){
		int lwidth=(width*3/5),lheight=height*2/3;
		int rwidth=(width*2/5)-55,rheight=height*2/3;
		int bwidth=width-50,bheight=height/3-100;
		
		JPanel listPanel=new JPanel();
		listPanel.setPreferredSize(new Dimension(lwidth,lheight));
		listPanel.setBackground(Color.white);
		panel3.add(listPanel);
		
		JPanel resultPanel=new JPanel();
		resultPanel.setPreferredSize(new Dimension(rwidth,rheight));
		resultPanel.setBackground(Color.white);
		panel3.add(resultPanel);
		
		JPanel bottomPanel=new JPanel();
		bottomPanel.setPreferredSize(new Dimension(bwidth,bheight));
		bottomPanel.setBackground(Color.white);
		panel3.add(bottomPanel);
	}
	
	void makeTab4(){
		int lwidth=(width*3/5),lheight=height*2/3;
		int rwidth=(width*2/5)-55,rheight=height*2/3;
		int bwidth=width-50,bheight=height/3-100;
		JPanel listPanel=new JPanel();
		listPanel.setPreferredSize(new Dimension(lwidth,lheight));
		listPanel.setBackground(Color.white);
		panel4.add(listPanel);
		
		JPanel resultPanel=new JPanel();
		resultPanel.setPreferredSize(new Dimension(rwidth,rheight));
		resultPanel.setBackground(Color.white);
		panel4.add(resultPanel);
		
		JPanel bottomPanel=new JPanel();
		bottomPanel.setPreferredSize(new Dimension(bwidth,bheight));
		bottomPanel.setBackground(Color.white);
		panel4.add(bottomPanel);
	}
}
