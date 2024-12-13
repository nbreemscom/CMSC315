import java.util.*;

public class DirectedMatrixNetwork<Vertex> implements WeightedTree<Vertex>, Network<Vertex>, Iterable<Vertex>
{
    double [][] edgeMatrix;
    ArrayList<Vertex>   vertexNames;
    HashMap<Vertex, Integer> vertexMap;

    /**
     *  Initializes this Network object to have a specified initial capacity.
     *  @param capacity - the initial capacity of this Network object.
     *  @throws IllegalArgumentException � if capacity is less than 0.
     */
    public DirectedMatrixNetwork (int capacity)
    {
        edgeMatrix = new double[capacity][capacity];
        vertexNames = new ArrayList<Vertex>();
        vertexMap = new HashMap<Vertex, Integer>();
        
        for (int i = 0; i < edgeMatrix.length; i++)
            for (int j = 0; j < edgeMatrix.length; j++)
                edgeMatrix[i][j] = -1;

    } // constructor

    /**
     * Initialized this Network object to be empty. 
     */
    public DirectedMatrixNetwork()
    {
        this(2);
    } // default constructor



    void printMatrix() {
        System.out.println("there are " 
                + vertexMap.size() + " verticies.");
         for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix.length; j++) {
                System.out.print(edgeMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *  Initializes this Network object to a shallow copy of a specified Network object.
     *  The averageTime(V, E) is O(V + E).
     *  @param network - the Network object that this Network object is
     *                                 initialized to a shallow copy of.
     */
    public DirectedMatrixNetwork (DirectedMatrixNetwork<Vertex> network)
    {
        throw new UnsupportedOperationException();
    } // copy constructor


    /**
     *  Determines if this Network object contains no vertices.
     *  @return true - if this Network object contains no vertices.
     */
    public boolean isEmpty()
    {
        return vertexMap.isEmpty();
    } // method isEmpty


    /**
     *  Determines the number of vertices in this Network object.
     *  @return the number of vertices in this Network object.
     */
    public int size()
    {
        return vertexMap.size();
    } // method size


    /**
     *  Returns the number of edges in this Network object.
     *  The averageTime (V, E) is O (V).
     *  @return the number of edges in this Network object.
     */
    public int getEdgeCount()
    {
        int count = 0;
        for (int i = 0; i < edgeMatrix.length; i++)
            for (int j = 0; j < edgeMatrix.length; j++)
                if (edgeMatrix[i][j] > 0)
                    count++;
        return count;
    } // method getEdgeCount


    /**
     *  Determines the weight of an edge in this Network object.
     *  The averageTime (V, E) is O (E / V).
     *  @param v1 - the beginning Vertex object of the edge whose weight is sought.
     *  @param v2 - the ending Vertex object of the edge whose weight is sought.
     *  @return the weight of edge <v1, v2>, if <v1, v2> forms an edge; return �1.0 if
     *                <v1, v2> does not form an edge in this Network object.
     */
    public double getEdgeWeight (Vertex v1, Vertex v2)
    {
        if (!containsVertex(v1) || !containsVertex(v2))
            return -1;
        return edgeMatrix[vertexMap.get(v1)][vertexMap.get(v2)];
    } // method getEdgeWeight


    /**
     *  Determines if this Network object contains a specified Vertex object.
     *  @param vertex - the Vertex object whose presence is sought.
     *  @return true - if vertex is an element of this Network object.
     */
    public boolean containsVertex (Vertex vertex)
    {
        return vertexMap.containsKey(vertex);
    } // method containsVertex


    /**
     *  Determines if this Network object contains an edge specified by two vertices.
     *  The averageTime (V, E) is O (E / V).
     *  @param v1 - the beginning Vertex object of the edge sought.
     *  @param v2 - the ending Vertex object of the edge sought.
     *  @return true - if this Network object contains the edge <v1, v2>.
     */
    public boolean containsEdge (Vertex v1, Vertex v2)
    {
        return (getEdgeWeight(v1, v2) > 0);
    } // method containsEdge


    /**
     *  Ensures that a specified Vertex object is an element of this Network object.
     *  @param vertex - the Vertex object whose presence is ensured.
     *  @return true - if vertex was added to this Network object by this call; returns
     *               false if vertex was already an element of this Network object when
     *               this call was made.
     */
    public boolean addVertex (Vertex vertex)
    {     
        if (vertexMap.containsKey(vertex))
            return false;
            
        if (edgeMatrix.length < vertexMap.size() + 1)
            resizeEdgeMatrix();
        
        int newVertexIndex = getFirstEmptyVertex();
        vertexNames.add(vertex);
        vertexMap.put(vertex, newVertexIndex);
        edgeMatrix[newVertexIndex][newVertexIndex] = 0;
        return true;
    } // method addVertex

    int getFirstEmptyVertex() {
        for (int i = 0; i < edgeMatrix.length; i++) {
            if (edgeMatrix[i][i] == -1)
                return i;
        }
        throw new RuntimeException("Not enough room in matrix.");
    }
    
    void resizeEdgeMatrix() {
        double[][] newEdgeMatrix = new double[edgeMatrix.length*2][edgeMatrix.length*2];
        for (int i = 0; i < newEdgeMatrix.length; i++)
            for (int j = 0; j < newEdgeMatrix.length; j++)
                newEdgeMatrix[i][j] = -1;
                
        for (int i = 0; i < edgeMatrix.length; i++)
            for (int j = 0; j < edgeMatrix.length; j++)
                newEdgeMatrix[i][j] = edgeMatrix[i][j];
        
        edgeMatrix = newEdgeMatrix;
    }

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
        if (!vertexMap.containsKey(v1) || !vertexMap.containsKey(v2))
            throw new IllegalArgumentException("One of your verticies does not exist.");
            
        if (v1.equals(v2))
            return false;
            
        if (edgeMatrix[vertexMap.get(v1)][vertexMap.get(v2)] > 0)
            return false;
        
        edgeMatrix[vertexMap.get(v1)][vertexMap.get(v2)] = weight;
        return true;
        
    } // method addEdge


    /**
     *  Ensures that a specified Vertex object is not an element of this Network object.
     *  The averageTime (V, E) is O (V + E).
     *  @param vertex - the Vertex object whose absence is ensured.
     *  @return true - if vertex was removed from this Network object by this call;
     *                returns false if vertex was not an element of this Network object
     *                when this call was made.
     */
    public boolean removeVertex (Vertex vertex)
    {
        if (!vertexMap.containsKey(vertex))
            return false;
         
        int indexToRemove = vertexMap.get(vertex);
        for (int i = 0; i < edgeMatrix.length; i++) {
            edgeMatrix[i][indexToRemove] = -1;
            edgeMatrix[indexToRemove][i] = -1;
        }
        
        vertexMap.remove(vertex);
        vertexNames.remove(indexToRemove);
        
        return true;
    } // removeVertex

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
        throw new UnsupportedOperationException();
    } // method removeEdge


