import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class JsonParser implements IJottoJsonParser {

	@Override
	public String[] getGuesses(String jstring) {
		JSONParser parser = new JSONParser();
		try {
			Map map = (Map) parser.parse(jstring);
			String[] ret = new String[map.size()];
			for(Object o : map.keySet()) {
				Integer dex = Integer.parseInt(o.toString());
				ret[dex] = (String) map.get(o);
			}
			return ret;
		} catch (ParseException e) {
			return null;
		}
	}

}
