package graph;

import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final Map<Vertex<V>, Boolean> visited = new HashMap<>();
    private final Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private final Vertex<V> start;

    public BreadthFirstSearch(WeightedGraph<V> graph, Vertex<V> start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(WeightedGraph<V> graph, Vertex<V> start) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        visited.put(start, true);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, true);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public boolean isConnected(Vertex<V> target) {
        return visited.containsKey(target);
    }

    @Override
    public List<Vertex<V>> pathTo(Vertex<V> target) {
        if (!isConnected(target)) return null;

        List<Vertex<V>> path = new ArrayList<>();
        for (Vertex<V> x = target; !x.equals(start); x = edgeTo.get(x)) {
            path.add(x);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}
