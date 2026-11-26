package demo;

import graph.AdjacencyListGraph;
import graph.Graph;
import list.List;
import practice.FewestEdges;

/** A demo of FewestEdges on the notes' first example. */
public final class FewestEdgesDemo {

  private FewestEdgesDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    for (int vertex = 0; vertex <= 4; vertex++) {
      graph.addVertex(vertex);
    }
    graph.addEdge(0, 1, 1);
    graph.addEdge(1, 2, 1);
    graph.addEdge(2, 3, 2);
    graph.addEdge(0, 4, 3);
    graph.addEdge(4, 3, 1);
    System.out.println("edges: 0->1 (1), 1->2 (1), 2->3 (2), 0->4 (3), 4->3 (1)");

    // The paths 0 -> 1 -> 2 -> 3 and 0 -> 4 -> 3 both cost 4. Expect 0 4 3.
    List<Integer> path = FewestEdges.search(graph, 0, 3);
    String line = "shortest path with the fewest edges from 0 to 3:";
    for (int i = 0; i < path.size(); i++) {
      line += " " + path.get(i);
    }
    System.out.println(line);
  }
}
