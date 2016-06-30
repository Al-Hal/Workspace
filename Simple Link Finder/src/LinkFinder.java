import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class LinkFinder {

	private ArrayList<String> links = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		LinkFinder finder = new LinkFinder();

		InputStream in = new FileInputStream("C:\\Users\\Baldielocks\\Downloads\\neumont.edu");
		finder.processPage(in);
		
		while(finder.getLinks().hasNext()){
			String link = finder.getLinks().next();
			System.out.println(link);
		}

	}

	public void processPage(InputStream in) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while(reader.ready()){
			String s = reader.readLine();
			System.out.println(s);
		}
		String pattern = "<\\s*[Aa]\\s+[Hh][Rr][Ee][Ff]\\s*=\\s*\"[^\"]+\"\\s*\\w*=.*\".*[^\"]*\".*>";
	}

	public Iterator<String> getLinks() {
		
		Iterator<String> linkIt = links.iterator();

		return linkIt;

	}

}

