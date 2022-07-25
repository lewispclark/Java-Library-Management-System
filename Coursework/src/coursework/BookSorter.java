package coursework;

import java.util.Comparator;

public class BookSorter implements Comparator<Book>{

	@Override
	public int compare(Book b1, Book b2) {
		// TODO Auto-generated method stub
		if(b1.getPrice() == b2.getPrice()) return 0;
		else if (b1.getPrice() > b2.getPrice()) return 1;
		else return -1;
	}

}
