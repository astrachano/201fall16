
public class Jot {
	public int myRank;
	public String myGuess;
	public int myCommon;
	
	public Jot(int rank, String guess, int common) {
		myRank = rank;
		myGuess = guess;
		myCommon = common;
	}
	
	public String toString() {
		return myRank + " " + myGuess + " " + myCommon;
	}
}
