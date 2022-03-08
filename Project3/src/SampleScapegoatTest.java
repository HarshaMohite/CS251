
import java.io.*;
import java.util.ArrayList;

public class SampleScapegoatTest {

	int score = 0;

	public SampleScapegoatTest() {
		score = 0;
	}

	public ArrayList<String> readInputs(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		ArrayList<String> inputs = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			inputs.add(line.trim());
		}
		br.close();
		return inputs;
	}

	public void testRebuild() {
		Scapegoat tree = new Scapegoat();
		Scapegoat.Node[] nodes = new Scapegoat.Node[10];
		for (int i = 0; i < 10; i++) {
			nodes[i] = tree.new Node(new T(i+1), null, null, null);
		}
		nodes[4].left = nodes[1]; nodes[1].parent = nodes[4];
		nodes[1].left = nodes[0]; nodes[0].parent = nodes[1];
		nodes[1].right = nodes[2]; nodes[2].parent = nodes[1];
		nodes[2].right = nodes[3]; nodes[3].parent = nodes[2];
		nodes[4].right = nodes[6]; nodes[6].parent = nodes[4];
		nodes[6].left = nodes[5]; nodes[5].parent = nodes[6];
		nodes[6].right = nodes[7]; nodes[7].parent = nodes[6];
		nodes[7].right = nodes[8]; nodes[8].parent = nodes[7];
		nodes[8].right = nodes[9]; nodes[9].parent = nodes[8];

		System.out.println("Inbalanced BST");
		System.out.println("preorder: " + tree.preorder(nodes[4]));
		System.out.println("inorder: " + tree.inorder(nodes[4]));

		String preorderSolution = "preorder: [[data=6 0], [data=3 0], [data=2 0], [data=1 0], [data=5 0], [data=4 0], "
				+ "[data=9 0], [data=8 0], [data=7 0], [data=10 0]]";
		String inorderSolution = "inorder: [[data=1 0], [data=2 0], [data=3 0], [data=4 0], [data=5 0], [data=6 0], "
				+ "[data=7 0], [data=8 0], [data=9 0], [data=10 0]]";
		Scapegoat.Node newRoot = tree.rebuild(nodes[4]);
		System.out.println("After rebuild");
		String preorderResult = "preorder: " + tree.preorder(newRoot);
		String inorderResult = "inorder: " + tree.inorder(newRoot);
		System.out.println(preorderResult);
		System.out.println(inorderResult);

		if ((preorderResult + inorderResult).equals(preorderSolution + inorderSolution)) {
			System.out.println("Rebuild correct");
			System.out.println("Score for rebuild: 5\n");
			this.score += 5;
		}
		else {
			System.out.println("Rebuild incorrect");
			System.out.println("Score for rebuild: 0\n");
		}

	}

	public void testScapegoat(String filename) throws IOException {
		ArrayList<String> inputs = readInputs(filename);
		String name = filename.trim().split("\\.")[0];
		BufferedReader br = new BufferedReader(new FileReader(name + "_output.txt"));
		ArrayList<String> outputs = new ArrayList<String>();
		String line;
		while ((line = br.readLine()) != null) {
			if (line.contains("preorder")) {
				outputs.add(line.trim() + br.readLine().trim());
			}
		}
		br.close();

		int correct = 1;
		Scapegoat tree = new Scapegoat();
		int cnt = 0;
		for (String input : inputs) {
			String[] string = input.trim().split(" ");
			if (string[0].equals("add")) {
				tree.add(new T(Integer.parseInt(string[1])));
			}
			else if (string[0].equals("remove")) {
				tree.remove(new T(Integer.parseInt(string[1])));
			}
			else if (string[0].equals("print")) {
				String result = "preorder: " + tree.preorder(tree.root()) + "inorder: " + tree.inorder(tree.root());
				if (! result.equals(outputs.get(cnt))) {
					correct = 0;
				}
				cnt += 1;
			}
		}
		assert cnt == outputs.size();
		this.score += correct * 10;
		if (correct == 1) {
			System.out.println("Test for " + filename + " is correct.");
			System.out.println("Score for " + filename + ": " + correct * 10);
		}
		else {
			System.out.println("Test for " + filename + " is incorrect.");
			System.out.println("Score for " + filename + ": " + correct * 10);
		}
	}

	public static void main(String[] args) throws IOException {
		// This is a sample test code, which only test 3 cases (rebuild, test6, and test7)
		// We will test more cases on Vocareum when you submit your code.
		SampleScapegoatTest test = new SampleScapegoatTest();
		// test.output("test6.txt");
		// test.output("test7.txt");

		try {
			test.testRebuild();
			for (int i = 6; i <= 7; i++) {
				test.testScapegoat("test/test" + i + ".txt");
			}
		}
		finally {
			System.out.println("\nMax score for Scapegoat: 25");
			System.out.println("\nTotal score for Scapegoat: " + test.score);
		}

	}

}
