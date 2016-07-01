import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkFinder {

	private ArrayList<String> links = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		LinkFinder finder = new LinkFinder();

		InputStream in = new FileInputStream("C:\\Users\\Baldielocks\\Downloads\\neumont.edu");
		finder.processPage(in);

		Iterator<String> linkIt = finder.getLinks();
//		
//		while(linkIt.hasNext()){
//			String link = linkIt.next();
//			System.out.println(link);
//		}

	}

	public void processPage(InputStream in) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while(reader.ready()){
			String s = reader.readLine();
			
			String pattern = "<\\s*[Aa]\\s+[Hh][Rr][Ee][Ff]\\s*=\\s*\"([^\"]+)\"\\s*(\\w+\\s*=\\s*\"[^\"]+\"\\s*)*\\s*>.*";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(s);
			boolean matches = m.matches();
			if(matches){
				System.out.println(m.group(1));
			}
			else{
				System.out.println(s);
			}
		}

	}

	public Iterator<String> getLinks() {

		return links.iterator();

	}

}

