package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class BreadthFirstSearch {
    Graph graph;
    //	Map<Vertex, Boolean> explored;
    List<Vertex> explored;
    Queue<Vertex> queue;

    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
        queue = new LinkedList<Vertex>();
    }

    public void perform() {
        List<Vertex> vertices = graph.getVertices();
        int random = new Random().nextInt(vertices.size());
        Vertex startingVertex = vertices.get(random);
        perform(startingVertex);
    }

    public List<Vertex> perform(Vertex startingVertex) {
        List<Vertex> exploredVertices = new ArrayList<Vertex>();
        startingVertex.markAsExplored();
        startingVertex.setDistance(0);
        exploredVertices.add(startingVertex);
        queue.add(startingVertex);
        while (!queue.isEmpty()) {
            Vertex frontVertex = queue.poll();
            for (Vertex neighbour : frontVertex.getNeighbours()) {
//				if (neighbour.explored == false) {
                if (!exploredVertices.contains(neighbour)) {
                    neighbour.markAsExplored();
                    neighbour.setDistance(frontVertex.distanceFromSource + 1);
                    exploredVertices.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        return exploredVertices;
    }
}
