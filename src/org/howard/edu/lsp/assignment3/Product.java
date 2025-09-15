package org.howard.edu.lsp.assignment3;

// One raw input row from products.csv.
// Immutable after construction.

public class Product {
    private final int productId;
    private final String name;
    private final double price;
    private final String category;

    public Product(int productId, String name, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getProductId() { return productId; }

    public String getName() { return name; }
    
    public double getPrice() { return price; }
    
    public String getCategory() { return category; }
}
