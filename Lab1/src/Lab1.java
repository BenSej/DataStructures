import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Lab1 {
	
	static int x = 1;
	
	public static void main(String[] args) throws IOException {
		while (x <= 5) {
			run();
			x++;
		}
	}
	
	static void run() throws IOException {
		Writer writer = new FileWriter("out" + x + ".txt");
		Path path = Paths.get("in" + x + ".txt");
		int target = Integer.parseInt(Files.readAllLines(path).get(1));
		
		String[] numbers = Files.readAllLines(path).get(2).split(" ");
		
		String originalArray = Arrays.toString(numbers).replaceAll(",", "");
		String unsortedArray = originalArray.substring(1, originalArray.length() - 1);
		
		sort(numbers);
		
		String numArray = Arrays.toString(numbers).replaceAll(",", "");
		String sortedArray = numArray.substring(1, numArray.length() - 1);
		
		int leftIndex = 0;
		int rightIndex = numbers.length - 1;
		while (leftIndex < rightIndex) {
			int sum = Integer.parseInt(numbers[leftIndex]) + Integer.parseInt(numbers[rightIndex]);
			if (sum == target) {
				writer.write(String.valueOf(target));
				writer.write("\n");
				writer.write(unsortedArray);
        		writer.write("\n");
        		writer.write(sortedArray);
        		writer.write("\n");
        		writer.write("Yes");
        		writer.write("\n");
        		writer.write(numbers[leftIndex] + "+" + numbers[rightIndex] + "=" + target);
        		writer.close();
        		return;
			} else if (sum < target) {
				leftIndex++;
			} else {
				rightIndex--;
			}
		}
		writer.write(String.valueOf(target));
		writer.write("\n");
		writer.write(unsortedArray);
		writer.write("\n");
		writer.write(sortedArray);
		writer.write("\n");
		writer.write("No");
		writer.close();
		return;
	}
	
	static void sort(String[] arr) {
		int length = arr.length;
		for (int i = length / 2 - 1; i >= 0; i--) {
			heapify(arr, length, i);
		}
		for (int i = length - 1; i > 0; i--) {
			int temp = Integer.parseInt(arr[0]);
			arr[0] = arr[i];
			arr[i] = String.valueOf(temp);
			heapify(arr, i, 0);
		}
	}
	
	static void heapify(String[] arr, int length, int x) {
		int largest = x;
		int left = 2 * x + 1;
		int right = 2 * x + 2;
		if (left < length && Integer.parseInt(arr[left]) > Integer.parseInt(arr[largest])) {
			largest = left;
		}
		if (right < length && Integer.parseInt(arr[right]) > Integer.parseInt(arr[largest])) {
			largest = right;
		}
		if (largest != x) {
			int swap = Integer.parseInt(arr[x]);
			arr[x] = arr[largest];
			arr[largest] = String.valueOf(swap);
			heapify(arr, length, largest);
		}
	}
}