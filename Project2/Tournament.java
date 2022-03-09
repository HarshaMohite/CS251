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

        /*
        this.teamNames = teamNames;
        this.teamNo = teamNames.length;
        this.teamPQ = new SoccerTeamPriorityQueue(this.teamNo);
        for (int i = 0; i < teamNo; i++) {
            SoccerTeam s = new SoccerTeam(teamNames[i]);
            this.teamPQ.insert(s);
        }

         */


        this.teamNames = new String[teamNames.length];
        for (int i = 0; i < teamNames.length; i++) {
            this.teamNo++;
            this.teamNames[i] = teamNames[i];
        }
        this.teamPQ = new SoccerTeamPriorityQueue(this.teamNo);
        for (int i = 0; i < teamNo; i++) {
            this.teamPQ.insert(new SoccerTeam(teamNames[i]));
        }


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

        SoccerTeamPriorityQueue P = new SoccerTeamPriorityQueue(teamNo);
        SoccerTeamPriorityQueue R = new SoccerTeamPriorityQueue(teamPQ);
        SoccerTeam max = new SoccerTeam(R.getMax());
        P.insert(max);
        SoccerTeam[] S = R.getTeamList();
        for (int i = 1; i < k; i++) {
            SoccerTeam T = P.getMax();
            int queuePos = R.getTeamTable().get(T.getName()).getPosInQueue();
            if (R.getLeftChild(queuePos) < teamNo) {
                P.insert(new SoccerTeam(R.getTeamList()[R.getLeftChild(queuePos)]));
            }
            if (R.getRightChild(queuePos) < teamNo) {
                P.insert(new SoccerTeam(R.getTeamList()[R.getRightChild(queuePos)]));
            }
            //P.insert(S[R.getLeftChild(0)]);
            //P.insert(S[R.getRightChild(0)]);
            P.delMax();
        }
        return R.getTeamTable().get(P.getMax().getName());
        //return P.getMax();

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
        SoccerTeam s1 = new SoccerTeam(teamPQ.getTeamTable().get(teamName1));
        SoccerTeam s2 = new SoccerTeam(teamPQ.getTeamTable().get(teamName2));
        s1.setGoalsScored(s1.getGoalsScored() + goal1);
        s1.setGoalsConceded(s1.getGoalsConceded() + goal2);
        s2.setGoalsScored(s2.getGoalsScored() + goal2);
        s2.setGoalsConceded(s2.getGoalsConceded() + goal1);
        if (goal1 > goal2) {
            s1.setPoints(s1.getPoints() + 3);
        }
        else if (goal2 > goal1) {
            s2.setPoints(s2.getPoints() + 3);
        }
        else {
            s1.setPoints(s1.getPoints() + 1);
            s2.setPoints(s2.getPoints() + 1);
        }
        s1.setMatchesPlayed(s1.getMatchesPlayed() + 1);
        s2.setMatchesPlayed(s2.getMatchesPlayed() + 1);
        teamPQ.update(s1.getPosInQueue(), s1);
        s2.setPosInQueue(teamPQ.getTeamTable().get(teamName2).getPosInQueue());
        teamPQ.update(s2.getPosInQueue(), s2);
        return;
    }

    /**
     * @return string representation of the priority queue
     */
    public String toString() {
        return "Priority Queue and hash table:\n" + this.teamPQ.toString();
    }

}