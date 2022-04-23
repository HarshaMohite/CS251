import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class Test {

    private double score;

    public Test() {
        score = 0;
    }

    private String[] matrixTarget;
    private int ansPattern;
    private int numMatrix;

    String[] readDataPatternIn(String filename) {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            if (filename.contains(".in") && filename.contains("pattern")) {
                String S = sc.nextLine();
                String p = sc.nextLine();
                String input[] = new String[2];
                input[0] = S;
                input[1] = p;
                sc.close();
                return input;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int readDataPatternOut(String filename) {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            if (filename.contains(".out") && filename.contains("pattern")) {

                // first line : answer
                String temp = sc.nextLine();
                this.ansPattern = Integer.parseInt(temp);
                sc.close();

                return -1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private char[][] readDataMatrixIn(String filename) {
        File file = new File("./" + filename);
        try {
            Scanner sc = new Scanner(file);
            if (filename.contains(".in") && filename.contains("matrix")) {
                int n;
                String temp = sc.nextLine();
                n = Integer.parseInt(temp);

                char[][] matrix = new char[n][n];

                for (int i = 0; i < n; i++) {
                    temp = sc.nextLine();

                    for (int j = 0; j < n; j++)
                        matrix[i][j] = temp.charAt(j);
                }

                temp = sc.nextLine();
                this.numMatrix = Integer.parseInt(temp);
                this.matrixTarget = new String[this.numMatrix];
                for (int l = 0; l < this.numMatrix; l++) {
                    temp = sc.nextLine();
                    this.matrixTarget[l] = temp;
                }

                sc.close();

                return matrix;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int[][] readDataMatrixOut(String filename) {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            if (filename.contains(".out") && filename.contains("matrix")) {
                ArrayList<Integer> test = new ArrayList<Integer>();
                while (sc.hasNextLine()) {
                    String temp = sc.nextLine();
                    test.add(Integer.parseInt(temp));
                }
                sc.close();
                int[][] output = new int[test.size() / 3][3];
                for (int i = 0; i < test.size(); i++)
                    output[i / 3][i % 3] = test.get(i);
                return output;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String lastOccurrenceFunction(String filename) {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            if (filename.contains(".out") && filename.contains("pattern")) {
                // first line : answer
                String temp = sc.nextLine();
                sc.close();
                return temp;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String genKMPTable(String filename) {

        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            if (filename.contains(".out") && filename.contains("pattern")) {
                // first line : answer
                String temp = sc.nextLine();
                sc.close();
                return temp;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    void testPattern(String filename) {
        String arr, target;
        String input[];
        input = readDataPatternIn(filename + ".in");
        arr = input[0];
        target = input[1];
        int Ans = readDataPatternOut(filename + ".out");

        int resKMP, resBM;
        int[] tableKMP;
        String ansTableKMP;
        ansTableKMP = genKMPTable(filename + "_KMP.out");
        try {
            tableKMP = KMP.genKMPTable(target);
        } catch (StringIndexOutOfBoundsException e) {
            tableKMP = null;
            score += 0;
        }
        try {
            resKMP = KMP.find(arr, target);
        } catch (StringIndexOutOfBoundsException e) {
            resKMP = -2;
            score += 0;
        }
        String ansTableBM = lastOccurrenceFunction(filename + "_BM.out");
        Hashtable<Character, Integer> tableBM;
        try {
            tableBM = BoyerMoore.lastOccurrenceFunction(target);
        } catch (StringIndexOutOfBoundsException e) {
            tableBM = null;
            score += 0;
        }
        try {
            resBM = BoyerMoore.find(arr, target);
        } catch (StringIndexOutOfBoundsException e) {
            resBM = -2;
            score += 0;
        }

        if (ansTableKMP.equals(Arrays.toString(tableKMP))) {
            System.out.println("Table KMP correct for " + filename);
            score += 5;
        } else {
            System.out.println("Table KMP incorrect for " + filename);
        }

        if (resKMP == this.ansPattern) {
            System.out.println("KMP answer correct for " + filename);
            score += 5;
        } else {
            System.out.println("KMP answer incorrect for " + filename);
        }

        if (ansTableBM.equals(tableBM.toString())) {
            System.out.println("Table BM correct for " + filename);
            score += 5;
        } else {
            System.out.println("Table BM incorrect for " + filename);
        }

        if (resBM == this.ansPattern) {
            System.out.println("BM answer correct for " + filename);
            score += 5;
        } else {
            System.out.println("BM answer incorrect for " + filename);
        }
    }

    private void testMatrix(String filename) {
        char[][] matrix;
        matrix = readDataMatrixIn(filename + ".in");

        int[][] Ans = readDataMatrixOut(filename + ".out");
        Main_pattern model = new Main_pattern();
        for (int l = 0; l < this.numMatrix; l++) {
            int[] out = model.find(matrix, this.matrixTarget[l]);
            if (Arrays.equals(Ans[l], out)) {
                System.out.println("Function find Matrix correct for " + filename + " task " + String.valueOf(l));
                score += 5;
            } else {
                System.out.println("Function findMatrix incorrect for " + filename);
                System.out.println("Your output");
                //            System.out.println(out);
                for (int i = 0; i < 3; i++) {
                    System.out.print(out[i] + " ");
                }

                System.out.println("\nCorrect output");
                //            System.out.println(Ans);
                for (int i = 0; i < 3; i++) {
                    System.out.print(Ans[l][i] + " ");
                }
                System.out.println("\n");

            }
        }
    }

    public static void main(String[] args) {
        double totalScore = 0;
        try {
            Test pattern = null;
            for (int i = 1; i < 3; i++) {
                pattern = new Test();
                System.out.println("Pattern Test " + i);
                pattern.testPattern("testFiles/pattern_test" + i);
                System.out.println("Score: " + pattern.score + "\n");
                totalScore += pattern.score;
            }

            for (int i = 1; i < 3; i++) {
                pattern = new Test();
                System.out.println("Matrix Test " + i);
                pattern.testMatrix("testFiles/matrix_test" + i);
                System.out.println("Score: " + pattern.score + "\n");
                totalScore += pattern.score;
            }

        } finally {
            System.out.println("\nMax score for Pattern: " + 100);
            System.out.println("\nYour total score for Pattern: " + totalScore);
        }
    }

}
