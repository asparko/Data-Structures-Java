package vipqueue;

public class Stack<T> implements IStack<T> {
	private int size;
	private Node<T> top;
	
	public Stack() {
		this.size=0;
		this.top = null;
	}

	@Override
	public boolean isEmpty() {
		if (size==0) {
			return true;
		} return false;
	}

	@Override
	public T peek() {
		if (this.isEmpty()){
			return null;
		} else {
			return top.data;
		}
	}

	@Override
	public T pop() {
		if (this.isEmpty()){
			return null;
		} else {
			Node<T> temp = top;
			top = temp.next;
			if (!(top==null)){ //if there is something left after the pop
				top.prev = null;
			}
			this.size--;
			return temp.data;
		}
	}

	@Override
	public void push(T element) {
		Node<T> temp = new Node<T>(element, this.top, null);
		if (!(size == 0)){
			top.prev = temp;
		}
		top = temp;
		size++;
	}

	

}
