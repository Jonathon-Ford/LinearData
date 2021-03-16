/*
 * This is code for a custom queue with the following methods
 * enqueue - adds an element to the queue
 * dequeue - removes the first in element in the queue and returns the element
 * peek - looks at the next in line
 * size - returns how many elements are in the line
 * isEmpty - returns true if the queue is empty
 */
import java.util.LinkedList;

public class Queue<T> {
	
	//The list that the queue will use
	private LinkedList<T> que;
	
	//Constructor
	public Queue() {
		que = new LinkedList<T>();
	}
	
	//Adds an element to the queue
	public void enqueue (T toEnqueue) {
		que.add(toEnqueue);
	}
	
	//Removes an element and returns the element
	public T dequeue () throws Exception {
		
		//If the que is empty throw and exception
		if(que.isEmpty()) {
			throw new Exception("Empty queue");
		}
		
		//Set a variable to be equal to the first element in the queue
		T element = que.get(0);
		
		//Remove the first element
		que.remove(0);
		
		return element;
	}
	
	//This method looks at the top of the queue
	public T peek() {
		return que.get(0);
	}
	
	//Returns the size of the list
	public int size() {
		return que.size();
	}
	
	//This method returns a true if the queue is empty
	public boolean isEmpty() {
		return que.isEmpty();
	}

}
