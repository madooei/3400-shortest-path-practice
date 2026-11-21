package graph;

import map.HashMap;
import map.Map;

/**
 * Adjacency-list implementation of the Graph ADT, backed by a map of maps.
 *
 * @param <V> the type of the vertices in this graph.
 */
public class AdjacencyListGraph<V> implements Graph<V> {

  private Map<V, Map<V, Double>> adjacency;  // vertex -> (out-neighbor -> weight)
  private int edgeCount;                     // how many edges the graph holds

  public AdjacencyListGraph() {
    adjacency = new HashMap<>();  // the graph starts empty
    edgeCount = 0;
  }

  @Override
  public boolean addVertex(V vertex) {
    if (vertex == null) {
      throw new IllegalArgumentException();
    }
    if (adjacency.containsKey(vertex)) {
      return false;  // already present
    }
    adjacency.put(vertex, new HashMap<>());  // start it with no edges
    return true;
  }

  @Override
  public boolean removeVertex(V vertex) {
    if (vertex == null) {
      throw new IllegalArgumentException();
    }
    if (!adjacency.containsKey(vertex)) {
      return false;  // not present; nothing changed
    }
    // remove the edges leaving the vertex
    edgeCount -= adjacency.get(vertex).size();
    adjacency.remove(vertex);
    // remove the edges pointing into the vertex, wherever they are
    for (V other : adjacency) {
      Map<V, Double> out = adjacency.get(other);
      if (out.remove(vertex)) {  // remove reports whether an edge was there
        edgeCount--;
      }
    }
    return true;
  }

  @Override
  public boolean containsVertex(V vertex) {
    if (vertex == null) {
      throw new IllegalArgumentException();
    }
    return adjacency.containsKey(vertex);
  }

  @Override
  public boolean addEdge(V from, V to, double weight) {
    if (from == null || to == null) {
      throw new IllegalArgumentException();
    }
    if (from.equals(to)) {
      throw new IllegalArgumentException();
    }
    if (!adjacency.containsKey(from) || !adjacency.containsKey(to)) {
      throw new IllegalArgumentException();  // both endpoints must be in the graph
    }
    Map<V, Double> out = adjacency.get(from);
    boolean isNew = out.put(to, weight);  // put reports whether the edge was new
    if (isNew) {
      edgeCount++;
    }
    return isNew;
  }

  @Override
  public boolean addEdge(V from, V to) {
    return addEdge(from, to, 1.0);
  }

  @Override
  public boolean removeEdge(V from, V to) {
    if (from == null || to == null) {
      throw new IllegalArgumentException();
    }
    Map<V, Double> out = adjacency.get(from);
    if (out == null || !out.remove(to)) {
      return false;  // no such edge; nothing changed
    }
    edgeCount--;
    return true;
  }

  @Override
  public boolean containsEdge(V from, V to) {
    if (from == null || to == null) {
      throw new IllegalArgumentException();
    }
    Map<V, Double> out = adjacency.get(from);
    return out != null && out.containsKey(to);
  }

  @Override
  public double weight(V from, V to) {
    if (from == null || to == null) {
      throw new IllegalArgumentException();
    }
    Map<V, Double> out = adjacency.get(from);
    if (out == null || !out.containsKey(to)) {
      throw new IllegalArgumentException();  // no such edge
    }
    return out.get(to);
  }

  @Override
  public Iterable<V> neighbors(V vertex) {
    if (vertex == null) {
      throw new IllegalArgumentException();
    }
    Map<V, Double> out = adjacency.get(vertex);
    if (out == null) {
      throw new IllegalArgumentException();  // vertex not in the graph
    }
    return out;  // its keys are the out-neighbors
  }

  @Override
  public Iterable<V> vertices() {
    return adjacency;  // its keys are the vertices
  }

  @Override
  public int numVertices() {
    return adjacency.size();
  }

  @Override
  public int numEdges() {
    return edgeCount;
  }
}
