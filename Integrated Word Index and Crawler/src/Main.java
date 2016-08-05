
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		PersistentArray.delete("PersistentArray.bin");
		PersistentArray array = new PersistentArray("PersistentArray.bin");
		PersistentArray.initialize("PersistentArray.bin", 10, 5);

	}

}
