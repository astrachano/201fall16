import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class BestGuesser {
	 private ArrayList<String> myWordList;
	 private HashMap<String,int[]> myCommon;
	  
	    public BestGuesser() {
	    		// We do the variable we already have.
	        myWordList = new ArrayList<String>();
	        myCommon = new HashMap<String,int[]>();
	        Scanner s;
			try {
				s = new Scanner(new File("kwords5.txt"));
			
		        while (s.hasNext()) {
		            myWordList.add(s.next());
		        }
	        s.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public String getGuess(){
	    	return getBest(myWordList);
	    }
	    public String getBest(List<String> words){
	    	myWordList = new ArrayList<String>(words);
	    	doRemoveData();
	    	int total = myWordList.size();
	    	HashMap<String,Double> map = new HashMap<String,Double>();
	    	for(String s : myWordList) {
	    		int[] data = myCommon.get(s);
	    		double sum = 0.0;
	    		for(int i : data) {
	    			double prob = 1.0*i/total;
	    			int remove = total-i;
	    			sum += prob*remove;
	    			System.out.printf("%2.2g\t%d\n", prob,remove);
	    		}
	    		map.put(s, sum);
	    		System.out.printf("%s\t%3.2g\n", s,sum);
	    	}
	    	double best = Collections.max(map.values());
	    	ArrayList<String> b = new ArrayList<String>();
	    	for(String s : map.keySet()){
	    		if (map.get(s) == best){
	    			b.add(s);
	    		}
	    	}
	    	for(String s : b) {
	    		System.out.println(s+" "+best);
	    	}
	    	Collections.shuffle(b);
	    	return b.get(0);
	    }
	    
	    private void doRemoveData(){
	    	int count = 0;
	    	System.out.printf("processing %d words\n",myWordList.size());
	    	for(String s : myWordList) {
	    		count++;
	    		if (count % 100 == 0) {
	    			System.err.print(".");
	    		}
	    		int[] data = new int[6];
	    		myCommon.put(s, data);
	    		for(String t : myWordList) {
	    			
	    			int common = commonCount(s,t);
	    			data[common]++;
	    		}
	    	}
	    	System.err.println("..finished");
	    }
	    
	    public double score(String word) {
	    	int[] counts = new int[6];
	    	for(String s : myWordList) {
	    		int common = commonCount(s,word);
	    		counts[common]++;
	    	}
	    	return 0;
	    }
	    
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
	    
	    public static void main(String[] args) {
	    	BestGuesser bg = new BestGuesser();
	    	String[] a = {"audit","berry","clean","debug","exist","frame","ghost","habit","items","jelly"};
	    	List<String> s = Arrays.asList(a);
	    	System.out.println("best guess: "+bg.getBest(s));
	    }
}
