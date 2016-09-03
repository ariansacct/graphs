package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DepthFirstSearch {
	Graph graph;
	List<Vertex> exploredVertices;
	Stack<Vertex> stack;
	
	public DepthFirstSearch(Graph graph) {
		this.graph = graph;
		exploredVertices = new ArrayList<Vertex>();
		stack = new Stack<Vertex>();
	}
	
	public void perform() {
		List<Vertex> vertices = graph.getVertices();
		int random = new Random().nextInt(vertices.size());
		Vertex startingVertex = vertices.get(random);
		perform(startingVertex);
	}
	
	public void performRecursive() {
		List<Vertex> vertices = graph.getVertices();
		int random = new Random().nextInt(vertices.size());
		Vertex startingVertex = vertices.get(random);
		performRecursive(startingVertex);
	}
	
	public void performRecursive(Vertex startingVertex) {
		startingVertex.markAsExplored();
		exploredVertices.add(startingVertex);
		for (Vertex neighbour : startingVertex.getNeighbours()) {
			if (! neighbour.explored)
				performRecursive(neighbour);
		}
	}

	public List<Vertex> perform(Vertex startingVertex) {
		List<Vertex> exploredVertices = new ArrayList<Vertex>();
		startingVertex.markAsExplored();
		startingVertex.setDistance(0);
		exploredVertices.add(startingVertex);
		stack.add(startingVertex);
		while (! stack.isEmpty()) {
			Vertex frontVertex = stack.pop();
			for (Vertex neighbour : frontVertex.getNeighbours()) {
				if (! exploredVertices.contains(neighbour)) {
					neighbour.markAsExplored();
					neighbour.setDistance(frontVertex.distanceFromSource + 1);
					exploredVertices.add(neighbour);
					stack.add(neighbour);
				}
			}
		}
		return exploredVertices;
	}
}
