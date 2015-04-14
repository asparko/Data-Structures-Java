package vipqueue;

public interface IStack<T> {
	public boolean isEmpty();
    public T peek();
    public T pop();
    public void push(T element);
}
