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
        return 0;
    }

    public static void main(String []args)
    {
        try {
            BipartiteMatching model = new BipartiteMatching(0, 0);
            File myObj = new File("./src/com/cs251/sampleBipartiteData.txt");
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