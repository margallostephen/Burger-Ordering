public class Node {

	private double price;
	private String ingredientName, productName;
	private int stock, maxCapacity, quantity, consumed, batch, orderNum;
	private Node next;

	public Node(String productName, int quantity, double price, int orderNum) {
		this.setProductName(productName);
		this.setQuantity(quantity);
		this.setPrice(price);
		this.setOrderNum(orderNum);
	}

	public Node(String ingredientName, int stock, int maxCapacity, int consumed, int batch) {
		this.setIngredientName(ingredientName);
		this.setStock(stock);
		this.setMaxCapacity(maxCapacity);
		this.setConsumed(consumed);
		this.setBatchNum(batch);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getConsumed() {
		return consumed;
	}

	public void setConsumed(int consumed) {
		this.consumed = consumed;
	}

	public int getBatchNum() {
		return batch;
	}

	public void setBatchNum(int batchNum) {
		this.batch = batchNum;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;	
	}
}
