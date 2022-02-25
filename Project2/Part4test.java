import java.util.ArrayList;

public class Part4test {

    public static double doTournamentTest(String teamNameInput, String teamNameOutput, String gameUpdateInput,
                                          String gameUpdateOutput) {

        double grade = 5.00;

        String[] teamNames = TestRoutines.parseFileToLine(teamNameInput);

        Tournament tournament = new Tournament(teamNames);

        String[] tourninfo = TestRoutines.parseFileToLine(teamNameOutput);

        boolean res = (TestRoutines.compareStringArrays(tourninfo,
                tournament.toString().split("\n")));

        // System.out.println(res);
        if (res) {
            System.out.println("Constructor for tournament test passed");
            grade = 5.0;
        } else {
            System.out.println("Constructor for tournament test failed");
            return grade;
        }

        ArrayList<ArrayList<String>> queues = TestRoutines.parseFileToLineChunks(gameUpdateOutput);

        String[] matchInfo = TestRoutines.parseFileToLine(gameUpdateInput);

        int numOfMatch = matchInfo.length;
        double grade1 = 0.0, grade2 = 0.0;
        for (int i = 0; i < numOfMatch; i++) {
            String[] tokens = matchInfo[i].split(",");
            // System.out.println(tournament.getTeamNames()[Integer.parseInt(tokens[0])] +
            // Integer.parseInt(tokens[1])
            // + tournament.getTeamNames()[Integer.parseInt(tokens[2])]
            // + Integer.parseInt(tokens[3]));

            tournament.updateTournament(tournament.getTeamNames()[Integer.parseInt(tokens[0])],
                    Integer.parseInt(tokens[1]),
                    tournament.getTeamNames()[Integer.parseInt(tokens[2])], Integer.parseInt(tokens[3]));
            // System.out.println(tournament);
            // System.out.println(tournament.findKthTeam(Integer.parseInt(tokens[4])) + "is
            // the "
            // + Integer.parseInt(tokens[4]) + "th team");

            ArrayList<String> queue = queues.get(i);
            SoccerTeam kthTeam = tournament.findKthTeam(Integer.parseInt(tokens[4]));

            String[] outputs = TestRoutines.changeToArray(queue);
            String actualK = kthTeam + "is the " + Integer.parseInt(tokens[4])
                    + "th team";
            res = (outputs[outputs.length - 1].compareTo(actualK) == 0);
            if (res == false) {
                System.out.println("Finding kth Rank implementation failed");
            } else {
                grade1 += 3.0;
                System.out.println("Another Finding kth Rank implementation test passed");
            }
            res = (TestRoutines.compareStringArrays(outputs,
                    (tournament.toString() + "\n" + kthTeam + "is the "
                            + Integer.parseInt(tokens[4]) + "th team").trim().split("\n")));
            if (res == false) {
                System.out.println("Tournament Update implementation failed");
            } else {
                grade2 += 3.0;

                System.out.println("Another Tournament Update implementation test passed");
            }
        }

        double fullGrade = 5.0 + (grade1 * 15.00) / (numOfMatch * 3.0) + (grade2 * 15.00) / (numOfMatch * 3.0);

        return fullGrade;

    }

    public static double test() {
        System.out.println("Testing Part 4:");
        double grade = doTournamentTest("testdata/part4testteaminput.txt", "testdata/part4testteamoutput.txt",
                "testdata/part4matchinputtest.txt", "testdata/part4matchoutputtest.txt");
        System.out.println("Total score for part 4:" + grade + "/" + "35.00");
        return grade;
    }

    public static void main(String[] args) {

        test();
    }

}
