package vipqueue;

public class a1Tester {

	public static void main(String[] args) {
		/*Stack<Integer> st = new Stack<>();
		Queue<Integer> qu = new Queue<>();
	     for (int i=0; i<5; i++){
	        st.push((Integer)i*i);  
	        qu.enqueue((Integer)(i+7));
	     }
	     while (!st.isEmpty())  
	    	 System.out.printf("->ST %d", st.pop());
	     //System.out.printf("Peek->%d ", qu.peek());
	     while (!qu.isEmpty())  
	    	 System.out.printf("->QU %d", qu.dequeue());
	     //System.out.printf("Peek->%d ", st.peek());
	     //System.out.printf("->%d", st.pop());
	     //System.out.printf("Peek->%d ", st.peek());
	     */
	     VipQueue<Integer> vq = new VipQueue<Integer>();
	     for (int i=0; i<5; i++){
	        vq.enqueue((Integer)i);             // a "regular" enqueue
	        vq.vipEnqueue((Integer)(i*i));      // a vip enqueue
	     }
	     //System.out.printf("->%d", vq.size);
	     while (!(vq.isEmpty())) 
	     	System.out.printf("->%d", vq.dequeue());
	   }
		
}


