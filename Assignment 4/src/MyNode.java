public class MyNode implements Node{

		private String name;
		private int id;
		static int test=-1;				//incremented by NodeFactory each time a node is created
		
		public MyNode(String name) {
			this.name = name;
			this.id=test;				//incremented by NodeFactory, so first call will have id=0
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public int getId() {
			return id;
		}
		
}
