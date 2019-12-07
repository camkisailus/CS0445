package cs445.hw5;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * *****************************************************************************
 * Assignment 5 CS0445
 * *****************************************************************************
 * Boggle
 * *****************************************************************************
 * <p>
 * This class stores a dictionary containing words that can be used in a Boggle
 * game.
 *
 * @author Constantinos Costa (costa.c@cs.pitt.edu)
 * @date Thursday, November 21, 2019
 *****************************************************************************/
public class SortedDictionary implements DictionaryInterface {
    /**
     * Method to get dictionary from file; dictionary is sorted and only has
     * valid length words.
     *
     * @param wordList A list of legal words in Boggle
     * @see BoggleGUI
     */
    private ArrayList<String> dictionary; // Stores dictionary

    SortedDictionary() {
        dictionary = new ArrayList<String>();
    }

    SortedDictionary(String fname) throws Exception {
        dictionary = new ArrayList<String>();
        this.buildDictionary(fname);
    }

    /**
     * Create the SortedDictionary from the file
     *
     * @param fname the file name to be loaded
     * @use quickSort method to sort the end result
     */
    public void buildDictionary(String fname) throws Exception {
        Scanner in = new Scanner(new File(fname));
        
        while(in.hasNext()){
            String word = in.next();
            word = word.toUpperCase();
            addWord(word);
        }
        
        quickSort(dictionary,0,dictionary.size()-1);     

        
    }
    

    /**
     * This method checks if a word is in the dictionary specified by
     * buildDictionary using binary search.
     *
     * @param wordToCheck the word to be checked
     * @return true when the word is in the dictionary, false otherwise
     * @see BoggleGUI
     */
    @Override
    public boolean checkWord(String wordToCheck) {
        boolean result;
        result = BinarySearch(dictionary, 0, dictionary.size() - 1, wordToCheck);
        return result;
    }

    @Override
    public void addWord(String word) {
        dictionary.add(word);
    }

    /**
     * This method checks if a word in the tree starts
     * with the specified word
     *
     * @param word the word to be checked
     * @return true when a word match the beginning only of a
     * word in the dictionary, false otherwise
     * @see BoggleGUI
     */
    @Override
    public boolean checkWordBeginning(String word) {
       // TODO: Fill the code - You should use the binarySearch method that returns the position
        int k = BinarySearch(dictionary, word);
        if(dictionary.get(k).startsWith(word)) return true;
        if(k==0){
            String str = dictionary.get(k);
            String next = dictionary.get(k+1);
            if(str.startsWith(word) || next.startsWith(word)){
                return true;
            }
            return false;
        }else if (k==dictionary.size()){
            String str = dictionary.get(k);
            String prev = dictionary.get(k-1);
            if(str.startsWith(word) || prev.startsWith(word)){
                return true;
            }
            return false;

        }else{
            String next = dictionary.get(k+1);
            String prev = dictionary.get(k-1);
            if(next.startsWith(word) || prev.startsWith(word)){
                return true;
            }
            return false;
        }
    }

    /**
     * This method sorts a ArrayList of strings using Quicksort.
     *
     * @param dat  the ArrayList to be sorted low the leftmost boundary of the
     *             ArrayList to be sorted high the rightmost boundary of the ArrayList
     *             to be sorted
     * @param low
     * @param high
     * @return void
     */
    void quickSort(ArrayList<String> data, int low, int high) {

    	if(low<high) {
        
	        String pivot = data.get((low+high)/2);
	        int left = low;
	        int right = high;
	        
	        swap(data,(low+high)/2,right);
	        int k = partition(data, low, high-1, pivot);
	        quickSort(data, left, k-1);
	        quickSort(data, k+1, right);
        }
    }

    /**
     * Quicksort helper method that partitions a ArrayList into two halves based on
     * a "pivot." All the elements less than the pivot are placed in the left
     * half, all the rest are in the right half.
     *
     * @param data The ArrayList being sorted left The leftmost boundary right The
     *             rightmost boundary
     * @return the location of the pivot in the ArrayList
     */
    public int partition(ArrayList<String> data, int left, int right, String pivot) {
        int end = right;
    	while(left<=right){
            if(pivot.compareTo(data.get(left))<0) {
            	swap(data,left,right);
            	right--;
            }else if(pivot.compareTo(data.get(left))>=0) {
            	left++;
            }
        }
    	swap(data,left,end+1);
        return left;
    }

    /**
     * This method swaps two elements in a ArrayList (regardless of their type).
     *
     * @param data The ArrayList i The position of one element j The position of
     *             the other element
     * @return void
     */
    public void swap(ArrayList<String> data, int i, int j) {
        String str = data.get(i);
        String str_2 = data.get(j);
        data.set(i,str_2);
        data.set(j,str);
    }

    /**
     * This method performs a recursive binary search on a ArrayList. It returns
     * true if the search item is in the ArrayList and false otherwise.
     *
     * @param s The ArrayList to be searched front The leftmost boundary of the
     *          ArrayList that we're still interested in back The rightmost
     *          boundary item The thing we're searching for
     * @return true when the item is in the ArrayList, false otherwise
     */
    boolean BinarySearch(ArrayList<String> s, int front, int back,
                                String item) {
        while(front<=back){
            int mid = (front+back)/2;
            if(s.get(mid).compareToIgnoreCase(item)<0){
                int new_front = mid+1;
                return BinarySearch(s, new_front, back,item);
            }else if(s.get(mid).compareToIgnoreCase(item)>0){
                int new_back = mid-1;
                return BinarySearch(s, front, new_back,item);
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * This method performs a binary search on a ArrayList. It returns
     * the index an item might be inserted or the item itself if it was found.
     *
     * @param s The ArrayList to be searched front The leftmost boundary of
     *          the ArrayList that we're still interested in back The
     *          rightmost boundary item The thing we're searching for
     * @return the index of the desired location
     */
    static int BinarySearch(ArrayList<String> s, String item) {
        int mid, front = 0, back = s.size() - 1;
        while(front<=back){
            mid = (front+back)/2;
            if(s.get(mid).compareToIgnoreCase(item)<0){
                front = mid+1;   
            }else if(s.get(mid).compareToIgnoreCase(item)>0){
                back = mid-1;    
            }else{
            	return mid;
            }
        }
        return front;
    }

    public static void main(String[] args) throws Exception {
        SortedDictionary sd = new SortedDictionary();
        sd.buildDictionary(args[0]);
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Enter word: ");
            String word = in.next();
            word = word.toUpperCase();
            if (sd.checkWord(word))
                System.out.println("Word found");
        }

    }
}
