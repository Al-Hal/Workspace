import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Crawler{

	private ArrayList<URL> visited = new ArrayList<URL>();
	private ArrayList<URL> toVisit = new ArrayList<URL>();
	String startingUrl;
	int maxPageVisits;
	Visit visit;

	public Crawler(String startingUrl, int maxPageVisits, Visit visit) throws MalformedURLException{
		this.startingUrl = startingUrl;
		this.maxPageVisits = maxPageVisits;
		URL start = new URL(this.startingUrl);
		toVisit.add(start);
		this.visit = visit;
	}

	public void crawl() throws IOException{
		int pagesVisited = 0;
		while(pagesVisited < maxPageVisits && !toVisit.isEmpty()){
			URL website = toVisit.get(0);
			visited.add(website);
			toVisit.remove(website);
			visit.visit(website);
			visitUrl(website);
			pagesVisited++;
		}

	}

	private void visitUrl(URL url) throws IOException{

		LinkFinder finder = new LinkFinder(visit);
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

}
