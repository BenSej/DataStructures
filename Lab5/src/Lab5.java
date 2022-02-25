import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lab5 {
	
	public static FileWriter writer;
	public static Path path;
	
	public static void main(String[] args) throws IOException {
		int k = 2;
		while (k <= 3) {
			path = Paths.get("in" + k + "_edges.txt");
			writer = new FileWriter("out" + k + ".txt");
			run();
			k++;
		}
	}
	
	static void run() throws IOException {
		writer.write("Graph edges: vertice1, vertice2, weight of the edge" + "\n");
		writer.write("\n");
		
		String input = Files.readString(path);
		input = input.substring(input.indexOf("\n"));
		input = input.trim();
		String[] array = input.split("\n");
		int i = 0;
		int numVertices = array.length + 1;
		int numEdges = numVertices - 1;
		
		Graph graph = new Graph(numVertices, numEdges);
		for (String s : array) {
			Edge e = new Edge();
			String[] values = s.split(" ");
			e.start = Integer.parseInt(values[0].trim());
			e.end = Integer.parseInt(values[1].trim());
			e.weight = Integer.parseInt(values[2].trim());
			graph.edge[i] = e;
			i++;

			writer.write("edge: " + e.start + ", " + e.end + ", " + e.weight + "\n");
		}
		writer.write("\n");
		writer.write("Kruskal spanning tree edges: vertice1, vertice2, weight of the edge" + "\n");
		writer.write("\n");
		graph.kruskal();
		writer.close();
	}
}
