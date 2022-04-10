import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MaxFlow
{
    HashMap<Integer,ArrayList<Edge>> adj_list;      // adjacency list representation of graph
    int []parent;                                   // parent array used in bfs
    int N;                                          // total number of nodes

    /** TODO
    * initialize constructor function. 
    * @param N : number of nodes
    */
    public MaxFlow(int N)
    {
        this.N = N + 2;
        this.adj_list = new HashMap<>();
        this.parent = new int[this.N];
        Arrays.fill(this.parent, -1);
    }

    /** TODO
    * gradually build the graph by inserting edges
    * this function inserts a new edge into the graph
    *
    * hint : remember to consider the opposite direction of flow
    *
    * @param source : source node
    * @param destination : destination node
    * @param flow_rate : maximum rate of flow through the edge
    */
    public void insEdge(int source, int destination, int flow_rate)
    {
        Edge newEdge = new Edge(source, destination, flow_rate);
        Edge reverseEdge = new Edge(newEdge.destination, newEdge.source, 0);

        initializeArrayList(source);
        // make sure there's no duplicate
        boolean edgeExists = false;
        for (Edge compareEdge : adj_list.get(source)) {
            if ((compareEdge.source == newEdge.source)
                && (compareEdge.destination == newEdge.destination)) {
                edgeExists = true;
            }
        }
        if (!edgeExists) { // if no duplicate, add
            adj_list.get(source).add(newEdge);
        }

        // do the same in the other direction
        initializeArrayList(destination);
        boolean reverseEdgeExists = false;
        for (Edge compareEdge : adj_list.get(destination)) {
            if ((compareEdge.source == reverseEdge.source)
                && (compareEdge.destination == compareEdge.destination)) {
                reverseEdgeExists = true;
            }
        }
        if (!reverseEdgeExists) {
            adj_list.get(destination).add(reverseEdge);
        }

    }

    /**
     * Helper function to check if an ArrayList is initialized, and if not,
     * initialize it.
     * @param index Index in the adjacency list to check.
     */
    public void initializeArrayList(int index) {
        if (adj_list.get(index) == null) {

            adj_list.put(index, new ArrayList<>());
        }
    }

    /** TODO
    * implement BFS function        
    *
    * @return true if there is a path; if no, return false.
    */
    boolean bfs()
    {
        Arrays.fill(this.parent, -1);

        Queue<Integer> Q = new LinkedList<Integer>(); // queue of size |V|
        Q.add(0); // enqueue v
        boolean[] visited = new boolean[N];
        Arrays.fill(visited, false);
        visited[0] = true; // mark v as visited

        while (!Q.isEmpty()) {
            int u = Q.remove();
            for (Edge adjacentEdge : adj_list.get(u)) {
                int w = adjacentEdge.destination;
                if (!visited[w] && (adjacentEdge.flow_rate > 0)) {
                    Q.add(w);
                    visited[w] = true;
                    parent[w] = u;
                }
            }
        }
        if (parent[N-1] != -1) {
            return true;
        }
        return false;
    }

    /** TODO
    * implement path augmentation
    *
    * traverse the graph using BFS to find a path from source to sink
    * find the possible amount of flow along the path
    * add the flow to the total maximum flow
    * update the flow rate of the edges along the path 
    * repeat as long as a path exist from source to sink with nonzero flow
    *
    * @return maximum amount of flow
    */
    int pathAugmentation()
    {
        int pond = 0;
        int barrel = N - 1;
        int maxflow = 0;

        while (bfs()) {
            int flow = Integer.MAX_VALUE;
            ArrayList<Edge> P = getPath();
            for (Edge e : P) {
                if (e.flow_rate < flow) {
                    flow = e.flow_rate;
                }
            }
            for (Edge e : P) {
                setFlow(e.source, e.destination, e.flow_rate - flow);
                setFlow(e.destination, e.source, getFlow(e.destination, e.source) + flow);
            }
            maxflow += flow;
        }

        /*default value provided*/
        return maxflow;
    }

    public ArrayList<Edge> getPath() {
        Stack<Edge> reversePathStack = new Stack<Edge>();
        ArrayList<Edge> pathList = new ArrayList<>();
        int currentVertex = N - 1;
        int parentVertex = parent[currentVertex];
        while (currentVertex != 0) {
            Edge nextEdge = new Edge(parentVertex, currentVertex, getFlow(parentVertex, currentVertex));
            reversePathStack.push(nextEdge);
            currentVertex = parentVertex;
            parentVertex = parent[parentVertex];
        }
        while (!reversePathStack.isEmpty()) {
            pathList.add(reversePathStack.pop());
        }
        return pathList;
    }

    /** TODO
    * get the flow along a certain edge
    *
    * @param source : source node of the directed edge
    * @param destination : destination node of the directed edge
    *
    * @return flow rate along the edge
    */
    int getFlow(int source, int destination)
    {
       for (Edge flowEdge : adj_list.get(source)) {
           if ((flowEdge.source == source) && (flowEdge.destination == destination)) {
               return flowEdge.flow_rate;
           }
       }
       return 0;
    }

    /** TODO
    * set the value of flow along a certain edge
    *
    * @param source : source node of the directed edge
    * @param destination : destination node of the directed edge
    * @param flow_rate : flow rate along the edge        
    */
    void setFlow(int source, int destination, int flow_rate)
    {
       boolean edgeFound = false;

       for (Edge flowEdge : adj_list.get(source)) {
           if ((flowEdge.source == source) && (flowEdge.destination == destination)) {
               flowEdge.flow_rate = flow_rate;
               edgeFound = true;
               return;
           }
       }

       if (edgeFound == false) {
           Edge newEdge = new Edge(source, destination, flow_rate);
           adj_list.get(source).add(newEdge);
       }
    }

    public static void main(String []args)
    {
        try {
            MaxFlow obmax = new MaxFlow(0);
            File myObj = new File("./src/sampleMaxFlowData.txt");
            Scanner myReader = new Scanner(myObj);
            int line = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                if(line == 0)
                {
                    int tot = Integer.parseInt(data);
                    System.out.println(tot);
                    obmax = new MaxFlow(tot);
                }
                else
                {
                    String []comp = data.split(" ");
                    int s = Integer.parseInt(comp[0]);
                    int d = Integer.parseInt(comp[1]);
                    int f = Integer.parseInt(comp[2]);
                    System.out.println(s+" "+d+" "+f);
                    obmax.insEdge(s, d, f);
                }
                line += 1;
            }
            myReader.close();
            int mflow = obmax.pathAugmentation();
            System.out.println("Maxflow is: "+mflow);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
