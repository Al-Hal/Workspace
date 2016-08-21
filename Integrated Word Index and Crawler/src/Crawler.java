import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Crawler implements Runnable{

	private ArrayList<URL> visited = new ArrayList<URL>();
	private ArrayList<URL> toVisit = new ArrayList<URL>();
	String startingUrl;
	int maxPageVisits;
	Visit visit;
	int pagesVisited = 0;

	public Crawler(String startingUrl, int maxPageVisits, Visit visit) throws MalformedURLException{
		this.startingUrl = startingUrl;
		this.maxPageVisits = maxPageVisits;
		URL start = new URL(this.startingUrl);
		toVisit.add(start);
		this.visit = visit;
	}

	public void crawl() throws IOException{	
		new Thread(this).start();
	}

	private void visitUrl(URL url) throws IOException, InterruptedException{

		LinkFinder finder = new LinkFinder(visit);
		InputStream in = url.openStream();
		Stream<String> lines = finder.getLines(in);
		lines.forEach((l) -> visit.getLine(l));
		URL childUrl = null;
		for(Object o: finder.getLinks().toArray()){
			String s = o.toString();
			if(s.toLowerCase().startsWith("http:")){
				childUrl = new URL(s);
			}else if(!s.toLowerCase().startsWith("https:")){
				childUrl = new URL(startingUrl + s);
			}
			if(!visited.contains(childUrl) && !toVisit.contains(childUrl)){
				toVisit.add(childUrl);
				new Thread(this).join();
			}
		}
	}

	@Override
	public void run() {
		while(pagesVisited < maxPageVisits && !toVisit.isEmpty()){
			pagesVisited++;
			URL website = toVisit.get(0);
			visited.add(website);
			toVisit.remove(website);
			visit.visit(website);
			try {
				visitUrl(website);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
