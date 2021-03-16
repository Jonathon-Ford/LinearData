import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Jonathon Ford
 * Project 2 Knights Path
 * Gerald Heuring
 * 
 * This is a program that will find the shortest path for a knight to make on a chess board to go from a user inserted 
 * spot one to a final spot.
 */
public class Project2Main {

	public static void main(String args[]) throws Exception {
		
		int startX, startY, endX, endY;
		
		//Take in user inputed start and end locations for the knight
		Scanner input = new Scanner(System.in);
		
		while(true){
			
			//Start with the starting location
			System.out.println("Input the starting row and column separated by a space:");
			startX = input.nextInt();
			startY = input.nextInt();
			
			if(startX > 7 || startX < 0) {
				System.out.println("The column must be between 0 and 7. Try again.");
			}
			else if(startY > 7 || startY < 0) {
				System.out.println("The row must be between 0 and 7. Try again.");
			}
			else 
				break;
		}
		
		while(true) {
			//Then get the desired location
			System.out.println("Input the final row and column separated by a space:");
			endX = input.nextInt();
			endY = input.nextInt();
			
			if(endX > 7 || endX < 0) {
				System.out.println("The column must be between 0 and 7. Try again.");
			}
			else if(endY > 7 || endY < 0) {
				System.out.println("The row must be between 0 and 7. Try again.");
			}
			else 
				break;
		}
		
		input.close();
		
		//Declare the first location and queue to make the first knight, and then declare the final move to compare with
		//moves the knight makes
		int[] firstLocation = {startX,startY};
		int[] finalLocation = {endX,endY};
		Queue<Knight> movesToMake = new Queue<Knight>();
		
		//Create the first knight and put it in the queue for moves to make
		Knight firstKnight = new Knight(new ArrayList<int[]>(), firstLocation);
		movesToMake.enqueue(firstKnight);
		
		//Get the Knight that lands on the desired location first
		Knight finalKnight = pathfinder(movesToMake, finalLocation);
		
		System.out.println("Found Path:");
		for(int i = 0; i < finalKnight.getPastMoves().size(); i++) {
			System.out.println(finalKnight.getPastMoves().get(i)[0] + "," + finalKnight.getPastMoves().get(i)[1]);
		}
	}
	
	
	/*
	 * This outer loop goes through an ever expanding queue of knights, it removes one to spawn its possible moves
	 */
	public static Knight pathfinder (Queue<Knight> movesToMake, int[] finalLocation) throws Exception {
		
		//List of possible moves to make
		int[][] moves = {	{-2,-1},
							{-2, 1},
							{-1, 2},
							{1, 2},
							{2, 1},
							{2,-1},
							{1,-2},
							{-1,-2},
						};
		//A boolean board to help cut down on pathfinding time, it will stop the program from adding paths to the
		//queue that have already been there (as it will be a shorter path)
		boolean[][] chessBoard = new boolean[8][8];
		
		//Until the final Knight is found loop through the ever growing queue of moves
		while(true) {
			
			//Set up a counter and set it to 0 every time it hits this point to loop through the possible moves,
			//a knight to spawn off of, a current location to change, and then set the current location to be true
			//on the chessBoard
			int counter = 0;
			Knight currentParent = movesToMake.dequeue();
			int[] currentLocation = currentParent.getCurrentLocation();
			chessBoard[currentLocation[0]] [currentLocation[1]] = true;
			
			//Loop through the 8 moves that can be made
			while(counter < 8) {
				
				//set the next move to be current X coordinate plus the move to me made X coordinate and same for y
				int[] nextMove = {(currentLocation[0] + moves[counter][0]),(currentLocation[1] + moves[counter][1])};
				
				//If it is the final move then create a new knight and return it to be the final knight
				if (Arrays.equals(nextMove, finalLocation)) {
					return new Knight(currentParent.getPastMoves(), nextMove);
				}
				//Add the newly moved knight only if it can exist on the board and a knight has not already been there
				else if (nextMove[0] >= 0 && nextMove[0] <= 7 && nextMove[1] >= 0 && nextMove[1] <= 7 && !chessBoard[nextMove[0]][nextMove[1]]) {
					movesToMake.enqueue(new Knight(currentParent.getPastMoves(), nextMove));
				}
				
				//Move on to the next possible move
				counter++;
			}
		}
	}
}
