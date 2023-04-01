import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.ScrollPane;

public class Adminframe extends JFrame {
	
	//declarations of all the attributes needed for the Admin frame
	private JPanel contentPane;
	private final JButton btnNewButton = new JButton("View all products");
	private final JButton btnNewButton_1 = new JButton("Add a new product");
	private static final Object[][] data = {};
	private static final Object[] columns = {"Barcode ID", "Device Name","Device Type","Brand","Colour","Connectivity","Quantity","Original Price","Retail Price","Extra info"};
	private JTextField barcodeField;
	private JTextField nameField;
	private JTextField brandField;
	private JTextField colourField;
	private JTextField OriginalPField;
	private JTextField quantityField;
	private JTextField OriginalRField;
	private JTextField connectivityField;
	private JTextField extraInfoField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//run the frame
					Adminframe admframe = new Adminframe();
					admframe.setVisible(true);
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
	public Adminframe()  {
		//creation of the admin frame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel validationText = new JLabel("New label");
		validationText.setBounds(600, 358, 174, 23);
		validationText.setVisible(false);
		contentPane.add(validationText);
		
		
		//label for admin page, introduces the admin
		JLabel lblNewLabel = new JLabel("Welcome Admin");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 26));
		lblNewLabel.setBounds(144, 5, 485, 41);
		contentPane.add(lblNewLabel);
		
		//Add stock form
		//input colour of new product
		colourField = new JTextField();
		colourField.setColumns(10);
		colourField.setBounds(308, 335, 80, 23);
		colourField.setVisible(false); //dont inital display the component
		contentPane.add(colourField);
		
		JLabel colourLabel = new JLabel("Colour:");
		colourLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		colourLabel.setBounds(255, 333, 52, 24);
		colourLabel.setVisible(false); //dont inital display the component
		contentPane.add(colourLabel);
		//input original price of new product
		OriginalPField = new JTextField();
		OriginalPField.setColumns(10);
		OriginalPField.setBounds(600, 226, 80, 23);
		OriginalPField.setVisible(false);//dont inital display the component
		contentPane.add(OriginalPField);
		//input extra info depending on device type of new product
		JLabel extraInfoLabel = new JLabel("Extra Info: ");
		extraInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		extraInfoLabel.setBounds(522, 333, 76, 24);
		extraInfoLabel.setVisible(false);//dont inital display the component
		contentPane.add(extraInfoLabel);
		//input quantity of new product
		quantityField = new JTextField();
		quantityField.setColumns(10);
		quantityField.setBounds(599, 167, 80, 23);
		quantityField.setVisible(false);//dont inital display the component
		contentPane.add(quantityField);
		
		JLabel connectivityLabel = new JLabel("Connectivity:");
		connectivityLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		connectivityLabel.setBounds(509, 106, 88, 24);
		connectivityLabel.setVisible(false);//dont inital display the component
		contentPane.add(connectivityLabel);
		//input extra info of new product
		extraInfoField = new JTextField();
		extraInfoField.setColumns(10);
		extraInfoField.setBounds(599, 335, 80, 23);
		extraInfoField.setVisible(false);//dont inital display the component
		contentPane.add(extraInfoField);
		
		//input retail price of new product
		OriginalRField = new JTextField();
		OriginalRField.setColumns(10);
		OriginalRField.setBounds(599, 282, 80, 23);
		OriginalRField.setVisible(false);//dont inital display the component
		contentPane.add(OriginalRField);
		
		JLabel OriginalPLabel = new JLabel("Original Price: ");
		OriginalPLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		OriginalPLabel.setBounds(503, 225, 99, 24);
		OriginalPLabel.setVisible(false);//dont inital display the component
		contentPane.add(OriginalPLabel);
		
		JLabel OriginalRLabel = new JLabel("Retail Price: ");
		OriginalRLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		OriginalRLabel.setBounds(522, 280, 80, 24);
		OriginalRLabel.setVisible(false);//dont inital display the component
		contentPane.add(OriginalRLabel);
		
		//input connectivity of new product
		connectivityField = new JTextField();
		connectivityField.setColumns(10);
		connectivityField.setBounds(599, 108, 80, 23);
		connectivityField.setVisible(false);//dont inital display the component
		contentPane.add(connectivityField);
		
		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		quantityLabel.setBounds(522, 165, 80, 24);
		quantityLabel.setVisible(false);//dont inital display the component
		contentPane.add(quantityLabel);
		
		//input name of new product
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(308, 166, 80, 23);
		nameField.setVisible(false);//dont inital display the component
		contentPane.add(nameField);
		
		JLabel barcodeLabel = new JLabel("Barcode ID:");
		barcodeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		barcodeLabel.setBounds(231, 106, 80, 24);
		barcodeLabel.setVisible(false);//dont inital display the component
		contentPane.add(barcodeLabel);
		
		//input brand of new product
		brandField = new JTextField();
		brandField.setColumns(10);
		brandField.setBounds(308, 278, 80, 23);
		brandField.setVisible(false);//dont inital display the component
		contentPane.add(brandField);
		
		//create a combobox to select whether the new product is a keyboard or mouse
		JComboBox TypeField = new JComboBox();
		TypeField.setModel(new DefaultComboBoxModel(new String[] {"keyboard", "mouse"}));
		TypeField.setBounds(308, 227, 80, 22);
		TypeField.setVisible(false);//dont inital display the component
		contentPane.add(TypeField);
		
		JLabel brandLabel = new JLabel("Brand: ");
		brandLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		brandLabel.setBounds(254, 276, 44, 24);
		brandLabel.setVisible(false);//dont inital display the component
		contentPane.add(brandLabel);
		
		//input barcode of new product
		barcodeField = new JTextField();
		barcodeField.setBounds(308, 108, 80, 23);
		barcodeField.setVisible(false);//dont inital display the component
		contentPane.add(barcodeField);
		barcodeField.setColumns(10);
		
		JLabel deviceNameLabel = new JLabel("Device Name:");
		deviceNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		deviceNameLabel.setBounds(223, 164, 80, 24);
		deviceNameLabel.setVisible(false);//dont inital display the component
		contentPane.add(deviceNameLabel);
		
		JLabel deviceTypeLabel = new JLabel("Device Type:");
		deviceTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		deviceTypeLabel.setBounds(227, 226, 80, 24);
		deviceTypeLabel.setVisible(false);//dont inital display the component
		contentPane.add(deviceTypeLabel);
		
	
		
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
	    int ArrayNum = 0;
	    Stock[] StocksArray = new Stock[50];
	    Stock st1 =null;
		while (filescanner_1.hasNextLine()){
			String [] userInfo = filescanner_1.nextLine().split(",");
		    st1 = new Stock(Integer.parseInt(userInfo[0].trim()),userInfo[1].trim(),userInfo[2].trim(),userInfo[3].trim() , userInfo[4].trim() , userInfo[5].trim(),Integer.parseInt(userInfo[6].trim()), Double.parseDouble(userInfo[7].trim()), Double.parseDouble(userInfo[8].trim()), userInfo[9].trim());
		    StocksArray [ArrayNum] = st1;
			ArrayNum++;
	    }
		//create a new table model for the products table
		DefaultTableModel productTableModel;
		productTableModel = new DefaultTableModel(data, columns) {
			//declare types of each column for more reliability with sorting
			Class[] types = { Integer.class, String.class, String.class, String.class,
					String.class, String.class, Integer.class, Integer.class,Integer.class,
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
	
		for(int i=0; i<ArrayNum; i++) {
			//add all values for the table to the product table
			productTableModel.addRow(new Object[]{
					 StocksArray[i].getBarcodeID(),StocksArray[i].getDeviceName(),
					 StocksArray[i].getDeviceType(),StocksArray[i].getBrand(),
					 StocksArray[i].getColour(),StocksArray[i].getConnectivity(),
					 StocksArray[i].getQuantity(),StocksArray[i].getOriginalPrice(),
					 StocksArray[i].getRetailPrice(),StocksArray[i].getExtraInfo()});
		}
		
		//create the table component
		JTable productTable;
		productTable = new JTable(productTableModel);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(productTableModel);
		productTable.setRowSorter(sorter);
		
		
		productTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		productTable.setBounds(208, 57, 566, 438);
		productTable.setVisible(false);
		
		// create the scroll pane for the product table
		JScrollPane scrollPane = new JScrollPane(productTable);
		scrollPane.setBounds(207, 57, 567, 438);
		scrollPane.setVisible(false);
		contentPane.add(scrollPane);
		
		//add product button
		JButton AddButton = new JButton("Add Product");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean correctFormat = false;
				boolean validated = false;
				//if the device type is mouse then check it is an integer
				if (TypeField.getSelectedItem().equals("mouse")) {
					try {
						//try parsing the input as integer if it returns an error then display
						// the error message
						int i = Integer.parseInt(extraInfoField.getText());
						correctFormat = true;
						validationText.setVisible(false);
					}catch(NumberFormatException j){
						validationText.setVisible(true);
						validationText.setText("Invalid Number");
					}
					try {
						//try parsing the barcode as integer if it returns an error then display
						// the error message
						int j = Integer.parseInt(barcodeField.getText());
						validated = true;
						validationText.setVisible(false);
					}catch(NumberFormatException j){
						validationText.setVisible(true);
						validationText.setText("Invalid Barcode");
						validated = false;
					}
				}
				//if the device type is keyboard then check it is UK or US
				else if (TypeField.getSelectedItem().equals("keyboard")) {
					if  ((extraInfoField.getText().equals("UK") 
							|| (TypeField.getSelectedItem()).toString().equals("US"))) {
						// if it is not UK or US then return an error message
						// the error message
						correctFormat = true;
						validationText.setVisible(false);
					}
					else {
						validationText.setVisible(true);
						validationText.setText("Invalid String - Try UK or US");
					}
					try {
						//try parsing the barcode as integer if it returns an error then display
						// the error message
						int j = Integer.parseInt(barcodeField.getText());
						validated = true;
						validationText.setVisible(false);
					}catch(NumberFormatException j){
						validationText.setVisible(true);
						validationText.setText("Invalid Barcode");
						validated = false;
					}
				}
				//validation
				
				if (correctFormat && validated) {
					try {
						//only if the format is correct then add it to the table and text file
						Users.Admins.addToStock(Integer.parseInt(barcodeField.getText()), (TypeField.getSelectedItem()).toString(), nameField.getText(), brandField.getText(), 
								colourField.getText(), connectivityField.getText(), Integer.parseInt(quantityField.getText()),
								Double.parseDouble(OriginalPField.getText()), Double.parseDouble(OriginalRField.getText()), extraInfoField.getText());
						productTableModel.addRow(new Object[]{
								Integer.parseInt(barcodeField.getText()), (TypeField.getSelectedItem()).toString(), nameField.getText(), brandField.getText(), 
								colourField.getText(), connectivityField.getText(), Integer.parseInt(quantityField.getText()),
								Double.parseDouble(OriginalPField.getText()), Double.parseDouble(OriginalRField.getText()), extraInfoField.getText()});
						
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		AddButton.setBounds(396, 409, 202, 41);
		AddButton.setVisible(false);
		contentPane.add(AddButton);
		
		//set the actions for the buttons clicks to display seperate screens
		//display products table
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productTable.setVisible(true);
				scrollPane.setVisible(true);
				colourField.setVisible(false);
				validationText.setVisible(false);
				colourLabel.setVisible(false);
				OriginalPField.setVisible(false);
				extraInfoLabel.setVisible(false);
				quantityField.setVisible(false);
				connectivityLabel.setVisible(false);
				extraInfoField.setVisible(false);
				OriginalRField.setVisible(false);
				OriginalPLabel.setVisible(false);
				OriginalRLabel.setVisible(false);
				connectivityField.setVisible(false);
				quantityLabel.setVisible(false);
				TypeField.setVisible(false);
				nameField.setVisible(false);
				barcodeLabel.setVisible(false);
				brandField.setVisible(false);
				brandLabel.setVisible(false);
				barcodeField.setVisible(false);
				deviceNameLabel.setVisible(false);
				deviceTypeLabel.setVisible(false);
				AddButton.setVisible(false);
			}
		});
		
		//display form for adding to Stock
		btnNewButton.setBounds(45, 71, 152, 49);
		contentPane.add(btnNewButton);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productTable.setVisible(false);
				scrollPane.setVisible(false);
				validationText.setVisible(false);
				colourField.setVisible(true);
				colourLabel.setVisible(true);
				OriginalPField.setVisible(true);
				extraInfoLabel.setVisible(true);
				quantityField.setVisible(true);
				connectivityLabel.setVisible(true);
				extraInfoField.setVisible(true);
				OriginalRField.setVisible(true);
				OriginalPLabel.setVisible(true);
				OriginalRLabel.setVisible(true);
				connectivityField.setVisible(true);
				quantityLabel.setVisible(true);
				TypeField.setVisible(true);
				nameField.setVisible(true);
				barcodeLabel.setVisible(true);
				brandField.setVisible(true);
				brandLabel.setVisible(true);
				barcodeField.setVisible(true);
				deviceNameLabel.setVisible(true);
				deviceTypeLabel.setVisible(true);
				AddButton.setVisible(true);
	
				
			}
		});

		btnNewButton_1.setBounds(45, 163, 152, 49);
		
		contentPane.add(btnNewButton_1);
		
		//back button returns to main frame
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
}
