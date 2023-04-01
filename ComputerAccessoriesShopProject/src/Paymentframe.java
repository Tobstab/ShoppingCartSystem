import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import javax.swing.JLabel;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Paymentframe extends JFrame {
	//declarations of all the attributes needed for the Payment frame
	Customerframe cusframe = new Customerframe();
	Object[] columns = cusframe.getBasketcolumns();
	Object[][] data = {};
	private JPanel contentPane;
	private JTextField displayPriceField;
	private JTextField nameField;
	private JTextField DigitField_16;
	private JTextField DigitField_3;
	private JTextField namefield;
	private JTextField addressField;
	private JTextField emailField;
	private boolean paymentType;
	//declare public variable total price to be displayed and dynamically changed
	double totalPrice = 0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//run the frame
					Paymentframe cusframe = new Paymentframe();
					cusframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Paymentframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		//creation of the payment frame.
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel paymentlabel = new JLabel("Select Your Payment Type");
		paymentlabel.setBounds(254, 23, 262, 49);
		paymentlabel.setHorizontalAlignment(SwingConstants.CENTER);
		paymentlabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		contentPane.add(paymentlabel);
		
		JLabel basketLabel = new JLabel("Shopping basket");
		basketLabel.setBounds(53, 76, 236, 24);
		basketLabel.setForeground(new Color(0, 0, 0));
		basketLabel.setHorizontalAlignment(SwingConstants.CENTER);
		basketLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(basketLabel);
		
		
		// functionality to get the previous shop from the customer
		// the file from which to read from
		File inputFile = new File("Userbasket.txt");
	    Scanner filescanner_1 = null;
		try {
			filescanner_1 = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create the shopping basket array
	    int ArrayNum = 0;
	    ShoppingBasket[] basketsArray = new ShoppingBasket[20];
	    ShoppingBasket basketrow =null;
	    //add values to the array
		while (filescanner_1.hasNextLine()){
			String [] userInfo = filescanner_1.nextLine().split(",");
		    basketrow = new ShoppingBasket(Integer.parseInt(userInfo[0].trim()),userInfo[1].trim(),userInfo[2].trim(),userInfo[3].trim() , userInfo[4].trim());
		    basketsArray [ArrayNum] = basketrow;
			ArrayNum++;
	    }
				
		DefaultTableModel basketTableModel;
		basketTableModel = new DefaultTableModel(data, columns) {
				 @Override
				    public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				       
				    }
				};;
		//add values from the array to the table model
		for(int i=0; i<ArrayNum; i++) {
			basketTableModel.addRow(new Object[]{
					basketsArray[i].getBarcode(),basketsArray[i].getName(),
					basketsArray[i].getBrand(),basketsArray[i].getQuantity(),
					basketsArray[i].getPrice()
					});
		}
		//create new table for basket
		JTable basket2Table;
		//initialise table with the table model and data
		basket2Table = new JTable(basketTableModel);
		
		basket2Table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		basket2Table.setBounds(208, 57, 566, 438);
		basket2Table.setVisible(false);
		basket2Table.getTableHeader().setReorderingAllowed(false);
		//display tbale on scroll pane
		JScrollPane scrollPane_1 = new JScrollPane(basket2Table);
		scrollPane_1.setBounds(43, 111, 259, 375);
		scrollPane_1.setVisible(true);
		basket2Table.setVisible(true);
		contentPane.add(scrollPane_1);
		
		//display the price
		displayPriceField = new JTextField();
		
		NumberFormat decimalFormat = new DecimalFormat("#0.00");     
		//for each item in the basket
		//add the price columns together
		for (int j=0; j<basket2Table.getRowCount();j++) {
			totalPrice += Double.parseDouble(basket2Table.getValueAt(j, 4).toString().replace("£"," "))*Double.parseDouble(basket2Table.getValueAt(j, 3).toString());
		}
		//display the price to the text box
		displayPriceField.setText("£"+decimalFormat.format(totalPrice));
		
		
		displayPriceField.setFont(new Font("Tahoma", Font.BOLD, 16));
		displayPriceField.setBounds(216, 497, 86, 24);
		contentPane.add(displayPriceField);
		displayPriceField.setColumns(10);
		
		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		priceLabel.setBounds(163, 495, 47, 28);
		contentPane.add(priceLabel);
		
		//form for completing payment
		JLabel CardLabel = new JLabel("Enter 16 Digit Card Number:");
		CardLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		CardLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CardLabel.setBounds(323, 261, 186, 24);
		contentPane.add(CardLabel);
		CardLabel.setVisible(false);
		
		DigitField_16 = new JTextField();
		DigitField_16.setBounds(510, 261, 212, 24);
		contentPane.add(DigitField_16);
		DigitField_16.setColumns(10);
		DigitField_16.setVisible(false);
		
		JLabel securityNumberLabel = new JLabel("Enter 3 Digit Security Code:");
		securityNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		securityNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		securityNumberLabel.setBounds(323, 300, 186, 24);
		contentPane.add(securityNumberLabel);
		securityNumberLabel.setVisible(false);
		
		DigitField_3 = new JTextField();
		DigitField_3.setColumns(10);
		DigitField_3.setBounds(510, 300, 39, 24);
		contentPane.add(DigitField_3);
		DigitField_3.setVisible(false);

		namefield = new JTextField();
		namefield.setEditable(false);
		namefield.setBounds(510, 194, 119, 20);
		namefield.setText(Mainframe.getUsername());
		contentPane.add(namefield);
		namefield.setColumns(10);
		namefield.setVisible(false);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		nameLabel.setBounds(462, 191, 47, 24);
		contentPane.add(nameLabel);
		nameLabel.setVisible(false);
		
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setHorizontalAlignment(SwingConstants.LEFT);
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		addressLabel.setBounds(446, 226, 66, 24);
		contentPane.add(addressLabel);
		addressLabel.setVisible(false);
		
		addressField = new JTextField();
		addressField.setText((String) null);
		addressField.setEditable(false);
		addressField.setColumns(10);
		addressField.setText(Mainframe.getAddress());
		contentPane.add(namefield);
		addressField.setBounds(510, 228, 212, 20);
		contentPane.add(addressField);
		addressField.setVisible(false);
		
		JLabel emailLabel = new JLabel("Enter Your PayPal Email:");
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		emailLabel.setBounds(342, 261, 168, 24);
		contentPane.add(emailLabel);
		emailLabel.setVisible(false);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(510, 261, 212, 24);
		contentPane.add(emailField);
		emailField.setVisible(false);

		JLabel payCompletedLabel = new JLabel("");
		payCompletedLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		payCompletedLabel.setBounds(366, 443, 320, 28);
		contentPane.add(payCompletedLabel);
		payCompletedLabel.setVisible(false);
		
		//confirm payment button
		JButton confirmButton = new JButton("Confirm Payment");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paymentType) {
					//paid using paypal
					//save to log
					Users.Customers.saveToLog(basket2Table, payCompletedLabel,totalPrice, "purchased", "PayPal");
					payCompletedLabel.setVisible(true);
				}
				else {
					//paid using card
					//save to log
					Users.Customers.saveToLog(basket2Table, payCompletedLabel,totalPrice, "purchased", "Credit Card");
					payCompletedLabel.setVisible(true);
			}
			}});
		confirmButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		confirmButton.setBounds(366, 361, 320, 49);
		contentPane.add(confirmButton);
		confirmButton.setVisible(false);
		
		//display paypal payment form
		JButton paypalButton = new JButton("PayPal");
		paypalButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		paypalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paymentType = true;
				CardLabel.setVisible(false);
				DigitField_16.setVisible(false);
				securityNumberLabel.setVisible(false);
				DigitField_3.setVisible(false);
				confirmButton.setVisible(true);
				namefield.setVisible(true);
				nameLabel.setVisible(true);
				addressLabel.setVisible(true);
				addressField.setVisible(true);
				emailField.setVisible(true);
				emailLabel.setVisible(true);
			}
		});
		paypalButton.setBounds(366, 108, 150, 37);
		contentPane.add(paypalButton);
		
		//display card payment form
		JButton cardButton = new JButton("Card");
		cardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentType = false;
				CardLabel.setVisible(true);
				DigitField_16.setVisible(true);
				securityNumberLabel.setVisible(true);
				DigitField_3.setVisible(true);
				confirmButton.setVisible(true);
				namefield.setVisible(true);
				nameLabel.setVisible(true);
				addressLabel.setVisible(true);
				addressField.setVisible(true);
				emailField.setVisible(false);
				emailLabel.setVisible(false);
			}
		});
		cardButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		cardButton.setBounds(543, 108, 143, 37);
		contentPane.add(cardButton);
		
		//cancel button action
		JButton CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//save to log 
				Users.Customers.saveToLog(basket2Table, payCompletedLabel,totalPrice, "cancelled", " ");
				payCompletedLabel.setVisible(true);
			}
		});
		CancelButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		CancelButton.setBounds(20, 494, 99, 56);
		contentPane.add(CancelButton);
		//back button for getting back to the customer frame
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Customerframe frame;
				frame = new Customerframe();
				frame.setVisible(true);
				dispose();
				
			}
		});
		backButton.setBounds(0, 11, 110, 23);
		contentPane.add(backButton);	
		}
	}
