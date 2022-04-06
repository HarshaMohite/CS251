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
        
    }

    /** TODO
    * implement BFS function        
    *
    * @return true if there is a path; if no, return false.
    */
    boolean bfs()
    {
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
        /*default value provided*/
        return 0;
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
       
    }

    public static void main(String []args)
    {
        try {
            MaxFlow obmax = new MaxFlow(0);
            File myObj = new File("./src/com/cs251/sampleMaxFlowData.txt");
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
