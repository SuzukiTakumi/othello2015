package summer2015;

import javax.swing.JOptionPane;

public class TurtleExtend extends TurtleBase {
	
	StringLinkedList sll;
	
	public static void main(String[] args){
		new TurtleExtend().start();
	}
	
	void start(){
		super.start();
		commands();
	}
	
	void commands(){
		while(true){
			String cmd=inputCommand("命令>");
			if(!processCommand(cmd))break;
		}
	}
	
	String inputCommand(String text){
		return JOptionPane.showInputDialog(text);
	}
	
	boolean processCommand(String cmd){
		String[] words=cmd.split(" ");
		double parameter=0;
		
		if(words.length==1){
			if(words[0].equals("END")){
				return false;
			}else if(words[0].equals("PU")){
				super.penUpMode();
			}else if(words[0].equals("PD")){
				super.penDownMode();
			}else if(words[0].equals("CAPROC")){
				
			}else if(words[0].equals("SEPROC")){
				
			}else if(words[0].equals("CLPROC")){
				
			}
		}else if(words.length==2){
			parameter=decodeParameter(words[1]);
			if(parameter==0){
				return true;
			}
			if(words[0].equals("FWD")){
				super.forward(parameter);
			}else if(words[0].equals("RT")){
				super.rotate(parameter);
			}else if(words[0].equals("SLL")){
				super.slide(parameter, "l");
			}else if(words[0].equals("REPROC")){
				
			}
		}
		return true;
	}
	
	double decodeParameter(String word){
		double param=0;
		try{
			param=Double.parseDouble(word);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "範囲外のパラメータ指定です。 : "+param);
		}
		return param;
	}
}
