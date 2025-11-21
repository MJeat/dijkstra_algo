import java.util.*;

public class Dijkstra {

    public static Map<String, Integer> shortestPath(
            Graph graph, String start, Map<String, String> previous) {

        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));

        for (String node : graph.getNodes()) {
            dist.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }

        dist.put(start, 0);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Edge edge : graph.getNeighbors(current.name)) {   // <-- FIXED HERE
                int newDist = dist.get(current.name) + edge.weight;

                if (newDist < dist.get(edge.to)) {
                    dist.put(edge.to, newDist);
                    previous.put(edge.to, current.name);
                    pq.add(new Node(edge.to, newDist));
                }
            }
        }
        return dist;
    }

    static class Node {
        String name;
        int distance;

        Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }
}
