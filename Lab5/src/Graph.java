import java.io.IOException;

public class Graph {
	
	int numVertices;
	int numEdges;
	Edge[] edge;
	
	Graph(int v, int e) {
		numEdges = e;
		numVertices = v;
		edge = new Edge[numEdges];
		for (int i = 0; i < e; i++) {
			edge[i] = new Edge();
		}
	}
	
	int findParent(Subset subArray[], int i) {
		if (subArray[i].parent != i) {
			subArray[i].parent = findParent(subArray, subArray[i].parent);
		}
		return subArray[i].parent;
	}
	
	void union(Subset subArray[], int x, int y) {
		int xRoot = findParent(subArray, x);
		int yRoot = findParent(subArray, y);
		
		if (subArray[xRoot].rank < subArray[yRoot].rank) {
			subArray[xRoot].parent = yRoot;
		} else if (subArray[xRoot].rank > subArray[yRoot].rank) {
			subArray[yRoot].parent = xRoot;
		} else {
			subArray[yRoot].parent = xRoot;
			subArray[xRoot].rank++;
		}
	}
	
	void kruskal() throws IOException {
		int totalWeight = 0;
		Edge result[] = new Edge[numVertices];
		int i = 0;
		int j = 0;
		
		for (i = 0; i < numVertices; i++) {
			result[i] = new Edge();
		}
		sort(edge);
		
		Subset[] subArray = new Subset[numVertices];
		for (i = 0; i < numVertices; i++) {
			subArray[i] = new Subset();
		}
		
		for (int v = 0; v < numVertices; v++) {
			subArray[v].parent = v;
			subArray[v].rank = 0;
		}
		
		i = 0;
		
		while (j < numVertices - 1 && i < numEdges) {
			Edge nextEdge = new Edge();
			nextEdge = edge[i++];
			
			int x = findParent(subArray, nextEdge.start);
			int y = findParent(subArray, nextEdge.end);
			
			if (x != y) {
				result[j++] = nextEdge;
				union(subArray, x, y);
			}
		}
		
		for (i = 0; i < j; i++) {
			totalWeight += result[i].weight;
			Lab5.writer.write("edge: " + result[i].start + ", " + result[i].end + ", " + result[i].weight + "\n");
		}
		Lab5.writer.write("\n");
		Lab5.writer.write("Kruskal spanning tree weight is " + totalWeight);
	}
	
	static void sort(Edge[] input) { 
        int length = input.length; 
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (input[j].weight > input[j + 1].weight) {
                    Edge temp = input[j]; 
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }
        }
    }
}
