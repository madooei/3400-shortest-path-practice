package practice;

import adaptiveheap.AdaptivePriorityQueue;
import adaptiveheap.IndexedArrayHeap;
import graph.Graph;
import list.ArrayList;
import list.List;
import map.HashMap;
import map.Map;
import set.HashSet;
import set.Set;
import stack.ArrayStack;
import stack.Stack;

/** Practice problem: the shortest path from a source to a target. */
public final class ShortestPath {

  private ShortestPath() {
    // This class should not be instantiated!
  }

  // Returns the shortest path from source to target as a list of vertices that
  // starts with source and ends with target, or an empty list if no path
  // connects them. Assumes graph is not null and undirected (every edge from a
  // to b has a matching edge from b to a with the same weight), source and
  // target are vertices of graph, and no edge weight is negative.
  public static <V> List<V> search(Graph<V> graph, V source, V target) {
    Map<V, Double> distance = new HashMap<>();
    Map<V, V> predecessor = new HashMap<>();
    Set<V> settled = new HashSet<>();
    AdaptivePriorityQueue<V, Double> frontier = new IndexedArrayHeap<>();

    for (V v : graph.vertices()) {
      distance.put(v, Double.POSITIVE_INFINITY);
    }
    distance.put(source, 0.0);
    frontier.add(source, 0.0);

    while (frontier.size() > 0) {
      V v = frontier.peek();
      frontier.remove();
      settled.add(v);

      if (v.equals(target)) {
        return pathTo(predecessor, source, target);
      }

      for (V neighbor : graph.neighbors(v)) {
        if (settled.contains(neighbor)) {
          continue;
        }
        double through = distance.get(v) + graph.weight(v, neighbor);
        if (through < distance.get(neighbor)) {
          distance.put(neighbor, through);
          predecessor.put(neighbor, v);
          if (frontier.contains(neighbor)) {
            frontier.changePriority(neighbor, through);
          } else {
            frontier.add(neighbor, through);
          }
        }
      }
    }
    return new ArrayList<>();  // the target was never settled, so there is no path
  }

  // Returns the path from source to target, read from the predecessors.
  // Assumes target was reached, so its predecessors lead back to source.
  private static <V> List<V> pathTo(Map<V, V> predecessor, V source, V target) {
    Stack<V> stack = new ArrayStack<>();
    V step = target;
    stack.push(step);
    while (!step.equals(source)) {
      step = predecessor.get(step);
      stack.push(step);
    }

    List<V> path = new ArrayList<>();
    while (!stack.isEmpty()) {
      path.add(stack.top());
      stack.pop();
    }
    return path;
  }
}
