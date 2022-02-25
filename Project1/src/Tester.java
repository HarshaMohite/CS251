import java.util.ArrayList;

public class Tester {

    private int score;
    private final ArrayList<String> expressions;
    private final ArrayList<Boolean> answers;
    private final ArrayList<Integer> evaluated;

    public Tester() {
        expressions = new ArrayList<>();
        answers = new ArrayList<>();
        evaluated = new ArrayList<>();
        expressions.add("()()");
        answers.add(false);
        expressions.add("((a))");
        answers.add(true);
        expressions.add("(a))");
        answers.add(false);
        expressions.add("(a + b)");
        answers.add(true);
        expressions.add("a + b");
        answers.add(true);
        expressions.add("(a ) b)");
        answers.add(false);
        expressions.add("(a + b * z)");
        answers.add(true);
        expressions.add("(a + (a + b * z) + (x % u) + (p * x))");
        answers.add(true);
        expressions.add("a + ((a + b * z) + (x * u)) + (p * x)");
        answers.add(true);
        expressions.add("((a + (a + b * z) + (x * u) + (p * x))");
        answers.add(false);
        expressions.add("(a + ((a + b * z) + (x * u) + (p * x))");
        answers.add(false);
        expressions.add("(a + (a + b * * z) + (x * u) + (p * x))");
        answers.add(false);

        evaluated.add(0);
        evaluated.add(2);
        evaluated.add(2);
        evaluated.add(100);
        evaluated.add(1486);
        evaluated.add(3320);
    }

    public static void main(String[] args) {

        Tester tester = new Tester();
        System.out.println("\nTesting Part 1: Stack");
        System.out.println("----------------------");
        boolean part1 = false;
        try {
            part1 = tester.testStackInt();
        } catch (Exception E) {
            E.printStackTrace();
        }
        if (part1) {
            tester.score += 15;
            System.out.println("Part 1 passed");
            tester.printScore(1, tester.score);
        } else {
            System.out.println("Part 1 failed");
            System.out.println("Please correct Part 1 to test other parts");
            tester.printScore(1, 0);
            tester.printScore(2, 0);
            tester.printScore(3, 0);
            tester.printScore(4, 0);
            tester.printScore(0, tester.score);
            return;
        }

        boolean part2 = false;

        System.out.println("\nTesting Part 2: Expression Validation");
        System.out.println("-------------------------------------");

        try {
            part2 = tester.testExpressionValidation();
        } catch (Exception E) {
            E.printStackTrace();
        }

        if (part2) {
            tester.score += 25;
            System.out.println("Part 2 passed");
            tester.printScore(2, tester.score - 15);
        } else {
            System.out.println("Part 2 failed");
            System.out.println("Please correct Part 2 to test other parts");
            tester.printScore(2, 0);
            tester.printScore(3, 0);
            tester.printScore(4, 0);
            tester.printScore(0, tester.score);
            return;
        }

        boolean part3 = false;

        System.out.println("\nTesting Part 3: Building Expression Tree");
        System.out.println("----------------------------------------");

        try {
            part3 = tester.testTree();
        } catch (Exception E) {
            E.printStackTrace();
        }

        if (part3) {
            tester.score += 40;
            System.out.println("Part 3 passed");
            tester.printScore(3, tester.score - 40);
        } else {
            System.out.println("Part 3 failed");
            System.out.println("Please correct Part 3 to test other parts");
            tester.printScore(3, 0);
            tester.printScore(4, 0);
            tester.printScore(0, tester.score);
            return;
        }

        boolean part4 = false;

        System.out.println("\nTesting Part 4: Evaluating Expression Tree");
        System.out.println("------------------------------------------");

        try {
            part4 = tester.testExpressionEvaluation();
        } catch (Exception E) {
            E.printStackTrace();
        }

        if (part4) {
            tester.score += 20;
            System.out.println("Part 4 passed");
            tester.printScore(4, tester.score - 80);
        } else {
            System.out.println("Part 4 failed");
            System.out.println("Please correct Part 4 to complete");
            tester.printScore(4, 0);
            tester.printScore(0, tester.score);
            return;
        }


        tester.printScore(0, tester.score);
        System.out.println("\nCongratulations Well Done!!!");
        System.out.println("Do not forget to submit on Vocareum. Hopefully you get a 100");

    }


