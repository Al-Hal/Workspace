
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Lookup {

	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		WordIndex wordIndex = new WordIndex("shalladay");
		System.out.print("Enter word to lookup: ");
		String word = input.nextLine();
		
		Iterator<WordIndex.UrlEntry> url = wordIndex.getUrls(word);
		while(url.hasNext()){
			WordIndex.UrlEntry u1 = url.next();
			System.out.println("URL: " + u1.getUrl() + "	Occurrences: " + u1.getCount());
		}
	}

}
