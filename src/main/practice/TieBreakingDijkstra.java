package practice;

import graph.Graph;
import list.ArrayList;
import list.List;
import map.Map;
import stack.ArrayStack;
import stack.Stack;

/** Practice problem: Dijkstra's algorithm, breaking ties by the fewest edges. */
public final class TieBreakingDijkstra {

  private TieBreakingDijkstra() {
    // This class should not be instantiated!
  }

  // Returns the shortest path from source to target as a list of vertices that
  // starts with source and ends with target. Among shortest paths, returns one
  // with the fewest edges. Returns an empty list if no path connects them.
  // Assumes graph is not null, source and target are vertices of graph, and
  // every edge weight is positive.
  public static <V> List<V> search(Graph<V> graph, V source, V target) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Returns the path from start to target, read from a predecessor map built
  // by a search from start. Returns an empty list if target was not reached.
  // Assumes predecessor, start, and target are not null.
  private static <V> List<V> pathTo(Map<V, V> predecessor, V start, V target) {
    List<V> path = new ArrayList<>();
    if (!target.equals(start) && !predecessor.containsKey(target)) {
      return path;  // target unreachable: no path
    }

    Stack<V> stack = new ArrayStack<>();
    V step = target;
    stack.push(step);
    while (!step.equals(start)) {
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
