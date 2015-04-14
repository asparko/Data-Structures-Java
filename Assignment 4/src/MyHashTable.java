public class MyHashTable<K,V> implements HashTable<K, V>{
	
	private int arrSize; 
	private LLNode[] arr;
	
	//CONSTRUCTOR
	public MyHashTable(){
		this.arrSize = 2003; 		//prime number size suitable for ~1000 entries
		this.arr = new LLNode[arrSize];
	}
	
	
	//PUBLIC METHODS
	@Override
	public void put(K key, V value) {
		int code = key.hashCode()%arrSize;
		//System.out.println(code);
		LLNode<K,V> newNode = new LLNode<K,V>(key, value);
		if (arr[code]!=null)					//if slot is occupied
			newNode.next = arr[code];			//put in beginning of linked list
		arr[code] = newNode;
	}

	@Override
	public V get(K key) {
		int code = key.hashCode()%arrSize;
		LLNode<K,V> temp = arr[code];
		while (temp != null) { 					//search through linked list
			if(temp.key.equals(key)) break; 	//if you find right key, break
			temp = temp.next; 					//else go to next node in linked list
		}
		if (temp==null){
			return null;
		}
		else return (V) temp.value;
	}

	@Override
	public V remove(K key) {
		int code = key.hashCode()%arrSize;
		LLNode<K,V> temp = arr[code];
		
		//is it the first element in the linked list?
		if (temp.key.equals(key)){
			arr[code]=temp.next; //move 2nd element to front of linked list
			return temp.value;
		}
		
		//is it elsewhere in the linked list?
		while (temp.next != null) { 				//search through linked list
			if(temp.next.key.equals(key)) break; 	//if you find right key, break
			temp = temp.next; 						//else go to next node in linked list
		}
		
		//if key led to empty location, or if it was never found in the linked list:
		if (temp==null) return null;
		
		//else it was found somewhere in the linked list:
		LLNode<K,V> found = temp.next;
		temp.next = found.next;
		return found.value;
	}
	
}
