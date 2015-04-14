package MinMaxHeap;

public class A3Tester {

	public static void main(String[] args) {
		MinMaxHeap mm = new MinMaxHeap(35);
		//System.out.println("full"+mm.isFull());
		//for(int i=0; i<10; i++){
		//	mm.insert(i*2);
			//System.out.println("full"+mm.isFull());
		//}	
		mm.insert(28);
		mm.insert(57);
		mm.insert(30);
		mm.insert(36);
		mm.insert(34);
		mm.insert(27);
		mm.insert(25);
		mm.insert(5); mm.insert(65); mm.insert(37); mm.insert(45);
		mm.insert(59); 
		mm.insert(50); mm.insert(39);mm.insert(38);mm.insert(45);
		mm.insert(80); mm.insert(8); mm.insert(30); mm.insert(31);
		mm.insert(15);
		mm.insert(20); mm.insert(15);mm.insert(12);mm.insert(10);
		mm.insert(18); mm.insert(17);mm.insert(16);mm.insert(32);
		mm.insert(13);mm.insert(14);
		/*
		for(int j =0; j<6; j++){
			System.out.println();
			for(int i=1; i<35; i++){
				if(i>=Math.pow(2, j) && i<Math.pow(2, j+1))
					System.out.print("  "+mm.getElem(i));
			}
		}
		*/
		System.out.println(mm.getArray());
		
		mm.deleteMin();
		System.out.println(mm.getArray());
		
		mm.deleteMax();
		System.out.println(mm.getArray());
		/*
		for(int j =0; j<6; j++){
			System.out.println();
			for(int i=1; i<35; i++){
				if(i>=Math.pow(2, j) && i<Math.pow(2, j+1))
					System.out.print("  "+mm.getElem(i));
			}
		}*/
		/*
		mm.insert(-3);
		mm.insert(87);
		mm.insert(3);
		mm.insert(3);
		mm.insert(3);
		mm.insert(7);
		mm.insert(5);
		mm.insert(2);
		for(int i=0; i<24; i++){
			for(int j=(int)Math.pow(2, i); j<)
			System.out.println("elem " + i + "= "+mm.getElem(i));
		}*/
	}

}
