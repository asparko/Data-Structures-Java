package vipqueue;

public class VipQueue<T> implements IVipQueue<T> {

	protected Queue<T> vqueue;
	protected Stack<T> vstack;
	protected int size;
	
	public VipQueue() {
		vqueue = new Queue<>();
		vstack = new Stack<>();
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		if (size==0) {
			return true;
		} return false;
	}

	@Override
	public T peek() {
		T temp;
		if (!(vstack.isEmpty())){
			//VIPs in the stack come first
			temp = vstack.peek();
			return temp;
		} else if (!(vqueue.isEmpty())){
			//if no VIP's, normal queue-folk come next
			temp = vqueue.peek();
			return temp;
		} else { 
			return null;
		}
	}

	@Override
	public T dequeue() {
		T temp;
		if (!(vstack.isEmpty())){
			//first check if there are any VIP's in the stack
			this.size--;
			temp = vstack.pop();
			return temp;
		} else if (!(vqueue.isEmpty())){
			//if no VIP's, dequeue normal folks in the queue
			this.size--;
			temp = vqueue.dequeue();
			return temp;
		} else {
			return null;
		} 
	}

	@Override
	public void enqueue(T element) {
		this.size++;
		vqueue.enqueue(element);
	}

	@Override
	public void vipEnqueue(T element) {
		this.size++;
		vstack.push(element);
	}

}
