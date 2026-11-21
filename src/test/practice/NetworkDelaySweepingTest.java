package practice;

import graph.Graph;

/** Runs the network-delay scenarios against the sweeping solution. */
public class NetworkDelaySweepingTest extends NetworkDelayTest {

  @Override
  protected <V> double networkDelay(Graph<V> graph, V source) {
    return NetworkDelaySweeping.networkDelay(graph, source);
  }
}
