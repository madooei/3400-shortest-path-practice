package practice;

import graph.Graph;

/** Runs the cheapest-path scenarios against the settled-stop solution. */
public class MinCostPathSettledTest extends MinCostPathTest {

  @Override
  protected <V> double cheapestCost(Graph<V> graph, V source, V target) {
    return MinCostPathSettled.cheapestCost(graph, source, target);
  }
}
