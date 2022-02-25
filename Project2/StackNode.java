public class StackNode<Item> {

    /**
     * Value of the respective node
     */
    Item value;
    /**
     * Pointer to the next element in the stack
     */
    StackNode<Item> next;

    /**
     * Default constructor
     */
    public StackNode() {
        this.value = null;
        this.next = null;
    }

    /**
     * Constructor that initializes this.value to the parameter value
     *
     * @param value
     */
    public StackNode(Item value) {
        this.value = value;
        this.next = null;
    }

    /**
     * Constructor that initializes the respective parameters
     *
     * @param value
     * @param next
     */
    public StackNode(Item value, StackNode<Item> next) {
        this.value = value;
        this.next = next;
    }
}