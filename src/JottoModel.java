import java.util.*;
import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;


/**
 * JottoModel.java
 * @author ola, mac
 * 
 * YOUR NAME HERE
 * 
 * This is the base class for a Jotto model. The idea is that you (the 
 * student) will implement the stub methods that are currently marked 
 * TODO to create a program that plays Jotto. You'll likely need to add 
 * instance variables, and (probably) helper methods as well.
 * 
 ************************************************
 * A note on Model/View
 * --------------------
 * Like many of the assignments you'll see, this one comes with a pre-made 
 * graphical user interface (or "GUI", pronounced "gooey"). That's nice!
 * 
 * Writing code for GUIs is a pain, which is why we provide it. However, 
 * your code _will_ need to interact correctly with ours. To facilitate 
 * this, we use what's called a "Model-View" design. In short, the code is 
 * broken into two major pieces: one that stores the data and does the 
 * computation (the "Model") and another to do the interaction and 
 * visualization (the "View"). In this case, we've provided the View 
 * (it's in JottoViewer.java); feel free to read that code, but you won't 
 * need to edit it, or understand what it's doing, to complete this 
 * assignment.
 * 
 * Your job is to implement the Model, (the JottoModel class) 
 * which is in this file. There are several methods provided that deal
 * with interacting with the view; those you won't need to modify.
 * 
 * There are also several methods that you will need to modify. They are 
 * marked with a TODO below.
 * 
 * You may want to implement some private, helper methods that are called
 * in the code below to avoid duplicate code, that will depend on what 
 * you write.
 */

public class JottoModel {
	// The View. We store this so that we can call its methods, 
	// like "Show a message."
    private JottoViewer myView; 
    
    // The list of legal words.
    private ArrayList<String> myWordList;
    
    /*
     * TODO: Add any instance variables you need. I recommend storing
     * the guess you just made, and probably a Random object 
     * (which generates Random numbers). See PopularityContest.java for
     * an example of using a Random object. 
     */
    
    /**
     * Initialize the model appropriately. This is going to include 
     * initializing myWordList (which is the list of possible words), 
     * plus any instance variables you choose to add.
     * 
     * TODO: Add any necessary code to this method.
     */
    public JottoModel() {
    		// We do the variable we already have.
        myWordList = new ArrayList<String>();
        try {
			initialize(new Scanner(new File("kwords5.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

  
 

    /**
     * Read in words. 
     * 
     * You don't need to modify this method at all.
     * 
     * @param s is scanner that is source of words.
     */
    public void initialize(Scanner s) {
        myWordList.clear();
        while (s.hasNext()) {
            myWordList.add(s.next());
        }
    }

    public String getGuessFromGuesses(String guesses){
    	
    	ArrayList<String> words = new ArrayList<String>(myWordList);
 	
    	System.out.println("json: "+guesses);
    	IJottoJsonParser pp = new GsonParser(); // new JsonParser(); //new SimpleParser();
    	String[] data = pp.getGuesses(guesses);
		for(int k=0; k < data.length; k++) {
			String combo = data[k];
			String guess = combo.substring(0, 5);
			int howMany = Integer.parseInt(combo.substring(6));
			
			int before = words.size();
			Iterator<String> iter = words.iterator();
			while (iter.hasNext()) {
				String word = iter.next();
				if (commonCount(word,guess) != howMany) {
					//System.out.printf("%s %s %d\n", word,guess,howMany);
					iter.remove();
				}
			}
			words.remove(guess);
			int after = words.size();
			System.out.printf("b/a %d %d\n",before,after);
		}
    	if (words.size() > 0) {
	    	Collections.shuffle(words);
	    	return words.get(0);
    	}
    	return "";
    }
    
    /**
     * Returns number of letters in common to a and b, ensuring
     * each common letter only counts once in total returned.
     * 
     * TODO: Implement this method! You're going to need it to actually
     * implement Jotto.
     * 
     * @param a is one string being compared
     * @param b is other string being compared
     * @return number of letters in common to a and b
     */
    
    private int indexOf(char ch, char[] letters){
    	for(int k=0; k < letters.length; k++){
    		if (letters[k] == ch) return k;
    	}
    	return -1;
    }
    
    private int commonCount(String a, String b) {
    	    // TODO: Implement this method!
    		
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
}
