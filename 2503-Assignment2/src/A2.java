import java.io.FileNotFoundException;
import java.util.Scanner;

/** 
 * COMP 2503 Winter 2023 Assignment 2 
 * 
 * This program must read a input stream and keeps track of the 
 * frequency at which an avenger is mentioned either by name or alias.
 *
 * @author Shamil Baig, Denzel Pascual, Ghoza Ghazali
 * @date February 6, 2023
*/

public class A2 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	/*
	 *Initialize variable
	 */
	private int topN = 4;
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);
	
	/*
	 * Initialize linked list
	 */
	private SLL<Avenger> mentionList = new SLL<Avenger>();
	private SLL<Avenger> alphabticalList = new SLL<Avenger>();
	private SLL<Avenger> mostPopularList = new SLL<Avenger>(new AvengerComparatorFreqDescending());
	private SLL<Avenger> leastPopularList = new SLL<Avenger>(new AvengerComparatorFreqAscending());
	
	public static void main(String[] args) throws FileNotFoundException {
		A2 a2 = new A2();
		a2.run();
	}

	public void run() throws FileNotFoundException {
		readInput();
		createdOrderedLists();
		printResults();
	}

	/**
	 * Method to traverse through the linked list of mentionList and add it to the other three linked lists
	 */
	private void createdOrderedLists() {
		// TODO: 
		// Create a mover and traverse through the mentionList.
		// Add each avenger to the other three lists.
		Node<Avenger> mover = mentionList.getHead();
		while (mover != null) {
			Avenger avenger = mover.getData();
			// Add to alphabetical list
			alphabticalList.addInOrder(avenger);
			// Add to most popular list
			mostPopularList.addInOrder(avenger);
			// Add to least popular list
			leastPopularList.addInOrder(avenger);
			mover = mover.getNext();
		}
		
	}

	/**
	 * read the input stream and keep track  
	 * how many times avengers are mentioned by alias or last name.
	 * @throws FileNotFoundException 
	 */
	private void readInput() throws FileNotFoundException {
		/*
		Error catching, if the input is empty prompt the user with error message.
		In a loop, while the scanner object has not reached end of stream,
		 	- read a word.
		 	- clean up the word
		    - if the word is not empty, add the word count. 
		    - Check if the word is either an avenger alias or last name then
				- Create a new avenger object with the corresponding alias and last name.
				- if this avenger has already been mentioned, increase the frequency count for the object already in the list.
				- if this avenger has not been mentioned before, add the newly created avenger to the end of the list, remember to set the frequency.
		*/ 
		if(!input.hasNext()) {
			System.out.println("*----------------------------------------*");
			System.out.println();
			System.out.println("input file is empty, try a different input");
			System.out.println();
			System.out.println("*----------------------------------------*");
			System.out.println();
		}
		while (input.hasNext()) {

			String word = cleanWord(input.next());

			if (word.length() > 0) {
				totalwordcount++;
				for(int i = 0; i < avengerRoster.length; i++) {
					if(word.equals(avengerRoster[i][0]) || word.equals(avengerRoster[i][1])) {
						Avenger avenger = new Avenger(avengerRoster[i][0], avengerRoster[i][1]);
						if(mentionList.findItem(avenger) == null) {
							avenger.addFrequency();
							mentionList.addTail(avenger);
						} else {
							mentionList.findItem(avenger).getData().addFrequency();
						}
					}
				}
			} 
		}
		input.close();
	}
	
	/**
	 * Method to catch only words and turn to lowercase
	 * @param next
	 * @return
	 */
	private String cleanWord(String next) {
		// First, if there is an apostrophe, the substring
		// before the apostrophe is used and the rest is ignored.
		// Words are converted to all lowercase.
		// All other punctuation and numbers are skipped.
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}
	

	/**
	 * print the results
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionList.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		// Todo: Print the list of avengers in the order they appeared in the input
		// Make sure you follow the formatting example in the sample output
		mentionList.printList();	
		
		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		topPrintList(mostPopularList.size(), mostPopularList.getHead(), topN);
		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");
		// Todo: Print the least popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		topPrintList(leastPopularList.size(), leastPopularList.getHead(), topN);
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of avengers in alphabetical order
		alphabticalList.printList();
		System.out.println();
	}
	
	/**
	 * Prints out the top most popular and least popular avengers according to the mention size
	 * @param size
	 * @param lList
	 * @param n
	 */
	public void topPrintList(int size, Node<Avenger> lList , int n) { 
		for(int i = 0; i < n && i < size; i++) {
			System.out.println(lList.getData());
			lList = lList.getNext();
		}
	}
}
