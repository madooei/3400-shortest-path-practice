package practice;

import graph.AdjacencyListGraph;
import graph.Graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The scenarios for the cost of the cheapest path to a target. A concrete
 * subclass supplies cheapestCost() to pick the solution under test.
 */
public abstract class MinCostPathTest {

  // Calls the solution under test.
  protected abstract <V> double cheapestCost(Graph<V> graph, V source, V target);

  @Test
  public void targetIsTheSource() {
    int[][] edges = {};
    assertEquals(0.0, cheapestCost(undirected(1, edges), 0, 0));
  }

  @Test
  public void noEdgesReachesNothing() {
    int[][] edges = {};
    assertEquals(-1.0, cheapestCost(undirected(2, edges), 0, 1));
  }

  @Test
  public void oneEdgeCostsItsWeight() {
    int[][] edges = {{0, 1, 6}};
    assertEquals(6.0, cheapestCost(undirected(2, edges), 0, 1));
  }

  @Test
  public void neighborCostsItsEdge() {
    assertEquals(6.0, cheapestCost(chapterGraph(), 0, 2));
  }

  @Test
  public void firstDistanceGivenIsNotTheAnswer() {
    // 1 is first given 50, down the direct edge, and improves to 22 through 3.
    assertEquals(22.0, cheapestCost(chapterGraph(), 0, 1));
  }

  @Test
  public void cheapestRouteCanBeTheLongOne() {
    // A search that stops the first time 4 is given a distance reports 52.
    assertEquals(39.0, cheapestCost(chapterGraph(), 0, 4));
  }

  @Test
  public void edgesRunBothWays() {
    assertEquals(39.0, cheapestCost(chapterGraph(), 4, 0));
  }

  @Test
  public void searchSettlesALoweredVertexBeforeACostlierOne() {
    // The edge 0-2 puts 2 on the frontier at 10, and the route 0, 1, 2 lowers
    // it to 2. So 2 is settled before 3, which is on the frontier at 5, and
    // the route 0, 1, 2, 3 lowers 3 to 3.
    int[][] edges = {{0, 1, 1}, {0, 2, 10}, {1, 2, 1}, {2, 3, 1}, {0, 3, 5}};
    assertEquals(3.0, cheapestCost(undirected(4, edges), 0, 3));
  }

  @Test
  public void isolatedTargetIsUnreachable() {
    Graph<Integer> graph = chapterGraph();
    graph.addVertex(6);
    assertEquals(-1.0, cheapestCost(graph, 0, 6));
  }

  @Test
  public void targetInAnotherComponentIsUnreachable() {
    Graph<Integer> graph = chapterGraph();
    graph.addVertex(6);
    graph.addVertex(7);
    graph.addEdge(6, 7, 1);
    graph.addEdge(7, 6, 1);
    assertEquals(-1.0, cheapestCost(graph, 0, 7));
  }

  @Test
  public void fractionalCostsAreKept() {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    graph.addVertex(0);
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addEdge(0, 1, 0.5);
    graph.addEdge(1, 0, 0.5);
    graph.addEdge(1, 2, 0.25);
    graph.addEdge(2, 1, 0.25);
    graph.addEdge(0, 2, 1.0);
    graph.addEdge(2, 0, 1.0);
    assertEquals(0.75, cheapestCost(graph, 0, 2));
  }

  @Test
  public void largeCostIsKept() {
    int[][] edges = {{0, 1, 2000000000}};
    assertEquals(2000000000.0, cheapestCost(undirected(2, edges), 0, 1));
  }

  @Test
  public void stringVerticesWork() {
    Graph<String> graph = new AdjacencyListGraph<>();
    graph.addVertex("Albany");
    graph.addVertex("Boston");
    graph.addEdge("Albany", "Boston", 3.0);
    graph.addEdge("Boston", "Albany", 3.0);
    assertEquals(3.0, cheapestCost(graph, "Albany", "Boston"));
  }

  @Test
  public void stopsAtATargetEqualToTheOneInTheGraph() {
    // Java reuses one Integer object for each value from -128 to 127. These
    // vertices are above that range, so the target given to cheapestCost is
    // equal to the vertex in the graph but is a different object.
    Graph<Integer> graph = new AdjacencyListGraph<>();
    graph.addVertex(1000);
    graph.addVertex(1001);
    graph.addEdge(1000, 1001, 1.0);
    graph.addEdge(1001, 1000, 1.0);
    assertEquals(1.0, cheapestCost(graph, 1000, 1001));
  }

  // Builds an undirected graph on the vertices 0 through vertexCount - 1,
  // adding each edge {a, b, w} in both directions with weight w.
  private static Graph<Integer> undirected(int vertexCount, int[][] edges) {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    for (int vertex = 0; vertex < vertexCount; vertex++) {
      graph.addVertex(vertex);
    }
    for (int[] edge : edges) {
      graph.addEdge(edge[0], edge[1], edge[2]);
      graph.addEdge(edge[1], edge[0], edge[2]);
    }
    return graph;
  }

  // Builds the chapter's weighted graph.
  private static Graph<Integer> chapterGraph() {
    int[][] edges = {{0, 1, 50}, {0, 2, 6}, {1, 3, 9}, {1, 4, 30},
                     {2, 3, 7}, {3, 5, 15}, {4, 5, 11}};
    return undirected(6, edges);
  }
}
