package coursework;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	// Auto generated Serial ID
	private static final long serialVersionUID = -4637798532142988273L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// set theme
				try {
		            // Set look and feel
		        UIManager.setLookAndFeel(
		        		"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			    } 
			    catch (Exception e) {
			    	e.printStackTrace();
			    }
				
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setTitle("Bookshop Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ArrayList<User> users = new ArrayList<User>();
		boolean hasUsers = true;
		// Get users from user accounts file
		try {
			File usersFile = new File("UserAccounts.txt");
			Scanner myReader = new Scanner(usersFile);
			// Create user object with each row
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
		        String [] splitData = line.split(", ");
		        UserType userType = UserType.getType(splitData[6]);
		        
		        User newUser = null;
		        if (userType == UserType.ADMIN) {
	        		newUser = new Admin(line);
		        }
		        else if(userType == UserType.CUSTOMER) {
		        	newUser = new Customer(line);
		        }
		        users.add(newUser);
			}
			myReader.close();
		// If file cannot be found, output error
	    } catch (FileNotFoundException e) {
	      hasUsers = false;
	    }
		
		// Get usernames from all users for menu choice
		ArrayList<String> usernames = new ArrayList<String>();
		for(User user : users){
			usernames.add(user.getUsername());
		}
		
		JLabel lblNoUsers = new JLabel("Error: UserAccounts file could not be found.");
		lblNoUsers.setVisible(false);
		lblNoUsers.setBounds(118, 72, 200, 13);
		contentPane.add(lblNoUsers);
		
		// Add usernames to JComboBox
		Object[] usernamesArray = usernames.toArray();
		JComboBox<Object> comboBoxUsernames = new JComboBox<Object>(usernamesArray);
		comboBoxUsernames.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBoxUsernames.setBounds(145, 95, 140, 34);
		contentPane.add(comboBoxUsernames);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			/**
			 * Hide login screen and show the main bookshop window
			 */
			public void actionPerformed(ActionEvent e) {
				// Open book shop window, hide login window
				Bookshop bookshop = new Bookshop(users.get(comboBoxUsernames.getSelectedIndex()));
				bookshop.setVisible(true);
				setVisible(false);
			}
		});
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 15));
		btnLogin.setBounds(164, 140, 106, 36);
		contentPane.add(btnLogin);
		
		// If users file cannot be found, disable login 
		if(!hasUsers) {
			lblNoUsers.setVisible(true);
			comboBoxUsernames.setEnabled(false);
			btnLogin.setEnabled(false);
		}
		
	}
}
