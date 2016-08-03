import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class WordIndex {

	public class UrlEntry{

		private String url;
		private long count;

		public UrlEntry(String url, long count){
			this.url = url;
			this.count = count;
		}
		public String getUrl(){
			return url;

		}
		public long getCount(){
			return count;

		}
	}

	private PersistentArray hashIndex;
	private ListFile wordLists;
	private ListFile urlLists;
	private static int initialValue = -1;

	public static void initialize(String indexName, long indexSize) throws IOException{
		PersistentArray.initialize(indexName + ".indexArray", (int) indexSize, initialValue);
		ListFile.initialize(indexName + ".urlList");
		ListFile.initialize(indexName + ".wordList");
	}

	public static void delete(String indexName){
		PersistentArray.delete(indexName + ".indexArray");
		ListFile.delete(indexName + ".wordList");
		ListFile.delete(indexName + ".urlList");
	}

	public WordIndex(String indexName) throws FileNotFoundException{
		hashIndex = new PersistentArray(indexName + ".indexArray");
		urlLists = new ListFile(indexName + ".urlList");
		wordLists = new ListFile(indexName + ".wordList");
	}

	public void close() throws IOException{
		hashIndex.close();
		wordLists.close();
		urlLists.close();
	}

	public void add(String word, String url) throws IOException{
		int index = word.hashCode()% (int)(hashIndex.getLength()/8);
		if(hashIndex.get(index) == initialValue){
			UrlEntry urlEntry = new UrlEntry(url, 1);
			Entry entry = new Entry(urlEntry.getUrl(), urlEntry.getCount(), initialValue);
			long urlOffset = urlLists.newEntry(entry);
			Entry wordEntry = new Entry(word, urlOffset, initialValue);
			long wordOffset = wordLists.newEntry(wordEntry);
			hashIndex.set(index, wordOffset);
		}else{
			long wordOffset = hashIndex.get(index);
			Entry entry = wordLists.getEntry(wordOffset);
			Entry wordStore = new Entry(null, 0, 0);
			boolean hasChanged = false;
			do{
				if(hasChanged){
					entry = new Entry(wordStore.getString(), wordStore.getValue(), wordStore.getValue());
				}
				if(entry.getString().equals(word)){
					Entry uEntry = urlLists.getEntry(entry.getValue());
					Entry urlStore = new Entry(null, 0, 0);
					boolean urlChange = false;
					long urlOffset = entry.getValue();
					
					System.out.println("Word links to url at: " + entry.getValue());
					
					do{
						if(urlChange){
							uEntry = new Entry(urlStore.getString(), urlStore.getValue(), urlStore.getLink());
							urlChange = false;
						}
						if(uEntry.getString().equals(url)){
							uEntry.setValue(uEntry.getValue() + 1);
							urlLists.putEntry(urlOffset, uEntry);
						}else if(uEntry.getLink() != initialValue){
							urlStore = urlLists.getEntry(uEntry.getLink());
							urlChange = true;
							urlOffset = uEntry.getLink();
							
							System.out.println(urlOffset);
							
						}else{
							UrlEntry urlEntry = new UrlEntry(url, 1);
							Entry newUrlEntry = new Entry(urlEntry.getUrl(), urlEntry.getCount(), initialValue);
							long newOffset = urlLists.newEntry(newUrlEntry);
							uEntry.setLink(newOffset);
							urlLists.putEntry(urlOffset, uEntry);
							
							System.out.println("Link changed at: " + urlOffset + " Link to " + newOffset);
							System.out.println("New entry at: " + newOffset);
							
							uEntry = urlLists.getEntry(newOffset);
						}
					}while(!uEntry.getString().equals(url));
				}else if(entry.getLink() != initialValue){
					wordStore = wordLists.getEntry(entry.getLink());
					hasChanged = true;
					wordOffset = entry.getLink();
				}else{
					UrlEntry urlEntry = new UrlEntry(url, 1);
					Entry newUrlEntry = new Entry(urlEntry.getUrl(), urlEntry.getCount(), initialValue);
					long urlOffset = urlLists.newEntry(newUrlEntry);
					Entry wordEntry = new Entry(word, urlOffset, initialValue);
					long newWordOffset = wordLists.newEntry(wordEntry);
					entry.setLink(newWordOffset);
					wordLists.putEntry(wordOffset, entry);
					entry = wordLists.getEntry(newWordOffset);
					
				}
			}while(!entry.getString().equals(word));
		}
	}

	public Iterator<UrlEntry> getUrls(String word) throws IOException{
		ArrayList<UrlEntry> urls = new ArrayList<UrlEntry>();
		int index = (word.hashCode()% (int)(hashIndex.getLength()/8));
		long wordIndex = hashIndex.get(index);
		Entry entry = wordLists.getEntry(wordIndex);
		Entry wordStore = new Entry(null, 0, 0);
		boolean hasChanged = false;
		do{
			if(hasChanged){
				entry = new Entry(wordStore.getString(), wordStore.getValue(), wordStore.getLink());
				hasChanged = false;
			}
			if(entry.getString().equals(word)){
				Entry uEntry = urlLists.getEntry(entry.getValue());
				Entry urlStore = new Entry(null, 0, 0);
				do{
					if(!urls.isEmpty()){
						uEntry = new Entry(urlStore.getString(), urlStore.getValue(), urlStore.getLink());
					}
					UrlEntry url = new UrlEntry(uEntry.getString(), uEntry.getValue());
					urls.add(url);
					if(uEntry.getLink() != initialValue){
						urlStore = urlLists.getEntry(uEntry.getLink());
					}
				}while(uEntry.getLink() != initialValue);
			}else{
				wordStore = wordLists.getEntry(entry.getLink());
				hasChanged = true;
			}
		}while(!entry.getString().equals(word));
		return urls.iterator();
	}

}
