/**
 * Part 2
 */

import java.util.ArrayList;

public class SoccerTeamHashTable {

    private ArrayList<SoccerTeam>[] table;
    private int numTeams;
    private int tableCapacity;

    /**
     * Initialize a new SoccerTeam array with the argument passed.
     */
    public SoccerTeamHashTable(int capacity) {
        this.tableCapacity = this.getNextPrime(capacity);
        this.table = new ArrayList[this.tableCapacity];
        this.numTeams = 0;
    }

    // manual for testing
    public static void main(String[] args) {

    }

    /**
     * Write the code for the evaluating the expression
     *
     * @param expression
     * @return
     */
    public int evaluateExpression(String expression) {
        int[] expressionValues = new int[28];
        for (int i = 0; i < 26; i++) {
            expressionValues[i] = (char)('a' + i);
        }
        expressionValues[26] = 10;
        expressionValues[27] = this.tableCapacity;
        Expression expressionObject = new Expression(expression);
        TreeNode treeRoot = expressionObject.makeTree();
        return expressionObject.evaluate(treeRoot, expressionValues);
    }

    /**
     * for a given name, it will return an expression to calculate the hash value.
     *
     * @param name
     * @return
     */
    public String getExpression(String name) {
        String expression = "(";
        expression += "(";
        //int asciiValue = Character.getNumericValue(name.charAt(0));
        expression += name.charAt(0);
        expression += "%M)";
        for (int i = 1; i < name.length(); i++) {
            expression += "+(((";
            //asciiValue = Character.getNumericValue(name.charAt(i));
            expression += name.charAt(i);
            expression += "%M)";
            for (int j = 0; j < i; j++) {
                expression += "*E";
            }
            expression += ")%M)";
        }
        expression += ")%M";
        return expression;
    }

    /**
     * calculate the hashvalue.
     *
     * @param name
     * @return
     */
    public int getHash(String name) {
        //TODO implement getHash
        return evaluateExpression(getExpression(name));
    }

    /**
     * find if a SoccerTeam object with the name is in the hash table
     *
     * @param name
     * @return
     */
    public SoccerTeam get(String name) {
        //TODO implement get
        for (SoccerTeam s : this.table[getHash(name)]) {
            if (s.getName().compareTo(name) == 0) {
                return s;
            }
        }
        return null;
    }

    /**
     * Insert the SoccerTeam object in the hash table.
     *
     * @param c
     */
    public void put(SoccerTeam c) {
        //TODO implement put
        int hash = getHash(c.getName());
        /*if (this.table[hash].size() == 0) {
            this.table[hash].add(c);
        }*/
        if (this.table[hash] == null) { // if nothing at this hash, add
            this.table[hash] = new ArrayList<SoccerTeam>();
            this.table[hash].add(c);
            this.numTeams++;
        }
        else { // if something there, chain:
            boolean teamAdded = false;
            for (SoccerTeam existingTeam : this.table[hash]) { // see if exists or needs replacing
                if (c.equals(existingTeam)) {
                    teamAdded = true;
                    break;
                }
                if (existingTeam.getName().compareTo(c.getName()) == 0) {
                    //existingTeam = c;
                    //this.table[hash].add(c);
                    this.table[hash].add(table[hash].indexOf(existingTeam), c);
                    this.table[hash].remove(existingTeam);
                    teamAdded = true;
                    break;
                }
            }
            if (!teamAdded) { // otherwise, just add
                this.table[hash].add(c);
                this.numTeams++;
            }
        }
        //System.out.println(c.getMatchesPlayed());
        return;
    }

    /**
     * remove the soccer team else return null
     *
     * @param name
     * @return
     */
    public SoccerTeam remove(String name) {
        //TODO implement remove
        int hash = getHash(name);
        SoccerTeam foundTeam = null;
        for (SoccerTeam s : this.table[hash]) {
            if (s.getName().compareTo(name) == 0) {
                foundTeam = s;
                this.table[hash].remove(s);
                numTeams--;
                return foundTeam;
            }
        }
        return null;
    }

    // return the number of teams
    public int size() {
        return this.numTeams;
    }

    // get the table capacity
    public int getTableCapacity() {
        return this.tableCapacity;
    }

    // get the next prime number p >= num
    private int getNextPrime(int num) {
        if (num == 2 || num == 3) {
            return num;
        }

        int rem = num % 6;

        switch (rem) {
            case 0:
            case 4:
                num++;
                break;
            case 2:
                num += 3;
                break;
            case 3:
                num += 2;
                break;
        }

        while (!isPrime(num)) {
            if (num % 6 == 5) {
                num += 2;
            } else {
                num += 4;
            }
        }

        return num;
    }

    // determines if a number > 3 is prime
    private boolean isPrime(int num) {
        if (num % 2 == 0) {
            return false;
        }

        int x = 3;

        for (int i = x; i < num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    // return the table
    public ArrayList<SoccerTeam>[] getArray() {
        return this.table;
    }

    // returns the string representation of the object
    public String toString() {
        String ret = "";
        for (int i = 0; i < this.table.length; i++) {
            ret += Integer.valueOf(i);

            // System.out.println(this.table[i]);

            if (this.table[i] != null) {
                for (SoccerTeam team : this.table[i]) {
                    ret += " ";
                    ret += team.toString();
                    ret += " ";
                }

                ret = ret.trim();
                // ret = ret.substring(0, ret.length() - 1);

            }

            ret += "\n";
        }
        return ret.substring(0, ret.length() - 1);
    }
}