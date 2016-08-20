package graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Graph {

	private List<Vertex> vertices;
	private List<Edge> edges;

	public List<Vertex> getVertices() {
		return vertices;
	}

	public Graph(String filename) throws FileNotFoundException {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		File file = new File(filename);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		Scanner scanner = new Scanner(bufferedReader);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] tokens = line.split("\\s+");
			int uLabel = Integer.valueOf(tokens[0]);
			Vertex u = this.find(uLabel);
			if (u == null) {
				u = this.addVertex(Integer.valueOf(tokens[0]));
			}

			for (int i = 1; i < tokens.length; i++) {
				
				int vLabel = Integer.valueOf(tokens[i]);
				Vertex v = this.find(vLabel);
				if (v == null) {
					v = this.addVertex(vLabel);
				}
				
				if (! edgeExists(u, v))
					this.addEdge(u, v);
				
			}
		}
		scanner.close();
		System.out.println("Graph with " + this.vertices.size() + " vertices and " + this.edges.size() + " edges.");
	}

	private boolean edgeExists(Vertex u, Vertex v) {
		for (Edge edge : edges) {
			if ((edge.endpoint1.equals(u) && edge.endpoint2.equals(v)) || edge.endpoint1.equals(v) && edge.endpoint2.equals(u))
				return true;
		}
		return false;
	}

	public boolean contains(int vertexLabel) {
		for (Vertex vertex : vertices) {
			if (vertex.getLabel() == vertexLabel) {
				return true;
			}
		}
		return false;
	}

	public Vertex addVertex(int label) {
		Vertex vertex = new Vertex(label);
		vertices.add(vertex);
		return vertex;
	}

	public void addEdge(Vertex u, Vertex v) {
		Edge edge = new Edge(u, v);
		this.edges.add(edge);
		u.addIncidentEdge(edge);
		v.addIncidentEdge(edge);
	}

	public Vertex find(int label) {
		for (Vertex vertex : vertices) {
			if (vertex.getLabel() == label) {
				return vertex;
			}
		}
		return null;
	}

	public void printVertices() {
		for (Vertex vertex : vertices) {
			System.out.println(vertex.getLabel());
		}
	}

	public boolean isConnected() {
		Set<List<Vertex>> allExploredVertices = computeConnectedComponents();
		if (allExploredVertices.size() > 1)
			return false;
		else return true;
	}

	public Set<List<Vertex>> computeConnectedComponents() {
		Set<List<Vertex>> allExploredVertices = new HashSet<List<Vertex>>();
		for (Vertex vertex : vertices) {
			if (vertex.explored == false) {
				BreadthFirstSearch bfs = new BreadthFirstSearch(this);
				List<Vertex> exploredVerticesThisIteration = bfs.perform(vertex);
				allExploredVertices.add(exploredVerticesThisIteration);
			}
		}
		return allExploredVertices;

	}
	
}
