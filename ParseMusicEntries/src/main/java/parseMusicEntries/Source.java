/**
 * 
 */
package parseMusicEntries;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew
 *	Contains information about source within a music collection. Each source may contain tune entries	
 *
 */
public class Source {
	//source/entry variables	
	private String collection;
	private int sourceNumber;											//current source number
	private String callNumber;							//call number for current source, indicated by bold text
	private String author;						//
	private String title;							//
	private String inscription;
	private String description;							//description of current source, containing all details that cannot be parsed
	//"source_collection", 
	private static String[] fields = {"source_number", "source_call_number", 
			"source_author", "source_title", "source_inscription", "source_description"};
	List<Entry> entries = new ArrayList<Entry>();
	
	Source(String collection, int src){
		this.collection = collection;
		sourceNumber = src;
		callNumber = null;							//call number for current source, indicated by bold text
		author = null;						//
		title = null;							//
		inscription = null;
		description = null;							//description of current source, containing all details that cannot be parsed		
	}
	//--------------------------------------------------------------------------------
	public String[] toArray() {					//source information in array format
		//collection, 
		String[] arr = {Integer.toString(sourceNumber), callNumber, author, title, inscription, description};
		return arr;
	}
	//--------------------------------------------------------------------------------
	public int getSourceNumber() {
		return sourceNumber;
	}
	//--------------------------------------------------------------------------------
	public void setCallNumber(String callNumber){
		this.callNumber = callNumber;		
	}
	//--------------------------------------------------------------------------------
	public String getCallNumber() {
		return callNumber;
	}
	//--------------------------------------------------------------------------------
	public void setAuthor(String author) {
		author = author.replace(".", "")
				.trim();
		if(author.startsWith("[") && !author.endsWith("]"))
			author +=  "]";
		this.author = author;
	}
	//--------------------------------------------------------------------------------
	public String getAuthor() {
		return author;
	}
	//--------------------------------------------------------------------------------
	public void setTitle(String title) {
		title = title.trim();
		if(title.endsWith("]") && !title.endsWith("[sic]"))
			title = "[" + title;		
		this.title = title;
	}
	//--------------------------------------------------------------------------------
	public String getTitle() {
		return title;
	}
	//--------------------------------------------------------------------------------
	public void setInscription(String inscription) {
		this.inscription = inscription;
	}
	//--------------------------------------------------------------------------------
	public String getInscription() {
		return inscription;
	}
	//--------------------------------------------------------------------------------
	public void setDescription(String description) {
		this.description = trimmedDescription(description);
	}
	//--------------------------------------------------------------------------------
	public String getDescription() {
		return description;
	}
	//--------------------------------------------------------------------------------
	public String toString() {
		return "Source: " + sourceNumber +
				"\nAuthor: "  +  author + 
				"\nTitle: "  +  title +
				"\nDescription: "  +  description +
				"\nCall number: " + callNumber + 				
				"\nInscription(s): " + inscription  +
				"\n----------------end of source-------------\n\n";
	}
	//--------------------------------------------------------------------------------
	public static String[] getFields(){
		return fields;
	}
	//--------------------------------------------------------------------------------
	private String trimmedDescription(String description) {
		if(description.startsWith(".")) {
			return description.substring(2).trim();
		}
		else if(description.startsWith(".]")) {
			if(title != null) {
				title += "]";
			}
			return description.substring(3).trim();			
		}
		else if (description.startsWith("?]")){
			if(title != null) {
				title += "?]";
			}
			return description.substring(3);
		}
		else {	 
			return description;
		}		
	}
}
