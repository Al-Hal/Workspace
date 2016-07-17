import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {
		Crawler crawler = new Crawler("http://shalladay-iis1.student.neumont.edu", 5);
		crawler.crawl();
	}

}
