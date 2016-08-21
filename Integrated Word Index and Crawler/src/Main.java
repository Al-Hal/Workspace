
import java.io.IOException;

public class Main {

	static String fileName = "shalladay";
	
	public static void main(String[] args) throws IOException {
		WordIndex.delete(fileName);
		WordIndex.initialize(fileName, 10);
		Visit visit = new Visit();
		Crawler crawler = new Crawler("http://shalladay-iis1.student.neumont.edu", 5, visit);
		crawler.crawl();
	}

}
