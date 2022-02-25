import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Exercise1 {
	static int x = 1;
	public static void main(String[] args) throws IOException {
		while (x <= 5) {
			run();
			x++;
		}
	}
	public static void run() throws IOException {
		Writer writer = new FileWriter("out" + x + ".txt");
		Path path = Paths.get("in" + x + ".txt");
        String[] numbers = Files.readAllLines(path).get(2).split(" ");
        int target = Integer.parseInt(Files.readAllLines(path).get(1));
		String numArray = Arrays.toString(numbers).replaceAll(",", "");
		
        for (int i = 0; i < numbers.length; i++) {
        	for (int j = 0; j < numbers.length; j++) {
        		if (Integer.parseInt(numbers[i]) + Integer.parseInt(numbers[j]) == target) {
        			writer.write(String.valueOf(target));
            		writer.write("\n");
            		writer.write(numArray.substring(1, numArray.length() - 1));
            		writer.write("\n");
            		writer.write("Yes");
            		writer.write("\n");
            		writer.write(numbers[i] + "+" + numbers[j] + "=" + target);
            		writer.close();
            		return;
        		}
        	}
        }
        writer.write(String.valueOf(target));
		writer.write("\n");
		writer.write(numArray.substring(1, numArray.length() - 1));
		writer.write("\n");
		writer.write("No");
		writer.close();
	}
}