# Shortest Paths — Practice

Two practice problems on shortest paths: how long a signal sent from one vertex of a directed graph takes to reach every vertex, and the cost of the cheapest path between two given vertices of an undirected graph. Each problem takes a `Graph<V>`, has two solutions, and has a JUnit suite that runs against each solution separately.

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
        AdjacencyListGraph.java       # unchanged copy of the graph chapter's implementation
      set/
        Set.java                      # unchanged copy of the course Set ADT
        HashSet.java                  # a set backed by the course HashMap
      map/
        Map.java                      # unchanged copy of the course Map ADT
        HashMap.java                  # unchanged copy of the course hash table
      list/
        List.java                     # unchanged copy of the course List ADT
        ArrayList.java                # unchanged copy of the course array list
      stack/
        Stack.java                    # unchanged copy of the course Stack ADT
        ArrayStack.java               # unchanged copy of the course array stack
      adaptiveheap/
        AdaptivePriorityQueue.java    # unchanged copy of the AdaptivePriorityQueue ADT
        IndexedArrayHeap.java         # the adaptive heap chapter's heap, with a HashMap index map
      shortestpath/
        Dijkstra.java                 # copy of the chapter's Dijkstra
      practice/
        NetworkDelaySweeping.java     # network delay, sweeping every edge until nothing improves
        NetworkDelaySettling.java     # network delay, with Dijkstra's algorithm
        MinCostPathFullSearch.java    # cheapest path cost, from a full search
        MinCostPathSettled.java       # cheapest path cost, stopping once the target is settled
      demo/                           # one demo per solution class
        NetworkDelaySweepingDemo.java
        NetworkDelaySettlingDemo.java
        MinCostPathFullSearchDemo.java
        MinCostPathSettledDemo.java
    test/
      practice/
        NetworkDelayTest.java         # the network-delay scenarios, written once
        NetworkDelaySweepingTest.java
        NetworkDelaySettlingTest.java
        MinCostPathTest.java          # the cheapest-path scenarios, written once
        MinCostPathFullSearchTest.java
        MinCostPathSettledTest.java
  scripts/
    test.sh                           # compile and run the JUnit tests
    run.sh                            # compile and run one demo
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh practice.NetworkDelaySweepingTest` — compiles everything and runs only that test class, so you can test one solution at a time.
- `scripts/run.sh demo.NetworkDelaySettlingDemo` — compiles everything and runs that one demo. Every solution class has its own demo, `<Class>Demo`.

## What's here

- `practice.NetworkDelaySweeping` — `networkDelay`, which relaxes every edge of the graph, pass after pass, until a whole pass changes nothing, then takes the largest time.
- `practice.NetworkDelaySettling` — `networkDelay`, which runs Dijkstra's algorithm and takes the largest distance.
- `practice.MinCostPathFullSearch` — `cheapestCost`, which runs Dijkstra's algorithm to the end and reads the target's distance.
- `practice.MinCostPathSettled` — `cheapestCost`, which runs the same search but stops as soon as the target is settled.
- `practice.NetworkDelayTest` and `practice.MinCostPathTest` — the scenarios for each problem, written once. Each solution has a subclass that runs them against it.
- `demo.NetworkDelaySweepingDemo`, `demo.NetworkDelaySettlingDemo`, `demo.MinCostPathFullSearchDemo`, and `demo.MinCostPathSettledDemo` — one demo per solution class, each on an example from the notes.
- `shortestpath.Dijkstra` — a copy of the chapter's class. `NetworkDelaySettling` and `MinCostPathFullSearch` call `shortestDistances`.
- `graph.Graph<V>` and `graph.AdjacencyListGraph<V>` — unchanged copies of the graph chapter's classes. Every solution takes a `Graph`.
- `adaptiveheap.AdaptivePriorityQueue<T, P>` and `adaptiveheap.IndexedArrayHeap<T, P>` — the adaptive heap chapter's priority queue. The index map is a `HashMap` instead of a `TreeMap`, so the items need no ordering. `Dijkstra` and `MinCostPathSettled` keep their frontier in one.
- `set`, `map`, `list`, and `stack` — unchanged copies of the course's set, map, list, and stack, which the graph, `Dijkstra`, and the solutions are built on. `pathTo` turns the route around with a stack.
