/**
 * CS 251: Data Structures and Algorithms
 * Project 3: Part 1
 *
 * TODO: implement the sorting methods.
 *
 * @author TODO: add your name here
 * @username TODO: add your Purdue username here
 * @sources TODO: list your sources here
 *
 * DO NOT COPY PASTE THE ALGORITHMS FOR SORTING METHODS FROM THE INTERNET. WE WILL KNOW!
 *
 */

import java.util.ArrayList;

public class Sorting<Item extends Comparable<Item>> {


    /**
     *
     * Default Constructor
     *
     */
    public Sorting() {
    }

    /**
     *
     * returns true if the Item at index @param i in @ param list is greater than Item at index @param j
     * else return false
     *
     * @param list
     * @param i
     * @param j
     * @return
     */
    public boolean greaterThan(ArrayList<Item> list, int i, int j) {
        return list.get(i).compareTo(list.get(j)) > 0;
    }

    /**
     *
     * returns true if the Item @param i is greater than Item @param j else return false
     *
     * @param i
     * @param j
     * @return
     */
    public boolean greaterThan(Item i, Item j) {
        return i.compareTo(j) > 0;
    }


    /**
     *
     * returns true if the Item at index @param i in @ param list is lesser than Item at index @param j
     * else return false
     *
     * @param list
     * @param i
     * @param j
     * @return
     */
    public boolean lessThan(ArrayList<Item> list, int i, int j) {
        return list.get(i).compareTo(list.get(j)) < 0;
    }

    /**
     *
     * returns true if the Item @param i is less than Item @param j else return false
     *
     * @param i
     * @param j
     * @return
     */
    public boolean lessThan(Item i, Item j) {
        return i.compareTo(j) < 0;
    }


    /**
     *
     * returns true if the Item @param i is equal to Item @param j else return false
     *
     * @param i
     * @param j
     * @return
     */
    public boolean equal(Item i, Item j) {
        return i.compareTo(j) == 0;
    }


    /**
     *
     * returns true if the Item at index @param i in @ param list is equal to the Item at index @param j
     * else return false
     *
     * @param list
     * @param i
     * @param j
     * @return
     */
    public boolean equal(ArrayList<Item> list, int i, int j) {
        return list.get(i).compareTo(list.get(j)) == 0;
    }


    /**
     * Sorts the list in ascending order using Insertion Sort.
     *
     *
     * @param list
     */
    public ArrayList<Item> insertionSort(ArrayList<Item> list) {
        // TODO: part 1
        int n = list.size();
        for (int i = 0; i < n; i++) {
            int j = i -1;
            while (j >= 0 && greaterThan(list.get(j), list.get(j + 1))) {
                swap(list, j, j + 1);
                j -= 1;
            }
        }
        return list;
    }


    /**
     *
     * Sorts the list in ascending order using Merge Sort.
     *
     * @param list
     */
    public ArrayList<Item> mergeSort(ArrayList<Item> list) {
        // TODO: part 1
        ArrayList<Item> newList = mergeSort2(list, 0, list.size() - 1);
        return newList;
    }

    public ArrayList<Item> mergeSort2(ArrayList<Item> list, int l, int r) {
        ArrayList<Item> newList = list;
        if (l < r) {
            int m = (l + r) / 2;
            newList = mergeSort2(newList, l, m);
            newList = mergeSort2(newList, m + 1, r);
            newList = merge(newList,l, m, r);
        }

        return newList;
    }

    public ArrayList<Item> merge(ArrayList<Item> list, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        ArrayList<Item> L = new ArrayList<Item>(n1 + 1);
        ArrayList<Item> R = new ArrayList<Item>(n2 + 1);

        for (int i = 0; i < n1 - 1; i++) {
            L.set(i, list.get(l + i));
        }

        for (int j = 0; j < n2 - 1; j++) {
            R.set(j, list.get(m + j + 1));
        }
        int i = 0;
        int j = 0;
        for (int k = l; k < r - 1; k++) {
            if (lessThan(L.get(i), R.get(j)) || equal(L.get(i), R.get(j))) {
                list.set(k, L.get(i));
                i++;
            }
            else {
                list.set(k, R.get(j));
                j++;
            }
        }
        return list;
    }


    /**
     *
     * prints the Items in the @param list.
     *
     * @param list
     */
    public void print(ArrayList<Item> list) {
        int n = list.size();

        StringBuffer bf = new StringBuffer();
        bf.append(list.get(0).toString());

        for (int i = 1;i < n; i += 1) {
            bf.append(" " + list.get(i).toString());
        }

        System.out.println(bf.toString());
    }


    /**
     *
     * Swaps the element at index @param i with element at index @param j in the @param list.
     *
     * @param list
     * @param i
     * @param j
     */
    private void swap(ArrayList<Item> list, int i, int j) {
        Item temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     *
     * This is the main function to run the Sorting class to help with debugging.
     *
     * @param args
     */
    public static void main(String[] args) {

        Sorting<Integer> s = new Sorting<>();
        ArrayList<Integer> A = new ArrayList<Integer>();
        Integer[] K = {4,4,3,1,3,9,8,2,5,6};

        for (Integer k : K) {
            A.add(k);
        }

        s.print(A);

    }

}
