package externalSort;

/***********************************************************************
 * Represents a machine with limited memory that can sort tape drives.
 ***********************************************************************/

public class TapeSorter {

    private int memorySize;
    private int tapeSize;
    private static int THRESHOLD=10; //sorting this many elements uses insertion sort 
    public int[] memory;

    public TapeSorter(int memorySize, int tapeSize) {
        this.memorySize = memorySize;
        this.tapeSize = tapeSize;
        this.memory = new int[memorySize];
    }

    /********************************************************
     * Sorts the first `size` items in memory via quicksort
     ********************************************************/
    public void quicksort(int size) {
    	int right = size-1;
    	quicksort(memory,0,right);	
    }
    
    private void quicksort(int[] a, int left, int right){
    	//find median of first, middle, last elements, and store median as pivot
    	int pivot = findPivot(a, left, right);	
    	
    	//recursively call quicksort until sub-arrays are below a threshold size	
    	if(left+THRESHOLD <= right) { //if array is larger than threshold
    		int i = left;
    		int j = right-1;
    		for( ; ; ){
    			while(a[++i]<pivot){}
    			while(a[--j]>pivot){}
    			if (i < j){
    				swapElements(a,i,j);
    			}
    			else 
    				break;
    		}
    		swapElements(a,i,right-1);
    		quicksort(a,left,i-1);
    		quicksort(a, i+1,right);
    	
    	} else { //use insertion sort on subarray once it's small enough
    		a=insertionSort(a,left,right);
    	}
    }


    
    /**
     * Reads in numbers from drive `in` into memory (a chunk), sorts it, then writes it out to a different drive.
     * It writes chunks alternatively to drives `out1` and `out2`.
     *
     * If there are not enough numbers left on drive `in` to fill memory, then it should read numbers until the end of
     * the drive is reached.
     *
     * Example 1: Tape size = 8, memory size = 2
     * ------------------------------------------
     *   BEFORE:
     * in: 4 7 8 6 1 3 5 7
     *
     *   AFTER:
     * out1: 4 7 1 3 _ _ _ _
     * out2: 6 8 5 7 _ _ _ _
     *
     *
     * Example 2: Tape size = 10, memory size = 3
     * ------------------------------------------
     *   BEFORE:
     * in: 6 3 8 9 3 1 0 7 3 5
     *
     *   AFTER:
     * out1: 3 6 8 0 3 7 _ _ _ _
     * out2: 1 3 9 5 _ _ _ _ _ _
     *
     *
     * Example 3: Tape size = 13, memory size = 4
     * ------------------------------------------
     *   BEFORE:
     * in: 6 3 8 9 3 1 0 7 3 5 9 2 4
     *
     *   AFTER:
     * out1: 3 6 8 9 2 3 5 9 _ _ _ _ _
     * out2: 0 1 3 7 4 _ _ _ _ _ _ _ _
     */
    public void initialPass(TapeDrive in, TapeDrive out1, TapeDrive out2) {
    	//Reads in a chunk, sorts, writes to alternate drives
		//Execute full passes, then execute final "if-statement" only if partial pass needed
    	
    	int fullPasses = tapeSize/memorySize;			//number of full-sized chunks to sort
    	boolean partPass = tapeSize%memorySize != 0;	//true if partPass needed (leftovers)
    	
    	TapeDrive out;									//where to write sorted chunks						
    	
    	for (int i=0; i<fullPasses; i++){
    		for (int j=0; j<memorySize; j++){
    			memory[j] = in.read();
    		}
    		quicksort(memorySize);	
    		
    		//set out drive
    		if (i%2==0)
    			out = out1;
    		else 
    			out = out2;
    		
    		//write to out drive
    		for (int j=0; j<memorySize; j++){
    			 out.write(memory[j]);
    		}
    	}	
    	//Do a partial pass if needed
    	if (partPass) {
    		int remain = tapeSize%memorySize;
    		for (int j=0; j<remain; j++){
        		memory[j] = in.read();
        	}
        	quicksort(remain);
    		
        	//set out drive
    		if (fullPasses%2==0)
    			out = out1;
    		else 
    			out = out2;
    		
    		//write to out drive
    		for (int j=0; j<remain; j++){
    			 out.write(memory[j]);
    		}			
    	}
    	//reset tapeDrive counters
    	in.reset();
    	out1.reset();
    	out2.reset();
    }

