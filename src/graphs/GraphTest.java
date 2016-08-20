package graphs;

import java.io.FileNotFoundException;

public class GraphTest {

	public static void main(String[] args) throws FileNotFoundException {
		Graph graph = new Graph("input2.txt");
		BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
		Vertex vertexOne = graph.find(1);
		Vertex vertexTwo = graph.find(2);
//		bfs.perform(vertexOne);
//		1bfs.perform(vertexTwo);
		if (graph.isConnected())
			System.out.println("Yes");
		else
			System.out.println("No");
	}

}
