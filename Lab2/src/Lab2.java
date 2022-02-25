import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lab2 {
	
	static Path path = null;
	static int i = 0;
	
	public static void main(String[] args) throws IOException {
		
		while (i <= 100) {
			path = Paths.get("in_abc" + i + ".txt");
			sort();
			heapSort();
			i++;
		}
		System.out.println("done");
	}
	
	static void sort() throws IOException {
		String stringK = "";
		int k = 0;
		
		try {
			stringK = Files.readAllLines(path).get(0).substring(10, 13);
			stringK.trim();
			List<String> temp = toInt(stringK.toCharArray());
			String toK = "";
			for (String s : temp) {
				toK += s;
			}
			k = Integer.parseInt(toK);
		} catch (Exception e) {
			return;
		}
		
		Writer writer = new FileWriter("out_abc" + i + "a.txt");
		
		String list = Files.readString(path).toLowerCase();
		list = list.substring(13);
		list = list.replaceAll("\n", "");
		list = list.trim();
		String[] array = list.split(" ");
		int[] A = new int[k + 1];
		
		for (String s : array) {
			char[] word = s.toCharArray();
			List<String> intWord = toInt(word);
			String temp = "";
			for (String t : intWord) {
				temp += t;
			}
			String index = temp.toString();
			while (index.length() < 6) {
				index += "0";
			}
			int ind = Integer.parseInt(index);
			A[ind] += 1;
		}
		for (int i = 1; i <= k; i++) {
			if (A[i] != 0) {
				int j = 0;
				while (j < A[i]) {
					String index = String.valueOf(i);
					if (index.length() < 6 && index.length() % 2 != 0) {
						index  = "0" + index;
					}
					String[] temp = index.split("(?<=\\G..)");
					List<String> ind = Arrays.asList(temp);
					char[] toPrint = toString(ind);
					int t = 0;
					for (char c : toPrint) {
						if (t == toPrint.length - 1) {
							writer.write(c);
							writer.write(" ");
						} else {
							writer.write(c);
							t++;
						}
					}
					j++;
				}
			}
		}
		writer.close();
	}
	
	static void heapSort() throws IOException {
		String[] array = null;
		
		try {
			String list = Files.readString(path).toLowerCase();
			list = list.substring(13);
			list = list.replaceAll("\n", "");
			list = list.trim();
			list = list.replaceAll("  ", " ");
			list = list.replaceAll("   ", " ");
			array = list.split(" ");
		} catch (Exception e) {
			return;
		}

		Writer writer = new FileWriter("out_abc" + i + "b.txt");
		
		List<String> intWords = new ArrayList<>();
		
		for (String s : array) {
			if (s.contentEquals("")) {
				continue;
			}
			char[] word = s.toCharArray();
			List<String> intWord = toInt(word);
			String temp = "";
			for (String t : intWord) {
				temp += t;
			}
			String index = temp.toString();
			while (index.length() < 6) {
				index += "0";
			}
			intWords.add(index);
		}
		
		
		String[] readyToSort = intWords.toArray(new String[intWords.size()]);
		
		
		int length = readyToSort.length;
		for (int i = length / 2 - 1; i >= 0; i--) {
			heapify(readyToSort, length, i);
		}
		
		for (int i = length - 1; i > 0; i--) {
			int temp = Integer.parseInt(readyToSort[0]);
			readyToSort[0] = readyToSort[i];
			readyToSort[i] = String.valueOf(temp);
			heapify(readyToSort, i, 0);
		}
		
		
		List<String> print = Arrays.asList(readyToSort);
		
		for (int i = 0; i < print.size(); i++) {
			if (print.get(i).length() < 6 && print.get(i).length() % 2 != 0) {
				print.set(i, "0" + print.get(i));
			}
		}
		
		for (String s : print) {
			String[] temp = s.split("(?<=\\G..)");
			List<String> ind = Arrays.asList(temp);
			char[] toPrint = toString(ind);
			int t = 0;
			for (char c : toPrint) {
				if (t == toPrint.length - 1) {
					writer.write(c);
					writer.write(" ");
				} else {
					writer.write(c);
					t++;
				}
			}
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
	
	static List<String> toInt(char[] arr) {
		List<String> list = new ArrayList<String>();
		for (char c : arr) {
			if (c == 'a') {
				list.add("01");
			} else if (c == 'b') {
				list.add("02");
			} else if (c == 'c') {
				list.add("03");
			} else if (c == 'd') {
				list.add("04");
			} else if (c == 'e') {
				list.add("05");
			} else if (c == 'f') {
				list.add("06");
			} else if (c == 'g') {
				list.add("07");
			} else if (c == 'h') {
				list.add("08");
			} else if (c == 'i') {
				list.add("09");
			} else if (c == 'j') {
				list.add("10");
			} else if (c == 'k') {
				list.add("11");
			} else if (c == 'l') {
				list.add("12");
			} else if (c == 'm') {
				list.add("13");
			} else if (c == 'n') {
				list.add("14");
			} else if (c == 'o') {
				list.add("15");
			} else if (c == 'p') {
				list.add("16");
			} else if (c == 'q') {
				list.add("17");
			} else if (c == 'r') {
				list.add("18");
			} else if (c == 's') {
				list.add("19");
			} else if (c == 't') {
				list.add("20");
			} else if (c == 'u') {
				list.add("21");
			} else if (c == 'v') {
				list.add("22");
			} else if (c == 'w') {
				list.add("23");
			} else if (c == 'x') {
				list.add("24");
			} else if (c == 'y') {
				list.add("25");
			} else if (c == 'z') {
				list.add("26");
			}
		}
		return list;
	}
	
	static char[] toString(List<String> x) {
		char[] word = new char[x.size()];
		for (int i = 0; i < x.size(); i++) {
			if (x.get(i).contentEquals("01")) {
				word[i] = 'a';
			} else if (x.get(i).contentEquals("02")) {
				word[i] = 'b';
			} else if (x.get(i).contentEquals("03")) {
				word[i] = 'c';
			} else if (x.get(i).contentEquals("04")) {
				word[i] = 'd';
			} else if (x.get(i).contentEquals("05")) {
				word[i] = 'e';
			} else if (x.get(i).contentEquals("06")) {
				word[i] = 'f';
			} else if (x.get(i).contentEquals("07")) {
				word[i] = 'g';
			} else if (x.get(i).contentEquals("08")) {
				word[i] = 'h';
			} else if (x.get(i).contentEquals("09")) {
				word[i] = 'i';
			} else if (x.get(i).contentEquals("10")) {
				word[i] = 'j';
			} else if (x.get(i).contentEquals("11")) {
				word[i] = 'k';
			} else if (x.get(i).contentEquals("12")) {
				word[i] = 'l';
			} else if (x.get(i).contentEquals("13")) {
				word[i] = 'm';
			} else if (x.get(i).contentEquals("14")) {
				word[i] = 'n';
			} else if (x.get(i).contentEquals("15")) {
				word[i] = 'o';
			} else if (x.get(i).contentEquals("16")) {
				word[i] = 'p';
			} else if (x.get(i).contentEquals("17")) {
				word[i] = 'q';
			} else if (x.get(i).contentEquals("18")) {
				word[i] = 'r';
			} else if (x.get(i).contentEquals("19")) {
				word[i] = 's';
			} else if (x.get(i).contentEquals("20")) {
				word[i] = 't';
			} else if (x.get(i).contentEquals("21")) {
				word[i] = 'u';
			} else if (x.get(i).contentEquals("22")) {
				word[i] = 'v';
			} else if (x.get(i).contentEquals("23")) {
				word[i] = 'w';
			} else if (x.get(i).contentEquals("24")) {
				word[i] = 'x';
			} else if (x.get(i).contentEquals("25")) {
				word[i] = 'y';
			} else if (x.get(i).contentEquals("26")) {
				word[i] = 'z';
			}
		}
		return word;
	}
}