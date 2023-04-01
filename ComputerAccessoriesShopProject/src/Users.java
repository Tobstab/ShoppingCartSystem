import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Users {
	// declaration of attributes of Users class
	private int userId;
	private String userName;
	private String surName;
	private String address;
	private String postcode;
	private String role;
	public int getUserID() { //getter method to obtain the users ID
		return userId;
	}
	public String getUserName() { //getter method to obtain the user name
		return userName;
	}
	public String getUserSurname() { //getter method to obtain the user surname
		return surName;
	}
	public String getUserAddress() { //getter method to obtain the user address
		return address;
	}
	public String getUserPostcode() { //getter method to obtain the user postcode
		return postcode;
	}
	public String getUserRole() { //getter method to obtain the user role
		return role;
	}
	//method to display each user to console
	public String displayUsers(){
		return "ID: "+userId+", Name: "+userName+", Surname: "+surName +", address: " + address + ", role: "+role;
	}
	//constructor for Users class
	public Users(int userid, String name, String surname, String address, String role,String Postcode) {
		this.userId=userid;
		this.userName=name;
		this.surName=surname;
		this.address=address;
		this.postcode=Postcode;
		this.role=role;
	
	}
	//constructor for admins which extends Users class
	public static class Admins extends Users{
		public Admins(int userid, String name, String surname, String address, String role,String Postcode) {
			super(userid, name, surname, address, role, Postcode);
		}
		//admin method to add product to the stock
		public static void addToStock(int barcode, String Name,String Type,String brand,String colour,
				String connectivity,int Quantity,double oglPrice,double rtlPrice,String Info) throws IOException {
			//file of which to write to
			FileWriter outputFile = new FileWriter("Stock.txt", true);
			BufferedWriter bw = new BufferedWriter(outputFile);
			//write to file
			bw.write(barcode +", "+ Name+", " + Type+", "+brand+", "+colour+", "+connectivity+", "+Quantity+", "+oglPrice+", "+rtlPrice+", "+Info+"\n");
			bw.close();
		} 	
	}
	//constructor for Customers which extends Users class
	public static class Customers extends Users{
		public Customers(int userid, String name, String surname, String address, String role,String Postcode) {
			super(userid, name, surname, address, role,Postcode);

		}
		public static void saveToLog(JTable table, JLabel label, double price, String Status, String purchase_type) {
			for (int j = 0; j<table.getRowCount();j++) {
				//add to the log with card as the purchase type
				Log logfile = new Log(Mainframe.getID().toString(),Mainframe.getPostcode(),
						table.getValueAt(j, 0).toString(), Double.parseDouble(table.getValueAt(j, 4).toString().replace("£", "")),
						Integer.parseInt(table.getValueAt(j, 3).toString()),Status,purchase_type);
				try {
					logfile.createLog();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
			}
			//display price and method of payment
			NumberFormat decimalFormat = new DecimalFormat("#0.00"); 
			label.setText("£"+decimalFormat.format(price) +" paid using "+purchase_type);
		}
		public static void clearBasket(JLabel label, DefaultTableModel model, JTextField text) {
			label.setText("Basket Cleared");
			model.setRowCount(0);
			text.setText("");
		}
		public static void previousBasket(JTable table, JLabel warning, DefaultTableModel model,JTextField text) {
			if (table.getRowCount() >0) {
				//do nothing
				warning.setText("Clear the basket first");
			}
			else {
				warning.setText("Found Previous Session");
				File inputFile = new File("Userbasket.txt");
			    Scanner filescanner_1 = null;
				try {
					filescanner_1 = new Scanner(inputFile);
				} catch (FileNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			    int ArrayNum = 0;
			    ShoppingBasket[] basketsArray = new ShoppingBasket[5];
			    ShoppingBasket basketrow =null;
				while (filescanner_1.hasNextLine()){
					String [] userInfo = filescanner_1.nextLine().split(",");
				    basketrow = new ShoppingBasket(Integer.parseInt(userInfo[0].trim()),userInfo[1].trim(),userInfo[2].trim(),userInfo[3].trim() , userInfo[4].trim());
				    basketsArray [ArrayNum] = basketrow;
					ArrayNum++;
			    }
								
				for(int i=0; i<ArrayNum; i++) {
					model.addRow(new Object[]{
							basketsArray[i].getBarcode(),basketsArray[i].getName(),
							basketsArray[i].getBrand(),basketsArray[i].getQuantity(),
							basketsArray[i].getPrice()
							});
				}
				table = new JTable(model);
			}
			double totalPrice = 0;
			NumberFormat decimalFormat = new DecimalFormat("#0.00");     
		 
			for (int j=0; j<table.getRowCount();j++) {
				totalPrice += Double.parseDouble(table.getValueAt(j, 4).toString().replace("£"," "))*Double.parseDouble(table.getValueAt(j, 3).toString());
				}
			text.setText("£"+decimalFormat.format(totalPrice));
			}
		}
}
