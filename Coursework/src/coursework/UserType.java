package coursework;

public enum UserType {
	ADMIN,
	CUSTOMER;

	/**
	 * Returns user type of given string
	 * 
	 * @param string describing user type
	 * @return usertype enum
	 */
	public static UserType getType(String string) {
		if(string.toLowerCase().equals("admin")) {
			return ADMIN;
		}
		else if(string.toLowerCase().equals("customer")) {
			return CUSTOMER;
		}
		else return null;
	}
}
