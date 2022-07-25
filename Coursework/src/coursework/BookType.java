package coursework;

public enum BookType {
	AUDIOBOOK,
	EBOOK,
	PAPERBACK;
	
	/**
	 * Returns book type of given string
	 * 
	 * @param string describing book type
	 * @return booktype enum
	 */
	public static BookType getType(String string) {
		if(string.toLowerCase().equals("audiobook")) {
			return AUDIOBOOK;
		}
		else if(string.toLowerCase().equals("ebook")) {
			return EBOOK;
		}
		else if(string.toLowerCase().equals("paperback")) {
			return PAPERBACK;
		}
		else return null;
	}
}
