# Shortest Paths — Practice

Two practice problems on shortest paths: the shortest path between two given vertices of an undirected graph, and how long a signal sent from one vertex of a directed graph takes to reach every vertex. Each problem takes a `Graph<V>` and has a JUnit suite.

## Prerequisites

- JDK 17+ (JUnit 6 requires it).
- The JUnit jar is already vendored in `lib/`; there is nothing to download.

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  lib/
    junit-platform-console-standalone-6.1.0.jar
  src/
    main/
      graph/
        Graph.java                    # unchanged copy of the Graph ADT
        AdjacencyListGraph.java       # unchanged copy of the implementation from the chapter on graphs
      set/
        Set.java                      # unchanged copy of the course Set ADT
        HashSet.java                  # a hash set with separate chaining, copied from the chapter on separate chaining
      map/
        Map.java                      # unchanged copy of the course Map ADT
        HashMap.java                  # unchanged copy of the course hash table
      adaptiveheap/
        AdaptivePriorityQueue.java    # unchanged copy of the AdaptivePriorityQueue ADT
        IndexedArrayHeap.java         # the heap from the chapter on adaptive priority queues, with a HashMap index map
      list/
        List.java                     # unchanged copy of the course List ADT
        ArrayList.java                # unchanged copy of the course array list
      stack/
        Stack.java                    # unchanged copy of the course Stack ADT
        ArrayStack.java               # unchanged copy of the course array stack
      shortestpath/
        Dijkstra.java                 # copy of the chapter's Dijkstra
      practice/
        ShortestPath.java             # the shortest path to a target
        NetworkDelay.java             # time for a signal to reach every vertex
      demo/                           # one demo per solution class
        ShortestPathDemo.java
        NetworkDelayDemo.java
    test/
      practice/
        ShortestPathTest.java
        NetworkDelayTest.java
  scripts/
    test.sh                           # compile and run the JUnit tests
    run.sh                            # compile and run one demo
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh practice.ShortestPathTest` — compiles everything and runs only that test class, so you can test one solution at a time.
- `scripts/run.sh demo.NetworkDelayDemo` — compiles everything and runs that one demo. Every solution class has its own demo, `<Class>Demo`.

## Contents

- `practice.ShortestPath` — `search`, which records a predecessor each time a distance drops, stops when the target is settled, and follows the predecessors back.
- `practice.NetworkDelay` — `networkDelay`, which runs Dijkstra's algorithm and takes the largest distance.
- `practice.ShortestPathTest` and `practice.NetworkDelayTest` — the tests for each problem.
- `demo.ShortestPathDemo` and `demo.NetworkDelayDemo` — one demo per solution class, each on an example from the notes.
- `shortestpath.Dijkstra` — a copy of the chapter's class. `NetworkDelay` calls `search`.
- `graph.Graph<V>` and `graph.AdjacencyListGraph<V>` — unchanged copies of the classes from the chapter on graphs. Every solution takes a `Graph`.
- `adaptiveheap.AdaptivePriorityQueue<T, P>` and `adaptiveheap.IndexedArrayHeap<T, P>` — the priority queue from the chapter on adaptive priority queues. The index map is a `HashMap` instead of a `TreeMap`, so the items need no ordering. `Dijkstra` and `ShortestPath` keep their frontier in one.
- `set` and `map` — unchanged copies of the course's set and map, which the graph, `Dijkstra`, and the solutions are built on.
- `list` and `stack` — unchanged copies of the course's list and stack. `ShortestPath` returns a `List` and turns the route around with a `Stack`, as `pathTo` does in the chapter on graph search.
