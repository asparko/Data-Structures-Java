/**
 * Some examples of tests. You'll want to write more tests.
 */
public class ExampleTests {

    private static interface Animal {
        public String speak();
    }
    private static class Dog implements Animal {
        @Override
        public String speak() {
            return "woof";
        }
    }

    private static class Cat implements Animal {
        @Override
        public String speak() {
            return "meow";
        }
    }
    
    private static class Fish implements Animal {
        @Override
        public String speak() {
            return "blub";
        }
    }

    public static void main(String[] args) {

        // Hash table
    	
    	HashTable<String, Integer> ints = HashTableFactory.create();
    	for (int i=0; i<1000; i++)
    		ints.put(Integer.toString(i), i);
    	for (int i=0; i<20; i++)
    		System.out.println("Expecting " + i*i + " : " + ints.get(Integer.toString(i*i)));
    	for (int i=0; i<20; i++)
    		System.out.println("Expecting " + i*30 + " : " + ints.remove(Integer.toString(i*30)));
    	for (int i=0; i<20; i++)
    		System.out.println("Expecting null:" + ints.get(Integer.toString(i*30)));
    	for (int i=0; i<20; i++)
    		System.out.println("Expecting " + i*i + " : " + ints.get(Integer.toString(i*i)));
    	
        HashTable<String, Animal> animals = HashTableFactory.create();
        animals.put("dog", new Dog());
        animals.put("fish", new Fish());
        animals.put("cat", new Cat());
        System.out.println("Expecting 'woof': " + animals.get("dog").speak());
        System.out.println("Expecting 'blub': " + animals.get("fish").speak());
        System.out.println("Expecting 'meow': " + animals.get("cat").speak());
        System.out.println("Expecting 'woof': " + animals.remove("dog").speak());
        System.out.println("Expecting 'null': " + animals.get("dog"));
        System.out.println("Expecting 'blub': " + animals.remove("fish").speak());
        System.out.println("Expecting 'null': " + animals.get("fish"));
        System.out.println("Expecting 'meow': " + animals.get("cat").speak());
        System.out.println("Expecting 'meow': " + animals.remove("cat").speak());
        System.out.println("Expecting 'null': " + animals.get("cat"));
        animals.put("cat", new Cat());
        animals.put("dog", new Dog());
        animals.put("fish", new Fish());
        System.out.println("Expecting 'meow': " + animals.get("cat").speak());
        System.out.println("Expecting 'meow': " + animals.remove("cat").speak());
        System.out.println("Expecting 'null': " + animals.get("cat"));
        
        
        // Graph building
        Graph graph = GraphFactory.create(8);
        Node a = NodeFactory.create("a");
        Node b = NodeFactory.create("b");
        Node c = NodeFactory.create("c");
        Node d = NodeFactory.create("d");
        Node e = NodeFactory.create("e");
        Node f = NodeFactory.create("f");
        Node g = NodeFactory.create("g");
        Node h = NodeFactory.create("h");
       
        graph.addNodes(a, b, c,d,e,f,g,h);
        
        System.out.println("ID for a: " + a.getId());
        System.out.println("ID for b: " + b.getId());
        System.out.println("ID for c: " + c.getId());
        System.out.println("ID for a in graph: " + graph.lookupNode(a.getId()).getId());
        System.out.println("ID for b in graph: " + graph.lookupNode(b.getId()).getId());
        System.out.println("ID for c in graph: " + graph.lookupNode(c.getId()).getId());
        System.out.println("ID for c: " + graph.lookupNode("c").getId());
        System.out.println("ID for b: " + graph.lookupNode("b").getId());
        System.out.println("ID for a: " + graph.lookupNode("a").getId());
        
        graph.addEdge(b, a);
        graph.addEdge(c, d);
        graph.addEdge(b, f);
        
        graph.addEdge(c, h);
        graph.addEdge(c, g);
        
        graph.addEdge(d, h);
        
        graph.addEdge(e, b);
        //graph.addEdge(e, c);
        
        //graph.addEdge(f, e);
        graph.addEdge(h, g);
        
        
        
       // graph.sort();
        if (graph.isAcyclic()) 
            System.out.println("This graph is acyclic.");
        
        System.out.println("Expecting unique ids from nodes c, b, a:");
        System.out.println("ID for h: " + graph.lookupNode("h").getId());
        System.out.println("ID for b: " + graph.lookupNode("b").getId());
        System.out.println("ID for a: " + graph.lookupNode("a").getId());

        // Graph analysis
        //System.out.println("Expecting an acyclic graph with sorted output: a, b, c");
        graph.analyze();
        
    }

}
