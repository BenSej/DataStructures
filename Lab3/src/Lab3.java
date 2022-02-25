
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lab3 {
	
	static int tableSize = 191;
	static int doubleFactor = 181;
	static Vector<Integer> data = new Vector<Integer>();
	static FileWriter fout;
	
public static List<Integer> sortIncrease(List<Integer> list) {
		
		for (int i = 0; i < list.size() - 1; i++) {
			int minIndex = i;
			
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(j) <= list.get(minIndex)) {
					minIndex = j;
				}
			}
			int temp = list.get(minIndex);
			list.set(minIndex, list.get(i));
			list.set(i, temp);
		}
		return list;
	}
	
	public static List<Integer> sortDecrease(List<Integer> list) {
		
		for (int i = 0; i < list.size() - 1; i++) {
			int maxIndex = i;
			
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(j) > list.get(maxIndex)) {
					maxIndex = j;
				}
			}
			int temp = list.get(maxIndex);
			list.set(maxIndex, list.get(i));
			list.set(i, temp);
		}
		return list;
	}
	
	public static void testKeyValue(String description, HashInterface<Integer,Integer> hashTable, Integer key, Integer value) throws NumberFormatException, IOException {
		
		Integer previousCollisions = hashTable.getCollisions();
		hashTable.put(key, value);
		Integer retrievedValue = (Integer) hashTable.get(key);
		String retrievedText = "True";
		
		if (retrievedValue == null) {
			retrievedText = "False";
		}
		
		fout.write(key + " : " + value + " -> " + retrievedText + ", collisions " + (hashTable.getCollisions() - previousCollisions) + "\n");
		
		if (retrievedValue == null || !retrievedValue.equals(value)) {
			
			fout.write("Retrieved value " + retrievedText + " does not match stored value " + value + " for key " + key + "\n");
			throw new RuntimeException("value mismatch");
		}
	}
	
	public static void testInputKey (Integer key, HashInterface<Integer, Integer> lph, HashInterface<Integer, Integer> qph, HashInterface<Integer, Integer> dhph) throws NumberFormatException, IOException {
		
		Integer value = key * 2;
		
		testKeyValue("Linear", lph, key, value);
		testKeyValue("Quadratic", qph, key, value);
		testKeyValue("Double", dhph, key, value);
		fout.write("\n");
	}
	
	public static void testData(String description) throws NumberFormatException, IOException {
		
		fout.write("*** " + description + " Start *** " + "\n\n");
		LinearProbingHash<Integer, Integer> lph = new LinearProbingHash<Integer, Integer>(tableSize);
		QuadraticProbingHash<Integer, Integer> qph = new QuadraticProbingHash<Integer, Integer>(tableSize); 
		DoubleHashingProbingHash<Integer, Integer> dhph = new DoubleHashingProbingHash<Integer,Integer>(tableSize, doubleFactor);
		
		for (Integer key : data) {
			testInputKey(key, lph, qph, dhph);
		}
		
		fout.write("Linear " + lph.getCollisions() + " collisions\n");
		fout.write("Quadratic " + qph.getCollisions() + " collisions" + "\n");
		fout.write("Double " + dhph.getCollisions() + " collisions" + "\n");
		fout.write("\n*** " + description + " End ***" + "\n\n");

	}
	
	public static void readData (String inputFile) throws IOException {
		Path path = Paths.get(inputFile);
		String input = Files.readString(path);
		input = input.replaceAll("\n", "");
		String[] array = input.split(" ");
		
		for (String s : array) {
			data.add(Integer.valueOf(s));
		}
	}
	
	public static void testFile(String inputFilename, String outputFilename) throws IOException {
		System.out.println("Input file " + inputFilename + ", output file " + outputFilename + "\n");
		
		readData(inputFilename);
		fout = new FileWriter(outputFilename);
		testData("Random Order");
		
		List<Integer> list = Collections.list(data.elements());
		list = sortIncrease(list);
		data = new Vector<Integer>(list);
				
		testData("Ascending Order");
		
		list = sortDecrease(list);
		data = new Vector<Integer>(list);
		
		testData("Descending Order");
		data.removeAllElements();
		fout.close();
		System.out.println("Done" + "\n");
	}
	
	public static void main(String[] args) throws IOException {
		testFile("in150.txt", "out150.txt");
		testFile("in160.txt", "out160.txt");
		testFile("in170.txt", "out170.txt");
	}
}
