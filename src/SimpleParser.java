

public class SimpleParser implements IJottoJsonParser {
	public String[] getGuesses(String jstring){
		jstring = jstring.substring(1,jstring.length()-1);
		if (jstring.length() == 0) return new String[0];
		
		jstring = jstring.replaceAll("\"", "");
		
		String[] guesses = jstring.split(",");
		String[] ret = new String[guesses.length];
		for(String s : guesses) {
			String[] data = s.split(":");
			System.out.printf("%s %s\n",data[0],data[1]);
			int index = Integer.parseInt(data[0]);
			ret[index] = data[1];
		}
		return ret;
	}
}
