package practice;

import graph.Graph;

/** Runs the cheapest-cost scenarios against MinCostPathFullSearch. */
public class MinCostPathFullSearchTest extends MinCostPathTest {

  @Override
  protected <V> double cheapestCost(Graph<V> graph, V source, V target) {
    return MinCostPathFullSearch.cheapestCost(graph, source, target);
  }
}
