import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PersistentArray {

	private static RandomAccessFile file;
	int longBytes = 8;

	public PersistentArray(String arrayFileName) throws FileNotFoundException{
		File file = new File(arrayFileName);
		if(!file.exists()){
			throw new FileNotFoundException();
		}
		else{
			this.file = new RandomAccessFile(arrayFileName, "rws");
		}
	}

	public static void initialize(String arrayFileName, int arraySize, long initialValue) throws IOException{
		file = new RandomAccessFile(arrayFileName, "rws");
		for(int i = 0; i < arraySize; i++){
			file.seek(i * 8);
			file.writeLong(initialValue);
		}
		file.close();
	}

	public void set(int index, long value) throws IOException{
		file.seek(index * longBytes);
		file.writeLong(value);
	}

	public long get(int index) throws IOException{
		file.seek(index * longBytes);
		long data = file.readLong();
		return data;
	}

	public long getLength() throws IOException{
		return file.length();
	}

	public static void delete(String arrayFileName){
		File file = new File(arrayFileName);
		file.delete();
	}

	public void close() throws IOException{
		file.close();
	}

}
