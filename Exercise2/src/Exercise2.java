import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Exercise2 {
	
	static Path path = null;
	static int i = 0;
	
	public static void main(String[] args) throws IOException {
		while (i <= 100) {
			path = Paths.get("in" + i + ".txt");
			sort();
			heapSort();
			i++;
		}
	}
	
	static void sort() throws IOException {
		int k = 0;
		
		try {
			k = Integer.parseInt(Files.readAllLines(path).get(0));
		} catch (Exception e) {
			return;
		}
		
		Writer writer = new FileWriter("out" + i + "a.txt");
		
		String list = Files.readString(path);
		list = list.substring(String.valueOf(k).length());
		list = list.replaceAll(System.getProperty("line.separator"), "");
		String[] array = list.split(" ");
		int[] A = new int[k];
		
		for (String s : array) {
			s = s.replaceAll("\n", "");
			s = s.trim();
			A[Integer.parseInt(s)] += 1;
		}
		
		for (int i = 1; i < k; i++) {
			if (A[i] != 0) {
				int j = 0;
				while (j < A[i]) {
					writer.write(i + " ");
					j++;
				}
			}
		}
		writer.close();
	}
	
	static void heapSort() throws IOException {
		String[] array = null;
		
		try {
			int k = Integer.parseInt(Files.readAllLines(path).get(0));
			String list = Files.readString(path);
			list = list.substring(String.valueOf(k).length());
			list = list.replaceAll(System.getProperty("line.separator"), "");
			array = list.split(" ");
		} catch (Exception e) {
			return;
		}

		Writer writer = new FileWriter("out" + i + "b.txt");
		
		int length = array.length;
		for (int i = length / 2 - 1; i >= 0; i--) {
			heapify(array, length, i);
		}
		
		for (int i = length - 1; i > 0; i--) {
			int temp = Integer.parseInt(array[0]);
			array[0] = array[i];
			array[i] = String.valueOf(temp);
			heapify(array, i, 0);
		}
		
		for (String i : array) {
			writer.write(i + " ");
		}
		
		writer.close();
	}
	
	static void heapify(String[] arr, int length, int x) {
		int largestIndex = x;
		int leftIndex = 2 * x + 1;
		int rightIndex = 2 * x + 2;
		if (leftIndex < length && Integer.parseInt(arr[leftIndex]) > Integer.parseInt(arr[largestIndex])) {
			largestIndex = leftIndex;
		}
		if (rightIndex < length && Integer.parseInt(arr[rightIndex]) > Integer.parseInt(arr[largestIndex])) {
			largestIndex = rightIndex;
		}
		if (largestIndex != x) {
			int swap = Integer.parseInt(arr[x]);
			arr[x] = arr[largestIndex];
			arr[largestIndex] = String.valueOf(swap);
			heapify(arr, length, largestIndex);
		}
	}
}