import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Log {
	//declaration of attributes of log class
	private String id;
	private String postcode;
	private String barcode;
	private double price;
	private int quantity;
	private String status;
	private String purchase;
	
	//declaration and initialization of date
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date();
	
	public String getID() { //getter method to obtain the users ID
		return id;
	}
	public String getPostcode() { //getter method to obtain the postcode
		return postcode;
	}
	public String getBarcode() { //getter method to obtain the barcode
		return barcode;
	}
	public double getPrice() { //getter method to obtain the price
		return price;
	}
	public double getQuantity() { //getter method to obtain the Quantity
		return quantity;
	}
	public String getStatus() { //getter method to obtain the status
		return status;
	}
	public String getPurchase() { //getter method to obtain the purchase type
		return purchase;
	}
	
	//constructor of log class and assigning values to attributes
	public Log(String ID, String Postcode, String Barcode, double Price, int Quantity, String Status, String Purchase) {
		this.id=ID;
		this.postcode=Postcode;
		this.barcode=Barcode;
		this.price=Price;
		this.quantity=Quantity;
		this.status=Status;
		this.purchase=Purchase;

	
	}
	//write to log file and append to the top
	public void createLog() throws IOException {
		FileWriter outputFile;
		//initial string to be inserted into log
		String logInput = "";
		//file to write to
		File inputFile = new File("ActivityLog.txt");
	    Scanner filescanner = new Scanner(inputFile);
		while (filescanner.hasNextLine()){
			//take every previous line from the log and append a new line to it
			logInput = logInput + filescanner.nextLine()+"\n";
		}
		try {
			outputFile = new FileWriter("ActivityLog.txt");
			BufferedWriter bw = new BufferedWriter(outputFile);
			//write the latest log to the file
			bw.write(getID()+", "+getPostcode()+", "+getBarcode()+", "
					+ getPrice()+", "+getQuantity()+", "+ getStatus()+", "+getPurchase()+", "
					+ dateFormat.format(date)+"\n"+logInput);
		
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
