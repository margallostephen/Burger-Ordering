import static java.lang.System.out;

public class Queue {

    private Node front, rear; 

    public Node getFront() {
        return front;
    }
    
    public void setFront(Node front) {
        this.front = front;
    }

    public Node getRear() {
        return rear;
    }

    public void setRear(Node rear) {
        this.rear = rear;
    }

    public void enqueue(Node newNode) {
        if (getFront() == null) { 
            setFront(newNode);
            setRear(newNode);
        } else {
            getRear().setNext(newNode); 
            setRear(newNode); 
        }
    }

    public Node getCurrentBatch() {
        Node currentBatch = getFront(); 

        while (currentBatch.getStock() == 0 && currentBatch.getNext() != null) 
            currentBatch = currentBatch.getNext();
        
        return currentBatch; 
    }

    public int getAllStock() {
        Node currentBatch = getFront(); 
        int totalStock = 0;

        while (currentBatch != null) {
            totalStock += currentBatch.getStock(); 
            currentBatch = currentBatch.getNext();
        }
        
        return totalStock; 
    }

    public void displayInventory(String space, String label, String ingredient) {       
        out.printf("%n%s%n%" + space + "s%n%s%n%-8s%-11s%s%n", "=".repeat(30), label + " STOCK OF " + ingredient, "=".repeat(30), "Batch", "Consumed", "Remaining");

        Node currentBatch = getFront();

        while (currentBatch != null) {
            out.printf("%-8d%-11d%d%n", currentBatch.getBatchNum(), currentBatch.getConsumed(), currentBatch.getStock());
            currentBatch = currentBatch.getNext();
        }
    }

    public void displayOrder() {
		Node currentProduct = getFront(); 
        double total = 0.00; 
        int lastOrderNum; 
	
		out.printf("%n%s%d%n%-17s%-6s%s%n", "Order #", currentProduct.getOrderNum(), "Product", "Qty", "Price");
	
        while (currentProduct != null) {
            lastOrderNum = currentProduct.getOrderNum();
            total += currentProduct.getPrice();

            out.printf("%-17s%-6d%6.2f%n", currentProduct.getProductName(), currentProduct.getQuantity(), currentProduct.getPrice());

            currentProduct = currentProduct.getNext();
        
            try {
                if (currentProduct.getOrderNum() > lastOrderNum) { 
                    out.printf("%s%n%-23s%6.2f%n%s%n", "~".repeat(30), "Total:", total, "~".repeat(30));

                    total = 0.00; 

                    out.printf("%n%s%d%n%-17s%-6s%s%n", "Order #", currentProduct.getOrderNum(), "Product", "Qty", "Price");
                }
            } catch (NullPointerException e) {
                out.printf("%s%n%-23s%6.2f%n%s%n", "~".repeat(30), "Total:", total, "~".repeat(30));
            }
        }
	}
}