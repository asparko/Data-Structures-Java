package vipqueue;

public class Node<T> {
	T data;
	Node<T> next;
	Node<T> prev;
	
	public Node(T data, Node<T> n, Node<T> p) {
		this.data = data;
		this.next = n;
		this.prev = p;
	}

}
