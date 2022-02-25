/**
 * Part 0
 */

public class SoccerTeam {
    // global variables
    private final String name;
    private int goalsScored;
    private int goalsConceded;
    private int points;
    private int matchesPlayed;
    private int posInQueue;

    /**
     * Constructor to initialize the object with the name
     *
     * @param name
     */
    public SoccerTeam(String name) {
        this.name = name;
        this.goalsScored = 0;
        this.goalsConceded = 0;
        this.points = 0;
        this.matchesPlayed = 0;
        this.posInQueue = -1;
    }

    /**
     * constructor to initialize the object with different values
     *
     * @param name
     * @param points
     * @param matchesPlayed
     * @param goalsScored
     * @param goalsConceded
     * @param posInQueue
     */
    public SoccerTeam(String name, int points, int matchesPlayed, int goalsScored, int goalsConceded, int posInQueue) {
        this.name = name;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
        this.points = points;
        this.matchesPlayed = matchesPlayed;
        this.posInQueue = posInQueue;
    }

    /**
     * copy constructor
     *
     * @param other
     */
    public SoccerTeam(SoccerTeam other) {
        this.name = other.name;
        this.goalsScored = other.goalsScored;
        this.goalsConceded = other.goalsConceded;
        this.points = other.points;
        this.matchesPlayed = other.matchesPlayed;
        this.posInQueue = other.posInQueue;
    }

    /**
     * main to test your class
     */
    public static void main(String[] args) {

    }

    /**
     * provides a string representation of the object
     *
     * @return
     */
    @Override
    public String toString() {
        return "SoccerTeam{" + "name='" + name + '\'' + ", Matches Played=" + this.matchesPlayed + ", Goals Scored="
                + this.goalsScored + ", Goals Conceded=" + this.goalsConceded + ", Points=" + this.points +

                ", posInQueue=" + posInQueue + '}';
    }

    /**
     * For a function call, A.compareTo(B), it will return 1 if SoccerTeam A will have more
     * priority that SoccerTeam B; -1 if the other way around. If they have the same
     * priority, it will return 0;
     *
     * @param other
     * @return
     */
    public int compareTo(SoccerTeam other) {
        //TODO: Implement compareTo
        if (this.points > other.getPoints()) {
            return 1;
        }
        else if (this.points < other.getPoints()) {
            return -1;
        }

        int goalDifferenceA = this.goalsScored - this.goalsConceded;
        int goalDifferenceB = other.getGoalsScored() - other.getGoalsConceded();
        if (goalDifferenceA > goalDifferenceB) {
            return 1;
        }
        else if (goalDifferenceA < goalDifferenceB) {
            return -1;
        }

        if (this.matchesPlayed < other.getMatchesPlayed()) {
            return 1;
        }
        else if (this.matchesPlayed > other.getMatchesPlayed()) {
            return -1;
        }

        int lexComparison = this.name.compareTo(other.getName());
        if (lexComparison < 0) {
            return 1;
        }
        else if (lexComparison > 0) {
            return -1;
        }

        return 0;
    }

    /**
     * check if two objects are equal
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoccerTeam)) {
            return false;
        }
        SoccerTeam that = (SoccerTeam) o;
        return this.matchesPlayed == that.matchesPlayed && this.points == that.points
                && this.goalsScored == that.goalsScored && this.goalsConceded == that.goalsConceded
                && posInQueue == that.posInQueue && name.equals(that.name);
    }

    /**
     * get the position in queue
     *
     * @return
     */
    public int getPosInQueue() {
        return this.posInQueue;
    }

    /**
     * set the position in queue
     *
     * @param posInQueue
     */
    public void setPosInQueue(int posInQueue) {
        this.posInQueue = posInQueue;
    }

    /**
     * get the points
     *
     * @return
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * set the points
     *
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * get the number of matches played
     *
     * @return
     */
    public int getMatchesPlayed() {
        return this.matchesPlayed;
    }

    /**
     * set the number of matches played
     *
     * @param matchesPlayed
     */
    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    /**
     * get the goals scored
     *
     * @return
     */
    public int getGoalsScored() {
        return this.goalsScored;
    }

    /**
     * set the number of goals scored
     *
     * @param goalsScored
     */
    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    /**
     * get the number of goals conceded
     *
     * @return
     */
    public int getGoalsConceded() {
        return this.goalsConceded;
    }

    /**
     * set the number of goals conceded
     *
     * @param goalsConceded
     */
    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    /**
     * get the name of the team
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

}