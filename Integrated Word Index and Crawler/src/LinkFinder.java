import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LinkFinder {

	public ArrayList<String> links = new ArrayList<String>();
	Visit visit;

	public LinkFinder(Visit visit) {
		this.visit = visit;
	}

//	public void processPage(InputStream in) throws IOException {
//
//		Stream<String> lines = getLines(in);
//
//		in.close();
//
//	}

	public Stream<String> getLines(InputStream in){
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		Stream<String> lines = reader.lines();
		String pattern = "<\\s*[Aa]\\s+[Hh][Rr][Ee][Ff]\\s*=\\s*\"([^\"]+)\"\\s*(\\w+\\s*=\\s*\"[^\"]+\"\\s*)*\\s*>";
		Pattern p = Pattern.compile(pattern);
		lines = lines.filter((l) -> {
			Matcher m = p.matcher(l);
			if(m.find()) 
				links.add(m.group(1));
			return true;
		});
		return lines;

	}

	public Stream<String> getLinks(){
		return links.stream();
	}
}

