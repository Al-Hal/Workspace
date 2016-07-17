import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Crawler implements VisitAction{

	public ArrayList<URL> visited = new ArrayList<URL>();
	public ArrayList<URL> toVisit = new ArrayList<URL>();
	String startingUrl;
	int maxPageVisits; 

	public Crawler(String startingUrl, int maxPageVisits) throws MalformedURLException{
		this.startingUrl = startingUrl;
		this.maxPageVisits = maxPageVisits;
		URL start = new URL(this.startingUrl);
		toVisit.add(start);
	}

	public void crawl() throws IOException{
		int pagesVisited = 0;
		while(pagesVisited < maxPageVisits && !toVisit.isEmpty()){
//			if(!toVisit.isEmpty()){
				URL website = toVisit.get(0);
				visited.add(website);
				toVisit.remove(website);
				visitUrl(website);
				visit(website);
				pagesVisited++;
//			}
		}

	}

	public void visitUrl(URL url) throws IOException{
		
		LinkFinder finder = new LinkFinder();
		InputStream in = url.openStream();
		finder.processPage(in);
		URL childUrl = null;
		for(String s: finder.links){
			if(s.toLowerCase().startsWith("http:")){
				childUrl = new URL(s);
			}else if(!s.toLowerCase().startsWith("https:")){
				childUrl = new URL(startingUrl + s);
			}
			if(!visited.contains(childUrl) && !toVisit.contains(childUrl)){
				toVisit.add(childUrl);
			}
		}
	}

	public void visit(URL u) {
		System.out.println(u.toString());
	}

}
