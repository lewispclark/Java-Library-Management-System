package coursework;

import java.time.LocalDate;

public class Paperback extends Book{
	private int numOfPages;
	private String condition;
	
	// Constructor
	public Paperback(int ISBN, String title, String language, String genre, LocalDate releaseDate, double retailPrice,
			int quantity, int numOfPages, String condition) {
		super(ISBN, title, language, genre, releaseDate, retailPrice, quantity);
		
		this.numOfPages = numOfPages;
		this.condition = condition;
	}
	// String constructor
	public Paperback(String string) {
		super(string);
		String [] splitData = string.split(", ");
		this.numOfPages = Integer.parseInt(splitData[8]);
		this.condition = splitData[9];
	}
	
	// Getters
	public int getNumOfPages(){
		return this.numOfPages;
	}
	
	public String getCondition(){
		return this.condition;
	}
	
	@Override
	public String toString() {
		return String.format("%d, paperback, %s, %s, %s, %s, %.2f, %d, %d, %s", getISBN(), getTitle(), getLanguage(), getGenre(), getDate().format(Book.dateFormat), getPrice(), getQuantity(), numOfPages, condition);
	}
}