    /**
     *  Returns a LinkedList object of the neighbors of a specified Vertex object.
     *  @param v - the Vertex object whose neighbors are returned.
     *  @return a LinkedList of the vertices that are neighbors of v.
     */
    public LinkedList<Vertex> neighbors (Vertex v)
    {
        throw new UnsupportedOperationException();
    } // method neighbors


    /**
     *  Returns an Iterator object over the vertices in this Network object.
     *  @return an Iterator object over the vertices in this Network object.
     */
    public Iterator<Vertex> iterator()
    {
        throw new UnsupportedOperationException();
    } // method iterator



    /**
     *  Returns a breadth-first Iterator object over all vertices reachable from
     *  a specified Vertex object.
     *  The averageTime(V, E) is O(V).
     *  @param v - the start Vertex object for the Iterator object returned.
     *  @return a  breadth-first Iterator object over all vertices reachable from v.
     *  @throws IllegalArgumentException � if v is not an element of this Network
     *                 object.
     */
    public BreadthFirstIterator breadthFirstIterator (Vertex v)
    {
        if (!vertexMap.containsKey(v))
            return null;
        return new BreadthFirstIterator(v);
    } // method breadthFirstIterator


    /**
     *  Returns a depth-first Iterator object over all vertices reachable from
     *  a specified Vertex object.
     *  The averageTime(V, E) is O(V).
     *  @param v - the start Vertex object for the Iterator object returned.
     *  @return a  depth-first Iterator object over all vertices reachable from v.
     *  @throws IllegalArgumentException � if v is not an element of this Network
     *                 object.
     */
    public DepthFirstIterator depthFirstIterator (Vertex v)
    {
        throw new UnsupportedOperationException();
    } // method depthFirstIterator


    /**
     *  Determines if this (directed) Network object is connected.
     *  The averageTime(V, E) is O(VE + V * V).
     *  @return true � if this (directed) Network object is connected.
     */
    public boolean isConnected()
    {
        throw new UnsupportedOperationException();
    } // method isConnected


    /**
     *  Returns a minimum spanning tree for this connected Network object.
     *  The averageTime(V, E) is O(E log V).
     *  @return a minimum spanning tree for this connected Network object.
     *  @throws UnsupportedOperationException, if there is no path from the
     *                 chosen root to every other vertex in this Network object.
     */
    public WeightedTree<Vertex> getMinimumSpanningTree()
    {
        throw new UnsupportedOperationException();
    } // method getMinimumSpanningTree


