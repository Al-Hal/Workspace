import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Visit  implements VisitAction{

	WordIndex index;
	String url;
	String fileName = "shalladay";

	public Visit() throws IOException {
		index = new WordIndex(fileName);
	}

	@Override
	public void visit(URL u) {
		url = u.toString();
		System.out.println(u.toString());
	}

	@Override
	public void getLine(String line){
		String wordPattern = "[^\\p{Alpha}]";
		String[] words = line.split(wordPattern);
		for(String s: words){
			if(!s.equals("")){
				try {
					index.add(s.toLowerCase(), url);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
