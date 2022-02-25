/**
 * Part 3
 */
public class SoccerTeamPriorityQueue {

    private SoccerTeam[] teamList;
    private int numTeams;
    private SoccerTeamHashTable teamTable;

    /**
     * @return return the priority queue
     */
    public SoccerTeam[] getTeamList() {
        return teamList;
    }

    /**
     * @return the team table
     */
    public SoccerTeamHashTable getTeamTable() {
        return teamTable;
    }

    /**
     * Constructor of the class.
     * Initialize a new SoccerTeam array with the argument passed.
     */
    public SoccerTeamPriorityQueue(int capacity) {
        this.teamList = new SoccerTeam[capacity];
        this.numTeams = 0;
        this.teamTable = new SoccerTeamHashTable(capacity);

    }

    /**
     * Constructor to initialize the priority queue and global variables
     *
     * @param spq
     */
    public SoccerTeamPriorityQueue(SoccerTeamPriorityQueue spq) {
        SoccerTeam[] teams = spq.getTeamList();
        this.teamList = new SoccerTeam[teams.length];
        this.numTeams = teams.length;
        this.teamTable = new SoccerTeamHashTable(this.numTeams);

        for (int i = 0; i < this.numTeams; i++) {
            this.teamList[i] = new SoccerTeam(teams[i]);
            this.teamTable.put(new SoccerTeam(teams[i]));
        }

    }

    /**
     * @return return the number of teams
     */
    public int getNumTeams() {
        return this.numTeams;
    }

    /**
     * @return String representation of the priority queue
     */
    public String toString() {
        String str = "Priority Queue:\n";

        for (SoccerTeam team : this.teamList) {
            if (team != null) {
                str = str + team.toString() + "\n";
            }
        }

        str = str.substring(0, str.length());

        str += this.teamTable.toString();

        return str;
    }


    public int getParent(int index) {
        return (int) (index - 1) / 2;
    }

    public int getLeftChild(int index) {
        return index * 2 + 1;
    }

    public int getRightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * @return return the index at which the team was inserted
     */

    public int insert(SoccerTeam c) {
        //TODO: implement insert
        this.teamList[numTeams] = c;
        c.setPosInQueue(numTeams);
        this.numTeams++;
        int newIndex = heapifyUp(numTeams - 1);
        this.teamTable.put(teamList[newIndex]);
        return newIndex;
    }

    /**
     * Implement the heapifyUp function
     *
     * @param indexIN
     * @return the index where the heapifyUp for the given index ends
     */
    public int heapifyUp(int indexIN) {
        //TODO implement heapifyUp
        int index = indexIN;
        boolean swapMade = true;
        do {
            int parentIndex = (index - 1) / 2;
            if (this.teamList[index].compareTo(this.teamList[parentIndex]) == 1) {
                // make swap
                SoccerTeam temp = this.teamList[parentIndex];
                this.teamList[parentIndex] = this.teamList[index];
                this.teamList[index] = temp;
                // move index
                this.teamList[index].setPosInQueue(index);
                this.teamList[parentIndex].setPosInQueue(parentIndex);
                index = parentIndex;
            }
            else {
                swapMade = false;
            }
        } while (swapMade);
        return index;
    }

    /**
     * Implement the heapifyDown function
     *
     * @param index
     * @return the index where the heapifyDown for the given index ends
     */
    public int heapifyDown(int index) {
        //TODO implement heapifyDown
        int leftChildIndex = (2 * index) + 1;
        if (leftChildIndex >= numTeams) {
            leftChildIndex = -1;
        }

        int rightChildIndex = (2 * index) + 2;
        if (rightChildIndex >= numTeams) {
            rightChildIndex = -1;
        }

        boolean leftSwap = false;
        boolean rightSwap = false;

        if (leftChildIndex != -1) {
            if (teamList[index].compareTo(teamList[leftChildIndex]) == -1) {
                leftSwap = true;
            }
        }
        if (rightChildIndex != -1) {
            if (teamList[index].compareTo(teamList[rightChildIndex]) == -1) {
                rightSwap = true;
            }
        }

        // if
        if (leftSwap && rightSwap) {
            if (teamList[leftChildIndex].compareTo(teamList[rightChildIndex]) == -1) {
                rightSwap = false;
            }
            else {
                leftSwap = false;
            }
        }
        if (leftSwap) {
            SoccerTeam temp = teamList[leftChildIndex];
            teamList[leftChildIndex] = teamList[index];
            teamList[index] = temp;
            teamList[leftChildIndex].setPosInQueue(leftChildIndex);
            teamList[leftChildIndex].setPosInQueue(leftChildIndex);
            teamList[index].setPosInQueue(index);
            return heapifyDown(leftChildIndex);
        }
        else if (rightSwap) {
            SoccerTeam temp = teamList[rightChildIndex];
            teamList[rightChildIndex] = teamList[index];
            teamList[index] = temp;
            teamList[rightChildIndex].setPosInQueue(rightChildIndex);
            teamList[rightChildIndex].setPosInQueue(rightChildIndex);
            teamList[index].setPosInQueue(index);
            return heapifyDown(rightChildIndex);
        }

        return index;
    }

    /**
     * remove the team with the highest priority from the queue
     *
     * @return return the team removed
     */
    public SoccerTeam delMax() {
        //TODO implement delMax
        SoccerTeam topElement = teamList[0];
        int finalElementIndex = numTeams - 1;
        teamList[0] = teamList[finalElementIndex];
        teamList[0].setPosInQueue(0);
        teamList[finalElementIndex] = null;
        numTeams--;
        int newIndex = heapifyDown(0);
        teamTable.put(teamList[newIndex]);
        teamTable.remove(topElement.getName());
        return topElement;
    }

    /**
     * @return return the number of teams currently in the queue
     */
    public int size() {
        return this.numTeams;
    }

    /**
     * @return return true if the queue is empty; false else
     */
    public boolean isEmpty() {
        return (this.numTeams == 0);
    }

    /**
     * @return return the team with the maximum priority
     */
    public SoccerTeam getMax() {
        if (teamList[0] != null) {
            return teamList[0];
        }
        return null;
    }

    /**
     * @param index
     * @param oldTeamNewValue
     * @return return the new index of the team oldTeamNewValue updated in the heap
     */
    public int update(int index, SoccerTeam oldTeamNewValue) {
        //TODO implement update
        oldTeamNewValue.setPosInQueue(index);
        teamList[index] = oldTeamNewValue;
        int newIndex = heapifyUp(index);
        newIndex = heapifyDown(index);
        teamTable.put(teamList[newIndex]);
        return newIndex;
    }

}