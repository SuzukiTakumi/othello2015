package summer2015;

public class StringLinkedList {
	
	StringNode head=null;
	StringNode tail=null;
	
	public static void main(String[] args){
		StringLinkedList strll=new StringLinkedList();
		String[] strings={"FWD 10","RT 90","PU","PD"};//commandForTest
		for(int i=0;i<strings.length;i++){
			System.out.print(strll.isEmpty()+" ");
			strll.add(strings[i]);
			System.out.println(strll.toString());
		}
		System.out.println(strll.position("PD"));
	}
	
	boolean isEmpty(){
		if(head==null){
			return true;
		}else{
			return false;
		}
	}
	
	void add(String elem){
		StringNode n=new StringNode(elem);
		if(isEmpty()){
			head=n;
			tail=n;
		}else{
			tail.next=n;
			tail=n;
			tail.next=null;
		}
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("[ ");
		
		if(!isEmpty()){
			StringNode in=head;
			while(in!=null){
				sb.append(in.contents+" ");
				in=in.next;
			}
		}
		
		sb.append("]");
		return sb.toString();
	}
	
	int position(String elem){
		if(!isEmpty()){
			StringNode in=head;
			int num=0;
			while(in!=null){
				if(in.contents==elem)return num;
				in=in.next;
				num++;
			}
		}
		return -1;
	}
}

class StringNode{
	String contents;//key
	StringNode next;//next
	
	StringNode(String contents){
		this.contents=contents;
		this.next=null;
	}
}
