package coursework;

import java.time.LocalDate;

public class Audiobook extends Book {
	// Attibutes
	private double length;
	private String format;
	
	// Constructor
	public Audiobook(int ISBN, String title, String language, String genre, LocalDate releaseDate, double retailPrice,
			int quantity, double length, String format) {
		super(ISBN, title, language, genre, releaseDate, retailPrice, quantity);
		// TODO Auto-generated constructor stub
		this.length = length;
		this.format = format;
	}
	// String constructor
	public Audiobook(String string) {
		super(string);
		String [] splitData = string.split(", ");
		this.length = Double.parseDouble(splitData[8]);
		this.format = splitData[9];
	}
	
	// Getters
	public double getLength() {
		return this.length;
	}
	
	public String getFormat() {
		return this.format;
	}
	
	@Override
	public String toString() {
		return String.format("%d, audiobook, %s, %s, %s, %s, %.2f, %d, %.2f, %s", getISBN(), getTitle(), getLanguage(), getGenre(), getDate().format(Book.dateFormat), getPrice(), getQuantity(), length, format);
	}
}
