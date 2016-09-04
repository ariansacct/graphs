package graphs;

public class StronglyConnectedComponents {
	Graph graph;
	DepthFirstSearch dfs1;
	DepthFirstSearch dfs2;
	
	public StronglyConnectedComponents(Graph graph) {
		this.graph = graph;
		dfs1 = new DepthFirstSearch(graph);
		dfs2 = new DepthFirstSearch(graph);
	}
}
