package graph;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");
        Vertex<String> e = new Vertex<>("E");

        graph.addEdge(a, b, 1);
        graph.addEdge(a, c, 4);
        graph.addEdge(b, c, 2);
        graph.addEdge(b, d, 5);
        graph.addEdge(c, e, 1);
        graph.addEdge(d, e, 2);

        System.out.println("=== Breadth-First Search (BFS) from A to E ===");
        Search<String> bfs = new BreadthFirstSearch<>(graph, a);
        System.out.println("Path from A to E: " + bfs.pathTo(e));

        System.out.println("\n=== Dijkstra's Algorithm from A to E ===");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, a);
        System.out.println("Path from A to E: " + dijkstra.pathTo(e));
        System.out.println("Total distance: " + dijkstra.distanceTo(e));
    }
}
