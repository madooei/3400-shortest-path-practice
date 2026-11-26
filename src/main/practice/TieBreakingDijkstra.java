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
    Map<V, Double> distance = new HashMap<>();
    Map<V, Integer> edgeCount = new HashMap<>();
    Map<V, V> predecessor = new HashMap<>();
    Set<V> settled = new HashSet<>();
    AdaptivePriorityQueue<V, Double> frontier = new IndexedArrayHeap<>();

    for (V v : graph.vertices()) {
      distance.put(v, Double.POSITIVE_INFINITY);
    }
    distance.put(source, 0.0);
    edgeCount.put(source, 0);
    frontier.add(source, 0.0);

    while (frontier.size() > 0) {
      V v = frontier.peek();
      frontier.remove();
      settled.add(v);

      if (v.equals(target)) {
        break;
      }

      for (V neighbor : graph.neighbors(v)) {
        if (settled.contains(neighbor)) {
          continue;
        }
        double through = distance.get(v) + graph.weight(v, neighbor);
        int count = edgeCount.get(v) + 1;
        if (through < distance.get(neighbor)) {
          distance.put(neighbor, through);
          edgeCount.put(neighbor, count);
          predecessor.put(neighbor, v);
          if (frontier.contains(neighbor)) {
            frontier.changePriority(neighbor, through);
          } else {
            frontier.add(neighbor, through);
          }
        } else if (through == distance.get(neighbor) && count < edgeCount.get(neighbor)) {
          edgeCount.put(neighbor, count);
          predecessor.put(neighbor, v);
        }
      }
    }
    return pathTo(predecessor, source, target);
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
