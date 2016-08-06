package others;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RandomOutput {
	
	ArrayList<String> list;
	String endCmd = "/";
	
	void start(){
		String input;
		list = new ArrayList<>();
		while(true){
			input = JOptionPane.showInputDialog("追加する単語を入力("+ list.size() +")\n" + endCmd + "で終了");
			if(input.equals(endCmd)) break;
			if(input.length() == 0) continue;
			
			if(list.contains(input)) list.remove(input);
			else list.add(input);
		}
		
		int randomNum, count = 1;
		while(list.size() > 0){
			randomNum = (int) ( Math.random() * list.size());
//			System.out.println("size of list = " + list.size());
//			System.out.println("random number = " + randomNum);
			System.out.println(count + " : " +list.get(randomNum));
			list.remove(randomNum);
			count++;
		}
	}
	
	public static void main(String[] args){
		new RandomOutput().start();
	}
}
