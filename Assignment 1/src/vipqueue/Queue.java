package vipqueue;

public class Queue<T> implements IQueue<T> {

	private int size;
	private Node<T> back;
	private Node<T> front;
	
	public Queue() {
		this.size=0;
		this.back = null;
		this.front = null;
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
			return front.data;
		}
	}

	@Override
	public T dequeue() {
		if (this.isEmpty()){
			return null;
		} else {
			Node<T> temp = front;
			front = temp.prev;
			if (!(front ==null)){
				front.next = null;
			}
			this.size--;
			return temp.data;
		}
	}

	@Override
	public void enqueue(T element) {
		Node<T> temp = new Node<T>(element, this.back, null);
		if (!(size == 0)){ //if there were already nodes in queue:
			back.prev = temp; //make new node the back of the queue
			temp.next = back; //old back is now after the new node
		} else {
			this.back = temp; 
			this.front = temp;
		}
		back = temp;
		size++;
		
	}

}
