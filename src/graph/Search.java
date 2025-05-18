package graph;

import java.util.List;

public interface Search<V> {
    boolean isConnected(Vertex<V> target);
    List<Vertex<V>> pathTo(Vertex<V> target);
}

