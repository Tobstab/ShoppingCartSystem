import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Container;

public class Mainframe extends JFrame {
	//declarations of all the attributes needed for the Main frame
	private JPanel contentPane;
	private JTable logInfoTable;
	private DefaultTableModel logInfoModel;
	private static final Object[][] data = {};
	private static final Object[] columns = {"Name","address","user Id","postcode"};
	//declarations of all the variables that will be called via a getter method
	static String usernames;
	static String address;
	static String ID;
	static String postcode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//run the frame
					Mainframe frame = new Mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public Mainframe() throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		//contentPane the area where the components of the frame will reside
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//The label that is displayed to the user
		JLabel lblNewLabel = new JLabel("Please select which user you are");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 22));
		lblNewLabel.setBounds(250, 39, 332, 71);
		contentPane.add(lblNewLabel);
		
		// initialise the table model component
		logInfoModel = new DefaultTableModel(data, columns);
		
		//create a list model to allow dynamic changes to the JList.
		DefaultListModel listModel;
		listModel = new DefaultListModel();
		JList list = new JList(listModel);
		list.setFont(new Font("Tahoma", Font.BOLD, 20));
		// open the user text file and create a scanner object  	
		File inputFile = new File("UserAccounts.txt");
	    Scanner filescanner = new Scanner(inputFile);
	    int ArrayNum = 0;
	    //create a new Users array from the users class
	    Users[] usersArray = new Users[5];
	    Users st =null;
		while (filescanner.hasNextLine()){
			//for each line in the textfile, split each line and add it to the Users constructor
			String [] userInfo = filescanner.nextLine().split(",");
			st = new Users(Integer.parseInt(userInfo[0].trim()),userInfo[1].trim(),userInfo[2].trim(),(userInfo[3].trim() + userInfo[4] + userInfo[5]),userInfo[6], userInfo[4]);
			usersArray [ArrayNum] = st;
			//add each value into an array
			ArrayNum++;
		}
		//add the list to the scroll pane
		list.setBounds(80, 121, 130, 48);
		contentPane.add(list);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(55, 121, 380, 33);
		contentPane.add(scrollPane);
		
	    for (int i=0;i<ArrayNum;i++) {
	    	//for each value in the array, get the username,address,ID and postcode of the user
	    	listModel.addElement(usersArray[i].getUserSurname());
	    	logInfoModel.addRow(new Object[] {
	    			usersArray[i].getUserSurname(),
	    			usersArray[i].getUserAddress(),
	    			usersArray[i].getUserID(),
	    			usersArray[i].getUserPostcode()
	    			
	    	
	    	});
	    	//for developer display users to array
	    	System.out.println(usersArray[i].displayUsers());
	    }
	    //initialise the LogInfoTable with the logInfoModel
	    logInfoTable = new JTable(logInfoModel);
		
		JButton userSelect = new JButton("Select User");
		userSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//save the select input from the user
				usernames = (String) list.getSelectedValue();
				if (usernames.equals("Smith")) {
					//open the admin interface
					Adminframe admframe = new Adminframe();
					admframe.setVisible(true);
					dispose();
				}
				else if(usernames.equals("Williams") || usernames.equals("Taylor") || usernames.equals("Lee")) {
					for (int i =0; i<logInfoTable.getRowCount();i++) {
						//get all the details from the selected user
						if (usernames.equals(logInfoTable.getValueAt(i,0))){
							address = logInfoTable.getValueAt(i,1).toString();
							ID = logInfoTable.getValueAt(i,2).toString();
							postcode = logInfoTable.getValueAt(i,3).toString();
						}
					}
					//open the Customer interface
					Customerframe cusframe = new Customerframe();
					cusframe.setVisible(true);
					dispose();
					
				}
				else {
					//do nothing
				}
				
			}
		});
		userSelect.setBounds(546, 121, 107, 35);
		contentPane.add(userSelect);

	}
	
	public static String getUsername() {

		return usernames; //return the selected user
	}
	public static String getAddress() {

		return address; //return the selected user's address
	}
	public static String getID() {

		return ID; //return the selected user's ID
	}
	public static String getPostcode() {

		return postcode; //return the selected user's postcode
	}

}
	
