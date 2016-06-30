import static org.junit.Assert.*;

import org.junit.Test;

public class LinkFinderTest {

	@Test
	public void test() {
		
		LinkFinder finder = new LinkFinder();
		assertTrue(finder.getLinks("<a href=\"catalog.html\">Course Catalog</a>"));
	}

}
