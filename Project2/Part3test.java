import java.util.ArrayList;

public class Part3test {

    public static double doQtest(String inputFile, String outputFile) {
        String[] commands = TestRoutines.parseFileToLine(inputFile);

        ArrayList<ArrayList<String>> queues = TestRoutines.parseFileToLineChunks(outputFile);

        int result = 0;
        int fullresult = commands.length;
        SoccerTeamPriorityQueue stpq = null;
        for (int i = 0; i < commands.length; i++) {
            ArrayList<String> queue = queues.get(i);
            boolean res;
            String[] tokens = commands[i].split(",");
            if (tokens[0].compareTo("create") == 0) {
                stpq = new SoccerTeamPriorityQueue(Integer.parseInt(tokens[1]));
                // System.out.println(stpq);
                res = (TestRoutines.compareStringArrays(TestRoutines.changeToArray(queue),
                        stpq.toString().split("\n")));
                if (res == false) {
                    System.out.println("Constructor not implemented properly");
                } else {
                    result += 1;
                }

            } else if (tokens[0].compareTo("push") == 0) {
                stpq.insert(new SoccerTeam(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]),
                        Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6])));
                // System.out.println(stpq);
                res = (TestRoutines.compareStringArrays(TestRoutines.changeToArray(queue),
                        stpq.toString().trim().split("\n")));
                if (res == false) {
                    System.out.println("insert not implemented properly");
                } else {
                    result += 1;
                }

            } else if (tokens[0].compareTo("getmax") == 0) {
                // System.out.println("The max is " + stpq.getMax().toString());
                // System.out.println(stpq);
                res = (TestRoutines.compareStringArrays(TestRoutines.changeToArray(queue),
                        ("The max is " + stpq.getMax().toString() + "\n" + stpq.toString()).trim().split("\n")));
                if (res == false) {
                    System.out.println("getMax() not implemented properly");
                } else {
                    result += 1;
                }
            } else if (tokens[0].compareTo("delmax") == 0) {
                SoccerTeam deleted = stpq.delMax();
                // System.out.println("The max deleted is " + deleted.toString());
                // System.out.println(stpq);
                res = (TestRoutines.compareStringArrays(TestRoutines.changeToArray(queue),
                        ("The max deleted is " + deleted.toString() + "\n" + stpq.toString()).trim()
                                .split("\n")));
                if (res == false) {
                    System.out.println("delMax() not implemented properly");
                } else {
                    result += 1;
                }

            } else if (tokens[0].compareTo("update") == 0) {
                stpq.update(Integer.parseInt(tokens[7]),
                        new SoccerTeam(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]),
                                Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6])));
                // System.out.println(stpq);
                res = (TestRoutines.compareStringArrays(TestRoutines.changeToArray(queue),
                        stpq.toString().trim().split("\n")));
                if (res == false) {
                    System.out.println("update() not implemented properly");
                } else {
                    result += 1;
                }

            }
        }

        // System.out.println(result + "/" + fullresult);
        return (result * 25.0) / (double) fullresult;
    }

    public static double test() {
        System.out.println("Testing part 3:");
        double result = doQtest("testdata/part3test1input.txt", "testdata/part3test1output.txt");
        System.out.println("Total Score for part 3: " + result + "/25.00");
        return result;
    }

    public static void main(String[] args) {

        test();
    }
}
