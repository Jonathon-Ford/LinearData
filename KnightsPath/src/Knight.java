import java.util.ArrayList;
import java.util.List;

/*
 * This class is for the knight which holds information on its previous moves and its current move
 */
public class Knight {
	
	private List<int[]> pastMoves;
	private int[] currentLocation;
	
	//Constructor takes in a list of past moves and copies it to save as its past moves then adds the move it is
	//currently making into the past moves and current move
	public Knight(List<int[]> pastMoves, int[] currentMove) {
		this.pastMoves = new ArrayList(pastMoves);
		this.pastMoves.add(currentMove);
		currentLocation = currentMove;
	}
	
	//returns the list of past moves
	public List<int[]> getPastMoves(){
		return pastMoves;
	}
	
	//returns its current coordinates
	public int[] getCurrentLocation() {
		return currentLocation;
	}
}