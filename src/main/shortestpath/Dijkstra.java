package shortestpath;

import graph.Graph;
import list.ArrayList;
import list.List;
import map.HashMap;
import map.Map;
import priorityqueue.ArrayHeap;
import priorityqueue.PriorityQueue;
import set.HashSet;
import set.Set;
import stack.ArrayStack;
import stack.Stack;

/** Dijkstra's algorithm: the cheapest paths from one source in a weighted graph. */
public final class Dijkstra {

  private Dijkstra() {
    // This class should not be instantiated!
  }

  // Returns a map from every vertex of graph to the cost of the cheapest path
  // from source to it. A vertex that source cannot reach has the distance
  // Double.POSITIVE_INFINITY. Assumes graph is not null, source is a vertex of
  // graph, and no edge weight is negative.
  public static <V> Map<V, Double> shortestDistances(Graph<V> graph, V source) {
    Map<V, Double> distance = new HashMap<>();
    Set<V> settled = new HashSet<>();
    PriorityQueue<Step<V>> frontier = new ArrayHeap<>();

    for (V v : graph.vertices()) {
      distance.put(v, Double.POSITIVE_INFINITY);
    }
    distance.put(source, 0.0);
    frontier.add(new Step<>(source, 0.0));

    while (frontier.size() > 0) {
      Step<V> closest = frontier.peek();
      frontier.remove();
      V v = closest.vertex;

      if (settled.contains(v)) {
        continue;  // stale entry: v was already settled by a cheaper one
      }
      settled.add(v);

      for (V neighbor : graph.neighbors(v)) {
        if (settled.contains(neighbor)) {
          continue;
        }
        double through = distance.get(v) + graph.weight(v, neighbor);
        if (through < distance.get(neighbor)) {
          distance.put(neighbor, through);
          frontier.add(new Step<>(neighbor, through));
        }
      }
    }
    return distance;
  }

  // Returns the cost of the cheapest path from source to every vertex of
  // graph, and the paths themselves. Assumes graph is not null, source is a
  // vertex of graph, and no edge weight is negative.
  public static <V> Paths<V> shortestPaths(Graph<V> graph, V source) {
    Map<V, Double> distance = new HashMap<>();
    Map<V, V> predecessor = new HashMap<>();
    Set<V> settled = new HashSet<>();
    PriorityQueue<Step<V>> frontier = new ArrayHeap<>();

    for (V v : graph.vertices()) {
      distance.put(v, Double.POSITIVE_INFINITY);
    }
    distance.put(source, 0.0);
    frontier.add(new Step<>(source, 0.0));

    while (frontier.size() > 0) {
      Step<V> closest = frontier.peek();
      frontier.remove();
      V v = closest.vertex;

      if (settled.contains(v)) {
        continue;
      }
      settled.add(v);

      for (V neighbor : graph.neighbors(v)) {
        if (settled.contains(neighbor)) {
          continue;
        }
        double through = distance.get(v) + graph.weight(v, neighbor);
        if (through < distance.get(neighbor)) {
          distance.put(neighbor, through);
          predecessor.put(neighbor, v);
          frontier.add(new Step<>(neighbor, through));
        }
      }
    }
    return new Paths<>(distance, predecessor);
  }

  // A vertex paired with a tentative distance. Steps compare by distance only.
  private static class Step<V> implements Comparable<Step<V>> {
    private final V vertex;
    private final double distance;

    Step(V vertex, double distance) {
      this.vertex = vertex;
      this.distance = distance;
    }

    @Override
    public int compareTo(Step<V> other) {
      return Double.compare(this.distance, other.distance);
    }
  }

  // The result of shortestPaths: the cost of the cheapest path from the source
  // to every vertex, and the vertex before each one on that path.
  public static class Paths<V> {
    private final Map<V, Double> distance;
    private final Map<V, V> predecessor;

    Paths(Map<V, Double> distance, Map<V, V> predecessor) {
      this.distance = distance;
      this.predecessor = predecessor;
    }

    // Returns the cost of the cheapest path to target, or
    // Double.POSITIVE_INFINITY if the source cannot reach target. Assumes
    // target is a vertex of the graph.
    public double distanceTo(V target) {
      return distance.get(target);
    }

    // Returns the vertices of a cheapest path from source to target, source
    // first. Returns an empty list if target is unreachable. Assumes source is
    // the vertex the search started from and target is a vertex of the graph.
    public List<V> pathTo(V source, V target) {
      List<V> path = new ArrayList<>();
      if (distance.get(target) == Double.POSITIVE_INFINITY) {
        return path;  // unreachable: no path exists
      }

      Stack<V> stack = new ArrayStack<>();
      V step = target;
      stack.push(step);
      while (!step.equals(source)) {
        step = predecessor.get(step);
        stack.push(step);
      }

      while (!stack.isEmpty()) {
        path.add(stack.top());
        stack.pop();
      }
      return path;
    }
  }
}
