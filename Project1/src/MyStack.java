/**
 * CS 251: Data Structures and Algorithms
 * Project 1
 * <p>
 * TODO: Complete MyStack.
 *
 * @author Harsha Mohite, TODO: add your name here
 * @username hmohite, TODO: add your Purdue username here
 * @sources TODO: list your sources here
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
        size = 0;
        top = new StackNode<>();
    }

    /**
     * @return if the stack is empty or not
     */
    public boolean isEmpty() {
        if (top.value == null) {
            return true;
        }
        return false;
    }

    /**
     * Pushes a new a new value into the stack
     * Remember to update size
     *
     * @param value the value to be pushed
     */
    public void push(Item value) {
        top = new StackNode<>(value, top);
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
        Item poppedNodeValue = top.value;
        top = top.next;
        size--;
        return poppedNodeValue;
    }

    /**
     * @return the size of the stack
     */
    public int getSize() {
        return size;
    }

}
