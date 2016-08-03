import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class PersistentArrayTest {

	
	
	@Test
	public void initializeTest() throws IOException {
		PersistentArray array = new PersistentArray("PersistentArray.bin");
		PersistentArray.initialize("PersistentArray.bin", 10, 5);
		for(int i = 0; i < 10; i++){
			long valueCheck = array.get(i);
			assertEquals(5, valueCheck);
		}
	}
	@Test
	public void getSetTest() throws IOException {
		PersistentArray array = new PersistentArray("PersistentArray.bin");
		array.set(0, 75);
		long gotten = array.get(0);
		assertEquals(75, gotten);
	}
	@Test
	public void lengthTest() throws IOException {
		PersistentArray array = new PersistentArray("PersistentArray.bin");
		long length = array.getLength();
		assertEquals(80, length);
	}

}
