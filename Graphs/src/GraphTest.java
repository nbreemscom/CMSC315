
public class GraphTest {

	public static void main(String[] args) {
		UndirectedGraph g = new UndirectedGraph();
		
		g.addEdge("Sioux Center", "Orange City", 10);
		g.addEdge("Orange City", "Le Mars", 16);
		g.addEdge("Sioux Center", "Chicago", 560);
		
		System.out.println("My graph has " + g.getNumEdges() + " edges and " +
				g.getNumNodes() + " nodes.");
		
		System.out.println("Neighbors of Sioux Center are: " + g.getNeighborList("Sioux Center"));
	
		System.out.println("Iterating from Orange City: ");
		
//		System.out.println("Is the graph connected? " + g.isConnected());
		
	}

}
