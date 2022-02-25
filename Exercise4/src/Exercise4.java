import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

public class Exercise4 {
	
	static FileWriter fout;
	static Vector<Integer> inputData = new Vector<>();
	
	static void printHeap(String description, Heap<Integer> heap) throws IOException {
		fout.write(description + "\n");
		fout.write("[" + heap.size() + "] ");
		
		for (int i = 0; i < heap.size(); i++) {
			fout.write(" " + heap.getElement(i));
		}
		fout.write("\n");
		fout.write("\n");
	}
	
	static boolean areHeapElementsOrdered(Heap<Integer> heap, int i, int j) {
		return i >= heap.size() || j >= heap.size() || heap.getElement(i).compareTo((Integer) heap.getElement(j)) <= 0;
	}
	
	static String heapElement(Heap<Integer> heap, int i) {
		return i < heap.size() ? String.valueOf(heap.getElement(i)) : "none";
	}
	
	static void checkHeap(Heap<Integer> heap) throws IOException {
		for (int i = 0; i < heap.size(); i++) {
			if (areHeapElementsOrdered(heap, i, i * 2 + 1) && areHeapElementsOrdered(heap, i, i * 2 + 2)) {
				continue;
			}
			
			printHeap("Corrupted", heap);
			fout.write("Error: heap violation at index " + i);
			fout.write(", heap[" + i + "] = " + heapElement(heap, i));
			fout.write(", heap[" + (i * 2 + 1) + "] = " + heapElement(heap, i * 2 + 1));
			fout.write(", heap[" + (i * 2 + 2) + "] = " + heapElement(heap, i * 2 + 2));
			fout.write("\n");
			fout.close();
			throw new RuntimeException("Not a heap");
		}
	}
	
	static void insertOne(Heap<Integer> heap, int element) throws IOException {
		fout.write("Insert " + element + "\n");
		heap.insert(element);
		checkHeap(heap);
		printHeap("Heap after insert " + String.valueOf(element), heap);
	}
	
	void deleteOne(Heap<Integer> heap) throws IOException {
		fout.write("Delete " + heap.getElement(0) + "\n");
		heap.deleteMin();
	}
	
	static void testData() throws IOException {
		Heap<Integer> heap = new Heap<>();
		
		for (Integer key : inputData) {
			heap.insert(key);
			checkHeap(heap);
			System.out.println(heap.heapVector);
		}
			
		printHeap("Heap", heap);
		
		insertOne(heap, 31);
		
		insertOne(heap, 14);
			
		while (heap.size() > 0) {
			heap.deleteMin();
			fout.write("Delete min" + "\n");
			printHeap("Heap after Delete min", heap);
		}
		fout.write("Deleted all");
		fout.close();
	}
	
	static void readData(String inputFile) throws IOException {
		Path path = Paths.get(inputFile);
		String fin = Files.readString(path);
		fin = fin.replaceAll("\n", "");
		fin = fin.trim();
		String[] input = fin.split(" ");
		
		fout.write("Input data ");
		for (String s : input) {
			Integer i = Integer.valueOf(s);
			inputData.add(i);
			fout.write(s + " ");
		}
		fout.write("\n");
	}
	
	static void testFile(String inputFile, String outputFile) throws IOException {
		fout = new FileWriter(outputFile);
		readData(inputFile);
	}
	
	public static void main(String[] args) {
		try {
			for (int i = 1; i <= 4; i++) {
				String suffix = i + ".txt";
				testFile("in" + suffix, "out" + suffix);
				testData();
				inputData.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}