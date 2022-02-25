public class Part12test {

    private final SoccerTeamHashTable table;

    private final int capacity;

    public Part12test(int capacity) {
        this.table = new SoccerTeamHashTable(capacity);
        this.capacity = capacity;
    }

    public static SoccerTeam[] getTeams(String inputFile) {
        SoccerTeam[] teamsExpected = TestRoutines.parseFileToTeam(inputFile);

        return teamsExpected;

    }

    public static double doHashTableTest(String inputFile, String outputFile) {
        SoccerTeam[] teamsExpected = TestRoutines.parseFileToTeam(inputFile);
        int teamNo = teamsExpected.length;
        Part12test test = new Part12test(teamNo);

        double total = 0;
        double expected = (double) teamNo * 3.0;

        for (int i = 0; i < teamNo; i++) {
            test.table.put(teamsExpected[i]);
            SoccerTeam teamGot = test.table.get(teamsExpected[i].getName());
            if (teamGot.equals(teamsExpected[i]) == false) {
                System.out.println("Get and put test failed");
                return (3.0 * total) / expected;
            }
            total += 1.0;
            test.table.remove(teamsExpected[i].getName());
            teamGot = test.table.get(teamsExpected[i].getName());
            if (teamGot != null) {
                System.out.println("Get and remove test failed");
                return (3.0 * total) / expected;

            }
            total += 1.0;
            test.table.put(teamsExpected[i]);
            teamGot = test.table.get(teamsExpected[i].getName());
            if (teamGot.equals(teamsExpected[i]) == false) {
                System.out.println("remove,put, get test failed");
                return (3.0 * total) / expected;
            }
            total += 1.0;

        }
        String[] hashlines = test.table.toString().split("\n");
        String[] expectedHashTableLines = TestRoutines.parseFileToLine(outputFile);
        int l = hashlines.length;

        expected = expected + (double) l * 2.0;

        // System.out.println(test.table);
        for (int i = 0; i < l; i++) {
            String expectedS = expectedHashTableLines[i].trim();
            String actual = hashlines[i].trim();
            // System.out.println(expectedS + "..");
            // System.out.println(actual);
            if (expectedS.compareTo(actual) != 0) {
                System.out.println("Hash table not matched:failed");
                // return (5.0 * total) / expected;
            }
            total += 2.0;
        }

        System.out.println("Hash Table test passed");
        return (5.0 * total) / expected;
    }

    public static double doHashTest(String inputFile, String outputFile) {
        SoccerTeam[] teamsExpected = TestRoutines.parseFileToTeam(inputFile);
        int teamNo = teamsExpected.length;
        Part12test test = new Part12test(teamNo);
        String[] actualOutputs = new String[teamNo];
        String[] expectedOutputs = TestRoutines.parseFileToLine(outputFile);

        double total = 0;
        double expected = (double) teamNo * 5.0;
        for (int i = 0; i < teamNo; i++) {
            int hashCoded1 = test.table.evaluateExpression(test.table.getExpression((teamsExpected[i].getName())));
            int hashCoded2 = test.table.getHash(teamsExpected[i].getName());
            String expression = test.table.getExpression((teamsExpected[i].getName()));
            actualOutputs[i] = expression + "," + hashCoded1 + "," + hashCoded2;
            if (expectedOutputs[i].compareTo(actualOutputs[i]) != 0) {
                System.out.println("\nexpression and hash test failed\nExpected:\n" + expectedOutputs[i] + "\nActual" +
                        "\n" +
                        actualOutputs[i]);

                return (5.0 * total) / expected;
            } else {
                total += 5.0;
            }

        }
        System.out.println("Hash test passed");
        return (5.0 * total) / expected;

    }

    public static double test() {
        double score1 = doHashTest("testdata/part12test1input.txt", "testdata/part12test1output.txt");
        double score2 = doHashTest("testdata/part12test2input.txt", "testdata/part12test2output.txt");
        double score3 = doHashTableTest("testdata/part12test1input.txt", "testdata/part12testtable1output.txt");
        double score4 = doHashTableTest("testdata/part12test2input.txt", "testdata/part12testtable2output.txt");

        double score = score1 + score2 + score3 + score4;
        System.out.println("Total Score for part 1+2: " + score + "/20.00");
        return score;
    }

    public static void main(String[] args) {
        System.out.println("Testing Part 1 & 2:");
        test();

    }

    public int getManualHash(String name) {
        int len = name.length();

        int hash = 0;

        for (int i = 0; i < len; i++) {
            int coeff = ((int) name.charAt(i)) % this.table.getTableCapacity();
            int exp = ((int) Math.pow(10, i)) % this.table.getTableCapacity();

            hash += (coeff * exp) % this.table.getTableCapacity();

        }

        return hash % this.table.getTableCapacity();
    }

}
