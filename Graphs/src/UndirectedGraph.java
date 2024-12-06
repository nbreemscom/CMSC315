import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class UndirectedGraph extends Graph {

	Map<String, Map<String, Double>> graph; 
	
	public UndirectedGraph() {
		graph = new HashMap<String, Map<String, Double>>();
	}
	
	public int getNumNodes() {
		return graph.size();
	}
	
	public int getNumEdges() {
		int edges = 0;
		for (Map<String, Double> m : graph.values()) {
			edges += m.size();
		}
		return edges >> 1;
	}
	
	public void addEdge(String a, String b, int x) {
		addEdge(a, b, x*1.0);
	}
	
	public void addEdge(String a, String b, double w) {
		if (!graph.containsKey(a)) {
			graph.put(a, new HashMap<String, Double>());
		}
		if (!graph.containsKey(b)) {
			graph.put(b, new HashMap<String, Double>());
		}
		
		graph.get(a).put(b, w);
		graph.get(b).put(a, w);
	}

	public List<String> getNeighborList(String v) {
		List<String> x = new ArrayList<String>();
		
		for (String key : graph.get(v).keySet())
			x.add(key);
		
		return x;
	}
	
	public Iterator<String> breadthFirstIterator(String v) {
		
	}
	
	class BreadthFirstIterator implements Iterator<String> {

		HashMap<String, Boolean> reached;
		Queue<String> q;
		
		public BreadthFirstIterator(String v) {
			reached = new HashMap<String, Boolean>();
			for (String key: graph.keySet()) {
				reached.put(key, false);
			}
			
			q = new LinkedList<String>();
			q.offer(v);
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !q.isEmpty();
		}

		@Override
		public String next() {
			String temp = q.poll();
			for (String key: graph.get(temp).keySet()) {
				if (!reached.get(key)
						q.offer(key);
			}
			
			while (reached.get(q.peek()))
					q.poll();
			
			return temp;
		}
		
	}
}
