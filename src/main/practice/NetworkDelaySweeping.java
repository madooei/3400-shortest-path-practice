package practice;

import graph.Graph;
import map.HashMap;
import map.Map;

/** Practice problem: network delay, by sweeping every edge until nothing improves. */
public final class NetworkDelaySweeping {

  private NetworkDelaySweeping() {
    // This class should not be instantiated!
  }

  // Returns the time for every vertex of graph to hear a signal sent from
  // source along its directed, weighted edges, or -1 if some vertex never
  // hears it. Assumes graph is not null, source is a vertex of graph, and no
  // edge weight is negative.
  public static <V> double networkDelay(Graph<V> graph, V source) {
    Map<V, Double> time = new HashMap<>();
    for (V v : graph.vertices()) {
      time.put(v, Double.POSITIVE_INFINITY);
    }
    time.put(source, 0.0);

    boolean improved = true;
    while (improved) {
      improved = false;
      for (V v : graph.vertices()) {
        if (time.get(v) == Double.POSITIVE_INFINITY) {
          continue;  // the signal has not reached v yet
        }
        for (V neighbor : graph.neighbors(v)) {
          double through = time.get(v) + graph.weight(v, neighbor);
          if (through < time.get(neighbor)) {
            time.put(neighbor, through);
            improved = true;
          }
        }
      }
    }

    double slowest = 0.0;
    for (V v : graph.vertices()) {
      if (time.get(v) == Double.POSITIVE_INFINITY) {
        return -1;  // this vertex never hears the signal
      }
      if (time.get(v) > slowest) {
        slowest = time.get(v);
      }
    }
    return slowest;
  }
}
