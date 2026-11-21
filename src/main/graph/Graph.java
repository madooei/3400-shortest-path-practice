package graph;

/**
 * A graph of vertices of type V: a set of vertices and a set of directed,
 * weighted edges joining distinct vertices. Weights are always double.
 *
 * @param <V> the type of the vertices in this graph.
 */
public interface Graph<V> {

  /**
   * Adds a vertex to this graph.
   *
   * @param vertex the vertex to add.
   * @return true if the vertex was added, false if it was already present.
   * @throws IllegalArgumentException if vertex is null.
   */
  boolean addVertex(V vertex);

  /**
   * Removes a vertex and every edge incident on it, in either direction.
   *
   * @param vertex the vertex to remove.
   * @return true if the vertex was removed, false if it was not present.
   * @throws IllegalArgumentException if vertex is null.
   */
  boolean removeVertex(V vertex);

  /**
   * Returns true if this graph contains the given vertex.
   *
   * @param vertex the vertex to look for.
   * @return true if the vertex is present, false otherwise.
   * @throws IllegalArgumentException if vertex is null.
   */
  boolean containsVertex(V vertex);

  /**
   * Adds a directed edge from one vertex to another and records its weight.
   * If the edge already exists, its weight is replaced with the given weight.
   *
   * @param from the vertex the edge leaves.
   * @param to the vertex the edge enters.
   * @param weight the weight of the edge.
   * @return true if the edge was new, false if it already existed.
   * @throws IllegalArgumentException if from or to is null, if they are equal,
   *     or if either is not already in the graph.
   */
  boolean addEdge(V from, V to, double weight);

  /**
   * Adds a directed edge with the default weight 1.0. Same preconditions and
   * return value as the three-argument overload.
   *
   * @param from the vertex the edge leaves.
   * @param to the vertex the edge enters.
   * @return true if the edge was new, false if it already existed.
   * @throws IllegalArgumentException if from or to is null, if they are equal,
   *     or if either is not already in the graph.
   */
  boolean addEdge(V from, V to);

  /**
   * Removes the directed edge from one vertex to another.
   *
   * @param from the vertex the edge leaves.
   * @param to the vertex the edge enters.
   * @return true if the edge was removed, false if it was not present.
   * @throws IllegalArgumentException if from or to is null.
   */
  boolean removeEdge(V from, V to);

  /**
   * Returns true if the directed edge from one vertex to another is present.
   *
   * @param from the vertex the edge leaves.
   * @param to the vertex the edge enters.
   * @return true if the edge is present, false otherwise.
   * @throws IllegalArgumentException if from or to is null.
   */
  boolean containsEdge(V from, V to);

  /**
   * Returns the weight recorded for the directed edge from one vertex to
   * another.
   *
   * @param from the vertex the edge leaves.
   * @param to the vertex the edge enters.
   * @return the weight of the edge.
   * @throws IllegalArgumentException if from or to is null, or if the edge
   *     is not present.
   */
  double weight(V from, V to);

  /**
   * Returns the out-neighbors of a vertex: the vertices reachable by one edge.
   *
   * @param vertex the vertex whose out-neighbors are returned.
   * @return the out-neighbors of the vertex.
   * @throws IllegalArgumentException if vertex is null, or if it is not in
   *     the graph.
   */
  Iterable<V> neighbors(V vertex);

  /**
   * Returns all the vertices of this graph.
   *
   * @return the vertices of this graph.
   */
  Iterable<V> vertices();

  /**
   * Returns the number of vertices, the count |V|.
   *
   * @return the number of vertices.
   */
  int numVertices();

  /**
   * Returns the number of edges, the count |E|.
   *
   * @return the number of edges.
   */
  int numEdges();
}
