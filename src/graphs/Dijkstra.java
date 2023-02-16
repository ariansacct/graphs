package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    Graph graph;
    Set<Vertex> processedVertices;
    Map<Vertex, Double> distances;
    Map<Vertex, ArrayList<Vertex>> paths;


    public Dijkstra(Graph graph) {
        this.graph = graph;
        processedVertices = new HashSet<Vertex>();
        distances = new HashMap<Vertex, Double>();
//		shortestPathValue = Double.POSITIVE_INFINITY;

    }

    public void perform(Vertex source) {
        processedVertices.add(source);
        distances.put(source, 0.0);
        while (processedVertices.size() != graph.getVertices().size()) {
            Vertex nextToAdd = null;
            double shortestPathValue = Double.POSITIVE_INFINITY;
            for (Vertex vertex : processedVertices) {
                for (Edge incidentEdge : vertex.getIncidentEdges()) {
                    if (!processedVertices.contains(incidentEdge.endpoint2)) {
                        double score = distances.get(vertex) + incidentEdge.getWeight();
                        if (score < shortestPathValue) {
                            nextToAdd = incidentEdge.endpoint2;
                            shortestPathValue = score;
                        }
                    }
                }
            }
            // now that its found
            distances.put(nextToAdd, shortestPathValue);
            processedVertices.add(nextToAdd);
            nextToAdd = null;
            shortestPathValue = Double.POSITIVE_INFINITY;
        }
    }

    public Map<Vertex, Double> execute(Vertex source) {
        
        Set<Vertex> conquered = new HashSet<>();
        Map<Vertex, Double> distances = new HashMap<>();
        conquered.add(source);
        int n = graph.getVertices().size();

        for (Vertex u : graph.getVertices()) {
            if (conquered.contains(u)) {
                Double min = Double.MAX_VALUE;
                Vertex v = null; // for now
                for (Edge e : u.incidentEdges) {
                    if (conquered.contains(e.endpoint1) && !conquered.contains(e.endpoint2)) {
                        u = e.endpoint1;
                        v = e.endpoint2;
                    }
                    else if (conquered.contains(e.endpoint2) && !conquered.contains(e.endpoint1)) {
                        u = e.endpoint2;
                        v = e.endpoint1;
                    }

                    // if you want the paths as well put the min check in an if
                    min = Math.min(distances.get(u) + e.weight, min);
                }

                distances.put(v, min);
                conquered.add(v);
            }
        }

        return distances;
    }

    public void printDistances() {
        System.out.println("Distances are:");
        for (Vertex vertex : distances.keySet()) {
            System.out.println("To " + vertex.label + ": " + distances.get(vertex));
        }
    }
}
