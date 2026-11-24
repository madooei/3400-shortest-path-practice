package demo;

import graph.AdjacencyListGraph;
import graph.Graph;
import practice.NetworkDelaySweeping;

/** A demo of NetworkDelaySweeping on the notes' first example: four vertices and five links. */
public final class NetworkDelaySweepingDemo {

  private NetworkDelaySweepingDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    Graph<Integer> graph = new AdjacencyListGraph<>();
    for (int vertex = 0; vertex <= 3; vertex++) {
      graph.addVertex(vertex);
    }
    graph.addEdge(0, 1, 4);
    graph.addEdge(0, 2, 1);
    graph.addEdge(2, 1, 2);
    graph.addEdge(1, 3, 5);
    graph.addEdge(2, 3, 9);
    System.out.println("links: 0->1 (4), 0->2 (1), 2->1 (2), 1->3 (5), 2->3 (9)");

    // Vertex 2 hears the signal at 1, vertex 1 at 3, and vertex 3 at 8. Expect 8.0.
    double delay = NetworkDelaySweeping.networkDelay(graph, 0);
    System.out.println("time for every vertex to hear a signal from 0: " + delay);
  }
}
