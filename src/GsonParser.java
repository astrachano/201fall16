import com.google.gson.*;
import java.util.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class GsonParser implements IJottoJsonParser {

	@Override
	public String[] getGuesses(String jstring) {
		Type type = new TypeToken<List<Jot>>(){}.getType();
	    List<Jot> list = new Gson().fromJson(jstring, type);
	    String[] ret = new String[list.size()];
	    for (int i=0;i < list.size();i++) {
	      Jot x = list.get(i);
	      System.out.println(x);
	      ret[x.myRank] = x.myGuess + ":" + x.myCommon;
	    }
		return ret;
	}

}
