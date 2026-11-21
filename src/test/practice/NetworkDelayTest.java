package practice;

import graph.AdjacencyListGraph;
import graph.Graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the network-delay problem. The scenarios are written once here;
 * each solution gets its own subclass that says which class to call, so you
 * can run the suite for one solution at a time.
 */
public abstract class NetworkDelayTest {

  // Calls the solution under test.
  protected abstract <V> double networkDelay(Graph<V> graph, V source);

  // Builds a directed graph on the vertices 0 through vertexCount - 1, adding
  // each link {a, b, w} as an edge from a to b of weight w.
  private static Graph<Integer> directed(int vertexCount, int[][] links) {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    for (int vertex = 0; vertex < vertexCount; vertex++) {
      graph.addVertex(vertex);
    }
    for (int[] link : links) {
      graph.addEdge(link[0], link[1], link[2]);
    }
    return graph;
  }

  // Builds the network of Example 1 on the problem page.
  private static Graph<Integer> exampleNetwork() {
    int[][] links = {{0, 1, 4}, {0, 2, 1}, {2, 1, 2}, {1, 3, 5}, {2, 3, 9}};
    return directed(4, links);
  }

  @Test
  public void loneVertexHearsTheSignalAtTimeZero() {
    int[][] links = {};
    assertEquals(0.0, networkDelay(directed(1, links), 0));
  }

  @Test
  public void noEdgesMeansTheOtherVertexNeverHears() {
    int[][] links = {};
    assertEquals(-1.0, networkDelay(directed(2, links), 0));
  }

  @Test
  public void oneLinkTakesItsWeight() {
    int[][] links = {{0, 1, 4}};
    assertEquals(4.0, networkDelay(directed(2, links), 0));
  }

  @Test
  public void linkIntoTheSourceCarriesNoSignal() {
    int[][] links = {{1, 0, 7}};
    assertEquals(-1.0, networkDelay(directed(2, links), 0));
  }

  @Test
  public void detourBeatsTheDirectLink() {
    int[][] links = {{0, 1, 10}, {0, 2, 1}, {2, 1, 1}};
    assertEquals(2.0, networkDelay(directed(3, links), 0));
  }

  @Test
  public void lastVertexToHearSetsTheTime() {
    assertEquals(8.0, networkDelay(exampleNetwork(), 0));
  }

  @Test
  public void isolatedVertexNeverHears() {
    Graph<Integer> graph = exampleNetwork();
    graph.addVertex(4);
    assertEquals(-1.0, networkDelay(graph, 0));
  }

  @Test
  public void signalCanStartAtAnyVertex() {
    int[][] links = {{1, 3, 5}, {1, 2, 3}, {2, 0, 1}};
    assertEquals(5.0, networkDelay(directed(4, links), 1));
  }

  @Test
  public void chainAgainstTheVertexOrderIsFullyReached() {
    // The links run 4 -> 3 -> 2 -> 1 -> 0, against the order the vertices are
    // numbered in.
    int[][] links = {{4, 3, 1}, {3, 2, 1}, {2, 1, 1}, {1, 0, 1}};
    assertEquals(4.0, networkDelay(directed(5, links), 4));
  }

  @Test
  public void fractionalTimesAreKept() {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    graph.addVertex(0);
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addEdge(0, 1, 0.5);
    graph.addEdge(1, 2, 1.25);
    assertEquals(1.75, networkDelay(graph, 0));
  }

  @Test
  public void stringVerticesWork() {
    Graph<String> graph = new AdjacencyListGraph<>();
    graph.addVertex("Albany");
    graph.addVertex("Boston");
    graph.addEdge("Albany", "Boston", 3.0);
    assertEquals(3.0, networkDelay(graph, "Albany"));
  }
}
