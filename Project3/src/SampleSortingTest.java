
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SampleSortingTest <Item extends Comparable<Item>> {

private double score;
	
	public SampleSortingTest() {
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
	
	private void testInsertionSort(String filename) {

		ArrayList<Item> data = readData(filename);
		Sorting<Item> sort = new Sorting<Item>();
		ArrayList<Item> sortedData = sort.insertionSort(data);
				
		boolean sorted = true;
		if (data.size() != sortedData.size())
			sorted = false;
		else {
			for (int i = 1; i < sortedData.size(); i++ ) {
				if (sort.lessThan(sortedData, i-1, i) || sort.equal(sortedData, i-1, i))
					continue;
				else{
					sorted = false;
					break;
				}
			}
		}
		
		if (sorted){
			System.out.println("Function insertionsort correct for " + filename);
			score += 2;
		}
		else {
			System.out.println("Function insertionsort incorrect for " + filename);
		}
	}
	
	private void testMergeSort(String filename) {
		ArrayList<Item> data = readData(filename);
		Sorting<Item> sort = new Sorting<Item>();
		ArrayList<Item> sortedData = sort.mergeSort(data);
				
		boolean sorted = true;
		if (data.size() != sortedData.size())
			sorted = false;
		else {
			for (int i = 1; i < sortedData.size(); i++ ) {
				if (sort.lessThan(sortedData, i-1, i) || sort.equal(sortedData, i-1, i))
					continue;
				else{
					sorted = false;
					break;
				}
			}
		}
		if (sorted){
			System.out.println("Function mergesort correct for " + filename);
			score += 2;
		}
		else {
			System.out.println("Function mergesort incorrect for " + filename);
		}
	}
	
	public static void main(String[] args) {
		// This is a sample test code, which only test 3 cases (test1, test2, and test4)
        // We will test more cases on Vocareum when you submit your code.
		double totalScore = 0;
		try {
			SampleSortingTest<?> sortingTest = null;
			for (int i = 1; i <= 2; i++) {
				sortingTest = new SampleSortingTest<Integer>();
				sortingTest.testInsertionSort("test/test" + i + ".txt");
				sortingTest.testMergeSort("test/test" + i + ".txt");
				totalScore += sortingTest.score;
			}
			
			for (int i = 4; i <= 4; i++) {
				sortingTest = new SampleSortingTest<String>();
				sortingTest.testInsertionSort("test/test" + i + ".txt");
				sortingTest.testMergeSort("test/test" + i + ".txt");
				totalScore += sortingTest.score;
			}
			
		}
		finally {
			System.out.println("\nMax score for Sorting: " + 12);
			System.out.println("\nYour total score for Sorting: " + totalScore);
		}
	}

}
