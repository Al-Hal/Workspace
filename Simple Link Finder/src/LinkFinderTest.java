import static org.junit.Assert.*;
import org.hamcrest.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class LinkFinderTest {

	@Test
	public void test() throws IOException {

		List<String> results = new ArrayList<String>();
		InputStream in = new FileInputStream("C:\\Users\\Baldielocks\\Downloads\\results");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while(reader.ready()){
			String s = reader.readLine();
			results.add(s);
		}
		reader.close();
		
		LinkFinder finder = new LinkFinder();
		
		Iterator<String> resultIt = results.iterator();
		Iterator<String> linkIt = finder.getLinks();
		while(resultIt.hasNext() && linkIt.hasNext()){
			assertEquals(results, finder.links);
		}
			
		
	}

}
