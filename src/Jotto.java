/**
 * @author Owen Astrachan
 * @version Sep 10, 2004
 * 
 * Starts up a Jotto program. You don't need to modify this file in any 
 * way.
 */

import org.json.simple.*;
import java.util.*;

public class Jotto {
    public static void main(String[] args){
        JottoModel model = new JottoModel();
        
        
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        map.put("apple", 3);
        map.put("stream", 4);
        map.put("great", 2);
        String json = JSONValue.toJSONString(map);
        
        System.out.println("json "+json);
        
        //String s = model.getGuessFromGuesses(json);
        JottoViewer viewer = new JottoViewer("Duke Jotto", model);
    }
}
