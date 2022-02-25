import java.util.Arrays;

public class Part0test {
    public static boolean doTest(String inputFile, String outputFile) {

        boolean result = true;

        SoccerTeam[] inteams = TestRoutines.parseFileToTeam(inputFile);

        // for (SoccerTeam team : inteams)
        // System.out.println(team);

        Arrays.sort(inteams, (a, b) -> -1 * a.compareTo(b));

        SoccerTeam[] outteams = TestRoutines.parseFileToTeam(outputFile);
        // System.out.println("-------888-----\n");

        // for (SoccerTeam club : inteams) {
        // System.out.println(club);
        // }

        // System.out.println("-------888-----\n");
        for (int i = 0; i < inteams.length; i++) {
            if (inteams[i].compareTo(outteams[i]) != 0) {
                System.out.println("Part 0 test failed");
                System.out.println("Expected:\n" + outteams[i]);
                System.out.println("Actual:\n" + inteams[i]);
                System.out.println("Have a look at " + outputFile + " to see how the teams should be sorted");
                return false;
            }
        }

        return result;
    }

    public static double test() {
        boolean result1 = doTest("testdata/part0test1input.txt", "testdata/part0test1output.txt");
        boolean result2 = doTest("testdata/part0test2input.txt", "testdata/part0test2output.txt");
        double mark = 0;
        if (result1) {
            mark += 10.0;
        }
        if (result2) {
            mark += 10.0;
        }
        double totalMark = 20.0;
        System.out.println("Total Score for Part 1: " + mark + "/" + totalMark);
        return mark;
    }

    public static void main(String[] args) {
        System.out.println("Testing Part 1:");
        test();
    }

}
