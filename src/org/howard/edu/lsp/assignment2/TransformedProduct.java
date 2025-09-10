package org.howard.edu.lsp.assignment2;

public class TransformedProduct {
    private final int productId;
    private final String name;     
    private final double price;    
    private final String category;  
    private final String priceRange; 

    public TransformedProduct(int productId, String name, double price, String category, String priceRange) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public String getPriceRange() { return priceRange; }

    /** Helper we’ll use when writing CSV later */
    public String toCsvRow() {
        return productId + "," + name + "," + String.format("%.2f", price) + "," + category + "," + priceRange;
    }
}
