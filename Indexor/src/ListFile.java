import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ListFile {

	private static RandomAccessFile file;

	public static void initialize(String listFileName) throws IOException{
		file = new RandomAccessFile(listFileName, "rws");
		file.close();
	}

	public static void delete(String listFileName){
		File file = new File(listFileName);
		file.delete();
	}

	public ListFile(String listFileName) throws FileNotFoundException{
		File file = new File(listFileName);
		if(!file.exists()){
			throw new FileNotFoundException();
		}
		else{
			this.file = new RandomAccessFile(listFileName, "rws");
		}
	}

	public void close() throws IOException{
		file.close();
	}

	public long newEntry(Entry entry) throws IOException{
		long position = file.length();
		
		putEntry(position, entry);
		
		return position;
	}

	public Entry getEntry (long offset) throws IOException{
		file.seek(offset);
		int stringLength = file.readInt();
		byte[] stringBits = new byte[stringLength];
		file.readFully(stringBits);

		String string = new String(stringBits);
		long value = file.readLong();
		long link = file.readLong();

		Entry entry = new Entry(string, value, link);

		return entry;
	}

	public void putEntry(long offset, Entry entry) throws IOException{
		file.seek(offset);
		file.writeInt(entry.getString().length());
		byte[] stringBits = entry.getString().getBytes();
		file.write(stringBits);
		file.writeLong(entry.getValue());
		file.writeLong(entry.getLink());
	}
	
}
