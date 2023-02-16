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
            String uLabel = tokens[0];
            Vertex u = this.find(uLabel);
            if (u == null) {
                u = this.addVertex(tokens[0]);
            }
            for (int i = 1; i < tokens.length; i++) {

                String vLabel = tokens[i];
                Vertex v = this.find(vLabel);
                if (v == null) {
                    v = this.addVertex(vLabel);
                }

                if (!edgeExists(u, v))
                    this.addEdge(u, v);

            }
        }
        scanner.close();
    }

    public Graph(String filename, boolean isWeighted) throws FileNotFoundException {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner scanner = new Scanner(bufferedReader);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split("\\s+");
            String uLabel = tokens[0];
            Vertex u = this.find(uLabel);
            if (u == null) {
                u = this.addVertex(tokens[0]);
            }
            for (int i = 1; i < tokens.length; i = i + 2) {
                String vLabel = tokens[i];
                Vertex v = this.find(vLabel);
                if (v == null) {
                    v = this.addVertex(vLabel);
                }

                // warning: not true for directed graphs
//				if (! edgeExists(u, v))
//					this.addEdge(u, v);
                this.addWeightedDirectedEdge(u, v, Double.valueOf(tokens[i + 1]));

            }
        }
        scanner.close();
    }

    public Graph reverseEdges() {
        List<Vertex> newVertices = new ArrayList<Vertex>();
        for (Vertex vertex : vertices) {
            newVertices.add(new Vertex(vertex.label));
        }
        for (Vertex vertex : newVertices) {

        }
        return null;
    }

    private boolean edgeExists(Vertex u, Vertex v) {
        for (Edge edge : edges) {
            if ((edge.endpoint1.equals(u) && edge.endpoint2.equals(v)) || edge.endpoint1.equals(v) && edge.endpoint2.equals(u))
                return true;
        }
        return false;
    }

    public boolean contains(String vertexLabel) {
        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(vertexLabel)) {
                return true;
            }
        }
        return false;
    }

    public Vertex addVertex(String label) {
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

    public void addWeightedDirectedEdge(Vertex u, Vertex v, double weight) {
        Edge edge = new Edge(u, v, weight);
        this.edges.add(edge);
        u.addIncidentEdge(edge);
    }

    public Vertex find(String label) {
        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(label)) {
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

    public void printConnectedComponents() {
        Set<List<Vertex>> cc = computeConnectedComponents();
        for (List<Vertex> list : cc) {
            for (Vertex vertex : list) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }

    public boolean areConnected(Vertex u, Vertex v) {
        BreadthFirstSearch bfs = new BreadthFirstSearch(this);
        List<Vertex> verticesReachable = bfs.perform(u);
        for (Vertex vertex : verticesReachable) {
            if (v.equals(vertex))
                return true;
        }
        return false;
    }

}
