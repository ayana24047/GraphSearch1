package graph;

import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<Vertex<V>, Double> distances = new HashMap<>();
    private final Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private final Vertex<V> start;

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> start) {
        this.start = start;

        for (Vertex<V> vertex : graph.getVertices()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);

        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparing(distances::get));
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            for (Map.Entry<Vertex<V>, Double> neighborEntry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = neighborEntry.getKey();
                double weight = neighborEntry.getValue();

                double newDistance = distances.get(current) + weight;
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, current);
                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public boolean isConnected(Vertex<V> target) {
        return distances.getOrDefault(target, Double.POSITIVE_INFINITY) < Double.POSITIVE_INFINITY;
    }

    @Override
    public List<Vertex<V>> pathTo(Vertex<V> target) {
        if (!isConnected(target)) return null;

        List<Vertex<V>> path = new ArrayList<>();
        for (Vertex<V> at = target; !at.equals(start); at = edgeTo.get(at)) {
            path.add(at);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    public double distanceTo(Vertex<V> target) {
        return distances.getOrDefault(target, Double.POSITIVE_INFINITY);
    }
}
