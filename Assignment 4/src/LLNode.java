public class LLNode<K,V> {
	K key;
	V value;
	LLNode<K,V> next;
		
	public LLNode(K key, V value, LLNode<K,V> n) {
		this.key = key;
		this.value = value;
		this.next = n;
	}
	
	public LLNode(K key, V value){
		this(key, value, null);
	}
	
	public LLNode(V value){
		this(null, value, null);
	}
}

