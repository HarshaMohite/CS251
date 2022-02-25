/**
 * CS 251: Data Structures and Algorithms
 * Project 1
 * <p>
 * TODO: Complete MyStack.
 *
 * @author
 * @username
 * @sources
 */

public class MyStack<Item> {


    /**
     * Pointer to top of the stack
     */
    private StackNode<Item> top;
    /**
     * Keeps track of the size of the stack
     */
    private int size;

    /**
     * Default constructor of the class initializes all
     * parameters to default values
     */
    public MyStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * @return if the stack is empty or not
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Pushes a new a new value into the stack
     * Remember to update size
     *
     * @param value the value to be pushed
     */
    public void push(Item value) {
        StackNode<Item> stackNode = new StackNode<>(value);
        stackNode.next = top;
        top = stackNode;
        size++;
    }

    /**
     * Peeks the top element of the stack
     *
     * @return the value at the top of the stack
     * @throws EmptyStackException if the stack is empty.
     *                             You can throw an exception by “throw new EmptyStackException();”
     */
    public Item peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.value;
    }

    /**
     * Pops the top element of the stack
     * Remember to update size
     *
     * @return the popped element
     * @throws EmptyStackException if the stack is empty
     *                             You can throw an exception by “throw new EmptyStackException();”
     */
    public Item pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        StackNode<Item> temp = top;
        top = top.next;
        size--;
        return temp.value;
    }

    /**
     * @return the size of the stack
     */
    public int getSize() {
        return size;
    }

}
