package practice;

import graph.Graph;

/** Runs the cheapest-path scenarios against the full-search solution. */
public class MinCostPathFullSearchTest extends MinCostPathTest {

  @Override
  protected <V> double cheapestCost(Graph<V> graph, V source, V target) {
    return MinCostPathFullSearch.cheapestCost(graph, source, target);
  }
}
