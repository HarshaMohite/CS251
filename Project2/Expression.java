/**
 * CS 251: Data Structures and Algorithms
 * Project 1
 * <p>
 * TODO: Complete Expression.
 *
 * @author , TODO: add your name here
 * @username , TODO: add your Purdue username here
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

        MyStack<Character> operatorsMyStack = new MyStack<>();
        MyStack<Character> characterMyStack = new MyStack<>();

        for (char ch : expression.toCharArray()) {
            if (ch == ' ') {
                continue;
            }
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%' || ch == '(') {
                operatorsMyStack.push(ch);
            } else if (Character.isAlphabetic(ch)) {
                characterMyStack.push(ch);
            } else if (ch == ')') {
                //WE POP UNTIL the next '(' and check whether the expression is valid
                try {
                    while (operatorsMyStack.pop() != '(') {
                        characterMyStack.pop();
                    }
                } catch (EmptyStackException e) {
                    return false;
                }

            }

        }

        while (!operatorsMyStack.isEmpty()) {
            try {
                operatorsMyStack.pop();
                characterMyStack.pop();
            } catch (EmptyStackException E) {
                return false;
            }
        }

        return characterMyStack.getSize() == 1;
    }

    /**
     * Makes an expression tree of the expression
     *
     * @return the root of the expression tree
     */
    public TreeNode makeTree() {
        String exp = expression.replaceAll(" ", "");

        MyStack<TreeNode> operators = new MyStack<>();
        MyStack<TreeNode> operands = new MyStack<>();

        for (char ch : exp.toCharArray()) {
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%' || ch == '(') {
                TreeNode treeNode = new TreeNode(ch);
                operators.push(treeNode);
            } else if (Character.isAlphabetic(ch)) {
                operands.push(new TreeNode(ch));
            } else if (ch == ')') {
                try {
                    TreeNode operator = operators.pop();
                    while (operator.value != '(') {
                        TreeNode rightOperand = operands.pop();
                        TreeNode leftOperand = operands.pop();
                        operator.left = leftOperand;
                        operator.right = rightOperand;
                        operands.push(operator);
                        operator = operators.pop();
                    }
                } catch (EmptyStackException E) {
                    E.printStackTrace();
                    return null;
                }
            }
        }
        try {
            while (!operators.isEmpty()) {

                TreeNode operator = operators.pop();
                TreeNode rightOperand = operands.pop();
                TreeNode leftOperand = operands.pop();
                operator.left = leftOperand;
                operator.right = rightOperand;
                operands.push(operator);
            }
            return operands.pop();
        } catch (EmptyStackException E) {
            E.printStackTrace();
            return null;
        }
    }




    /*
    source = https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java/42449385#42449385
     */

    /**
     * Evaluate the expression tree
     *
     * @param root   of the expression tree
     * @param values of all the variables the values is an int array of size 26.
     *               values[0] represent the value of ‘a’ and values[1] represent the value of ‘b’ and so on
     * @return the value of the evaluated expression
     */
    public int evaluate(TreeNode root, int[] values) {
        if (root.left != null && root.right != null) {
            int left = evaluate(root.left, values);
            int right = evaluate(root.right, values);
            int val = 0;
            switch (root.value) {
                case '+':
                    val = left + right;
                    break;
                case '-':
                    val = left - right;
                    break;
                case '*':
                    val = left * right;
                    break;
                case '/':
                    val = left / right;
                    break;
                case '%':
                    val = left % right;
                    break;
            }
            return val;

        } else {
            char x = root.value;
            if (x == 'E') {
                return values[26];
            }
            else if (x == 'M') {
                return values[27];
            }
            return values[x - 'a'];
        }
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
