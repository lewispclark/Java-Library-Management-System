package coursework;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public final class Functions {

	/**
	 * Append line to specified file
	 * 
	 * @param filename file to write to
	 * @param line to append to the file
	 */
	public static void writeToFile(String filename, String line) {
		FileWriter output = null;
 		try{
 			output = new FileWriter(filename, true);
 			BufferedWriter bw = new BufferedWriter(output);
 			bw.write(line);
 			bw.newLine();
 			bw.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (output != null) {
				try {
					output.flush();
					output.close();
				} catch (IOException e) {
					
				}
			}
		}
	}
	
	/**
	 * Updates stock file with given list of books
	 * 
	 * @param books to update stock file with
	 */
	public static void updateStockFile(ArrayList<Book> books) {
		// Clear file
		try {
			// Sets file to zero size
			new PrintWriter("Stock.txt").close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Rewrite file
		for(Book book : books) {
			Functions.writeToFile("Stock.txt", book.toString());
		}
	}
	
	/**
	 * Decrements the stock of the book with the given ISBN in the stock file by 1
	 * 
	 * @param ISBN of book to decrement stock of
	 */
	public static void decrementStock(int ISBN) {
		// Get current stocks
		ArrayList<Book> books = User.viewBooks();
		// update quantity
		for(Book book : books) {
			if(book.getISBN() == ISBN) {
				book.setQuantity(book.getQuantity()-1);
			}
		}
		// Rewrite stock file
		updateStockFile(books);
	}
}
