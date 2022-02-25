import java.util.Random;
import java.util.StringTokenizer;
import java.io.*;
import java.util.ArrayList;

public class TestRoutines {

    private static Random random = new Random();

    public static String[] changeToArray(ArrayList<String> lines) {
        String[] linesArray = new String[lines.size()];
        return (lines.toArray(linesArray));
    }

    public static boolean compareStringArrays(String[] A, String[] B) {
        int al = A.length;
        int bl = B.length;
        // System.out.println(al + " " + bl);
        if (al != bl)
            return false;

        for (int i = 0; i < al; i++) {
            // System.out.println(A[i] + A[i].length());
            // System.out.println(B[i] + B[i].length());
            if (A[i].compareTo(B[i]) != 0) {
                return false;
            }
        }

        return true;
    }

    public static ArrayList<ArrayList<String>> parseFileToLineChunks(String inputFile) {
        ArrayList<ArrayList<String>> lineChunks = new ArrayList<ArrayList<String>>();
        ArrayList<String> chunk = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                if (line.compareTo("%*&") == 0) {
                    lineChunks.add(chunk);
                    chunk = new ArrayList<String>();

                } else {
                    // System.out.println("ad " + line + chunk.size());
                    chunk.add(line);
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        return lineChunks;

    }

    public static String[] parseFileToLine(String inputFile) {

        ArrayList<String> lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        String[] linesArray = new String[lines.size()];
        return (lines.toArray(linesArray));
    }

    public static SoccerTeam[] parseFileToTeam(String inputFile) {

        ArrayList<SoccerTeam> teamList = new ArrayList<SoccerTeam>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                String name = tokenizer.nextToken();
                int points = Integer.parseInt(tokenizer.nextToken());
                int matchesPlayed = Integer.parseInt(tokenizer.nextToken());
                int goalsScored = Integer.parseInt(tokenizer.nextToken());
                int goalsConceded = Integer.parseInt(tokenizer.nextToken());
                int posInQueue = Integer.parseInt(tokenizer.nextToken());

                teamList.add(new SoccerTeam(name, points, matchesPlayed, goalsScored, goalsConceded, posInQueue));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        SoccerTeam[] teams = new SoccerTeam[teamList.size()];
        return (teamList.toArray(teams));
    }

    public static String generateTeamName(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    public static SoccerTeam generateTeam() {
        int nameLength = 5 + random.nextInt(5);

        SoccerTeam team = new SoccerTeam(generateTeamName(nameLength));
        team.setMatchesPlayed(random.nextInt(10));
        team.setPoints(team.getMatchesPlayed() * 3);
        team.setGoalsScored(random.nextInt(20));
        team.setGoalsConceded(random.nextInt(10));

        return team;

    }

    public static int compareTeams(SoccerTeam team1, SoccerTeam team2) {
        if (team1.getPoints() > team2.getPoints()) {
            return 1;
        } else if (team1.getPoints() < team2.getPoints()) {
            return -1;
        } else {

            if (team1.getGoalsScored() - team1.getGoalsConceded() > team2.getGoalsScored() - team2.getGoalsConceded()) {
                return 1;
            } else if (team1.getGoalsScored() - team1.getGoalsConceded() < team2.getGoalsScored()
                    - team2.getGoalsConceded()) {
                return -1;
            } else {
                if (team1.getMatchesPlayed() < team2.getMatchesPlayed())
                    return 1;
                else if (team1.getMatchesPlayed() > team2.getMatchesPlayed())
                    return -1;
                else {
                    if (team1.getName().compareTo(team2.getName()) < 0)
                        return 1;
                    else if ((team1.getName().compareTo(team2.getName()) > 0))
                        return -1;
                }
                return 0;
            }

        }

    }

}