    /**
     * Merges the first chunk on drives `in1` and `in2` and writes the sorted, merged data to drive `out`.
     * The size of the chunk on drive `in1` is `size1`.
     * The size of the chunk on drive `in2` is `size2`.
     *
     *          Example
     *       =============
     *
     *  (BEFORE)
     * in1:  [ ... 1 3 6 8 9 ... ]
     *             ^
     * in2:  [ ... 2 4 5 7 8 ... ]
     *             ^
     * out:  [ ... _ _ _ _ _ ... ]
     *             ^
     * size1: 4, size2: 4
     *
     *   (AFTER)
     * in1:  [ ... 1 3 6 8 9 ... ]
     *                     ^
     * in2:  [ ... 2 4 5 7 8 ... ]
     *                     ^
     * out:  [ ... 1 2 3 4 5 6 7 8 _ _ _ ... ]
     *                             ^
     */
    public void mergeChunks(TapeDrive in1, TapeDrive in2, TapeDrive out, int size1, int size2) {
    	int index1 = 0;								//to keep track of location in chunk
    	int index2 = 0;
    	
    	int current1 = in1.read();					//first element of in1
    	int current2 = in2.read();					//first element of in2
    	
    	while (index1 < size1 && index2 < size2) {	//if still inside both chunks, compare
    		if (current1 < current2){				//if first chunk has smaller item				
    			out.write(current1);
    			index1++;							//keep track of location in in1 chunk
    			if (index1 != size1)
    					current1 = in1.read();		//move on to next element in in1 if not at end
    										
    		} else {
    			out.write(current2);
    			index2++;
    			if (index2 != size2)
					current2 = in2.read();			//move on to next element in in1 if not at end
    			
    		}
    	}		
    	while (index1 < size1) {					//if there are still elems in chunk1 after chunk2 is done
    		out.write(current1);					//write rest of chunk
    		index1++;
    		if (index1 != size1)
				current1 = in1.read();				//move on to next element in in1 if not at end				
											
    	} 
    	while (index2 < size2) {					//if there are still elems in chunk2 after chunk1 is done
    		out.write(current2);					//write rest of chunk2				
			index2++;	
			if (index2 != size2)
				current2 = in2.read();				//move on to next element in in1 if not at end
    	} 
    }

    /**
     * Merges chunks from drives `in1` and `in2` and writes the resulting merged chunks alternatively to drives `out1`
     * and `out2`.
     *
     * The `runNumber` argument denotes which run this is, where 0 is the first run.
     *
     * -- Math Help --
     * The chunk size on each drive prior to merging will be: memorySize * (2 ^ runNumber)
     * The number of full chunks on each drive is: floor(tapeSize / (chunk size * 2))
     *   Note: If the number of full chunks is 0, that means that there is a full chunk on drive `in1` and a partial
     *   chunk on drive `in2`.
     * The number of leftovers is: tapeSize - 2 * chunk size * number of full chunks
     *
     * To help you better understand what should be happening, here are some examples of corner cases (chunks are
     * denoted within curly braces {}):
     *
     * -- Even number of chunks --
     * in1 ->   { 1 3 5 6 } { 5 7 8 9 }
     * in2 ->   { 2 3 4 7 } { 3 5 6 9 }
     * out1 ->  { 1 2 3 3 4 5 6 7 }
     * out2 ->  { 3 5 5 6 7 8 9 9 }
     *
     * -- Odd number of chunks --
     * in1 ->   { 1 3 5 } { 6 7 9 } { 3 4 8 }
     * in2 ->   { 2 4 6 } { 2 7 8 } { 0 3 9 }
     * out1 ->  { 1 2 3 4 5 6 } { 0 3 3 4 8 9 }
     * out2 ->  { 2 6 7 7 8 9 }
     *
     * -- Number of leftovers <= the chunk size --
     * in1 ->   { 1 3 5 6 } { 5 7 8 9 }
     * in2 ->   { 2 3 4 7 }
     * out1 ->  { 1 2 3 3 4 5 6 7 }
     * out2 ->  { 5 7 8 9 }
     *
     * -- Number of leftovers > the chunk size --
     * in1 ->   { 1 3 5 6 } { 5 7 8 9 }
     * in2 ->   { 2 3 4 7 } { 3 5 }
     * out1 ->  { 1 2 3 3 4 5 6 7 }
     * out2 ->  { 3 5 5 7 8 9 }
     *
     * -- Number of chunks is 0 --
     * in1 ->   { 2 4 5 8 9 }
     * in2 ->   { 1 5 7 }
     * out1 ->  { 1 2 4 5 5 7 8 9 }
     * out2 ->
     */
    public void doRun(TapeDrive in1, TapeDrive in2, TapeDrive out1, TapeDrive out2, int runNumber) {
    	//reset all drives being used
    	in1.reset();
    	in2.reset();
    	out1.reset();
    	out2.reset();
    	
    	int chunkSize = (int) (memorySize*Math.pow(2,runNumber));
    	int fullChunks = tapeSize / (chunkSize*2);		//full chunks per drive	
    	//Note: If fullChunks=0, full chunk on drive `in1` and a partial chunk on drive `in2`.
    	
    	int leftovers = tapeSize - 2*chunkSize*fullChunks;   	
    	//Note: if leftovers==0, only full chunks on each drive.

    	TapeDrive out;
    
    	//for full chunks:
    	for (int i=0; i<fullChunks; i++) { 			
    		if (i%2==0)
    			out = out1;
    		else
    			out = out2;
        	mergeChunks(in1, in2, out, chunkSize, chunkSize); 
        	
    	}
    	
    	//Now, deal with any leftovers
    	if (fullChunks%2==0) 			//if fullChunks even, write leftovers to out1
    		out = out1;
    	else							//if fullChunks odd, write leftovers to out2
    		out = out2;
    	
    	if (leftovers <= chunkSize)
    		mergeChunks(in1, in2, out, chunkSize, 0);
    	else 
    		mergeChunks(in1, in2, out, chunkSize, leftovers-chunkSize);	
    }

