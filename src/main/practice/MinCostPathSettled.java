package practice;

import adaptiveheap.AdaptivePriorityQueue;
import adaptiveheap.IndexedArrayHeap;
import graph.Graph;
import map.HashMap;
import map.Map;
import set.HashSet;
import set.Set;

/** Practice problem: the cheapest path cost, stopping once the target is settled. */
public final class MinCostPathSettled {

  private MinCostPathSettled() {
    // This class should not be instantiated!
  }

  // Returns the cost of the cheapest path from source to target, or -1 if no
  // path connects them. Assumes graph is not null and undirected (every edge
  // from a to b has a matching edge from b to a with the same weight), source
  // and target are vertices of graph, and no edge weight is negative.
  public static <V> double cheapestCost(Graph<V> graph, V source, V target) {
    Map<V, Double> distance = new HashMap<>();
    Set<V> settled = new HashSet<>();
    AdaptivePriorityQueue<V, Double> frontier = new IndexedArrayHeap<>();

    for (V v : graph.vertices()) {
      distance.put(v, Double.POSITIVE_INFINITY);
    }
    distance.put(source, 0.0);
    frontier.add(source, 0.0);

    while (frontier.size() > 0) {
      V v = frontier.peek();
      frontier.remove();
      settled.add(v);

      if (v.equals(target)) {
        return distance.get(v);  // settled: this distance is final
      }

      for (V neighbor : graph.neighbors(v)) {
        if (settled.contains(neighbor)) {
          continue;
        }
        double through = distance.get(v) + graph.weight(v, neighbor);
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
    return -1;  // the frontier emptied without ever settling the target
  }
}
