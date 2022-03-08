
/**
 * CS 251: Data Structures and Algorithms
 * Project 3: Part 1
 *
 * Use the algorithms written in sorting to implement this class.
 *
 */


public class T {
    int a;
    int b;

    /**
     *
     * Constructor
     *
     */
    public T () {
        this.a = 0;
        this.b = 0;
    }

    /**
     *
     * Constructor
     *
     */
    public T (int a) {
        this.a = a;
        this.b = 0;
    }

    /**
     *
     * Constructor
     *
     */
    public T (int a, int b) {
        this.a = a;
        this.b = b;
    }


    /**
     *
     * check if 'this' instance is lexicographic smaller than t2.
     * return -1 if 'this' < t2;
     * return 1 if 'this' > t2;
     * return 0 if 'this' == t2;
     *
     * @return int
     *
     */
    public int compareTo (T t2) {
        // check if this instance is lexicographic smaller than t2
        // return -1 if this < t2;
        // return 1 if this > t2;
        // return 0 if this == t2;

        if ((this.a < t2.a) || ((this.a == t2.a) && (this.b < t2.b))) {
            return -1;
        } else if ((this.a > t2.a) || ((this.a == t2.a) && (this.b > t2.b))) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     *
     * returns a string consisting of the two tuple values.
     *
     * @return String
     */
    public String toString() {
        return String.valueOf(this.a) + ' ' + String.valueOf(this.b);
    }

}

