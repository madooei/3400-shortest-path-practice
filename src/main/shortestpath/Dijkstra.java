package shortestpath;

import adaptiveheap.AdaptivePriorityQueue;
import adaptiveheap.IndexedArrayHeap;
import graph.Graph;
import map.HashMap;
import map.Map;
import set.HashSet;
import set.Set;

/** Dijkstra's algorithm: the cheapest paths from one source in a weighted graph. */
public final class Dijkstra {

  private Dijkstra() {
    // This class should not be instantiated!
  }

  // Returns a map from every vertex of graph to the cost of the cheapest path
  // from source to it. A vertex that source cannot reach has the distance
  // Double.POSITIVE_INFINITY. Assumes graph is not null, source is a vertex of
  // graph, and no edge weight is negative.
  public static <V> Map<V, Double> search(Graph<V> graph, V source) {
    Map<V, Double> distance = new HashMap<>();
    Set<V> settled = new HashSet<>();
    AdaptivePriorityQueue<V, Double> frontier = new IndexedArrayHeap<>();

    for (V vertex : graph.vertices()) {
      distance.put(vertex, Double.POSITIVE_INFINITY);
    }
    distance.put(source, 0.0);
    frontier.add(source, 0.0);

    while (frontier.size() > 0) {
      V vertex = frontier.peek();
      frontier.remove();
      settled.add(vertex);

      for (V neighbor : graph.neighbors(vertex)) {
        if (settled.contains(neighbor)) {
          continue;
        }
        double through = distance.get(vertex) + graph.weight(vertex, neighbor);
        if (through < distance.get(neighbor)) {
          distance.put(neighbor, through);
          if (frontier.contains(neighbor)) {
            frontier.changePriority(neighbor, through);
          } else {
            frontier.add(neighbor, through);
          }
        }
      }
    }
    return distance;
  }
}
