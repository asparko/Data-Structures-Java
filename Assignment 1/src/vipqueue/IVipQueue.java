package vipqueue;

public interface IVipQueue<T> extends IQueue<T> {
	public void vipEnqueue(T element);
}