    private boolean testStackInt() throws EmptyStackException {
        MyStack<Integer> myStack = new MyStack<>();
        try {
            myStack.pop();
            System.out.println("Empty Stack should throw EmptyStackException");
            return false;
        } catch (Exception e) {
            if (!(e instanceof EmptyStackException)) {
                System.out.println("Empty Stack should throw EmptyStackException");
                return false;
            }
        }

        int size = 0;

        for (int i = 0; i < 100; i++) {
            myStack.push(i);
            size++;
            if (myStack.getSize() != size) {
                System.out.println("Stack size error\nExpected: " + size + "\nActual: " + myStack.getSize());
                return false;
            }
        }

        for (int i = 99; i >= 51; i--) {
            int popped = myStack.pop();
            size--;
            if (popped != i) {
                System.out.println("Stack pop error\nExpected: " + i + "\nActual: " + popped);
                return false;
            }
            if (myStack.getSize() != size) {
                System.out.println("Stack size error\nExpected: " + size + "\nActual: " + myStack.getSize());
                return false;
            }
        }

        for (int i = 51; i < 100; i++) {
            myStack.push(i);
            size++;
            if (myStack.getSize() != size) {
                System.out.println("Stack size error\nExpected: " + size + "\nActual: " + myStack.getSize());
                return false;
            }
        }

        for (int i = 99; i >= 0; i--) {

            int peek = myStack.peek();
            if (peek != i) {
                System.out.println("Stack peek error\nExpected: " + i + "\nActual: " + peek);
                return false;
            }
            int popped = myStack.pop();
            size--;
            if (popped != i) {
                System.out.println("Stack pop error\nExpected: " + i + "\nActual: " + popped);
                return false;
            }
            if (myStack.getSize() != size) {
                System.out.println("Stack size error\nExpected: " + size + "\nActual: " + myStack.getSize());
                return false;
            }
        }

        try {
            myStack.pop();
            System.out.println("Empty Stack should throw EmptyStackException");
            return false;
        } catch (Exception e) {
            if (!(e instanceof EmptyStackException)) {
                System.out.println("Empty Stack should throw EmptyStackException");
                return false;
            }
        }

        return true;
    }

    private boolean testExpressionValidation() {
        for (int i = 0; i < expressions.size(); i++) {
            String exp = expressions.get(i);
            boolean ans = answers.get(i);

            Expression expression = new Expression(exp);
            boolean studentAns = expression.isValid();

            if (studentAns != ans) {
                System.out.println("Test Failed: " + exp);
                System.out.println("Expected: " + ans + "\nActual: " + studentAns);
                return false;
            }

        }


        return true;
    }

    private boolean testTree() {
        ArrayList<String> trees = TesterHelp.getTrees();
        int j = 0;
        for (int i = 0; i < expressions.size(); i++) {
            String exp = expressions.get(i);
            boolean ans = answers.get(i);
            if (ans) {
                Expression expression = new Expression(exp);
                TreeNode root = expression.makeTree();
                String studentTree = expression.print(root);
                String ansTree = trees.get(j++);
                if (!studentTree.equals(ansTree)) {
                    System.out.println("Trees Did not Match");
                    System.out.println("Expression: " + exp);
                    System.out.println("Expected:");
                    System.out.println(ansTree);
                    System.out.println("Actual:");
                    System.out.println(studentTree);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean testExpressionEvaluation() {
        int[] chars = new int[26];
        for (int i = 0; i < 26; i++) {
            chars[i] = i + i;
        }
        int j = 0;
        for (int i = 0; i < expressions.size(); i++) {
            String exp = expressions.get(i);
            boolean ans = answers.get(i);
            if (ans) {
                Expression expression = new Expression(exp);
                TreeNode root = expression.makeTree();
                int studentAns = expression.evaluate(root, chars);
                int ansEvaluate = evaluated.get(j++);
                if (!(studentAns == ansEvaluate)) {
                    System.out.println("Evaluated expression did not Match");
                    System.out.println("Expression: " + exp);
                    System.out.println("Expected:");
                    System.out.println(ansEvaluate);
                    System.out.println("Actual:");
                    System.out.println(studentAns);
                    return false;
                }
            }
        }
        return true;

    }

    private void printScore(int part, int score) {

        if (part == 0) {
            System.out.println("\nTotal Score: " + score);
            return;
        }

        System.out.println("Score for Part " + part + ": " + score);
    }


}
