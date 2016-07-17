import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkFinder {

	public ArrayList<String> links = new ArrayList<String>();


	public void processPage(InputStream in) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while(reader.ready()){
			String s = reader.readLine();
			String pattern = "<\\s*[Aa]\\s+[Hh][Rr][Ee][Ff]\\s*=\\s*\"([^\"]+)\"\\s*(\\w+\\s*=\\s*\"[^\"]+\"\\s*)*\\s*>";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(s);

			if(m.find()){
				links.add(m.group(1));
			}
		}
		reader.close();
		in.close();

	}

	public Iterator<String> getLinks() {

		return links.iterator();

	}

}

