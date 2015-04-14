package vipqueue;

public interface IQueue<T> {
	public boolean isEmpty();
    public T peek();
    public T dequeue();
    public void enqueue(T element);
}
