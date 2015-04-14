public class MyGraph extends Graph {

	private int capacity;								//vertex capacity
	private int currSize;								//current number of vertices
	private HashTable<String, Node> hashedNodes;		//hash table for lookup by name
	private LLNode[] adjList;							//adjacency list
	private int[] inDegrees;							//array of inDegrees
	private Stack<String, Node> zeros;					//stack of vertices with zero inDegree
	
	/* *CONSTRUCTOR* */
	public MyGraph(int n){
		this.capacity = n;
		hashedNodes = HashTableFactory.create();
		adjList = new LLNode[capacity];
		inDegrees = new int[capacity];
	}
	
	/* *METHODS* */
	@Override
	public void addNode(Node node) {
		hashedNodes.put(node.getName(), node);  		//put node in the hashTable
		LLNode newNode = new LLNode(node); 				//turn each vertex into a LinkedList
		adjList[currSize] = newNode;					//start a linkedList of adjacencies
		currSize++;
	}

	@Override
	public void addEdge(Node node1, Node node2) {
		int id1 = node1.getId();	
		LLNode newNode2 = new LLNode(node2);
		
		//Put node2 into adjacency list for node1:
		if (adjList[id1].next != null)
			newNode2.next = adjList[id1].next;
		adjList[id1].next = newNode2;
	}
	
	@Override
	public Node lookupNode(int id) {
		return (Node) adjList[id].value;
	}

	@Override
	public Node lookupNode(String name) {		
		return hashedNodes.get(name);
	}

	
	/* ****** Determine if a Graph is Acyclic ******** */
	@Override
	public boolean isAcyclic() {
		stackZeros();								//Creates array of vertex inDegrees and stacks those with 0 indegree
		int count = 0;								//Keep track of how many vertices are popped off stack
		while (!(zeros.isEmpty())){
			Node i = zeros.pop();
			count++;
			int id = i.getId();
			LLNode temp = adjList[id].next;
			while (temp != null){					//Go through adj list of each popped vertex and decrement indegree of its adjacent nodes
				int j = ((Node)temp.value).getId();
				inDegrees[j]--;
				if (inDegrees[j]==0)
					zeros.push(lookupNode(j));
				temp = temp.next;
			}
		}
		if (count==currSize) return true;			//If all nodes in graph have been popped off, then acyclic
		else return false;
	}


	/* ****** Topological Sort of Vertices ******** */
	@Override
	public int[] sort() {
		stackZeros();
		int[] sorted = new int[currSize];
		for (int k=0; k<currSize; k++){
			Node i = zeros.pop();
			int id = i.getId(); 
			sorted[k] = id;
			LLNode temp = adjList[id].next;
			while (temp != null){
				int j = ((Node)temp.value).getId();
				inDegrees[j]--;
				if (inDegrees[j]==0)
					zeros.push(lookupNode(j));
				temp = temp.next;
			}
		}	        
		return sorted;
	}
	
	/* ****** Create Stack of Vertex with zero indegree ******** */
	private void stackZeros(){  	
		inDegrees();
		zeros = new Stack<String, Node>();
		for (int i=0; i<currSize; i++){
			if (inDegrees[i]==0)
				zeros.push(lookupNode(i));
		}
	}
	
	/* ****** Create Array of Vertex InDegrees ******** */
	private void inDegrees(){		
		for (int v=0; v<currSize; v++){ 
			int id;
			LLNode temp = adjList[v].next;
			while (temp != null){
				id = ((Node)temp.value).getId();
				inDegrees[id]++;
				temp = temp.next;
			}
		}
	}

}
