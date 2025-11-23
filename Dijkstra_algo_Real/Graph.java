import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adj = new HashMap<>();

    public void addEdge(String src, String dest, int weight) {
        adj.putIfAbsent(src, new ArrayList<>());
        adj.putIfAbsent(dest, new ArrayList<>());
        adj.get(src).add(new Edge(dest, weight));
        adj.get(dest).add(new Edge(src, weight)); 
    }

    public List<Edge> getNeighbors(String node) {
        return adj.getOrDefault(node, new ArrayList<>());
    }

    public Set<String> getNodes() {
        return adj.keySet();
    }
}

class Edge {
    String to;
    int weight;

    Edge(String t, int w) {
        this.to = t;
        this.weight = w;
    }
   
}

