import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

public class BipartiteMatching {
    HashMap<Integer,ArrayList<Node>> adj_list;          // adjacency list representation of graph
    int []match;                                        // which node a node is matched with
    int M, N;                                           // M, N are number of machines and cards respectively    
    boolean []used;                                     // whether a node has been already used in matching or not

    /** TODO
    * initialize constructor function. 
    * @param M : number of machines
    * @param N : number of cards
    */
    public BipartiteMatching(int M, int N)
    {
		this.M = M;
        this.N = N;
        this.adj_list = new HashMap<Integer, ArrayList<Node>>();
        this.match = new int[M + N];
        for (int i = 0; i < match.length; i++) {
            match[i] = -1;
        }
        this.used = new boolean[M + N];
    }

    /** TODO
     * gradually build the graph by inserting edges
     * this function inserts all the nodes connected with node u

     * hint : remember that this is an undirected graph.

     * @param u : node under consideration
     * @param node_list : all the nodes connected with node u
    **/
    public void insList(int u, int []node_list)
    {
        int uOffsetIndex = u + M;
        initializeArrayList(uOffsetIndex);
        for (int connectedNode : node_list) {
            // Add into ArrayList at key u
            connectedNode = connectedNode - 1;
            Node nextNode = new Node(connectedNode);
            if (!nodeListContains(adj_list.get(uOffsetIndex), connectedNode)) {
                adj_list.get(uOffsetIndex).add(nextNode);
            }

            // For other link (since it's an undirected graph)
            initializeArrayList(connectedNode);
            Node originNode = new Node(uOffsetIndex); // Node for u
            if (!nodeListContains(adj_list.get(connectedNode), uOffsetIndex)) {
                adj_list.get(connectedNode).add(originNode);
            }
        }
    }

    /*
    Initializes a new ArrayList if none is found at the current index.
    Returns true if a new list was created, false otherwise.
     */
    public boolean initializeArrayList(int key) {
        if (adj_list.get(key) == null) {
            adj_list.put(key, new ArrayList<Node>());
        }
        return false;
    }

    public boolean nodeListContains(ArrayList<Node> list, int target) {
        for (Node nextNode : list) {
            if (nextNode.node_id == target) {
                return true;
            }
        }
        return false;
    }

    /** TODO
    * implement DFS function
    *
    * @param v : starting node for DFS 
    *
    * @return true if there is an augment path; if no, return false.
    */
    boolean dfs(int v)
    {
        if (used[v]) {
            return false;
        }
        used[v] = true;
        ArrayList<Node> adjacencyList = adj_list.get(v);
        for (Node u : adjacencyList) {
            if (match[u.node_id] == -1) {
                // an augmented path is found,
                // update Match and return true
                match[u.node_id] = v;
                match[v] = u.node_id;
                return true;
            }
            else { // there is a match
                // w is u's match
                // w is the node matched with u
                int w = match[u.node_id];
                if (!used[w]) {
                    if (dfs(w)) {
                        // an augmented path is found
                        match[u.node_id] = v;
                        match[v] = u.node_id;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** TODO
    *
    * implement the bipartite matching algorithm
    * traverse the nodes
    * call dfs to see if there exists any augment path
    *
    * @return the max matching
    */
    int bipartiteMatching()
    {
        int res = 0;
        for (int i = 0; i < M; i++) {
            // Initialize used array to false
            for (int j = 0; j < this.used.length; j++) {
                this.used[j] = false;
            }
            if (match[i] == -1) {
                if (dfs(i)) {
                    res++;
                }
            }

        }

        return res;
    }

    public static void main(String []args)
    {
        try {
            BipartiteMatching model = new BipartiteMatching(0, 0);
            File myObj = new File("./src/sampleBipartiteData.txt");
            Scanner myReader = new Scanner(myObj);
            int line = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
//                System.out.println(data);
                if(line == 0)
                {
                    String []str = data.split(" ");
                    int M = Integer.parseInt(str[0]);
                    int N = Integer.parseInt(str[1]);
                    System.out.println(M + "  " + N);
                    model = new BipartiteMatching(M, N);
                }
                else
                {
                    String []str = data.split(" ");
                    int [] input = new int[str.length];
                    for (int i=0; i<str.length; i++)
                        input[i] = Integer.parseInt(str[i]);

//                    System.out.println(input);
                    model.insList(line-1, input);
                }
                line += 1;
            }
            myReader.close();
            int res = model.bipartiteMatching();
            System.out.println("BipartiteMatching is: "+res);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}