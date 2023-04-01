
public class ShoppingBasket {
	private int barcode;
	private String name;
	private String brand;
	private String quantity;
	private String price;

	public int getBarcode() { //getter method to obtain the barcode
		return barcode;
	}
	public String getName() { //getter method to obtain the name
		return name;
	}
	public String getBrand() { //getter method to obtain the brand
		return brand;
	}
	public String getQuantity() { //getter method to obtain the quantity
		return quantity;
	}
	public String getPrice() { //getter method to obtain the price
		return price;
	}

	public ShoppingBasket(int Barcode, String Name, String Brand, String Quantity, String Price) {
		this.barcode=Barcode;
		this.name=Name;
		this.brand=Brand;
		this.quantity=Quantity;
		this.price=Price;
	
	}
	
}
