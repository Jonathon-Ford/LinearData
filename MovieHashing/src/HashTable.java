/*
 * This class is for a hash table used for hashing
 * 
 * It can take in a movie and place it into the table given a hash key
 * Once is has found a place on the table it will save the data to a linkedList with data of the same hash key
 * 
 * You can print the table and all its contents add something, remove something, search something, or search and 
 * remove something.
 */
public class HashTable {
	
	private LinkedMovieList[] table;
	private int compressionNumber;
	
	//Constructor
	public HashTable(int length) {
		//Creates a new arrayList and sets it to the size desired
		table = new LinkedMovieList[length];
		for(int i = 0; i < length; i++) {
			table[i] = new LinkedMovieList();
		}
		compressionNumber = length;
	}
	
	//This method adds a value to the linked list located at the position on the hash table = to its key % max # available
	public void add(Movie movie) {
		//Adds the movie to the linkedMovieList located at the position
		table[movie.getHashKey() % compressionNumber].add(movie); 
	}
	
	//Method to remove a movie from the hash table
	public void remove(Movie movie) {
		table[movie.getHashKey() % compressionNumber].remove(movie);
	}
	
	//Method to search and remove a movie or null if it is not on the table
	public void searchAndRemove(String name) {
		//Generate the hash key
		int hashKey = generateKey(name);
		boolean found = false;
		//Search, remove, and return the movie or a message if not there
		found = table[hashKey % compressionNumber].searchAndRemove(name);

		//If not found let the user know
		if(!found) {
			System.out.println("Movie not found");
		}
	}
	
	//Method to search for a movie
	public void search(String name) {
		//Generate the hashKey
		int hashKey = generateKey(name);
		Boolean isFound = false;
		//Search, remove, and return true if the movie was found
		isFound = table[hashKey % compressionNumber].search(name);

		//If it was never found let them know
		if(!isFound) {
			System.out.println("Movie not found.");
		}
	}
	
	//Method to return how many items are currently stored
	public double occupancy() {
		int listTotal = 0;
		for(int i = 0; i < table.length; i++) {
			listTotal += table[i].occupancy();
		}
		return listTotal / table.length;
	}
	
	//Method to return searched movies
	public int totalMoviesSearched() {
		int searched = 0;
		for(int i = 0; i < table.length; i++) {
			searched += table[i].getVisited();
		}
		return searched;
	}
	
	//Method to print the hash table
	public void print() {
		//Loop through the table and print the linkedlists
		for(int i = 0; i < table.length; i++) {
			table[i].print();
		}
	}
	
	//Method to get a hashKey
	public static int generateKey(String name) {
		String [] words;
        int [] result;
        words = name.split(" ");
        result = new int[words.length];
        for (int wordNbr = 0; wordNbr < words.length; wordNbr++) {
            if (words.length != 0 && words[wordNbr].length() > 0) {
                result[wordNbr] = Character.valueOf(words[wordNbr].charAt(0));
            }
        }
        int hashKey = result[0];
        for(int i = 1; i < result.length; i++) {
        	hashKey = (hashKey * 128) + result[i]; 
        }
        if(hashKey < 0) {
        	hashKey *= -1;
        }
        return hashKey;
	}
}

class LinkedMovieList {
	//Save the head of the list and end of the list
	private Node head;
	private Node tail;
	private int visited;
	private int occupancy;
	
	//Constructor
	public LinkedMovieList(){
		head = null;
		tail = null;
		visited = 0;
		occupancy = 0;
	}
	
	//The class for the nodes of the LinkedList
 	class Node {
		private Movie data;//Data to store
		private Node nextNode;//Next in the list
		
		//Constructor
		public Node(Movie data) {
			this.data = data;
			nextNode = null;
		}
	}
	
	//Method to add a value to the list
	public void add (Movie data) {
		
		//If the head not null the list is empty, so set the movie as the head and tail
		if(head == null) {
			head = new Node(data);
			tail = head;
		}
		//Have the current tail point to the added movie and set the movie as the current tail
		else {
			Node addedNode = new Node(data);
			tail.nextNode = addedNode;
			tail = addedNode;
		}
		//Index the occupancy
		occupancy++;
	}
	
	//Method to remove a value and fix the list afterward
	public void remove(Movie toRemove) {
		Node test = head;
		Node testPrevious = null;
		//Loop through and check if the node contains the movie
		while(test != null && !(test.data.equals(toRemove)) ) {
			testPrevious = test;
			test = test.nextNode;
			visited++;
		}
		
		//If the correct movie is found
		if(test.data.equals(toRemove)) {
			//And it is not the first in the list
			if(testPrevious != null) {
				//And it is not the last in the list
				if(test.nextNode != null)
					//Have the previous node point to the movie after this one
					testPrevious.nextNode = test.nextNode;
				else
					//If it is the last in the list, have it point to null (the end)
					testPrevious.nextNode = null;
			}
			else {//If it is the first
				//And not the only
				if(test.nextNode != null) {
					head = test.nextNode;
				}
				else {
					head = null;
					tail = null;
				}
			}
			occupancy--;
		}
		else {
			System.out.println("Movie not found");
		}
	}
	
	//Method for searching then removing data
	public boolean searchAndRemove (String name) {
		Node test = head;
		Node testPrevious = null;
		String storedName = "";
		//Loop through the LinkedList while it is not at the end
		while(test != null) {
			//Get the title
			storedName = test.data.getTitle();
			visited++;
			//If the title does not match what we are looking for
			if(!(storedName.equals(name))) {
				//Loop through to the next title and save the previous one
				testPrevious = test;
				test = test.nextNode;
			}
			else
				break;
		}
		
		//If the correct movie is found
		if(storedName.equals(name)) {
			//And it is not the first in the list
			if(testPrevious != null) {
				//And it is not the last in the list
				if(test.nextNode != null)
					//Have the previous node point to the movie after this one
					testPrevious.nextNode = test.nextNode;
				else
					//If it is the last in the list, have the previous node point to null (the end)
					testPrevious.nextNode = null;
			}
			else {//If it is the first
				//And not the only
				if(test.nextNode != null) {
					head = test.nextNode;
				}
				//If it is first and only, set the list to be empty
				else {
					head = null;
					tail = null;
				}
			}
			
			occupancy--;
			//Print the movie data
			System.out.println(test.data);
			//Return the data if found
			return true;
		}
		else {
			//Return null if not found
			return false;
		}
	}
	
	//Method for searching if a movie is on the table
	public boolean search (String name) {
		Node test = head;
		String storedName = "";
		//Go through and look for a movie with the title given
		while(test != null) {
			storedName = test.data.getTitle();
			visited++;
			if(!(storedName.equals(name))) {
				test = test.nextNode;
			}
			else
				break;
		}
		
		if(storedName.equals(name)) {
			System.out.println(test.data);
			return true;
		}
		else {
			return false;
		}
	}

	//Method to return how many elements are in the list
	public int occupancy () {
		return occupancy;
	}
	
	//Method to get searched
	public int getVisited() {
		return visited;
	}
	
	//Method to print the List
	public void print () {
		Node test = head;
		while(test != null) {
			System.out.println(test.data);
			test = test.nextNode;
		}
	}
}

