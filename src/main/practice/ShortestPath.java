package practice;

import graph.Graph;
import list.List;
import map.Map;

/** Practice problem: the shortest path from a source to a target. */
public final class ShortestPath {

  private ShortestPath() {
    // This class should not be instantiated!
  }

  // Returns the shortest path from source to target as a list of vertices that
  // starts with source and ends with target, or an empty list if no path
  // connects them. Assumes graph is not null and undirected (every edge from a
  // to b has a matching edge from b to a with the same weight), source and
  // target are vertices of graph, and no edge weight is negative.
  public static <V> List<V> search(Graph<V> graph, V source, V target) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Returns the path from source to target, read from the predecessors.
  // Assumes target was reached, so its predecessors lead back to source.
  private static <V> List<V> pathTo(Map<V, V> predecessor, V source, V target) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
