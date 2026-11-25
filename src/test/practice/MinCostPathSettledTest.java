package practice;

import graph.AdjacencyListGraph;
import graph.Graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Runs the cheapest-cost scenarios against MinCostPathSettled. */
public class MinCostPathSettledTest extends MinCostPathTest {

  @Override
  protected <V> double cheapestCost(Graph<V> graph, V source, V target) {
    return MinCostPathSettled.cheapestCost(graph, source, target);
  }

  @Test
  public void searchStopsWhenTheTargetIsSettled() {
    // On the chapter's graph, a search from 0 for 2 settles 0 and then 2. It
    // must stop there, so it asks only 0 for its neighbors.
    CountingGraph graph = new CountingGraph();
    for (int vertex = 0; vertex <= 5; vertex++) {
      graph.addVertex(vertex);
    }
    addUndirected(graph, 0, 1, 50);
    addUndirected(graph, 0, 2, 6);
    addUndirected(graph, 1, 3, 9);
    addUndirected(graph, 1, 4, 30);
    addUndirected(graph, 2, 3, 7);
    addUndirected(graph, 3, 5, 15);
    addUndirected(graph, 4, 5, 11);
    MinCostPathSettled.cheapestCost(graph, 0, 2);
    assertEquals(1, graph.neighborCalls);
  }

  private void addUndirected(Graph<Integer> graph, int first, int second, double weight) {
    graph.addEdge(first, second, weight);
    graph.addEdge(second, first, weight);
  }

  // An adjacency-list graph that counts its calls to neighbors.
  private static class CountingGraph extends AdjacencyListGraph<Integer> {
    private int neighborCalls;

    @Override
    public Iterable<Integer> neighbors(Integer vertex) {
      neighborCalls++;
      return super.neighbors(vertex);
    }
  }
}
