import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Stock {
	private int barcodeId;
	private String deviceName;
	private String deviceType;
	private String brand;
	private String colour;
	private String connectivity;
	private int quantity;
	private double originalPrice;
	private double retailPrice;
	private String extraInfo;
	public int getBarcodeID() { //getter method to obtain the users ID
		return barcodeId;
	}
	public String getDeviceName() { //getter method to obtain the users ID
		return deviceName;
	}
	public String getDeviceType() { //getter method to obtain the users ID
		return deviceType;
	}
	public String getBrand() { //getter method to obtain the users ID
		return brand;
	}
	public String getColour() { //getter method to obtain the users ID
		return colour;
	}
	public String getConnectivity() { //getter method to obtain the users ID
		return connectivity;
	}
	public int getQuantity() { //getter method to obtain the users ID
		return quantity;
	}
	public double getOriginalPrice() { //getter method to obtain the users ID
		return originalPrice;
	}
	public double getRetailPrice() { //getter method to obtain the users ID
		return retailPrice;
	}
	public String getExtraInfo() { //getter method to obtain the users ID
		return extraInfo;
	}
	public String displayStock(){
		return "Barcode: "+barcodeId+", Device Name: "+deviceName+", Type:"
				+ deviceType +", Brand: "+brand +", Colour: " + colour + 
				", Connectivity: "+connectivity+ ", Quantity: "+ quantity+
				", Original Cost: "+ originalPrice+", Cost: "+retailPrice+
				", Additional: " + extraInfo;
	}
	public static void main(String[] args) throws IOException {
		FileWriter filename = new FileWriter("Stock.txt",true);
		PrintWriter pw = new PrintWriter(filename);
		pw.println("String");
		pw.close();
	}
	public Stock(int barcode, String Name,String Type,String brand,String colour,
	String connectivity,int Quantity,double oglPrice,double rtlPrice,String Info) {
		this.barcodeId=barcode;
		this.deviceName=Name;
		this.deviceType=Type;
		this.brand=brand;
		this.colour=colour;
		this.connectivity=connectivity;
		this.quantity=Quantity;
		this.originalPrice=oglPrice;
		this.retailPrice=rtlPrice;
		this.extraInfo=Info;
	
	}
}
