package practice;

import graph.Graph;

/** Practice problem: the cheapest path cost, stopping once the target is settled. */
public final class MinCostPathSettled {

  private MinCostPathSettled() {
    // This class should not be instantiated!
  }

  // A vertex paired with a tentative distance. Steps compare by distance only.
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
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
