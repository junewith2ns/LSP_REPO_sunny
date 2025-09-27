package org.howard.edu.lsp.assignment3;

/**
 * TransformedProduct represents a product after all transformations are applied.
 * <p>
 * Extends {@link Product} by adding a priceRange field and a CSV export helper.
 * </p>
 */
public class TransformedProduct extends Product {
    private final String priceRange;

    /**
     * Build a TransformedProduct.
     *
     * @param productId  unique product ID
     * @param name       transformed product name (uppercase)
     * @param price      transformed price (may include discount/rounding)
     * @param category   transformed category (may be recategorized)
     * @param priceRange computed range: Low, Medium, High, Premium
     */
    public TransformedProduct(int productId, String name, double price, String category, String priceRange) {
        super(productId, name, price, category);
        this.priceRange = priceRange;
    }

    /**
     * @return the price range (Low, Medium, High, Premium)
     */
    public String getPriceRange() {
        return priceRange;
    }

    /**
     * Convert this product into one CSV row string.
     *
     * @return formatted CSV line with all fields
     */
    public String toCsvRow() {
        return getProductId() + "," +
               getName() + "," +
               getPrice() + "," +
               getCategory() + "," +
               priceRange;
    }
}
