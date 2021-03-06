
/**
 * CS 251: Data Structures and Algorithms
 * Project 3: Part 1
 *
 * TODO: implement scapegoat.
 *
 * @author TODO: add your username here
 * @username TODO: add your Purdue username here
 * @sources TODO: list your sources here
 *
 * Use the algorithms written in sorting to implement this class.
 *
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Scapegoat {

    // Root node
    private Node root;
    // max node count required to implement deletion.
    private int MaxNodeCount = 0;
    // total node number
    private int NodeCount = 0;
    // parameter alpha
    private static final double threshold = 0.57;

    public class Node {
        T data;
        Node parent;
        Node left;
        Node right;
        public Node (T data, Node parent, Node left, Node right){
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
        public String toString(){
            return "[data="+data+"]";
        }
    }




    /**
     *
     * Constructor
     *
     */
    public Scapegoat() {
        root = null;
    }


    /**
     *
     * Constructor
     *
     */
    public Scapegoat(T data) {
        root = new Node(data, null, null, null);
        NodeCount ++;
    }


    /**
     *
     * @return return the root of the scapegoat tree
     *
     */
    public Node root(){
        return this.root;
    }


    /**
     *
     * This function finds the first scapegoat node and returns it.
     *
     * @return void
     *
     */
    private Node scapegoatNode(Node node) {
        // TODO:
        // -----------------------
        /*
        if (size(node) <= threshold * size(node.parent)) {
            return scapegoatNode(node.parent);
        }
        else {
            return node;
        }
        */
        if ((double)size(node) <= threshold * (double)size(node.parent)) {
            return scapegoatNode(node.parent);
        }
        else if (size(node) > threshold * size(node.parent)) {
            return node.parent; // todo: NOTE: Made this parent
        }
        else {
            return node.parent;
        }
        // -----------------------
    }


    /**
     *
     * This function re-builds the tree rooted at node into a perfectly balanced tree.
     *
     * @return void
     *
     */
    public Node rebuild(Node node) {
        // TODO
        // rebuild the subtree whose root is node
        // -----------------------

        // Get list of all nodes
        List<Node> nodeList = inorder(node);

        /*
        // Sort list
        ArrayList<Node> sortList = new ArrayList<Node>();
        for (Node n : nodeList) {
            sortList.add(n);
        }
        Sorting sort = new Sorting();
        sortList = sort.insertionSort(sortList);
        */
        Node nodeParentRef = node.parent; // Save the parent
        Node baseNode = rebuildMain(nodeList, 0, nodeList.size() - 1, node.parent);
        baseNode.parent = nodeParentRef;
        if (nodeParentRef != null) { // Connect rebuilt tree to parent
            if (baseNode.data.compareTo(nodeParentRef.data) <= 0) {
                nodeParentRef.left = baseNode;
            }
            else {
                nodeParentRef.right = baseNode;
            }
        } else {
            this.root = baseNode;
        }

        return baseNode;
        // -----------------------
    }

    public Node rebuildMain(List<Node> nodeList, int startIndex, int endIndex, Node parentNode) {

        if (startIndex > endIndex) { // If no more indices here...
            return null;
        }

        // Find middle index
        int middleIndex = (startIndex + endIndex) / 2;
        if (((startIndex + endIndex) % 2) == 0) {
            middleIndex = middleIndex;
        }
        else {
            middleIndex++;
        }
        Node midNode = nodeList.get(middleIndex);

        // set parent, left, right for given node
        midNode.parent = parentNode;
        midNode.left  = rebuildMain(nodeList, startIndex, middleIndex - 1, midNode);
        midNode.right = rebuildMain(nodeList, middleIndex + 1, endIndex, midNode);

        // will eventually return the root of this subtree
        return midNode;
    }

    // WARNING: DO NOT USE
    /*
    public Node rebuildMain(ArrayList<Node> nodeList, int startIndex, int endIndex, Node parentNode) {
        if (endIndex < startIndex) {
            return null;
        }

        int middleIndex = (startIndex + endIndex) / 2;

        Node midNode = nodeList.get(middleIndex);

        midNode.parent = parentNode;
        midNode.left  = rebuildMain(nodeList, startIndex, middleIndex - 1, midNode);
        midNode.right = rebuildMain(nodeList, middleIndex + 1, endIndex, midNode);

        return midNode;
    }
    */

    /*
    public Node rebuildHelper(Node root) {

        return null;
    }
    */

    /**
     *
     * This function adds an element into the tree.
     *
     * @return void
     *
     */
    public void add(T data) {
        if (root == null) { // if root is null...
            root = new Node(data, null, null, null);
            NodeCount ++;
        } else {
            // TODO:
            // -----------------------
            int heightBalance = (int)Math.floor(Math.log(size(root)) / Math.log(1/threshold)) + 1; // is this cast and function proper?

            Node newNode = new Node(data, null, null, null);
            int insertDepth = add_recursive(root, newNode, null, 0); // should the depth start at 0?

            if (insertDepth == -1) { // -1 if data already exists
                return;
            }



            // -----------------------
        }

        if (NodeCount > MaxNodeCount) {
            MaxNodeCount = NodeCount;
        }
    }

    public int add_recursive(Node root, Node newNode, Node parent, int depth) {
        if (root == null) { // base case, when reaching a valid root
            if (parent == null) { // is the root null?
                // case: root
                root = newNode; // set root to new node
                return depth; // probably just return
            }
            else {
                //root = newNode; // see if this is setting the right ref
                newNode.parent = parent; // set parent; parent's left/right set in comparison checks
            }

            // todo: do scapegoat stuff here
            NodeCount++;
            // find height balance
            int heightBalance = (int)Math.floor(Math.log(size(this.root)) / Math.log(1/threshold)); // is this cast and function proper?
            if (depth > heightBalance) {
                // unbalanced
                findScapegoat(newNode.parent);
            }
            return depth;
        }

        // compare left/right
        if (newNode.data.compareTo(root.data) == -1) {
            if (root.left == null) {
                root.left = newNode;
                return add_recursive(null, newNode, root, depth + 1);
            }
            return add_recursive(root.left, newNode, root, depth + 1); // incrementing depth in recursive call
        }
        else if (newNode.data.compareTo(root.data) == 1) {
            if (root.right == null) {
                root.right = newNode;
                return add_recursive(null, newNode, root, depth + 1);
            }
            return add_recursive(root.right, newNode, root, depth + 1);
        }
        else {
            // case: same element
            return -1;
        }
    }

    public void findScapegoat(Node currentNode) {
        Node foundNode = scapegoatNode(currentNode);
        if (foundNode == null) {
            return;
        }
        rebuild(foundNode);

    }

    /**
     *
     * This function removes an element from the tree.
     *
     * @return void
     *
     */
    public void remove(T data) {
        // TODO
        // -----------------------

        removeHelper(this.root, data);
        NodeCount--;
        if (NodeCount <= (threshold * MaxNodeCount)) {
            rebuild(this.root);
            this.MaxNodeCount = NodeCount;
        }


        // -----------------------

    }

    public Node removeHelper(Node root, T data) {
        if (root == null) {
            return null;
        }

        if (data.compareTo(root.data) == -1) {
            root.left = removeHelper(root.left, data);
        }
        else if (data.compareTo(root.data) == 1) {
            root.right = removeHelper(root.right, data);
        }
        else {
            // case: Key is leaf
            if ((root.left == null) && (root.right == null)) {
                // set parent root to null
                if (root.parent != null) {
                    if (root.data.compareTo(root.parent.data) < 0) {
                        root.parent.left = null;
                    } else {
                        root.parent.right = null;
                    }
                }
                root = null;
            } // Case 3: Key is node w/ 2 children
            else if ((root.left != null) && (root.right != null)) {
                Node successor = succNode(root); // should this be root.right?
                root.data = successor.data;
                root.right = removeHelper(root.right, successor.data);
                //return null;
            } // Case 2: key is in a node w/ one child
            else {
                Node child;
                if (root.left != null) {
                    child = root.left;
                } else {
                    child = root.right;
                }
                child.parent = root.parent;
                if (root.parent != null) {
                    // set parent left or right to child
                    if (root.data.compareTo(root.parent.data) < 0) {
                        root.parent.left = child;
                    } else {
                        root.parent.right = child;
                    }
                } else {
                    this.root = child;
                }
                root = child;
            }
        }
        return root;
    }




    // preorder traversal
    public List<Node> preorder(Node node) {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        if(node.left != null){
            nodes.addAll(preorder(node.left));
        }
        if(node.right != null){
            nodes.addAll(preorder(node.right));
        }
        return nodes;
    }


    // inorder traversal
    public List<Node> inorder(Node node) {
        List<Node> nodes = new ArrayList<Node>();
        if(node.left != null){
            nodes.addAll(inorder(node.left));
        }
        nodes.add(node);
        if(node.right != null){
            nodes.addAll(inorder(node.right));
        }
        return nodes;
    }


    // not used, but you can use this to debug
    public void print() {
        List<Node> nodes = inorder(root);
        for(int i=0;i<nodes.size();i++){
            System.out.println(nodes.get(i).toString());
        }
    }


    // return the node whose data is the same as the given data.
    public Node find(T data) {
        Node current = root;
        int result;
        while(current != null){
            result = data.compareTo(current.data);
            if(result == 0){
                return current;
            }else if(result > 0){
                current = current.right;
            }else{
                current = current.left;
            }
        }
        return null;
    }


    // find the succNode
    public Node succNode(Node node) {
        Node succ = null;
        int result;
        Node current = node;
        while(current != null){
            result = node.data.compareTo(current.data);
            if(result < 0){
                succ = current;
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return succ;
    }

    // used in scapegoatNode function, not a delicated one...
    // Just the brute force calculating the node's children's nubmer. Can be accelerated.
    private int size (Node node) {
        if (node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }


    // BFS(not used, you may use this to help you debug)
    public List<Node> breadthFirstSearch() {
        Node node = root;
        List<Node> nodes = new ArrayList<Node>();
        Deque<Node> deque = new ArrayDeque<Node>();
        if(node != null){
            deque.offer(node);
        }
        while(!deque.isEmpty()){
            Node first = deque.poll();
            nodes.add(first);
            if(first.left != null){
                deque.offer(first.left);
            }
            if(first.right != null){
                deque.offer(first.right);
            }
        }
        return nodes;
    }


    public static void main(String[] args) {
        // write your code here
        Scapegoat tree = new Scapegoat();
        tree.add(new T(40));
        tree.add(new T(10));
        tree.remove(new T(40));
        System.out.println();

        tree.add(new T(8));
        tree.add(new T(12));
        tree.add(new T(7));
        tree.add(new T(9));
        tree.add(new T(11));
        tree.add(new T(14));
        tree.add(new T(16));
        System.out.println("adding 16: "+tree.breadthFirstSearch());
        tree.add(new T(18));
        System.out.println("adding 18: "+tree.breadthFirstSearch());
        System.out.println();

        tree.remove(new T(14));
        tree.remove(new T(16));
        System.out.println("removing 14,16: "+tree.breadthFirstSearch());
        tree.remove(new T(12));
        System.out.println("removing 12: "+tree.breadthFirstSearch());
        tree.remove(new T(18));
        System.out.println("removing 18: "+tree.breadthFirstSearch());
    }


}

