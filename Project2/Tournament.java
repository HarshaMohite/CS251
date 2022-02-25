/**
 * Part 4
 */


public class Tournament {

    private int teamNo;
    private String[] teamNames;
    private SoccerTeamPriorityQueue teamPQ;

    /**
     * Initialize the fields
     * @param teamNames
     */
    public Tournament(String[] teamNames) {
        //TODO implement tournament
    }

    /**
     * @return an array of team names
     */
    public String[] getTeamNames() {
        return this.teamNames;
    }

    /**
     * Find the kth team (1-index) from the teams
     * @param k
     */
    public SoccerTeam findKthTeam(int k) {
        //TODO implement findKthTeam
        return null;
    }

    /**
     * Update the priority queue and hash table with this information from a match.
     * @param teamName1
     * @param goal1
     * @param teamName2
     * @param goal2
     */
    public void updateTournament(String teamName1, int goal1, String teamName2, int goal2) {
        //TODO implement updateTournament
        return;
    }

    /**
     * @return string representation of the priority queue
     */
    public String toString() {
        return "Priority Queue and hash table:\n" + this.teamPQ.toString();
    }

}