package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Transformer applies all transformation rules to a list of Products.
 * <p>
 * Rules:
 * 1. Convert name to uppercase.
 * 2. Apply 10% discount if category = "Electronics" (round to 2 decimals, HALF_UP).
 * 3. If discounted price > 500 and category was "Electronics", set category to "Premium Electronics".
 * 4. Assign price range (Low, Medium, High, Premium).
 * </p>
 */
public class Transformer {

    /**
     * Run all transformations on a list of raw {@link Product} objects.
     *
     * @param products list of input products
     * @return list of transformed products with updated fields
     */
    public List<TransformedProduct> runAll(List<Product> products) {
        List<TransformedProduct> transformed = new ArrayList<>();

        for (Product p : products) {
            // 1. Uppercase name
            String name = p.getName().toUpperCase();

            // 2. Discount if Electronics
            double price = p.getPrice();
            String category = p.getCategory();
            if ("Electronics".equals(category)) {
                price = round2(price * 0.90);  // apply 10% discount
            }

            // 3. Recategorize if discounted price > 500
            if ("Electronics".equals(category) && price > 500.0) {
                category = "Premium Electronics";
            }

            // 4. Price range
            String priceRange;
            if (price <= 10.00) {
                priceRange = "Low";
            } else if (price <= 100.00) {
                priceRange = "Medium";
            } else if (price <= 500.00) {
                priceRange = "High";
            } else {
                priceRange = "Premium";
            }

            transformed.add(new TransformedProduct(
                p.getProductId(), name, price, category, priceRange
            ));
        }
        return transformed;
    }

    /**
     * Round a double to 2 decimals using HALF_UP.
     *
     * @param value number to round
     * @return rounded value
     */
    private double round2(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
