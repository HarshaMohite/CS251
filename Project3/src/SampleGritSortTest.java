
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SampleGritSortTest <Item extends Comparable<Item>> {

	private double score;

	public SampleGritSortTest() {
		score = 0;
	}

	private ArrayList<Item> readData(String filename) {
		File file = new File(filename);
		try {
			Scanner sc = new Scanner(file);
			if (filename.contains("test/test1.txt") || filename.contains("test/test2.txt") || filename.contains("test/test3.txt")) {
				ArrayList<Integer> test = new ArrayList<Integer>();
				while (sc.hasNextLine()) {
					String temp = sc.nextLine();
					test.add(Integer.parseInt(temp));
				}
				sc.close();
				return (ArrayList<Item>) test;
			}
			else {
				ArrayList<String> test = new ArrayList<String>();
				while (sc.hasNextLine()) {
					String temp = sc.nextLine();
					test.add(temp);
				}
				sc.close();
				return (ArrayList<Item>) test;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ArrayList<ArrayList<Item>> readChunks(String filename) throws NumberFormatException, IOException{
		ArrayList<ArrayList<Item>> chunks = new ArrayList<ArrayList<Item>>();
		String name = filename.split("\\.")[0];
		BufferedReader br = new BufferedReader(new FileReader(name + "_chunks.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			String[] strings = line.trim().split(" ");
			if (filename.contains("test/test1.txt") || filename.contains("test/test2.txt") || filename.contains("test/test3.txt")) {
				ArrayList<Integer> chunk = new ArrayList<Integer>();
				for (String string : strings)
					chunk.add(Integer.parseInt(string));
				chunks.add((ArrayList<Item>) chunk);
			}
			else {
				ArrayList<String> chunk = new ArrayList<String>();
				for (String string : strings)
					chunk.add(string);
				chunks.add((ArrayList<Item>) chunk);
			}
		}
		br.close();
		return chunks;
	}

	private HashMap<Integer, ArrayList<ArrayList<Item>>> readBuckets(String filename) throws NumberFormatException, IOException{
		HashMap<Integer, ArrayList<ArrayList<Item>>> buckets = new HashMap<Integer, ArrayList<ArrayList<Item>>>();
		String name = filename.split("\\.")[0];
		BufferedReader br = new BufferedReader(new FileReader(name + "_buckets.txt"));
		String line;
		int key = 0;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.length() >= 5 && line.substring(0, 5).equals("size:")) {
				key = Integer.parseInt(line.substring(5).trim());
				buckets.put(key, new ArrayList<ArrayList<Item>>());
				continue;
			}
			String[] strings = line.split(" ");
			if (filename.contains("test/test1.txt") || filename.contains("test/test2.txt") || filename.contains("test/test3.txt")) {
				ArrayList<Integer> chunk = new ArrayList<Integer>();
				for (String string : strings)
					chunk.add(Integer.parseInt(string));
				buckets.get(key).add((ArrayList<Item>) chunk);
			}
			else {
				ArrayList<String> chunk = new ArrayList<String>();
				for (String string : strings)
					chunk.add(string);
				buckets.get(key).add((ArrayList<Item>) chunk);
			}
		}
		br.close();
		return buckets;
	}

	private HashMap<Integer, ArrayList<Item>> readSortedBuckets(String filename) throws IOException {
		HashMap<Integer, ArrayList<Item>> sortedBuckets = new HashMap<Integer, ArrayList<Item>>();
		String name = filename.split("\\.")[0];
		BufferedReader br = new BufferedReader(new FileReader(name + "_sorted_buckets.txt"));
		String line;
		int key = 0;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.length() >= 5 && line.substring(0, 5).equals("size:")) {
				key = Integer.parseInt(line.substring(5).trim());
				sortedBuckets.put(key, new ArrayList<Item>());
				continue;
			}
			String[] strings = line.split(" ");
			if (filename.contains("test/test1.txt") || filename.contains("test/test2.txt") || filename.contains("test/test3.txt")) {
				ArrayList<Integer> chunk = new ArrayList<Integer>();
				for (String string : strings)
					chunk.add(Integer.parseInt(string));
				sortedBuckets.put(key, (ArrayList<Item>) chunk);
			}
			else {
				ArrayList<String> chunk = new ArrayList<String>();
				for (String string : strings)
					chunk.add(string);
				sortedBuckets.put(key, (ArrayList<Item>) chunk);
			}
		}
		br.close();
		return sortedBuckets;
	}

	private boolean checkChunks(ArrayList<ArrayList<Item>> chunks, ArrayList<ArrayList<Item>> solution) {
		if (chunks.size() != solution.size())
			return false;
		for (int i = 0; i < chunks.size(); i++) {
			if (! chunks.get(i).equals(solution.get(i)))
				return false;
		}
		return true;
	}

	private boolean checkBuckets(HashMap<Integer, ArrayList<ArrayList<Item>>> buckets,
			HashMap<Integer, ArrayList<ArrayList<Item>>> solution) {
		if (buckets.size() != solution.size())
			return false;
		for (Integer k : solution.keySet()) {
			if (! buckets.containsKey(k))
				return false;
			if (! buckets.get(k).equals(solution.get(k)))
				return false;
		}
		return true;
	}

	private boolean checkSortedBuckets(HashMap<Integer, ArrayList<Item>> buckets,
			HashMap<Integer, ArrayList<Item>> solution) {
		if (buckets.size() != solution.size())
			return false;
		for (Integer k : solution.keySet()) {
			if (! buckets.containsKey(k))
				return false;
			if (! buckets.get(k).equals(solution.get(k)))
				return false;
		}
		return true;
	}

	private void testGridSort(String filename) throws IOException {
		ArrayList<Item> data = readData(filename);
		GritSort<Item> sort = new GritSort<Item>();

		ArrayList<Item> dataClone = (ArrayList<Item>) data.clone();

		ArrayList<ArrayList<Item>> chunks = sort.makeChunks(data);
		ArrayList<ArrayList<Item>> chunksSolution = readChunks(filename);
		if (checkChunks(chunks, chunksSolution)) {
			System.out.println("Function makeChunks correct for " + filename);
			score += 1;
		}
		else
			System.out.println("Function makeChunks incorrect for " + filename);

		HashMap<Integer, ArrayList<ArrayList<Item>>> buckets = sort.makeBuckets(chunks);
		HashMap<Integer, ArrayList<ArrayList<Item>>> bucketsSolution = readBuckets(filename);
		if (checkBuckets(buckets, bucketsSolution)) {
			System.out.println("Function makeBuckets correct for " + filename);
			score += 1.5;
		}
		else
			System.out.println("Function makeBuckets incorrect for " + filename);

		HashMap<Integer, ArrayList<Item>> sortedBuckets = new HashMap<Integer, ArrayList<Item>>();
		HashMap<Integer, ArrayList<Item>> sortedBucketsSolution = readSortedBuckets(filename);
		for (Integer k : buckets.keySet())
			sortedBuckets.put(k, sort.mergeChunk(buckets.get(k)));
		if (checkSortedBuckets(sortedBuckets, sortedBucketsSolution)) {
			System.out.println("Function mergeChunk correct for " + filename);
			score += 1.5;
		}
		else
			System.out.println("Function mergeChunk incorrect for " + filename);

		ArrayList<Item> answer = (ArrayList<Item>) dataClone.clone();
		answer.sort(null);
		ArrayList<Item> result = sort.grit(dataClone);
		if (result.equals(answer)){
			System.out.println("Function grit correct for " + filename);
			score += 1;
		}
		else {
			System.out.println("Function grit incorrect for " + filename);
		}
	}

	public static void main(String[] args) {
		// This is a sample test code, which only test 3 cases (test1, test2, and test4)
        // We will test more cases on Vocareum when you submit your code.
		double totalScore = 0;
		try {
			SampleGritSortTest<?> gritTest = null;
			for (int i = 1; i <= 2; i++) {
				gritTest = new SampleGritSortTest<Integer>();
				gritTest.testGridSort("test/test" + i + ".txt");
				totalScore += gritTest.score;
			}

			for (int i = 4; i <= 4; i++) {
				gritTest = new SampleGritSortTest<String>();
				gritTest.testGridSort("test/test" + i + ".txt");
				totalScore += gritTest.score;
			}

		}
		catch (IOException e) {

		}
		finally {
			System.out.println("\nMax score for GritSort: " + 15);
			System.out.println("\nYour total score for GritSort: " + totalScore);
		}

	}

}