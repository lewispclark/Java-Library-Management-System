package coursework;

import java.time.LocalDate;

public class Ebook extends Book {
	// Attributes
	private int numOfPages;
	private String format;
	
	// Constructor
	public Ebook(int ISBN, String title, String language, String genre, LocalDate releaseDate, double retailPrice, int quantity, int numOfPages, String format) {
		super(ISBN, title, language, genre, releaseDate, retailPrice, quantity);
		// TODO Auto-generated constructor stub
		this.numOfPages = numOfPages;
		this.format = format;
	}
	// String constructor
	public Ebook(String string) {
		super(string);
		String [] splitData = string.split(", ");
		this.numOfPages = Integer.parseInt(splitData[8]);
		this.format = splitData[9];
	}
	
	// Getters
	public int getNumOfPages() {
		return this.numOfPages;
	}
	
	public String getFormat() {
		return this.format;
	}
	
	// Methods
	@Override
	public String toString() {
		return String.format("%d, ebook, %s, %s, %s, %s, %.2f, %d, %d, %s", getISBN(), getTitle(), getLanguage(), getGenre(), getDate().format(Book.dateFormat), getPrice(), getQuantity(), numOfPages, format);
	}
}
