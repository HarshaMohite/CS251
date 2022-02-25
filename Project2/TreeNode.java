public class TreeNode {

    /**
     * Value of the node
     */
    char value;

    /**
     * Pointer to the left child
     */
    TreeNode left;

    /**
     * Pointer to the right child
     */
    TreeNode right;

    /**
     * Default constructor
     */
    public TreeNode() {
        value = '\0';
        this.left = null;
        this.right = null;
    }

    /**
     * Constructor to initialize with value
     * @param value
     */
    public TreeNode(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString()
    {
        StringBuilder root = new StringBuilder("\n   \n   \n   \n");
        root = root.replace(2, 3, Character.toString(this.value));

        if(this.left != null)
        {
            root = root.replace(5, 6, "/");
            root = root.replace(9, 10, Character.toString(this.left.value));

        }

        if(this.right != null)
        {
            root = root.replace(7, 8, "\\");
            root = root.replace(11, 12, Character.toString(this.right.value));


        }

        return root.toString();

    }
}