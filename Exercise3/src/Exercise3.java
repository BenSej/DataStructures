import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class Exercise3 {
	static int n = 0;
	static int l = 0;
	static int k = 0;

	public static void main(String[] args) throws IOException {
		
		Writer writer1 = new FileWriter("out1.txt");
		Writer writer2 = new FileWriter("out2.txt");
		Writer writer3 = new FileWriter("out3.txt");
		Scanner scanner = new Scanner(System.in);
		
		List<Double> numbers = new ArrayList<>();
		
		n = scanner.nextInt();
		k = scanner.nextInt();
		l = scanner.nextInt();
		
		int t = 0;
		long seed = 1;
		while (t < n) {
			seed *= 5;
			double random = Generator(seed);
			numbers.add(random);
			writer1.write(String.valueOf(random));
			writer1.write("\n");
			t++;
		}
		writer1.close();
		
		numbers = sortIncrease(numbers);
		t = 0;
		while (t < numbers.size()) {
			writer2.write(String.valueOf(numbers.get(t)));
			writer2.write("\n");
			t++;
		}
		writer2.close();
		
		numbers = sortDecrease(numbers);
		t = 0;
		while (t < numbers.size()) {
			writer3.write(String.valueOf(numbers.get(t)));
			writer3.write("\n");
			t++;
		}
		writer3.close();
		scanner.close();
		System.out.println("Finished");
	}
	
	public static List<Double> sortIncrease(List<Double> list) {
		
		for (int i = 0; i < list.size() - 1; i++) {
			int minIndex = i;
			
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(j) <= list.get(minIndex)) {
					minIndex = j;
				}
			}
			double temp = list.get(minIndex);
			list.set(minIndex, list.get(i));
			list.set(i, temp);
		}
		return list;
	}
	
	public static List<Double> sortDecrease(List<Double> list) {
		
		for (int i = 0; i < list.size() - 1; i++) {
			int maxIndex = i;
			
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(j) > list.get(maxIndex)) {
					maxIndex = j;
				}
			}
			double temp = list.get(maxIndex);
			list.set(maxIndex, list.get(i));
			list.set(i, temp);
		}
		return list;
	}
	
	public static double Generator(long seed) {
		Random generator = new Random(seed);
		double random = generator.nextDouble() * (l - k) + k;
		return random;
	}
}
