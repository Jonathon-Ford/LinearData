/*
 * Jonathon Ford
 * Linear Data Structors
 * Gerald Heuring
 * 
 * This program is a movie storage and retrieval system that utilizes a custom hash table and linked list
 * 
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Project3Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//Set up the scanner for inputs and the file for the movies
		Scanner input = new Scanner(System.in);
		File movieList = new File("movies.txt");
		Scanner scanMovieList = new Scanner(movieList);
		
		//Get the length of the hash table
		System.out.println("Input a size for the hash table:");
		int hashSize;
		while(true) {
			hashSize = input.nextInt();
			input.nextLine();
			//If input is not over 0 tell them it is invalid and try again
			if(hashSize >= 1) {
				break;
			}
			else {
				System.out.println("Hash size must al least be 1, try again:");
			}
		}
		
		//Set up the hash table and add all of the movies on the list to to the hash table
		HashTable hash = new HashTable(hashSize);
		
		//While the file movies.txt has another line add the title runtime and date released to a new movie then to the hash
		while(scanMovieList.hasNextLine()) {
			
			try {
				hash.add(new Movie(
						scanMovieList.nextLine(),
						Integer.parseInt(scanMovieList.nextLine()),
						Integer.parseInt(scanMovieList.nextLine())
								  )
						);
			}
			catch(NoSuchElementException notEnoughLines)
			{
				System.out.println("At least one movie was less than 3 lines long.");
				notEnoughLines.printStackTrace();
				break;
			}
			catch(NumberFormatException zipNotFound)
			{
				System.out.println("When looking for a date or runtime a valid number was not found.");
				zipNotFound.printStackTrace();
				break;
			}
		}
		
		//Close the scanner
		scanMovieList.close();
		
		//Declare variables for the title of searched and added movies as well as year and runtime for added movies
		String title;
		int year, runtime;
		int getCommands = 0, findCommands = 0;
		String currentCommand;
		
		//Print Instructions
		System.out.println("Please type your next input, the list of valid inputs are:");
		System.out.println("A: To add a new movie.");
		System.out.println("L: To list all movies currently in the system.");
		System.out.println("G: To get a movie (note, this will remove it from the system).");
		System.out.println("F: To find a movie (Checks if it is in the system without removal).");
		System.out.println("O: To return the occupancy of the table.");
		System.out.println("Q: To quit the program.");
		
		//Loop until user chooses to exit
		do {
			//Make a visual break for clarity and ask for the next input
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Next input:");
			
			//Take in an input
			currentCommand = input.nextLine();
			
			switch(currentCommand) {
			
			case "A": 
				
				//Get nessisary inputs
				System.out.println("Please type the name of the movie:");
				title = input.nextLine();
				System.out.println("Please type the year the movie was released:");
				year = input.nextInt();
				System.out.println("Please type the runtime of the movie:");
				runtime = input.nextInt();
				
				//Add a movie to the hash table
				hash.add(new Movie(title, year, runtime));
				break;
				
			case "L":
				
				//Print all movies in the hash
				hash.print();
				break;
				
			case "G":
				
				//Get input for movie name
				System.out.println("Please type the name of the movie:");
				title = input.nextLine();
				
				//Search and remove the movie
				hash.searchAndRemove(title);
				getCommands++;
				break;
				
			case "F":
				
				//Get input for movie name
				System.out.println("Please type the name of the movie:");
				title = input.nextLine();
				
				//Search for the movie
				hash.search(title);
				findCommands++;
				break;
				
			case "O":
				
				//Print the occupancy
				System.out.println("The occupancy of the table is: " + hash.occupancy());
				break;
				
			case "Q":
				
				//Say goodbye, its only polite
				System.out.println("Goodbye!");
				break;
				
			default: 
				
				//Let them know their input was wack
				System.out.println("Invalid input, case matters, please try again.");
				
			}
			
		//Exit the program
		}while(!(currentCommand.equals("Q")));
		
		//Close the input
		input.close();
		
		//Print final stats
		System.out.println("Get Commands: " + getCommands);
		System.out.println("Find Commands: " + findCommands);
		System.out.println("Total movies visited: " + hash.totalMoviesSearched());
	}
}
