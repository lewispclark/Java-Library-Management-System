package coursework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {
	// User attributes
	private int userID;
	private String username;
	private String surname;
	private Address address;
	
	// Constructor
	public User(int userID, String username, String surname, Address address){
		this.userID = userID;
		this.username = username;
		this.surname = surname;
		this.address = address;
	}
	// String Constructor
	public User(String string){
		String[] splitData = string.split(", ");
		
		this.userID = Integer.parseInt(splitData[0]);
		this.username = splitData[1];
		this.surname = splitData[2];
		this.address = new Address(Integer.parseInt(splitData[3]), splitData[4], splitData[5]);
	}
	
	// Getters
	public int getUserID() {
		return this.userID;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	// Methods
	/** Gets the books from the stock file
	 */
	public static ArrayList<Book> viewBooks(){
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			// Read stock file
			File stockFile = new File("Stock.txt");
			Scanner myReader = new Scanner(stockFile);
			// Get each line
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				String [] splitData = line.split(", ");
				String bookType = splitData[1];
		        
				// Create book object with each row and add to ArrayList
				Book newBook = null;
		        if (bookType.equals("paperback")) {
		        	newBook = new Paperback(line);
		        }
		        else if(bookType.equals("ebook")) {
		        	newBook = new Ebook(line);
		        }
		        else if (bookType.equals("audiobook")) {
		        	newBook = new Audiobook(line);
		        }
		        books.add(newBook);
			}
			myReader.close();
			// Sort books by price
			books.sort(new BookSorter());
			return books;
		// If file cannot be found, output error and return null
	    } catch (FileNotFoundException e) {
	      return books;
	    }
	}
	
	/**
	 * searches through the stock file for a book with the given ISBN
	 * 
	 * @param ISBN of the book to search for
	 * @return Book with given ISBN if it exists in the stock file, null if it cannot be found
	 */
	public Book getBookByISBN(int ISBN) {
		for(Book book : viewBooks()) {
			if(book.getISBN() == ISBN) {
				return book;
			}
		}
		// If book cannot be found, return null
		return null;
	}
}
