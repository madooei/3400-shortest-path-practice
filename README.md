# Shortest Paths — Practice

Three practice problems on shortest paths: the cost of the cheapest path between two given vertices of an undirected graph, how long a signal sent from one vertex of a directed graph takes to reach every vertex, and the cheapest path itself. Each problem takes a `Graph<V>` and has a JUnit suite. The first problem has two solutions, and its suite runs against each one separately.

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
        MinCostPathFullSearch.java    # cheapest path cost, from a full search
        MinCostPathSettled.java       # cheapest path cost, stopping once the target is settled
        NetworkDelay.java             # time for a signal to reach every vertex
        CheapestPath.java             # the cheapest path itself
      demo/                           # one demo per solution class
        MinCostPathFullSearchDemo.java
        MinCostPathSettledDemo.java
        NetworkDelayDemo.java
        CheapestPathDemo.java
    test/
      practice/
        MinCostPathTest.java          # the cheapest-cost scenarios, written once
        MinCostPathFullSearchTest.java
        MinCostPathSettledTest.java
        NetworkDelayTest.java
        CheapestPathTest.java
  scripts/
    test.sh                           # compile and run the JUnit tests
    run.sh                            # compile and run one demo
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh practice.MinCostPathSettledTest` — compiles everything and runs only that test class, so you can test one solution at a time.
- `scripts/run.sh demo.NetworkDelayDemo` — compiles everything and runs that one demo. Every solution class has its own demo, `<Class>Demo`.

## Contents

- `practice.MinCostPathFullSearch` — `cheapestCost`, which runs Dijkstra's algorithm to the end and reads the target's distance.
- `practice.MinCostPathSettled` — `cheapestCost`, which runs the same search but stops as soon as the target is settled.
- `practice.NetworkDelay` — `networkDelay`, which runs Dijkstra's algorithm and takes the largest distance.
- `practice.CheapestPath` — `cheapestPath`, which records a predecessor each time a distance drops, stops when the target is settled, and follows the predecessors back.
- `practice.MinCostPathTest` — the scenarios for the first problem, written once. Each of its two solutions has a subclass that runs them against it. `practice.NetworkDelayTest` and `practice.CheapestPathTest` test the other two problems.
- `demo.MinCostPathFullSearchDemo`, `demo.MinCostPathSettledDemo`, `demo.NetworkDelayDemo`, and `demo.CheapestPathDemo` — one demo per solution class, each on an example from the notes.
- `shortestpath.Dijkstra` — a copy of the chapter's class. `MinCostPathFullSearch` and `NetworkDelay` call `search`.
- `graph.Graph<V>` and `graph.AdjacencyListGraph<V>` — unchanged copies of the classes from the chapter on graphs. Every solution takes a `Graph`.
- `adaptiveheap.AdaptivePriorityQueue<T, P>` and `adaptiveheap.IndexedArrayHeap<T, P>` — the priority queue from the chapter on adaptive priority queues. The index map is a `HashMap` instead of a `TreeMap`, so the items need no ordering. `Dijkstra`, `MinCostPathSettled`, and `CheapestPath` keep their frontier in one.
- `set` and `map` — unchanged copies of the course's set and map, which the graph, `Dijkstra`, and the solutions are built on.
- `list` and `stack` — unchanged copies of the course's list and stack. `CheapestPath` returns a `List` and turns the route around with a `Stack`, as `pathTo` does in the chapter on graph search.
