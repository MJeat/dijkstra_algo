import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {


        Graph graph = new Graph();

        // Load map data
        BufferedReader br = new BufferedReader(new FileReader("graph.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 3) {
                int w = Integer.parseInt(parts[2]);

                // UNDIRECTED GRAPH (REAL MAP)
                graph.addEdge(parts[0], parts[1], w);
                graph.addEdge(parts[1], parts[0], w);  
            }
        }
        br.close();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a starting location ");
        System.out.println("We have the following locations available:");
        for (String node : graph.getNodes()) {
            System.out.println("- " + node);
        }
        System.out.print("Your choice: ");
        String start = scanner.nextLine().replace(" ", "");
        System.out.println("Enter a destination ");
        System.out.println("We have the following locations available:");
        for (String node : graph.getNodes()) {
            System.out.println("- " + node);
        }
        System.out.print("Your choice: ");
        String end = scanner.nextLine().replace(" ", "");

        Map<String, String> previous = new HashMap<>();
        Map<String, Integer> distances = Dijkstra.shortestPath(graph, start, previous);
        

        if (!distances.containsKey(end) || distances.get(end) == Integer.MAX_VALUE) {
            System.out.println("Location not found.");
            return;
        }

        System.out.println("\nShortest distance from " + start + " to " + end + ": "
                + distances.get(end) + " meters");

        System.out.println("\nPath:");

        List<String> path = new ArrayList<>();
        String step = end;

        while (step != null) {
            path.add(step);
            step = previous.get(step);
        }

        Collections.reverse(path);

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
