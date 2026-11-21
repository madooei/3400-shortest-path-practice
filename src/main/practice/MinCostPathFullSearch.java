package practice;

import graph.Graph;
import map.Map;
import shortestpath.Dijkstra;

/** Practice problem: the cheapest path cost, from a full search. */
public final class MinCostPathFullSearch {

  private MinCostPathFullSearch() {
    // This class should not be instantiated!
  }

  // Returns the cost of the cheapest path from source to target, or -1 if no
  // path connects them. Assumes graph is not null and undirected (every edge
  // from a to b has a matching edge from b to a with the same weight), source
  // and target are vertices of graph, and no edge weight is negative.
  public static <V> double cheapestCost(Graph<V> graph, V source, V target) {
    Map<V, Double> distance = Dijkstra.shortestDistances(graph, source);

    double cost = distance.get(target);
    if (cost == Double.POSITIVE_INFINITY) {
      return -1;  // no route from the source ever reaches the target
    }
    return cost;
  }
}
