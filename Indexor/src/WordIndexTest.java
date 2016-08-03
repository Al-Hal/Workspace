import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;

public class WordIndexTest {

	@Test
	public void test() throws IOException {
		String fileName = "indexor";
		WordIndex.delete(fileName);
		WordIndex.initialize(fileName, 10);
		WordIndex wordIndex = new WordIndex(fileName);
		PersistentArray array = new PersistentArray(fileName + ".indexArray");
		ListFile urls = new ListFile(fileName + ".urlList");
		ListFile words = new ListFile(fileName + ".wordList");

		wordIndex.add("shoes", "lolashoetique.com");
		wordIndex.add("tops", "hotmiamistyles.com");
		wordIndex.add("dresses", "hotmiamistyles.com");
		wordIndex.add("dresses", "fashionnova.com");
		wordIndex.add("tops", "hotmiamistyles.com");

		int index = (int)("tops".hashCode()% 10);
		long offset = array.get(index);
		Entry wordEntry = words.getEntry(offset);
		Entry urlEntry = urls.getEntry(wordEntry.getValue());
//		Entry urlEntry2 = new Entry(null, 0, 0);
//
//		if(urlEntry.getLink() != -1){
//			urlEntry2 = urls.getEntry(urlEntry.getLink());
//		}
		//		System.out.println(urlEntry.getLink());
//		assertEquals("dresses", wordEntry.getString());
//		assertEquals("hotmiamistyles.com", urlEntry.getString());
//		assertEquals("fashionnova.com", urlEntry2.getString());
		
		assertEquals("tops", wordEntry.getString());
		assertEquals("hotmiamistyles.com", urlEntry.getString());
//		assertEquals("fashionnova.com", urlEntry2.getString());
		
		//		ArrayList<WordIndex.UrlEntry> dressUrls = new ArrayList<WordIndex.UrlEntry>();
		//		WordIndex.UrlEntry u1 = new WordIndex.UrlEntry(urlEntry.getString(), urlEntry.getValue());
		//		WordIndex.UrlEntry u2 = new WordIndex.UrlEntry(urlEntry2.getString(), urlEntry2.getValue());
		//		
		//		dressUrls.add(u1);
		//		dressUrls.add(u2);
		Iterator<WordIndex.UrlEntry> url = wordIndex.getUrls("tops");
		while(url.hasNext()){
			WordIndex.UrlEntry u1 = url.next();
			System.out.println(u1.getUrl() + " " + u1.getCount());
		}
	}

}
