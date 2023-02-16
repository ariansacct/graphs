package graphs;

public class Edge {
    Vertex endpoint1;
    Vertex endpoint2;
    double weight;

    public double getWeight() {
        return weight;
    }

    public Edge(Vertex endpoint1, Vertex endpoint2) {
        this.endpoint1 = endpoint1;
        this.endpoint2 = endpoint2;
        weight = -1.0;
    }

    public Edge(Vertex tail, Vertex head, double weight) {
        this.endpoint1 = tail;
        this.endpoint2 = head;
        this.weight = weight;
    }
}
