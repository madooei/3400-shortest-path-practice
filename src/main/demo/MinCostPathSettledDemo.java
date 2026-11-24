package demo;

import graph.AdjacencyListGraph;
import graph.Graph;
import practice.MinCostPathSettled;

/** A demo of MinCostPathSettled on the notes' second example, on the chapter's graph. */
public final class MinCostPathSettledDemo {

  private MinCostPathSettledDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    Graph<Integer> graph = buildExampleGraph();
    System.out.println("edges: 0-1 (50), 0-2 (6), 1-3 (9), 1-4 (30), 2-3 (7), 3-5 (15), 4-5 (11)");

    // The path 0 -> 2 -> 3 -> 5 -> 4 costs 39, less than 80 for 0 -> 1 -> 4. Expect 39.0.
    double cost = MinCostPathSettled.cheapestCost(graph, 0, 4);
    System.out.println("cheapest cost from 0 to 4: " + cost);
  }

  // The chapter's example: six vertices, 0 through 5, with weighted undirected edges.
  private static Graph<Integer> buildExampleGraph() {
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

  // Adds an undirected edge as a directed edge in each direction, with the same weight.
  private static void addUndirected(Graph<Integer> graph, int first, int second, double weight) {
    graph.addEdge(first, second, weight);
    graph.addEdge(second, first, weight);
  }
}
