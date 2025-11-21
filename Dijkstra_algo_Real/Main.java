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
            graph.addEdge(parts[0], parts[1], Integer.parseInt(parts[2]));
        }
        br.close();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter starting location: ");
        String start = scanner.nextLine().replace(" ", "");
        System.out.println("Enter destination: ");
        String end = scanner.nextLine().replace(" ", "");

        Map<String, String> previous = new HashMap<>();
        Map<String, Integer> distances = Dijkstra.shortestPath(graph, start, previous);

        if (!distances.containsKey(end)) {
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

        for (String p : path) {
            System.out.print(p + " -> ");
        }
        System.out.println("END");
    }
}
