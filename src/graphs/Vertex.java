package graphs;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    String label;
    List<Edge> incidentEdges;
    int distanceFromSource;
    boolean explored;

    public boolean equals(Object o) {
        if (!(o instanceof Vertex))
            return false;
        return this.label == ((Vertex) o).label;
    }

    public String toString() {
        return "Vertex " + label;
    }

    public String getLabel() {
        return label;
    }

    public Vertex(String label) {
        this.label = label;
        incidentEdges = new ArrayList<Edge>();
        distanceFromSource = Integer.MAX_VALUE;
        explored = false;
    }

    public void addIncidentEdge(Edge edge) {
        this.incidentEdges.add(edge);
    }

    public void setDistance(int distance) {
        distanceFromSource = distance;
    }

    public void markAsExplored() {
        explored = true;
        System.out.println("Just explored " + this.toString());
    }

    public List<Edge> getIncidentEdges() {
        return incidentEdges;
    }

    public List<Vertex> getNeighbours() {
        List<Vertex> neighbours = new ArrayList<Vertex>();
        for (Edge edge : incidentEdges) {
            if (edge.endpoint1.equals(this))
                neighbours.add(edge.endpoint2);
            else
                neighbours.add(edge.endpoint1);
        }
        return neighbours;
    }
}