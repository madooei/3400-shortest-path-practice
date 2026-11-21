package practice;

import graph.Graph;

/** Runs the network-delay scenarios against the settling solution. */
public class NetworkDelaySettlingTest extends NetworkDelayTest {

  @Override
  protected <V> double networkDelay(Graph<V> graph, V source) {
    return NetworkDelaySettling.networkDelay(graph, source);
  }
}
