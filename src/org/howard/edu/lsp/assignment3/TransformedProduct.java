package org.howard.edu.lsp.assignment3;


public class TransformedProduct extends Product {
    private final String priceRange;

    public TransformedProduct(int productId, String name, double price, String category, String priceRange) {
        super(productId, name, price, category);
        this.priceRange = priceRange;
    }

    // return the price range category (Low, Medium, High, Premium) 
    public String getPriceRange() {
        return priceRange;
    }

    public String toCsvRow() {
        return getProductId() + "," +
               getName() + "," +
               getPrice() + "," +
               getCategory() + "," +
               priceRange;
    }
}

