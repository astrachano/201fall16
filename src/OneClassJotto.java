import java.io.*;
import java.util.*;
/**
 * Jotto in one class, part of a case-study for Compsci 201 @ Duke
 * @author ola
 * @date August, 2016
 *
 */

public class OneClassJotto {
	private ArrayList<String> myWords;

	/**
	 * Initialize Jotto by reading in 5-letter words so that 
	 * a game can be played.
	 */
	
	public OneClassJotto() {
		String filename = "kwords5.txt";
		myWords = new ArrayList<String>();
		try {
			Scanner s = new Scanner(new File(filename));
			while (s.hasNext()) {
				myWords.add(s.next());
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.err.printf("couldn't open word file %s\n",filename);
			e.printStackTrace();
		}
	}

	/**
	 * Reduce universe of possible words by returning a list of those words
	 * that could be word being guessed. Create a new list that stores just
	 * those words from universe that have numCommon letters in common with guess
	 * @param universe is the current list of possible words
	 * @param guess is the word just guessed
	 * @param common is number of letters in common between guess and hidden word
	 * @return new list of strings that could be hidden word
	 */
	private ArrayList<String> reduceUniverse(ArrayList<String> universe, String guess, int numCommon) {
		ArrayList<String> keep = new ArrayList<String>();
		for(String word : universe) {
			if (commonCount(word,guess) == numCommon) {
				keep.add(word);
			}
		}
		return keep;
	}
	
	/**
	 * Play one game of Jotto where computer guesses the user's word.
	 */
	public void playGame() {

		Scanner scan = new Scanner(System.in);
		ArrayList<String> possibles = new ArrayList<String>(myWords);
		
		System.out.println("think of a five letter word, I'll try to guess it ");
		while (true) {
			if (possibles.size() == 0) {
				System.out.println("no more words to guess");
				break;
			}
			Collections.shuffle(possibles);
			String guess = possibles.get(0);
			System.out.printf("from %d words I guess %s\n",possibles.size(),guess);
			System.out.print("enter # in common for '"+guess+"' : ");
			String response = scan.nextLine();
			int common = Integer.parseInt(response);
			if (common == 6) {
				System.out.println("I guessed the word!");
				break;
			}

			// change universe to store just candidate words
			possibles = reduceUniverse(possibles,guess,common);
			possibles.remove(guess);
		}
		// game over
		scan.close();
	}

	/**
	 * Return index of first occurrence of ch in letters, -1 if no occurrence.
	 * @param ch
	 * @param letters
	 * @return index of first occurrence of ch in letters, or -1 if ch not in letters.
	 */
	private int indexOf(char ch, char[] letters){
		for(int k=0; k < letters.length; k++){
			if (letters[k] == ch) return k;
		}
		return -1;
	}

	/**
	 * Returns number of letters in common to both a and b, not 
	 * counting character at each index more than once in either string, i.e.,
	 * once a match for character in a found in b, don't count that occurrence in
	 * b again.
	 * 
	 * @param a
	 * @param b
	 * @return number of letters in common to a and b
	 */
	private int commonCount(String a, String b) {
		int count = 0;
		char[] achars = a.toCharArray();
		char[] bchars = b.toCharArray();
		for(int k=0; k < achars.length; k++){
			char ch = achars[k];
			int dex = indexOf(ch,bchars);
			if (dex >= 0){
				count++;
				bchars[dex] = '*';
			}
		}
		return count;
	}

	public static void main(String[] args) {
		OneClassJotto jotto = new OneClassJotto();
		jotto.playGame();
	}
}
