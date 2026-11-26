package practice;

import graph.Graph;
import map.Map;
import shortestpath.Dijkstra;

/** Practice problem: the time for a signal to reach every vertex. */
public final class NetworkDelay {

  private NetworkDelay() {
    // This class should not be instantiated!
  }

  // Returns the time for every vertex of graph to receive a signal sent from
  // source along its directed, weighted edges, or -1 if some vertex never
  // receives it. Assumes graph is not null, source is a vertex of graph, and no
  // edge weight is negative.
  public static <V> double networkDelay(Graph<V> graph, V source) {
    Map<V, Double> distances = Dijkstra.search(graph, source);
    double slowest = 0.0;
    for (Map.Entry<V, Double> entry : distances.entries()) {
      if (entry.value() == Double.POSITIVE_INFINITY) {
        return -1;
      }
      if (slowest < entry.value()) {
        slowest = entry.value();
      }
    }
    return slowest;
  }
}
