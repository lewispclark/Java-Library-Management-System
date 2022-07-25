package coursework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Customer extends User {
	// Attributes
	private ArrayList<Book> shoppingBasket;

	// Constructor
	public Customer(int userID, String username, String surname, Address address) {
		super(userID, username, surname, address);
		shoppingBasket = new ArrayList<Book>();
	}
	// String Constructor
	public Customer(String string){
		super(string);
		shoppingBasket = new ArrayList<Book>();
	}
	
	// Getters
	public ArrayList<Book> getBasket() {
		return shoppingBasket;
	}
	
	// Methods
	/**
	 * Calculates the total price of all the books in the users basket
	 * 
	 * @return total price of all books in basket
	 */
	public double getBasketTotal() {
		double total = 0;
		for(Book book : shoppingBasket) {
			total += book.getPrice();
		}
		return total;
	}
	
	/**
	 * Add the specified item to the customers basket
	 * 
	 * @param book to add to the basket
	 */
	public void addItemToBasket(Book book) {
		shoppingBasket.add(book);
	}
	
	/**
	 * Remove the book with the specified ISBN from the customers basket
	 * 
	 * @param ISBN of book to be removed from the basket
	 */
	public void removeItemFromBasket(int ISBN) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		// Calculate total quantity and total of all books with given ISBN in shopping basket
		int quantity = 0;
		double price = 0;
		for(Book book : shoppingBasket) {
			if(book.getISBN() == ISBN) {
				price = book.getPrice();
				quantity++;
				shoppingBasket.remove(book);
			};
		}
		// Update log file with new item cancellation
		String newLog = String.format("%d, %s, %d, %.2f, %d, cancelled, , %s", getUserID(), getAddress().getPostcode(),
				ISBN, price, quantity, dtf.format(LocalDateTime.now()));
		Functions.writeToFile("ActivityLog.txt", newLog);
	}
	
	/**
	 * Checks if a book with the given ISBN number exists in the shopping basket
	 * 
	 * @param isbn to search for
	 * @return true, if the book is in the shopping basket, false otherwise
	 */
	public boolean basketContains(int ISBN) {
		for(Book book : shoppingBasket) {
			if(book.getISBN() == ISBN) return true;
		}
		return false;
	}
	
	/**
	 * For each book in the shopping basket:
	 *  - counts quantity of book in basket and sets quantity
	 *  - adds book to log file
	 * Removes all books from basket
	 * 
	 * @param paymentMethod that user paid with
	 */
	public void checkoutBasket(String paymentMethod) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		ArrayList<String[]> books = new ArrayList<String[]>();
		
		for(Book book : shoppingBasket) {
			boolean containsBook = false;
			// Check if book is in books ArrayList
			for(String[] row : books) {
				if(Integer.parseInt(row[2]) == book.getISBN()) {
					containsBook = true;
					// Increase quantity of book
					row[4] = Integer.toString(Integer.parseInt(row[4])+1);
					break;
				}
			}
			// If book is not in books ArrayList, add it
			if(!containsBook) books.add(new String[] {Integer.toString(getUserID()), getAddress().getPostcode(),
					Integer.toString(book.getISBN()), Double.toString(book.getPrice()), "1", "purchased", paymentMethod,  dtf.format(LocalDateTime.now())});
			// Decrement stock of book
			Functions.decrementStock(book.getISBN());
		}
		// Update log file
		for(String[] row : books) {
			Functions.writeToFile("ActivityLog.txt", String.join(", ", row));
		}
		// Empty shopping basket
		shoppingBasket.clear();
	}
	
	/**
	 * Removes all items from basket. Updates the log file with each book that is removed
	 */
	public void cancelItems() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		ArrayList<Book> removedBooks = new ArrayList<Book>();
		for(Book book : shoppingBasket) {
			boolean containsBook = false;
			// Check if book is in removedBooks ArrayList
			for(Book removedBook : removedBooks) {
				if(book.getISBN() == removedBook.getISBN()) {
					// Increase quantity
					removedBook.setQuantity(removedBook.getQuantity()+1);
					containsBook = true;
					break;
				}
			}
			// If book is not in removedBook ArrayList, add it
			if(!containsBook) {
				Book temp = (Book) book;
				temp.setQuantity(1);
				removedBooks.add(temp);
			};
		}
		// Update log file
		for(Book book : removedBooks) {
			String newLog = String.format("%d, %s, %d, %.2f, %d, cancelled, , %s", getUserID(), getAddress().getPostcode(),
					book.getISBN(), book.getPrice(), book.getQuantity(), dtf.format(LocalDateTime.now()));
			Functions.writeToFile("ActivityLog.txt", newLog);
		}
		// Empty shopping basket
		shoppingBasket.clear();
	}
	
	/**
	 * Return books with titles that contain the title specified
	 * 
	 * @param title to search for
	 * @return books that match the criteria
	 */
	public ArrayList<Book> search(String title) {
		ArrayList<Book> output = new ArrayList<Book>();
		ArrayList<Book> books = super.viewBooks();
		// If specified title is part of title of book in stock, add to output
		for(Book book : books) {
			if(book.getTitle().toLowerCase().matches(".*" + title.toLowerCase() + ".*")) {
				output.add(book);
			};
		}
		return output;
	}
	
	/**
	 * Return books based on specified title and genre
	 * 
	 * @param title to search for 
	 * @param genre of book
	 * @return books that match the criteria
	 */
	public ArrayList<Book> advSearch(String title, String genre) {
		ArrayList<Book> output = new ArrayList<Book>();
		ArrayList<Book> books = super.viewBooks();
		// If specified title is part of title of book in stock, check genre
		for(Book book : books) {
			if(book.getTitle().toLowerCase().matches(".*" + title.toLowerCase() + ".*")) {
				// If book genre is "any" or is the same as the genre specified, add to output
				if(genre.equals("Any") || book.getGenre().equals(genre)) {
					output.add(book);
				}
			};
		}
		return output;
	}
	
	/**
	 * Return books based on multiple specified criterias
	 * 
	 * @param title to search for
	 * @param genre of book
	 * @param type of book
	 * @param minValue of numOfPages or length (depends on type)
	 * @param maxValueof numOfPages or length (depends on type)
	 * @return books that match the criteria
	 */
	public ArrayList<Book> advSearch(String title, String genre, String type, int minValue, int maxValue) {
		ArrayList<Book> output = new ArrayList<Book>();
		ArrayList<Book> books = super.viewBooks();
		if(type.equals("Any")) {
			return advSearch(title, genre);
		}
		BookType bookType = BookType.getType(type);
		// If part of search matches title of book in stock, add to output
		for(Book book : books) {
			// If specified title is part of title of book in stock, check genre
			if(book.getTitle().toLowerCase().matches(".*" + title.toLowerCase() + ".*")) {
				// If book genre is "any" or is the same as the genre specified, check extra info
				if(genre.equals("Any") || book.getGenre().equals(genre)) {
					// If book type is paperback and num of pages is in range, add to output
					if(bookType == BookType.PAPERBACK && book instanceof Paperback) {
						Paperback temp = (Paperback) book;
						if(temp.getNumOfPages() > minValue && temp.getNumOfPages() < maxValue) {
							output.add(book);
						}
					}
					// If book type is ebook and num of pages is in range, add to output
					else if(bookType == BookType.EBOOK && book instanceof Ebook) {
						Ebook temp = (Ebook) book;
						if(temp.getNumOfPages() > minValue && temp.getNumOfPages() < maxValue) {
							output.add(book);
						}
					}
					// If book type is audiobook and length is in range, add to output
					else if(bookType == BookType.AUDIOBOOK && book instanceof Audiobook) {
						Audiobook temp = (Audiobook) book;
						if(temp.getLength() > minValue && temp.getLength() < maxValue) {
							output.add(book);
						}
					}
				}
			};
		}
		return output;
	}
}
