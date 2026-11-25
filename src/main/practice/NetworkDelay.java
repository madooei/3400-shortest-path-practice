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
    Map<V, Double> distance = Dijkstra.search(graph, source);

    double slowest = 0.0;
    for (V v : graph.vertices()) {
      double time = distance.get(v);
      if (time == Double.POSITIVE_INFINITY) {
        return -1;  // this vertex never receives the signal
      }
      if (time > slowest) {
        slowest = time;
      }
    }
    return slowest;
  }
}
