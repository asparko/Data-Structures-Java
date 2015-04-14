public class Stack<K,V> {
	private int size;
	private LLNode<K,V> top;
	
	public Stack() {
		this.size=0;
		this.top = null;
	}

	public boolean isEmpty() {
		if (size==0) {
			return true;
		} return false;
	}

	public V peek() {
		if (this.isEmpty()){
			return null;
		} else {
			return top.value;
		}
	}

	public V pop() {
		if (this.isEmpty()){
			return null;
		} else {
			LLNode<K,V> temp = top;
			top = temp.next;
			this.size--;
			return temp.value;
		}
	}

	public void push(V element) {
		LLNode<K,V> temp = new LLNode<K,V>(null, element, this.top);
		top = temp;
		size++;
	}

	

}
