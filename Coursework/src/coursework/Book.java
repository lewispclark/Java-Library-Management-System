package coursework;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Book {
	// Atributes
	private int ISBN;
	private String title;
	private String language;
	private String genre;
	private LocalDate releaseDate;
	private double retailPrice;
	private int quantity;
	
	public final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	// Constructor
	public Book(int ISBN, String title, String language, String genre, LocalDate releaseDate, double retailPrice, int quantity){
		this.ISBN = ISBN;
		this.title = title;
		this.language = language;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.retailPrice = retailPrice;
		this.quantity = quantity;
	}
	// String constructor
	public Book(String string){
		// Convert each line to a book object
		String [] splitData = string.split(", ");
        String [] dateSplit = splitData[5].split("-");

        this.ISBN = Integer.parseInt(splitData[0]);
		this.title = splitData[2];
		this.language = splitData[3];
		this.genre = splitData[4];
		this.releaseDate = LocalDate.of(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[0]));
		this.retailPrice = Double.parseDouble(splitData[6]);
		this.quantity = Integer.parseInt(splitData[7]);
	}
	
	// Getters
	public int getISBN() {
		return this.ISBN;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public LocalDate getDate() {
		return this.releaseDate;
	}

	public double getPrice() {
		return this.retailPrice;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	// Setters
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// Methods
	public String toString() {
		return String.format("%d, %s, %s, %s, %s, %.2f, %d", ISBN, title, language, genre, releaseDate, retailPrice, quantity);
	}
}
