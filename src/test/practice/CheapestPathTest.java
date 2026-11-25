package practice;

import graph.AdjacencyListGraph;
import graph.Graph;
import list.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests for recovering the cheapest path from a source to a target. */
public class CheapestPathTest {

  @Test
  public void pathToTheSourceHasOneVertex() {
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 0, 0);
    assertEquals(1, path.size());
  }

  @Test
  public void pathToTheSourceIsTheSource() {
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 0, 0);
    assertEquals(0, path.get(0));
  }

  @Test
  public void pathToAVertexWithNoEdgesIsEmpty() {
    Graph<Integer> graph = chapterGraph();
    graph.addVertex(6);
    List<Integer> path = CheapestPath.cheapestPath(graph, 0, 6);
    assertEquals(0, path.size());
  }

  @Test
  public void pathToAVertexInAnotherComponentIsEmpty() {
    Graph<Integer> graph = chapterGraph();
    graph.addVertex(6);
    graph.addVertex(7);
    addUndirected(graph, 6, 7, 1);
    List<Integer> path = CheapestPath.cheapestPath(graph, 0, 7);
    assertEquals(0, path.size());
  }

  @Test
  public void pathToANeighborAcrossTheCheapestEdgeHasTwoVertices() {
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 0, 2);
    assertEquals(2, path.size());
  }

  @Test
  public void pathStartsAtTheSource() {
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 0, 4);
    assertEquals(0, path.get(0));
  }

  @Test
  public void pathEndsAtTheTarget() {
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 0, 4);
    assertEquals(4, path.get(4));
  }

  @Test
  public void pathHasEveryVertexOfTheCheapestRoute() {
    // The route 0, 2, 3, 5, 4 costs 39, and the route 0, 1, 4 costs 80.
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 0, 4);
    assertEquals(5, path.size());
  }

  @Test
  public void pathLeavesTheSourceAlongTheCheaperRoute() {
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 0, 4);
    assertEquals(2, path.get(1));
  }

  @Test
  public void pathListsTheVerticesBetweenInOrder() {
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 0, 4);
    assertEquals(3, path.get(2));
  }

  @Test
  public void pathUsesTheLastPredecessorOfTheTarget() {
    // 4 is first reached from 1 at 52, and later from 5 at 39.
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 0, 4);
    assertEquals(5, path.get(3));
  }

  @Test
  public void pathToAVertexUsesItsPredecessorAfterItsDistanceDrops() {
    // 1 is first reached from 0 at 50, and later from 3 at 22. So the path is
    // 0, 2, 3, 1, not 0, 1.
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 0, 1);
    assertEquals(3, path.get(2));
  }

  @Test
  public void pathFromAnotherSourceStartsThere() {
    List<Integer> path = CheapestPath.cheapestPath(chapterGraph(), 4, 0);
    assertEquals(5, path.get(1));
  }

  @Test
  public void pathSettlesALoweredVertexBeforeACostlierOne() {
    // The edge 0-2 puts 2 on the frontier at 10, and the route 0, 1, 2 lowers
    // it to 2. So 2 is settled before 3, which is on the frontier at 5, and
    // the path to 3 is 0, 1, 2, 3, not 0, 3.
    Graph<Integer> graph = new AdjacencyListGraph<>();
    for (int vertex = 0; vertex <= 3; vertex++) {
      graph.addVertex(vertex);
    }
    addUndirected(graph, 0, 1, 1);
    addUndirected(graph, 0, 2, 10);
    addUndirected(graph, 1, 2, 1);
    addUndirected(graph, 2, 3, 1);
    addUndirected(graph, 0, 3, 5);
    List<Integer> path = CheapestPath.cheapestPath(graph, 0, 3);
    assertEquals(4, path.size());
  }

  @Test
  public void pathAcrossALargeWeightIsKept() {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    graph.addVertex(0);
    graph.addVertex(1);
    addUndirected(graph, 0, 1, 2000000000);
    List<Integer> path = CheapestPath.cheapestPath(graph, 0, 1);
    assertEquals(2, path.size());
  }

  @Test
  public void stopsAtATargetEqualToTheOneInTheGraph() {
    // Java reuses one Integer object for each value from -128 to 127. These
    // vertices are above that range, so the target given to cheapestPath is
    // equal to the vertex in the graph but is a different object.
    Graph<Integer> graph = new AdjacencyListGraph<>();
    graph.addVertex(1000);
    graph.addVertex(1001);
    addUndirected(graph, 1000, 1001, 1);
    List<Integer> path = CheapestPath.cheapestPath(graph, 1000, 1001);
    assertEquals(2, path.size());
  }

  // The chapter's example: six vertices, 0 through 5, with weighted undirected edges.
  private Graph<Integer> chapterGraph() {
    Graph<Integer> graph = new AdjacencyListGraph<>();
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
    return graph;
  }

  private void addUndirected(Graph<Integer> graph, int first, int second, double weight) {
    graph.addEdge(first, second, weight);
    graph.addEdge(second, first, weight);
  }
}
