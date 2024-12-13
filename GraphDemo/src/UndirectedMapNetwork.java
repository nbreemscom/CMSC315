import java.util.Iterator;
import java.util.Map;

public class UndirectedMapNetwork<Vertex> extends DirectedMapNetwork<Vertex> implements Network<Vertex>, Iterable<Vertex>
{
    /**
     * Initialized this Network object to be empty. 
     */
    public UndirectedMapNetwork()
    {
            super();
    } // default constructor


    /**
     *  Initializes this Network object to have a specified initial capacity.
     *  @param capacity - the initial capacity of this Network object.
     *  @throws IllegalArgumentException � if capacity is less than 0.
     */
    public UndirectedMapNetwork (int capacity)
    {
        super(capacity);
    } // constructor


    /**
     *  Initializes this Network object to a shallow copy of a specified Network object.
     *  The averageTime(V, E) is O(V + E).
     *  @param network - the Network object that this Network object is
     *                                 initialized to a shallow copy of.
     */
    public UndirectedMapNetwork (UndirectedMapNetwork<Vertex> network)
    {
            super(network);
    } // copy constructor

    /**
     *  Returns the number of edges in this Network object.
     *  The averageTime (V, E) is O (V).
     *  @return the number of edges in this Network object.
     */
    public int getEdgeCount()
    {
            return super.getEdgeCount() / 2;
    } // method getEdgeCount


    /**
     *  Ensures that an edge is in this Network object.
     *  @param v1 - the beginning Vertex object of the edge whose presence
     *                         is ensured.
     *  @param v2 - the ending Vertex object of the edge whose presence is
     *                        ensured.
     *  @param weight - the weight of the edge whose presence is ensured.
     *  @return true - if the given edge (and weight) were added to this Network
     *                         object by this call; return false, if the given edge (and weight)
     *                         were already in this Network object when this call was made.
     */
    public boolean addEdge (Vertex v1, Vertex v2, double weight)
    {
        return super.addEdge(v1, v2, weight) && super.addEdge(v2, v1, weight);
    } // method addEdge


    /**
     *  Ensures that an edge specified by two vertices is absent from this Network
     *  object.
     *  The averageTime (V, E) is O (E / V).
     *  @param v1 - the beginning Vertex object of the edge whose absence is
     *                          ensured.
     *  @param v2 - the ending Vertex object of the edge whose absence is
     *                        ensured.
     *  @return true - if the edge <v1, v2> was removed from this Network object
     *                          by this call; return false if the edge <v1, v2> was not in this
     *                          Network object when this call was made.
     */
    public boolean removeEdge (Vertex v1, Vertex v2)
    {
        return super.removeEdge(v1, v2) && super.removeEdge(v2, v1);
    } // method removeEdge

    /**
     *  Returns a minimum spanning tree for this connected Network object.
     *  The averageTime(V, E) is O(E log V).
     *  @return a minimum spanning tree for this connected Network object.
     *  @throws UnsupportedOperationException, if there is no path from the
     *                 chosen root to every other vertex in this Network object.
     */
    public WeightedTree<Vertex> getMinimumSpanningTree()
    {
        Network<Vertex> tree = new DirectedMapNetwork<Vertex>();

        PurePriorityQueue<EdgeTriple<Vertex>> pq = new Heap<EdgeTriple<Vertex>>();

        EdgeTriple<Vertex> edgeTriple;

        Vertex root,
                       w,
                       x,
                       y,
                       z;

        Iterator<Vertex> netItr;

        Iterator<VertexWeightPair<Vertex>> listItr;

        double weight;

        if (isEmpty())
            return (WeightedTree<Vertex>)tree;
        netItr = adjacencyMap.keySet().iterator();
        root = netItr.next();

        tree.addVertex(root);

        for (Map.Entry<Vertex, Double> v : adjacencyMap.get(root).entrySet())   {
            w = v.getKey();
            weight = v.getValue();
            edgeTriple = new EdgeTriple<Vertex> (root, w, weight);
            pq.add (edgeTriple);
        } // adding root's edgeTriples to pq

        while (tree.size() < size()) {
            if (pq.isEmpty())
                throw new UnsupportedOperationException();
            edgeTriple = pq.removeMin();
            x = edgeTriple.getFromVertex();
            y = edgeTriple.getToVertex();
            weight = edgeTriple.getWeight();
            if (!tree.containsVertex (y)) {
                tree.addVertex (y);
                tree.addEdge (x, y, weight);

                for (Map.Entry<Vertex, Double> v : adjacencyMap.get(y).entrySet()) {
                    z = v.getKey();
                    if (!tree.containsVertex (z)) {
                        weight = v.getValue();
                        edgeTriple = new EdgeTriple<Vertex> (y, z, weight);
                        pq.add (edgeTriple);
                    } // z not already in tree
                } // iterating over y's neighbors
            } // y not already in tree
        } // tree has fewer vertices than this Network
        return (WeightedTree<Vertex>) tree;
    } // method getMinimumSpanningTree


    
} // class Network
