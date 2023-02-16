package graphs;

import java.io.FileNotFoundException;

public class GraphTest {

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph("in/weighted.txt", true);
        Dijkstra dijkstra = new Dijkstra(graph);
        Vertex s = graph.find("s");
        dijkstra.perform(s);
        dijkstra.printDistances();
    }
}
