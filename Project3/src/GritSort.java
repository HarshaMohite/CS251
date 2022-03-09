/**
 * CS 251: Data Structures and Algorithms
 * Project 3: Part 1
 *
 * TODO: implement gritSort.
 *
 * @author TODO: add your name here
 * @username TODO: add your Purdue username here
 * @sources TODO: list your sources here
 *
 * Use the algorithms written in sorting to implement this class.
 *
 */
import java.util.ArrayList;
import java.util.HashMap;

public class GritSort<Item extends Comparable<Item>> {

    /**
     *
     * Default Constructor
     *
     */
    public GritSort() {
    }

    /**
     *
     * Implement Grit Sort as defined in the Handout.
     * for example:
     * list = {3,5,6,8,10,2,4,5,6,1,2,4,5}
     * return value = {1,2,2,3,4,4,5,5,5,6,6,8,10}
     *
     *
     * @param list
     * @return sorted list
     *
     */
    public ArrayList<Item> grit(ArrayList<Item> list) {
        /*
            TODO: part 1
            TODO: STEP 1: implement and call makeChunks on list to divide the list into the sorted chunks.
            TODO: STEP 1.5: make the required number of buckets(can differ from implementation to implementation) and
            TODO            put each chunk into a bucket of proper size.
            TODO: STEP 2: implement and call mergeChunk to merge the chunks in a bucket. do this for every bucket
            TODO: STEP 3: merge the buckets and return the elements of the merged buckets as a list
         */
        ArrayList<ArrayList<Item>> chunkList = makeChunks(list); // divide list into sorted chunks
        HashMap<Integer, ArrayList<ArrayList<Item>>> bucketMap = makeBuckets(chunkList); // make buckets
        for (int key : bucketMap.keySet()) { // merge chunks in each bucket
            ArrayList<ArrayList<Item>> chunksInBucket = new ArrayList<ArrayList<Item>>();
            for (ArrayList<Item> chunk : bucketMap.get(key)) {
                chunksInBucket.add(chunk);
            }
            ArrayList<Item> mergedBucket = mergeChunk(chunksInBucket);
            bucketMap.get(key).clear();
            bucketMap.get(key).add(mergedBucket);
        }
        ArrayList<ArrayList<Item>> allBuckets = new ArrayList<ArrayList<Item>>(); // combined all buckets
        for (int key : bucketMap.keySet()) { // add each bucket to allBuckets
            allBuckets.add(bucketMap.get(key).get(0));
        }
        ArrayList<Item> finalList = new ArrayList<Item>();
        finalList = mergeChunk(allBuckets);
        return finalList;
    }

    /**
     *
     * This function takes in a list and returns an ArrayList of ArrayList.
     * Each chunk found is stored in an array list and then all the chunks are in turn stored in an array list which is
     * then returned.
     *
     * for example
     * list = {3,5,6,8,10,2,4,5,6,1,2,4,5}
     *
     * return value = {
     *                     {3,5,6,8,10},
     *                     {2,4,5,6},
     *                     {1,2,4,5}
     *                }
     *
     * @param list
     * @return array list of chunks(sorted sub-lists in the original input list)
     *
     */
    public ArrayList<ArrayList<Item>> makeChunks(ArrayList<Item> list) {
        // TODO: part 1
        Sorting sort = new Sorting();
        ArrayList<ArrayList<Item>> chunks = new ArrayList<ArrayList<Item>>();
        // start working on chunk 1, get first list item
        int chunkNum = 0;
        Item previous = list.get(0);
        chunks.add(0, new ArrayList<Item>()); // new arraylist instance
        if (list.size() >= 1) {
            chunks.get(0).add(list.get(0)); // add first list element
        }
        for (int i = 1; i < list.size(); i++) {
            // if next list item is less than prev., start new chunk
            if (sort.lessThan(list.get(i), previous)) {
                chunkNum++;
                chunks.add(chunkNum, new ArrayList<Item>());
            }
            chunks.get(chunkNum).add(list.get(i)); // add to chunk
            previous = list.get(i); // update prev.
        }
        return chunks;
    }



    /**
    *
    * This function takes in an ArrayList of ArrayList and returns an HashMap<Integer, ArrayList<ArrayList<Item>>> 
    * Chunks of the same size are stored in the same bucket. 
    * Each chunk of the size say S is stored in a bucket which corresponds to that size S.
    * Thus, for the returned HashMap, the key is the chunk size, and the corresponding value is a list of chunks of that size.
    * for example
    * chunks = {
    *              {3,5,6,8,10},
    *              {2,4,5,6},
    *              {1,2,4,5}
    *          }
    *
    * return value = {
    * 					4: {{2,4,5,6},{1,2,4,5}},
    * 					5: {{3,5,6,8,10}}
    * 				  }
    * i.e., the bucket for chunks of size 4 will have {{2,4,5,6},{1,2,4,5}} and
    * bucket for chunks of size 5 will have {{3,5,6,8,10}}
    *
    * @param chunks
    * @return HashMap, where key is the chunk size and value is a list of chunks of that size
    * (sorted sub-lists in the original input list)
    *
    */
    public HashMap<Integer, ArrayList<ArrayList<Item>>> makeBuckets(ArrayList<ArrayList<Item>> chunks) {
    	HashMap<Integer, ArrayList<ArrayList<Item>>> buckets = new HashMap<Integer, ArrayList<ArrayList<Item>>>();
    	// make buckets is a helper function. 
    	// CHANGE THIS ACCORDING TO YOUR IMPLEMENTATION

        // for every arraylist chunk
        for (ArrayList<Item> chunk : chunks) {
            int hashIndex = chunk.size(); // index to submit to hashmap
            // if there is no ArrayList of ArrayLists at hashIndex, create one
            if (buckets.get(hashIndex) == null) {
                buckets.put(hashIndex, new ArrayList<ArrayList<Item>>());
            }
            buckets.get(hashIndex).add(chunk); // add current chunk to hashmap bucket
        }
    	
	   	return buckets;
	}




    /**
     *
     * This function takes in a bucket(ArrayList of ArrayList) and returns an ArrayList which has the chunks in
     * the bucket merged.
     *
     * for example
     * bucket = {
     *          {2,4,5,6},
     *          {1,2,4,5}
     *         }
     *
     * return value = {1,2,2,4,4,5,5,6}
     *
     *
     * @param bucket
     * @return merged and sorted list
     *
     */
    public ArrayList<Item> mergeChunk(ArrayList<ArrayList<Item>> bucket) {
        // TODO: part 1
        ArrayList<Item> sortedList = new ArrayList<Item>();
        Sorting sort = new Sorting();
        for (ArrayList<Item> chunk : bucket) {
            sortedList.addAll(chunk);
        }
        sortedList = sort.mergeSort(sortedList);
        return sortedList;
    }




    /**
     *
     * This is the main function to run the gritSort class to help with debugging.
     *
     * @param args
     */
    public static void main(String[] args) {
        Sorting<Integer> s = new Sorting<>();
        ArrayList<Integer> A = new ArrayList<Integer>();
        Integer[] K = {3,5,6,8,10,2,4,5,6,1,2,4,5};

        for (Integer k : K) {
            A.add(k);
        }

        s.print(A);

    }

}
