
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class CrawlerTest {

	@Test
	public void test() throws IOException {
		
		List<String> results = new ArrayList<String>();
		InputStream in = new FileInputStream("C:\\Users\\Baldielocks\\Documents\\results.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while(reader.ready()){
			String s = reader.readLine();
			results.add(s);
		}
		reader.close();
		
		Crawler crawler = new Crawler("http://shalladay-iis1.student.neumont.edu", 45);
		Iterator<String> resultIt = results.iterator();
		Iterator<URL> linkIt = crawler.visited.iterator();
		while(resultIt.hasNext() && linkIt.hasNext()){
			assertEquals(resultIt.next(), linkIt.next().toString());
		}
	}
}