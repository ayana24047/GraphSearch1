package graph;

import java.util.*;

public class WeightedGraph<V> {
    private final Map<Vertex<V>, List<Edge<V>>> map = new HashMap<>();

    public void addVertex(Vertex<V> vertex) {
        map.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        addVertex(source);
        addVertex(dest);
        map.get(source).add(new Edge<>(source, dest, weight));
        source.addAdjacentVertex(dest, weight);  // для BFS и Dijkstra
    }

    public List<Edge<V>> getEdges(Vertex<V> vertex) {
        return map.getOrDefault(vertex, Collections.emptyList());
    }

    public Set<Vertex<V>> getVertices() {
        return map.keySet();
    }

    public boolean containsVertex(Vertex<V> vertex) {
        return map.containsKey(vertex);
    }

    public void printGraph() {
        for (Vertex<V> vertex : map.keySet()) {
            System.out.print(vertex + " --> ");
            for (Edge<V> edge : map.get(vertex)) {
                System.out.print(edge.getDest() + "(" + edge.getWeight() + ") ");
            }
            System.out.println();
        }