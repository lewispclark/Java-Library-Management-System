package coursework;

import java.util.ArrayList;

public class Admin extends User {
	// Constructor
	public Admin(int userID, String username, String surname, Address address) {
		super(userID, username, surname, address);
	}
	// String Constructor
	public Admin(String string){
		super(string);
	}
	
	// Methods
	/**
	 * Adds the given book to the stock file
	 * 
	 * @param book new book to add to the stock file
	 */
	public void addBook(Book book) {
		// Add Paperback book
		Functions.writeToFile("Stock.txt", book.toString());
	}
	
	/**
	 * Check if a book exists in the stock file with a given ISBN number
	 * 
	 * @param ISBN to look for in the stock file
	 * @return true, if the book does exist in the stock file, false if not
	 */
	public boolean bookExists(int ISBN) {
		// Check if book already exists in database with matching ISBN
		ArrayList<Book> books = viewBooks();
		for(Book book : books) {
			if(book.getISBN() == ISBN) {
				return true;
			}
		}
		return false;
	}
}
