package org.howard.edu.lsp.assignment3;

/**
 * Product represents one raw input row from products.csv.
 * <p>
 * Immutable after construction.
 * </p>
 */
public class Product {
    private final int productId;
    private final String name;
    private final double price;
    private final String category;

    /**
     * Build a Product object.
     * 
     * @param productId unique product ID
     * @param name product name
     * @param price product price
     * @param category product category
     */
    public Product(int productId, String name, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    /** @return the product ID */
    public int getProductId() { return productId; }

    /** @return the product name */
    public String getName() { return name; }
    
    /** @return the product price */
    public double getPrice() { return price; }
    
    /** @return the product category */
    public String getCategory() { return category; }
}
