package practice;

import graph.Graph;
import map.HashMap;
import map.Map;
import priorityqueue.ArrayHeap;
import priorityqueue.PriorityQueue;
import set.HashSet;
import set.Set;

/** Practice problem: the cheapest path cost, stopping once the target is settled. */
public final class MinCostPathSettled {

  private MinCostPathSettled() {
    // This class should not be instantiated!
  }

  // A vertex paired with the tentative distance it was added to the frontier
  // with. Steps compare by distance alone.
  private static class Step<V> implements Comparable<Step<V>> {
    private final V vertex;
    private final double distance;

    Step(V vertex, double distance) {
      this.vertex = vertex;
      this.distance = distance;
    }

    @Override
    public int compareTo(Step<V> other) {
      return Double.compare(this.distance, other.distance);
    }
  }

  // Returns the cost of the cheapest path from source to target, or -1 if no
  // path connects them. Assumes graph is not null and undirected (every edge
  // from a to b has a matching edge from b to a with the same weight), source
  // and target are vertices of graph, and no edge weight is negative.
  public static <V> double cheapestCost(Graph<V> graph, V source, V target) {
    Map<V, Double> distance = new HashMap<>();
    Set<V> settled = new HashSet<>();
    PriorityQueue<Step<V>> frontier = new ArrayHeap<>();

    for (V v : graph.vertices()) {
      distance.put(v, Double.POSITIVE_INFINITY);
    }
    distance.put(source, 0.0);
    frontier.add(new Step<>(source, 0.0));

    while (frontier.size() > 0) {
      Step<V> closest = frontier.peek();
      frontier.remove();
      V v = closest.vertex;

      if (settled.contains(v)) {
        continue;
      }
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
          frontier.add(new Step<>(neighbor, through));
        }
      }
    }
    return -1;  // the frontier emptied without ever settling the target
  }
}
