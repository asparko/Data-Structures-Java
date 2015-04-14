package MinMaxHeap;

public class MinMaxHeap {
    private int currentSize;
    private int[] arr;
    
    /************************
     *    CONSTRUCTOR
     ************************/
    public MinMaxHeap(int capacity){
        arr = new int[capacity + 1];
        currentSize = 0;
    }
    
    /************************
     *    PUBLIC METHODS
     ************************/
    public boolean isFull(){
    	return currentSize == arr.length - 1;
    }
    
    public boolean isEmpty(){
    	return currentSize == 0;
    }
    
    public void insert(int x){ //Needs to be O(logN)	
    	currentSize++;
    	int holeIdx = currentSize;

    	//Put x in 0th slot in array so everyone has a parent
    	arr[0]=x;
    	
    	//Get depth of hole
    	int depth = (int) (Math.log(holeIdx)/Math.log(2));
    	boolean minLevel = depth%2==0; //true if hole is @ a min level, false if a max level
    	
    	int holeParent = arr[holeIdx/2];
    	
    	if (minLevel) { //i.e. if parent is in a max level
    		if (x > holeParent) {
    			arr[holeIdx] = holeParent; //put parent in hole
    			percolateUpMax(x,holeIdx/2);
    		}
    		else
    			percolateUpMin(x,holeIdx); //*** this line will be called if inserting into the root
    	}
    	else { //if parent is a min level
    		if (x < holeParent){
    			arr[holeIdx] = holeParent; //put parent in hole
    			percolateUpMin(x, holeIdx/2);
    		}
    		else
    			percolateUpMax(x,holeIdx);
    	}
    }
    
   	
    public int min(){ //Needs to be O(1) 	
    	return arr[1];
    }
    
    public int max(){ //Needs to be O(1)
    	return Math.max(arr[2], arr[3]);
    }
    
    public int deleteMin(){ //Needs to be O(logN)
    	int min=min(); //Store min value to return at end of method
    	
    	//put last element into root and percolate it down to the right spot
    	arr[1]=arr[currentSize]; 
    	currentSize--;
    	
    	percolateDownMin(1);
    	return min;
    }
    
    public int deleteMax(){ //Needs to be O(logN)
    	int max=max(); //Store max value to return at end of method
    	int maxIdx;
    	
    	//put last element into max slot and percolate it down to the right spot
    	if (arr[2]>arr[3])
    		maxIdx = 2;
    	else 
    		maxIdx = 3;
    	
    	arr[maxIdx]=arr[currentSize]; 
    	currentSize--;
    	
    	percolateDownMax(maxIdx);

    	return max;
    }

    /************************
     *    PRIVATE METHODS
     ************************/
    private void percolateDownMin(int movingIdx){
    	int movingElem = arr[movingIdx];
    	
    	if (2*movingIdx <= currentSize){ //if there are children of movingElem
    		int m = getSmallestOffspring(movingIdx);
    		int smallestOffspring = arr[m];
    		
    		if (m >= 4*movingIdx) { //if gchild is smallest
    			if (movingElem > arr[m]){
    				arr[movingIdx]=smallestOffspring;
    				arr[m]=movingElem;
    				movingIdx = m;
    				if (movingElem > arr[m/2]){
    						arr[m] = arr[m/2];
    						arr[m/2]=movingElem;
    						movingIdx = m/2;
    				}
    				percolateDownMin(movingIdx);
    			}
    		} else { //if child is smallest
    			if (movingElem > smallestOffspring){
    				arr[movingIdx] = smallestOffspring;
    				arr[m] = movingElem;				
    			}
    		}
    	}   	
    }
    
    	
    private void percolateDownMax(int movingIdx){
    	int movingElem = arr[movingIdx];
    	
    	if (2*movingIdx <= currentSize){ //if there are children of movingElem
    		int m = getLargestOffspring(movingIdx);
    		int largestOffspring = arr[m];
    		
    		if (m >= 4*movingIdx) { //if gchild is largest
    			if (movingElem < arr[m]){ //if gchild is larger than moving element, swap them
    				arr[movingIdx]=largestOffspring;
    				arr[m]=movingElem;
    				movingIdx = m;
    				if (movingElem < arr[m/2]){
    						arr[m] = arr[m/2];
    						arr[m/2]=movingElem;
    						movingIdx = m/2;
    				}
    				percolateDownMax(movingIdx);
    			}
    		} else { //if child is smallest
    			if (movingElem < largestOffspring){
    				arr[movingIdx] = largestOffspring;
    				arr[m] = movingElem;				
    			}
    		}
    	}   	
    }
    	
    private int getSmallestOffspring(int index){
    	//Returns index of smallest of the children and grandchildren of a given index
    	int minIdx = 2*index;

    	if (2*index+1 <= currentSize && arr[minIdx]>arr[2*index+1]){
    		minIdx = 2*index+1;
    	}
    	if (4*index <= currentSize && arr[minIdx]>arr[4*index]){
    		minIdx = 4*index;
    	}
    	if (4*index+1 <= currentSize && arr[minIdx]>arr[4*index+1]){
    		minIdx = 4*index+1;
    	}
    	if (4*index+2 <= currentSize && arr[minIdx]>arr[4*index+2]){
    		minIdx = 4*index+2;
    	}
    	if (4*index+3 <= currentSize && arr[minIdx]>arr[4*index+3]){
    		minIdx = 4*index+3;
    	}
    	return minIdx;
    }
    
    private int getLargestOffspring(int index){
    	//Returns index of largest of the children and grandchildren of a given index
    	int maxIdx = 2*index;

    	if (2*index+1 <= currentSize && arr[maxIdx]<arr[2*index+1]){
    		maxIdx = 2*index+1;
    	}
    	if (4*index <= currentSize && arr[maxIdx]<arr[4*index]){
    		maxIdx = 4*index;
    	}
    	if (4*index+1 <= currentSize && arr[maxIdx]<arr[4*index+1]){
    		maxIdx = 4*index+1;
    	}
    	if (4*index+2 <= currentSize && arr[maxIdx]<arr[4*index+2]){
    		maxIdx = 4*index+2;
    	}
    	if (4*index+3 <= currentSize && arr[maxIdx]<arr[4*index+3]){
    		maxIdx = 4*index+3;
    	}
    	return maxIdx;
    }
    
    private void percolateUpMax(int x, int holeIdx){
    	/*Percolating up the max levels*/
    	
    	if (holeIdx > 3){ //if hole has a grandparent
    		int holeGrandParent = arr[holeIdx/4];

    		if (x > holeGrandParent) {
    			arr[holeIdx] = holeGrandParent; //put grandparent in hole
    			percolateUpMax(x, holeIdx/4);
    		} else  //put x in hole
    			arr[holeIdx] = x;
    	} else { //if hole is in second level
    		arr[holeIdx]=x;
    	}  	
    }
    
    private void percolateUpMin(int x, int holeIdx){
    	/*Percolating up the min levels*/
    	
    	if (holeIdx > 3){ //if hole has a grandparent (not in first 2 levels)
    		int holeGrandParent = arr[holeIdx/4];
    		if (x < holeGrandParent) {
    			arr[holeIdx] = holeGrandParent; //put grandparent in hole
    			percolateUpMin(x, holeIdx/4);
    		} else  //put x in hole
    			arr[holeIdx] = x;
    	} else { //if hole is in first level
    		arr[holeIdx] = x;
    	}
    }
}
