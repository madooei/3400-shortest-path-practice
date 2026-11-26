package practice;

import graph.AdjacencyListGraph;
import graph.Graph;
import list.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests for the shortest path with the fewest edges. */
public class FewestEdgesTest {

  @Test
  public void pathToTheSourceHasOneVertex() {
    List<Integer> path = FewestEdges.search(tiedGraph(1), 0, 0);
    assertEquals(1, path.size());
  }

  @Test
  public void pathToTheSourceIsTheSource() {
    List<Integer> path = FewestEdges.search(tiedGraph(1), 0, 0);
    assertEquals(0, path.get(0));
  }

  @Test
  public void pathToAnUnreachableVertexIsEmpty() {
    List<Integer> path = FewestEdges.search(tiedGraph(1), 3, 0);
    assertEquals(0, path.size());
  }

  @Test
  public void pathAlongOneEdgeHasTwoVertices() {
    Graph<Integer> graph = vertices(2);
    graph.addEdge(0, 1, 5.0);
    List<Integer> path = FewestEdges.search(graph, 0, 1);
    assertEquals(2, path.size());
  }

  @Test
  public void tieIsBrokenByTheFewestEdges() {
    // The paths 0, 1, 2, 3 and 0, 4, 3 both cost 4.
    List<Integer> path = FewestEdges.search(tiedGraph(1), 0, 3);
    assertEquals(3, path.size());
  }

  @Test
  public void tiedPathTakesTheRouteWithFewerEdges() {
    // 3 first gets the distance 4 through 2, and the path through 4 ties it later.
    List<Integer> path = FewestEdges.search(tiedGraph(1), 0, 3);
    assertEquals(4, path.get(1));
  }

  @Test
  public void cheaperPathWinsOverFewerEdges() {
    // With the edge 4 -> 3 at 2, the path through 4 costs 5, more than 4.
    List<Integer> path = FewestEdges.search(tiedGraph(2), 0, 3);
    assertEquals(4, path.size());
  }

  @Test
  public void tieAtAVertexOnThePathCarriesItsEdgeCountForward() {
    // 3 is reached with two edges through 4, after a tie. From 3, the path to
    // 5 has three edges. The path 0, 6, 7, 8, 5 also costs 5, but it has four
    // edges, and it reaches 5 first.
    Graph<Integer> graph = tiedGraph(1);
    for (int vertex = 5; vertex <= 8; vertex++) {
      graph.addVertex(vertex);
    }
    graph.addEdge(3, 5, 1.0);
    graph.addEdge(0, 6, 1.0);
    graph.addEdge(6, 7, 1.0);
    graph.addEdge(7, 8, 1.0);
    graph.addEdge(8, 5, 2.0);
    List<Integer> path = FewestEdges.search(graph, 0, 5);
    assertEquals(3, path.get(2));
  }

  @Test
  public void searchSettlesALoweredVertexBeforeACostlierOne() {
    // The edge 0 -> 2 puts 2 on the frontier at 10, and the path 0, 1, 2 lowers
    // it to 2. So 2 is settled before 3, which is on the frontier at 5, and the
    // path to 3 is 0, 1, 2, 3, not 0, 3.
    Graph<Integer> graph = vertices(4);
    graph.addEdge(0, 1, 1.0);
    graph.addEdge(0, 2, 10.0);
    graph.addEdge(1, 2, 1.0);
    graph.addEdge(2, 3, 1.0);
    graph.addEdge(0, 3, 5.0);
    List<Integer> path = FewestEdges.search(graph, 0, 3);
    assertEquals(4, path.size());
  }

  @Test
  public void pathAcrossALargeWeightIsKept() {
    Graph<Integer> graph = vertices(2);
    graph.addEdge(0, 1, 2000000000.0);
    List<Integer> path = FewestEdges.search(graph, 0, 1);
    assertEquals(2, path.size());
  }

  @Test
  public void searchStopsWhenTheTargetIsSettled() {
    // A search from 0 for 1 settles 0 and then 1. It must stop there, so it
    // asks only 0 for its neighbors.
    CountingGraph graph = new CountingGraph();
    addTiedEdges(graph, 1);
    FewestEdges.search(graph, 0, 1);
    assertEquals(1, graph.neighborCalls);
  }

  @Test
  public void stopsAtATargetEqualToTheOneInTheGraph() {
    // Java reuses one Integer object for each value from -128 to 127. These
    // vertices are above that range, so the target given to search is equal
    // to the vertex in the graph but is a different object.
    Graph<Integer> graph = new AdjacencyListGraph<>();
    graph.addVertex(1000);
    graph.addVertex(1001);
    graph.addEdge(1000, 1001, 1.0);
    List<Integer> path = FewestEdges.search(graph, 1000, 1001);
    assertEquals(2, path.size());
  }

  // The graph of Example 1 on the problem page, with the given weight on the
  // edge 4 -> 3. Weight 1 makes both paths from 0 to 3 cost 4.
  private Graph<Integer> tiedGraph(double lastWeight) {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    addTiedEdges(graph, lastWeight);
    return graph;
  }

  // Adds the vertices and edges of Example 1 to an empty graph.
  private void addTiedEdges(Graph<Integer> graph, double lastWeight) {
    for (int vertex = 0; vertex <= 4; vertex++) {
      graph.addVertex(vertex);
    }
    graph.addEdge(0, 1, 1.0);
    graph.addEdge(1, 2, 1.0);
    graph.addEdge(2, 3, 2.0);
    graph.addEdge(0, 4, 3.0);
    graph.addEdge(4, 3, lastWeight);
  }

  // A graph with the vertices 0 through count - 1 and no edges.
  private Graph<Integer> vertices(int count) {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    for (int vertex = 0; vertex < count; vertex++) {
      graph.addVertex(vertex);
    }
    return graph;
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
