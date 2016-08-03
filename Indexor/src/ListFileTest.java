import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class ListFileTest {

	@Test
	public void test() throws IOException {
		ListFile.delete("test.bin");
		ListFile.initialize("test.bin");
		ListFile list = new ListFile("test.bin");
		
		Entry entry = new Entry("Dude", 69, 46);
		long offset = list.newEntry(entry);
		Entry entry2 = new Entry("lemon", 38, 35);
		long offset2 = list.newEntry(entry2);
		Entry entry3 = new Entry("louboutins", 87, 19);
		long offset3 = list.newEntry(entry3);
		
		Entry gotten = list.getEntry(offset);
		Entry gotten2 = list.getEntry(offset2);
		Entry gotten3 = list.getEntry(offset3);
		
		assertEquals(entry.getString(), gotten.getString());
		assertEquals(entry.getValue(), gotten.getValue());
		assertEquals(entry.getLink(), gotten.getLink());
		
		assertEquals(entry2.getString(), gotten2.getString());
		assertEquals(entry2.getValue(), gotten2.getValue());
		assertEquals(entry2.getLink(), gotten2.getLink());
		
		assertEquals(entry3.getString(), gotten3.getString());
		assertEquals(entry3.getValue(), gotten3.getValue());
		assertEquals(entry3.getLink(), gotten3.getLink());
		
		Entry entry4 = new Entry("louboutins", 675, 4);
		list.putEntry(offset3, entry4);
		
		Entry gotIt = list.getEntry(offset3);
		assertEquals(entry4.getString(), gotIt.getString());
		assertEquals(entry4.getValue(), gotIt.getValue());
		assertEquals(entry4.getLink(), gotIt.getLink());
	}
}
