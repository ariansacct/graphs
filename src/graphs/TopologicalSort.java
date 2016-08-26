package graphs;

import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {
	Graph graph;
	DepthFirstSearch dfs;
	List<Vertex> explored;
	
	public TopologicalSort(Graph graph) {
		this.graph = graph;
		dfs = new DepthFirstSearch(graph);
		explored = new ArrayList<Vertex>();
	}
	
	public void perform() {
		List<Vertex> vertices = graph.getVertices();
		int currentLabel = vertices.size();
		for (Vertex vertex : vertices) {
			if (! vertices.contains(vertex)) {
				explored = dfs.perform(vertex);
				asasxasxa
			}
		}
	}
}
