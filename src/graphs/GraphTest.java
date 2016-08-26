package graphs;

import java.io.FileNotFoundException;

public class GraphTest {

	public static void main(String[] args) throws FileNotFoundException {
		Graph graph = new Graph("in/input2.txt");
//		BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
		Vertex vertexOne = graph.find(1);
		Vertex vertexTwo = graph.find(2);
		Vertex vertexThree = graph.find(3);
		Vertex vertexFour = graph.find(4);
//		bfs.perform(vertexOne);
//		1bfs.perform(vertexTwo);
//		if (graph.isConnected())
//			System.out.println("Yes");
//		else
//			System.out.println("No");
//		graph.printConnectedComponents();
		if (graph.areConnected(vertexTwo, vertexFour))
			System.out.println("Yes");
		else
			System.out.println("No");
		}

}
