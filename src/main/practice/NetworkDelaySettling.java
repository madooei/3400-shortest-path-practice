package practice;

import graph.Graph;
import map.Map;
import shortestpath.Dijkstra;

/** Practice problem: network delay, with Dijkstra's algorithm. */
public final class NetworkDelaySettling {

  private NetworkDelaySettling() {
    // This class should not be instantiated!
  }

  // Returns the time for every vertex of graph to hear a signal sent from
  // source along its directed, weighted edges, or -1 if some vertex never
  // hears it. Assumes graph is not null, source is a vertex of graph, and no
  // edge weight is negative.
  public static <V> double networkDelay(Graph<V> graph, V source) {
    Map<V, Double> distance = Dijkstra.shortestDistances(graph, source);

    double slowest = 0.0;
    for (V v : graph.vertices()) {
      double time = distance.get(v);
      if (time == Double.POSITIVE_INFINITY) {
        return -1;  // this vertex never hears the signal
      }
      if (time > slowest) {
        slowest = time;
      }
    }
    return slowest;
  }
}