    /**
     *  Finds a shortest path between two specified vertices in this Network
     *  object.
     *  The averageTime(V, E) is O(E log V).
     *  @param v1 - the beginning Vertex object.
     *  @param v2 - the ending Vertex object.
     *  @return a LinkedList object containing the vertices in a shortest path
     *                from Vertex v1 to Vertex v2.
     */
    public LinkedList<Object> getShortestPath (Vertex v1, Vertex v2)
    {
        throw new UnsupportedOperationException();
    } // method findShortestPath

 
    /**
     *  Returns a String representation of this Network object.
     *  The averageTime(V, E) is O(V + E).
     *  @return a String representation of this Network object.
     */
    public String toString()
    {
        throw new UnsupportedOperationException();
    } // method toString

    /** Returns a LinkedList of VertexWeightPair's which have an edge from
     * this vertex.
     * @param v - The vertex object we want to find all edges from.
     * @return The Linked List of edges.
     */
    LinkedList<VertexWeightPair<Vertex>> getEdgeList(Vertex v) {
        if (!vertexMap.containsKey(v)) return null;
        
        LinkedList<VertexWeightPair<Vertex>> edgeList = new LinkedList<VertexWeightPair<Vertex>>();
        int indexOfVertex = vertexMap.get(v);
        for (int i = 0; i < edgeMatrix.length; i++) {
            if (edgeMatrix[indexOfVertex][i] > 0) {
                VertexWeightPair<Vertex> wp = 
                        new VertexWeightPair<Vertex>(vertexNames.get(i),
                                                    edgeMatrix[indexOfVertex][i]);
                edgeList.add(wp);
            }
        }
        return edgeList;
    }
            
    /**************************************************************/
    protected class BreadthFirstIterator implements Iterator<Vertex>
    {
            protected PureQueue<Vertex> queue;
            protected HashMap<Vertex, Boolean> reached;
            protected Vertex current;

            /**
             * Initializes this BreadthFirstIterator at start.
             */
            public BreadthFirstIterator (Vertex start)
            {
                queue = new LinkedListPureQueue<Vertex>();
                reached = new HashMap<Vertex, Boolean>();
                
                Iterator<Vertex> itr = vertexMap.keySet().iterator();
                while (itr.hasNext())
                    reached.put (itr.next(), false);
                    
                queue.enqueue(start);
                reached.put(start, true);
            } // one-parameter constructor


            /**
             * Returns true if this BreadthFirstIterator has not reached all of its 
             * reachable vertices.  Otherwise, returns false.
             */
            public boolean hasNext()
            {
                return !(queue.isEmpty());
            } // method hasNext


            /**
             * Returns the next reachable vertex in this BreadthFirstIterator object.
             */    
            public Vertex next()
            {
                VertexWeightPair<Vertex> vertexWeightPair;

                Vertex to;

                current = queue.dequeue();

                LinkedList<VertexWeightPair<Vertex>> edgeList = getEdgeList(current);
                
                Iterator<VertexWeightPair<Vertex>> itr = edgeList.iterator();
                while (itr.hasNext())
                {
                    vertexWeightPair = itr.next();
                    to = vertexWeightPair.getVertex();

                    if (!reached.get (to))
                    {
                        reached.put (to, true);
                        queue.enqueue (to);
                    } // if
                } // while
                return current;
            } // method next

            
            /**
             * Removes the most recently returned vertex.
             */
            public void remove()
            {
                throw new UnsupportedOperationException();
            } // method remove

    } // class BreadthFirstIterator


    /**************************************************************/
    protected class DepthFirstIterator implements Iterator<Vertex>
    {
            PureStack<Vertex> stack;
            HashMap<Vertex, Boolean> reached;
            Vertex current;

            /**
             * Initializes this DepthFirstIterator at start.
             */
            public DepthFirstIterator (Vertex start)
            {
                throw new UnsupportedOperationException();
            } // one-parameter constructor


            /**
             * Returns true if this DepththFirstIterator has not reached all of its 
             * reachable vertices.  Otherwise, returns false.
             */
            public boolean hasNext()
            {
                throw new UnsupportedOperationException();        
            } // method hasNext


            /**
             * Returns the next reachable vertex in this DepththFirstIterator object.
             */    
            public Vertex next()
            {
                throw new UnsupportedOperationException();
            } // method next


            /**
             * Removes the most recently returned vertex.
             */
            public void remove()
            {
                throw new UnsupportedOperationException();
            } // method remove

    } // class DepthFirstIterator
} // class Network
