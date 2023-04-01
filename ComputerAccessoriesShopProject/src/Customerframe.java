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
import java.util.Vector;

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

public class Customerframe extends JFrame {
	//declarations of all the attributes needed for the Customer frame
	private JTable productTable; //create new table for product table
	private DefaultTableModel basketTableModel;
	private JTable basketTable;
	private static final Object[][] data = {};
	private static final Object[][] basketdata = {};
	private static final Object[] columns = {"Barcode ID", "Name","Type","Brand","Colour","Connectivity","Quantity","Price","Extra"};
	private static final Object[] basketcolumns = {"Barcode ID", "Name","Brand","Quantity","Price"};
	private JPanel contentPane;
	private JTextField searchField;
	private JTextField displayPriceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//run the frame
					Customerframe cusframe = new Customerframe();
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
	public Customerframe() {
		//creation of the customer frame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label for search bar
		JLabel searchLabel = new JLabel("Search");
		searchLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchLabel.setBounds(48, 69, 71, 20);
		contentPane.add(searchLabel);
		
		JLabel customerLabel = new JLabel("Welcome Customer");
		customerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		customerLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		customerLabel.setBounds(254, 23, 262, 44);
		contentPane.add(customerLabel);

		//label for shopping basket
		JLabel basketLabel = new JLabel("Shopping basket");
		basketLabel.setForeground(new Color(0, 0, 0));
		basketLabel.setHorizontalAlignment(SwingConstants.CENTER);
		basketLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		basketLabel.setBounds(538, 78, 236, 24);
		contentPane.add(basketLabel);
		
		// The show all products functionality
		// the file from which to read from
		File inputFile = new File("Stock.txt");
	    Scanner filescanner_1 = null;
		try {
			filescanner_1 = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create the product table array
	    int ArrayNum = 0;
	    Stock[] StocksArray = new Stock[50];
	    Stock st1 =null;
	    //add values to the array
		while (filescanner_1.hasNextLine()){
			String [] userInfo = filescanner_1.nextLine().split(",");
		    st1 = new Stock(Integer.parseInt(userInfo[0].trim()),userInfo[1].trim(),userInfo[2].trim(),userInfo[3].trim() , userInfo[4].trim() , userInfo[5].trim(),Integer.parseInt(userInfo[6].trim()), Double.parseDouble(userInfo[7].trim()), Double.parseDouble(userInfo[8].trim()), userInfo[9].trim());
		    StocksArray [ArrayNum] = st1;
			ArrayNum++;
	    }
		DefaultTableModel productTableModel;
		productTableModel = new DefaultTableModel(data, columns) {
			 Class[] types = { Integer.class, String.class, String.class, String.class,
								String.class, String.class, Integer.class, Integer.class,
								String.class};
			 @Override
		     public boolean isCellEditable(int row, int column) {
		        //all cells false
		        return false;
		       
		     }
			 @SuppressWarnings("unchecked")
			public Class getColumnClass(int column) {
				 return this.types[column];
	
	            }
			 };;
			//add values from the array to the product table model
		NumberFormat price = NumberFormat.getCurrencyInstance();
		for(int i=0; i<ArrayNum; i++) {
			productTableModel.addRow(new Object[]{
					 StocksArray[i].getBarcodeID(),StocksArray[i].getDeviceName(),
					 StocksArray[i].getDeviceType(),StocksArray[i].getBrand(),
					 StocksArray[i].getColour(),StocksArray[i].getConnectivity(),
					 new Integer(StocksArray[i].getQuantity()),  price.format(StocksArray[i].getRetailPrice())
					 ,StocksArray[i].getExtraInfo()});
		}
		
		
		
		//initialise product table with the table model and data
		productTable = new JTable(productTableModel);
		//declare the sorter to allowing sorting by quantity
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(productTableModel);
		//add sorter to the table
		productTable.setRowSorter(sorter);
		List<TableRowSorter.SortKey> sortQuantity = new ArrayList<>();
		 
		//sort by quantity column
		sortQuantity.add(new RowSorter.SortKey(6, SortOrder.DESCENDING));
		sorter.setSortKeys(sortQuantity);
		sorter.sort();
		
		//table conofigurations
		productTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		productTable.setBounds(208, 57, 566, 438);
		productTable.setVisible(false);
		productTable.getTableHeader().setReorderingAllowed(false);
		
		//add table to scroll pane
		JScrollPane scrollPane = new JScrollPane(productTable);
		scrollPane.setBounds(10, 108, 501, 375);
		scrollPane.setVisible(true);
		productTable.setVisible(true);
		contentPane.add(scrollPane);
		
		//create search bar
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//when a letter is entered beginning filtering
				String tableSearch = searchField.getText(); //input is the filter
				TableRowSorter<TableModel> searchbar = new TableRowSorter<TableModel>(productTableModel);
				productTable.setRowSorter(searchbar);
				searchbar.setRowFilter(RowFilter.regexFilter(tableSearch));
			}
		});
		//search bar configurations
		searchField.setBounds(118, 69, 196, 22);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		
		//basket table model with the data and column parameters
		basketTableModel = new DefaultTableModel(basketdata, basketcolumns) {
				 @Override
				    public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				       
				    }
				};;
		//create new table for basket
		basketTable = new JTable(basketTableModel);
		
		
		
		basketTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		basketTable.setBounds(208, 57, 566, 438);
		basketTable.setVisible(false);
		basketTable.getTableHeader().setReorderingAllowed(false);
		
		//add basket to another scroll pane
		JScrollPane scrollPane_1 = new JScrollPane(basketTable);
		scrollPane_1.setBounds(525, 108, 259, 375);
		scrollPane_1.setVisible(true);
		basketTable.setVisible(true);
		contentPane.add(scrollPane_1);
		
		//warning label for displaying errors
		JLabel warningLabel = new JLabel("");
		warningLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		warningLabel.setBounds(10, 38, 245, 20);
		contentPane.add(warningLabel);
		
		JButton addToBasketButton = new JButton("Add To Basket");
		addToBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//add to basket action
				boolean duplicate = false;
				int duplicateRow = 0;
				int duplicateValue = 0;
				//check to see fi there are duplicates in the basket
				for (int i=0; i<basketTable.getRowCount();i++) {
					//if there are duplicates then set it as duplicate
					if (basketTable.getValueAt(i,0).equals(productTable.getValueAt(productTable.getSelectedRow(), 0))) {
						duplicate = true;
						duplicateRow = i;
						duplicateValue = Integer.parseInt(basketTable.getValueAt(i, 3).toString());
						break;
					}
					else {
						//do nothing
					}
				}
				if (duplicate && (Integer.parseInt(productTable.getValueAt(productTable.getSelectedRow(),6).toString()) > duplicateValue)) {
					basketTable.setValueAt(duplicateValue+1, duplicateRow, 3);
					
				}
				//dont add it as another row instead add one to the quantity
				if (duplicate) {
					//don't add again
				}
				else {
					//add none duplicate to the basket
					basketTableModel.addRow(new Object[]{
							productTable.getValueAt(productTable.getSelectedRow(), 0),
							productTable.getValueAt(productTable.getSelectedRow(), 1),
							productTable.getValueAt(productTable.getSelectedRow(), 2),
							"1",
							productTable.getValueAt(productTable.getSelectedRow(), 7),
							});
				
				}
				//add the price every time
				double totalPrice = 0;
				NumberFormat decimalFormat = new DecimalFormat("#0.00");     
			 
				for (int j=0; j<basketTable.getRowCount();j++) {
					totalPrice += Double.parseDouble(basketTable.getValueAt(j, 4).toString().replace("£"," "))*Double.parseDouble(basketTable.getValueAt(j, 3).toString());
				}
				displayPriceField.setText("£"+decimalFormat.format(totalPrice));
			}
		});
		addToBasketButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		addToBasketButton.setBounds(129, 494, 387, 56);
		contentPane.add(addToBasketButton);
		
		//display the price
		displayPriceField = new JTextField();
		displayPriceField.setFont(new Font("Tahoma", Font.BOLD, 14));
		displayPriceField.setBounds(538, 506, 111, 38);
		contentPane.add(displayPriceField);
		displayPriceField.setColumns(10);
		
		//pay now button
		JButton payNowButton = new JButton("Pay Now");
		payNowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (basketTable.getRowCount()>0) {
					//add the current shopping basket to a textfile
					FileWriter outputFile;
					try {
						outputFile = new FileWriter("Userbasket.txt");
						BufferedWriter bw = new BufferedWriter(outputFile);
						int i=0;
						int j=0;
						for (i=0; i<basketTable.getRowCount();i++) {
							for (j=0; j<basketTable.getColumnCount();j++) {
								if (j < (basketTable.getColumnCount() -1)){
									bw.write(((basketTable.getValueAt(i,j)).toString()+", "));
								}
								else {
									bw.write((basketTable.getValueAt(i,j)).toString()+"\n");
								}
							
						}
						}
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//then open the payment frame
					Paymentframe payframe = new Paymentframe();
					payframe.setVisible(true);
					dispose();
				}
				else {
					//if the basket is empty dont leave the frame and display warning
					warningLabel.setText("Basket is Empty");
				}
			}
		});
		payNowButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		payNowButton.setForeground(new Color(0, 0, 0));
		payNowButton.setBounds(659, 506, 115, 39);
		contentPane.add(payNowButton);
		
		//save for later, also saves to text file
		JButton save4LaterButton = new JButton("Save For Later");
		save4LaterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				warningLabel.setText("Saved for later");
				FileWriter outputFile;
				try {
					outputFile = new FileWriter("Userbasket.txt");
					BufferedWriter bw = new BufferedWriter(outputFile);
					int i=0;
					int j=0;
					for (i=0; i<basketTable.getRowCount();i++) {
						for (j=0; j<basketTable.getColumnCount();j++) {
							if (j < (basketTable.getColumnCount() -1)){
								bw.write(((basketTable.getValueAt(i,j)).toString()+", "));
							}
							else {
								bw.write((basketTable.getValueAt(i,j)).toString()+"\n");
							}
						
					}
					}
					bw.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for (int j = 0; j<basketTable.getRowCount();j++) {
					Log logfile = new Log(Mainframe.getID().toString(),Mainframe.getPostcode(),
							basketTable.getValueAt(j, 0).toString(), Double.parseDouble(basketTable.getValueAt(j, 4).toString().replace("£", "")),
							Integer.parseInt(basketTable.getValueAt(j, 3).toString()),"saved"," ");
					try {
						//create a log once a product has been saved for later
						logfile.createLog();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
							
				}	
			}
		});
		save4LaterButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		save4LaterButton.setBounds(573, 51, 167, 20);
		contentPane.add(save4LaterButton);
		
		//get the values from the user basket
		JButton previousShopLabel = new JButton("Previous Shop");
		previousShopLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Users.Customers.previousBasket(basketTable, warningLabel, basketTableModel, displayPriceField);
			}
				
		});
		previousShopLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		previousShopLabel.setBounds(573, 31, 167, 19);
		contentPane.add(previousShopLabel);
		
		JButton clearBasketButton = new JButton("Clear Basket");
		clearBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Users.Customers.clearBasket(warningLabel, basketTableModel, displayPriceField);
			}
		});
		clearBasketButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		clearBasketButton.setBounds(573, 9, 167, 21);
		contentPane.add(clearBasketButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mainframe frame;
				try {
					frame = new Mainframe();
					frame.setVisible(true);
					dispose();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		backButton.setBounds(0, 11, 110, 23);
		contentPane.add(backButton);

			
	}

	public Object[] getBasketcolumns() {
		
		return basketcolumns;
	}
}
