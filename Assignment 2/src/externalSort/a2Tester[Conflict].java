package externalSort;
import java.util.Random;
public class a2Tester {

	
	public static void main(String[] args) {
		
		final int MEMSIZE = 3;
		final int TAPESIZE = 20;
		
		TapeSorter tapeSorter = new TapeSorter(MEMSIZE, TAPESIZE);
        
		TapeDrive t1 = TapeDrive.generateRandomTape(TAPESIZE);
        TapeDrive t2 = new TapeDrive(TAPESIZE);
        TapeDrive t3 = new TapeDrive(TAPESIZE);
        TapeDrive t4 = new TapeDrive(TAPESIZE);
        /*
        for (int i=-5; i<15; i++){
        	t1.write(2*i);
        	t2.write(i);       	
        }
 
        t1.reset();
    	t2.reset();
    	*/

     
        
/*        
//TO TEST MERGE CHUNKS       
        tapeSorter.mergeChunks(t1,t2,t4,7,10);
        
        i=0;
        System.out.println("\nDrive 4 after");
        t4.reset();
        while (i < TAPESIZE){
        	System.out.println(t4.read());
        	i++;
        }
*/
        
//TO TEST INITIAL PASS
        int i=0;
        System.out.println("Drive 1 before");
        while (i < TAPESIZE){
        	System.out.println(t1.read());
        	i++;
        }
        
        tapeSorter.initialPass(t1, t3, t4);
        i=0;
        
        System.out.println("\nDrive 3 after");
        while (i < TAPESIZE){
        	System.out.println(t3.read());
        	i++;
        }
        
        i=0;
        System.out.println("\nDrive 4 after");
        while (i < TAPESIZE){
        	System.out.println(t4.read());
        	i++;
        }

       
//TO TEST DO RUN      
       tapeSorter.doRun(t3,t4,t1,t2,0);       
       

 

       tapeSorter.doRun(t1,t2,t3,t4,1);       
       
       i=0;
       System.out.println("\nDrive 3 after doRun 0");
       t3.reset();
       while (i < TAPESIZE){
       	System.out.println(t3.read());
       	i++;
       }
       
       i=0;
       System.out.println("\nDrive 4 after doRun 0");
       t4.reset();
       while (i < TAPESIZE){
       	System.out.println(t4.read());
       	i++;
       }
        
		/*
		System.out.println(td.read());
		System.out.println(td.read());
		System.out.println(td.read());
		System.out.println(td.read());
		System.out.println(td.read());
		System.out.println(td.read());
		System.out.println(td.read());
		*/

		//Random genRandom = new Random();
		
		//System.out.println(8/9 + ((8%9==0) ? 0 : 1));
		
		//System.out.println(1%2 != 0);
		//int[] tester = new int[10];
		/*tester[0]=14;
		tester[1]=1;tester[2]=15;tester[3]=6;tester[4]=16;
		tester[5]=12;tester[6]=16;tester[7]=7;tester[8]=11;tester[9]=18;
		
		for (int i = 0; i<tester.length/2; i++){
    		tester[i]=genRandom.nextInt(20)*(-1)^(genRandom.nextInt(6));
    	}
		for (int i=tester.length/2;i<tester.length; i++){
    		tester[i]=genRandom.nextInt(20);
    	}
		for (int i=0; i<tester.length; i++)
			System.out.println(tester[i]);
		
		int[] sortedArr = quicksortTest(tester,100);
		//int[] sortedArr = insertionSort(tester,0,tester.length);
		
		for (int i=0; i<sortedArr.length; i++)
			System.out.println(sortedArr[i]);
			*/
	}
	
	public static int[] insertionSort(int[] a, int left, int right){
		int j;
		for (int p=left+1; p<=right; p++){ //cycle through elements (except 1st elem)
			int temp = a[p]; //put p'th elem in a temporary place
			for(j=p; j>left && temp<a[j-1]; j--){ //cycle down from p to beginning of list
				a[j]=a[j-1];
			}
			a[j]=temp;
		}
		return a;
	}

	public static int[] quicksortTest(int[] a, int size) {
	        // TODO: Implement me for 10 points
		 	//int toSort;
	    
	      	//if there are fewer than "size" elements in memory, just sort the amount in memory
	    	if (a.length < size) 
	    		size = a.length;
	    	
	    	int right = size-1;
	    	
	    	a = quicksort(a,0,right);
	    	return a;
	    }
	 
	private static int[] quicksort(int[] a, int left, int right){
		//find median of first, middle, last elements, and store median as pivot
    	int pivot = medianOf3(a, left, right);	
    	System.out.println("pivot= "+pivot);
    	
    	//recursively call quicksort until sub-arrays are below a threshold size
    	int threshold = 5;
    	if(left+threshold <= right) { //if array is larger than threshold
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
    		a=quicksort(a,left,i-1);
    		a=quicksort(a, i+1,right);
    	
    	} else { //use insertion sort on subarray once it's small enough
    		a=insertionSort(a,left,right);
    	}
    	return a;
	}
	
	private static int medianOf3(int[] a, int left, int right){
	    //don't do any swaps if left and right are equal!! will cause issues
		
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
	 
	 
	 private final static void swapElements(int[] arr, int idx1, int idx2){
	    	int temp = arr[idx1];
	    	arr[idx1] = arr[idx2];
	    	arr[idx2] = temp;
	    }
}
