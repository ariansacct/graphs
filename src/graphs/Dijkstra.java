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
					if (! processedVertices.contains(incidentEdge.endpoint2)) {
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
	
	public void printDistances() {
		System.out.println("Distances are:");
		for (Vertex vertex : distances.keySet()) {
			System.out.println("To " + vertex.label + ": " + distances.get(vertex));
		}
	}
}