    /**
     * Sorts the data on drive `t1` using the external sort algorithm. The sorted data should end up on drive `t1`.
     *
     * Initially, drive `t1` is filled to capacity with unsorted numbers.
     * Drives `t2`, `t3`, and `t4` are empty and are to be used in the sorting process.
     */
    public void sort(TapeDrive t1, TapeDrive t2, TapeDrive t3, TapeDrive t4) {
    	initialPass(t1, t3, t4);
    	int i;
    	for (i=0; memorySize*Math.pow(2, i) < tapeSize; i++){
    		if (i%2==0)
    			doRun(t3, t4, t1, t2, i);
    		else
    			doRun(t1, t2, t3, t4, i);
    	}
    	//if i is such that sorted data doesn't end up on t1, put it there!
    	
        if (i%2==0){			//here, sorted data ends up on t3
    	   t1.reset();
    	   t3.reset();
    	   for (int k=0; k<tapeSize; k++)
    		   t1.write(t3.read());
       }
    }

    /**    
     * INSERTION SORT HELPER FCN (USED BY QUICKSORT)
     */    
	private int[] insertionSort(int[] a, int left, int right){
		int j;
		for (int p=left+1; p<=right; p++){ 			//cycle through elements (except 1st elem)
			int temp = a[p]; 						//put p'th elem in a temporary place
			for(j=p; j>left && temp<a[j-1]; j--){ 	//cycle down from p to beginning of list
				a[j]=a[j-1];
			}
			a[j]=temp;
		}
		return a;
	}
	
	/** Finds median of 3 elements for quicksort */
    private int findPivot(int[] a, int left, int right){
    	//only find pivot if subarray is larger than 1 element
    	if (left!=right){
			
    		//find median of first, middle, last elements:
			int mid = (right+left)/2;
	    	
	    	if(a[right] < a[mid])
	    		swapElements(a, mid, right);
	    	if(a[right] < a[left])
	    		swapElements(a, left, right);
	    	if(a[mid] < a[left])
	    		swapElements(a, mid, left);
	    	
	    	//move pivot just right of the end of the array (since right-most element is larger than pivot already):
	    	swapElements(a, mid,right-1);
	    	return a[right-1]; //return pivot value
	    }
	    else return a[right];
    }
    
    /** Swaps two elements in an array */
    private final void swapElements(int[] arr, int idx1, int idx2){
    	int temp = arr[idx1];
    	arr[idx1] = arr[idx2];
    	arr[idx2] = temp;
    }
    
    public static void main(String[] args) {
        // Example of how to test
        TapeSorter tapeSorter = new TapeSorter(30, 187);
        TapeDrive t1 = TapeDrive.generateRandomTape(187);
        TapeDrive t2 = new TapeDrive(187);
        TapeDrive t3 = new TapeDrive(187);
        TapeDrive t4 = new TapeDrive(187);

        tapeSorter.sort(t1, t2, t3, t4);
        int last = Integer.MIN_VALUE;
        boolean sorted = true;
        for (int i = 0; i < 80; i++) {
            int val = t1.read();
            sorted &= last <= val;
            last = val;
        }
        if (sorted)
            System.out.println("Sorted!");
        else
            System.out.println("Not sorted!");
    }

}