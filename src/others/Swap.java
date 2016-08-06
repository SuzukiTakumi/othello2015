package others;

public class Swap {
	
	Integer a = 0, b =10;
	
	public static void main(String[] args){
		new Swap().start();
	}
	
	void start(){
		
		p("before Swaping");
		p("a = " + a);
		p("b = " + b);
		
		swap(a, b);
		
		p("before Swaping");
		p("a = " + a);
		p("b = " + b);
	}
	
	void swap(Integer ar, Integer br){
		int c = ar;
		ar = br;
		br = c;
	}
	
	void p(String cont){
		System.out.println(cont);
	}
}
