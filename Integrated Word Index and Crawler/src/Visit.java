import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Visit  implements VisitAction{

	WordIndex index;
	String url;
	String fileName = "shalladay";
	
	public Visit() throws IOException {
		WordIndex.delete(fileName);
		WordIndex.initialize(fileName, 10);
		index = new WordIndex(fileName);
	}
	
	@Override
	public void visit(URL u) {
		url = u.toString();
		System.out.println(u.toString());
	}

	@Override
	public void getLine(String line){
		String wordPattern = "\\w+";
		Pattern p = Pattern.compile(wordPattern);
		Matcher m = p.matcher(line);
		
		if(m.find()){
			try {
				index.add(m.group(), url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
