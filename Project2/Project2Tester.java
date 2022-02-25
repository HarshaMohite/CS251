public class Project2Tester {

    public static void main(String[] args) {
        double test0score = Part0test.test();
        if (test0score < 20) {
            printScore(test0score, 0, 0, 0);
            return;
        }
        double test12score = Part12test.test();
        if (test12score < 20) {
            printScore(test0score, test12score, 0, 0);
            return;
        }
        double test3score = Part3test.test();
        if (test3score < 25) {
            printScore(test0score, test12score, test3score, 0);
            return;
        }
        double test4score = Part4test.test();
        printScore(test0score, test12score, test3score, test4score);
    }

    public static void printScore(double test0score, double test12score, double test3score, double test4score) {
        partScore(0, test0score);
        partScore(2, test12score);
        partScore(3, test3score);
        partScore(4, test4score);
        System.out.println("Total Score: " + (test0score + test12score + test3score + test4score) + "/100.00");
    }

    public static void partScore(int part, double score) {
        System.out.println("Score for Part " + part + ": " + score);
    }

}
