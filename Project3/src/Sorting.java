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

    public ArrayList<Item> mergeSort2(ArrayList<Item> A, int l, int r) {
        ArrayList<Item> newList = A;
        if (l < r) {
            int m = (l + r) / 2;
            A = mergeSort2(A, l, m);
            A = mergeSort2(A, m + 1, r);
            A = merge(A, l, m, r);
        }

        return A;
    }

    public ArrayList<Item> merge(ArrayList<Item> A, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        ArrayList<Item> L = new ArrayList<Item>(n1 + 1);
        ArrayList<Item> R = new ArrayList<Item>(n2 + 1);

        // get Items from A, put in L
        for (int i = 0; i <= n1 - 1; i++) {
            L.add(A.get(l + i));
        }
        // get Items from A, put in R
        for (int j = 0; j <= n2 - 1; j++) {
            R.add(A.get(m + j + 1));
        }

        //L.add(n1, null);
        //R.add(n2, null);
        int i = 0;
        int j = 0;
        int kPos = l;
        for (int k = l; k <= r - 1; k++) {
            /*if (i >= L.size() || j >= R.size()) {
                break;
            }*/

            if (i < L.size() && j < R.size() && (lessThan(L.get(i), R.get(j)) || equal(L.get(i), R.get(j)))) {
                A.set(k, L.get(i));
                kPos++;
                i++;
            }
            else {
                if (j >= R.size()){
                    //kPos = k;
                    break;
                }
                A.set(k, R.get(j));
                kPos++;
                j++;
            }
            //kPos = k;
        }
        //kPos++;
        while (i < n1) {
            A.set(kPos, L.get(i));
            kPos++;
            i++;
        }
        while (j < n2) {
            A.set(kPos, R.get(j));
            kPos++;
            j++;
        }
        /*
        while (i < L.size() && kPos <= r - l + 1) {
            A.set(kPos, L.get(i));
            kPos++;
            i++;
        }
        while (j < R.size() && kPos <= r - l + 1) {
            A.set(kPos, R.get(j));
            kPos++;
            j++;
        }
        */
        return A;
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
        //Integer[] K = {4,4,3,1,3,9,8,2,5,6};
        Integer[] K = {4, 6, 3, 5, 7, 1};

        for (Integer k : K) {
            A.add(k);
        }

        s.print(A);

        A = s.mergeSort(A);
        s.print(A);

    }

}
