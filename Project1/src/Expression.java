/**
 * CS 251: Data Structures and Algorithms
 * Project 1
 * <p>
 * TODO: Complete Expression.
 *
 * @author Harsha Mohite, TODO: add your name here
 * @username hmohite, TODO: add your Purdue username here
 * @sources TODO: list your sources here
 */


public class Expression {

    /**
     * The expression to validate and evaluate
     */
    private final String expression;
    /**
     * Used for testing please do not modify
     */
    StringBuilder treeString;

    /**
     * Constructor to initialize the expression
     *
     * @param expression - the expression to evaluate
     */
    public Expression(String expression) {
        this.expression = expression;
        treeString = null;
    }

    /**
     * Checks whether the expression is valid or not
     *
     * @return true if the expression is valid
     */
    public boolean isValid() {
        MyStack<Character> operators = new MyStack<>();
        MyStack<Character> operands = new MyStack<>();
        for (int i = 0; i < expression.length(); i++) {
            Character currentChar = expression.charAt(i);

            // If open bracket or operator, push on operators
            if (currentChar == '(' || currentChar == '+' ||
                    currentChar == '-' || currentChar == '*' ||
                    currentChar == '/' || currentChar == '%') {
                operators.push(currentChar);
            }

            // If variable, push on operands
            else if (Character.isLowerCase(currentChar) && Character.isLetter(currentChar)) {
                operands.push(currentChar);
            }

            // If closing bracket...
            else if (currentChar == ')') {
                // Pop until open bracket (and pop that too)
                try {
                    while (operators.peek() != '(') {
                        operators.pop();
                        // Pop two operands, push one
                        operands.pop();
                        operands.push(operands.pop());
                    }
                    operators.pop();
                } catch (EmptyStackException e) {
                    return false;
                }
            }
        }

        // At the end, pop all operators, pop 2 push 1 operand
        try {
            while (!operators.isEmpty()) {
                operators.pop();
                operands.pop();
                operands.push(operands.pop());
            }
        } catch (EmptyStackException e) {
            return false;
        }

        // Valid if this is true
        return (operands.getSize() == 1 && operators.isEmpty());
    }

    /**
     * Makes an expression tree of the expression
     *
     * @return the root of the expression tree
     */
    public TreeNode makeTree() {
        MyStack<TreeNode> operands = new MyStack<>();
        MyStack<TreeNode> operators = new MyStack<>();

        for (int i = 0; i < expression.length(); i++) {
            Character currentChar = expression.charAt(i);

            //If open bracket or operator, push to operator stack
            if (currentChar == '(' || currentChar == '+' ||
                    currentChar == '-' || currentChar == '*' ||
                    currentChar == '/' || currentChar == '%') {
                operators.push(new TreeNode(currentChar));
            }

            // If variable, push on operands
            else if (Character.isLowerCase(currentChar) && Character.isLetter(currentChar)) {
                operands.push(new TreeNode(currentChar));
            }

            // If closing bracket...
            else if (currentChar == ')') {
                // Pop until open bracket (and pop that too)
                try {
                    while (operators.peek().value != '(') {
                        TreeNode poppedOperator = operators.pop();
                        poppedOperator.right = operands.pop();
                        poppedOperator.left = operands.pop();
                        operands.push(poppedOperator);
                    }
                    operators.pop();
                } catch (EmptyStackException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        try {
            while (!operators.isEmpty()) {
                TreeNode poppedOperator = operators.pop();
                poppedOperator.right = operands.pop();
                poppedOperator.left = operands.pop();
                operands.push(poppedOperator);
            }
        } catch (EmptyStackException e) {
            e.printStackTrace();
            return null;
        }

        try {
            return operands.pop();
        } catch (EmptyStackException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Evaluate the expression tree
     *
     * @param root   of the expression tree
     * @param values of all the variables the values is an int array of size 26.
     *               values[0] represent the value of ‘a’ and values[1] represent the value of ‘b’ and so on
     * @return the value of the evaluated expression
     */
    public int evaluate(TreeNode root, int[] values) {
        //TODO complete evaluate
        if (root.left == null && root.right == null) {
            return values[root.value - 'a'];
        }
        int leftVal;
        if (root.left != null) {
            leftVal = evaluate(root.left, values);
        }
        else {
            leftVal = Integer.MIN_VALUE;
        }
        int rightVal;
        if (root.right != null) {
            rightVal = evaluate(root.right, values);
        }
        else {
            rightVal = Integer.MIN_VALUE;
        }

        System.out.println(leftVal + root.value + rightVal);
        if (root.value == '+') {
            return leftVal + rightVal;
        }
        else if (root.value == '-') {
            return leftVal - rightVal;
        }
        else if (root.value == '*') {
            return leftVal * rightVal;
        }
        else if (root.value == '/') {
            return leftVal / rightVal;
        }
        else if (root.value == '%') {
            return leftVal % rightVal;
        }
        return Integer.MIN_VALUE;
    }

    /**
     * DO NOT MODIFY
     * Used to print the tree and for testing
     * source = https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java/42449385#42449385
     *
     * @param root
     * @return
     */
    public String print(TreeNode root) {
        treeString = new StringBuilder();
        print("", root, false);
        return treeString.toString();
    }

    /**
     * DO NOT MODIFY
     * Used to print the tree and for testing
     * source = https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java/42449385#42449385
     *
     * @param prefix
     * @param n
     * @param isLeft
     */
    public void print(String prefix, TreeNode n, boolean isLeft) {
        if (n != null) {
            treeString.append(prefix + (isLeft ? "|-- " : "\\-- ") + n.value + "\n");
            print(prefix + (isLeft ? "|   " : "    "), n.left, true);
            print(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }


    /**
     * Main Can be used for manual testing
     *
     * @param args
     */
//    public static void main(String[] args) {
//        Expression expression = new Expression("(a + (a + b * z) + (x % u) + (p * x))");
//        System.out.println(expression.isValid());
//        TreeNode root = expression.makeTree();
//        System.out.println(expression.print(root));
//        int[] chars = new int[26];
//        for (int i = 0; i < 26; i++) {
//            chars[i] = i + i;
//            System.out.println((char)('a' + i));
//            System.out.println(chars[i]);
//        }
//        System.out.println(expression.evaluate(root, chars));
//
//    }

}
