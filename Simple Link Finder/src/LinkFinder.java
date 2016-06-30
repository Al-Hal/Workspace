import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class LinkFinder {

	private ArrayList<String> links = new ArrayList<String>();

	public static void main(String[] args) {
		
		LinkFinder finder = new LinkFinder();
		
		
		
		while(finder.getLinks().hasNext()){
			String link = finder.getLinks().next();
			System.out.println(link);
		}

	}

	public void processPage(InputStream in) {




	}

	public Iterator<String> getLinks() {


//		String input = in.toString();
		String pattern = "<\\s*[Aa]\\s+[Hh][Rr][Ee][Ff]\\s*=\\s*\"[^\"]+\"\\s*\\w*=.*\".*[^\"]*\".*>";
		Pattern p = Pattern.compile(pattern);
//		Matcher m = p.matcher(input);

		Iterator<String> linkIt = links.iterator();
//		while(linkIt.hasNext()){
//			String link = linkIt.next();
//		}
		
		return linkIt;

	}

}

