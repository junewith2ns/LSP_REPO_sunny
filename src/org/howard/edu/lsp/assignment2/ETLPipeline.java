package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    private static int skippedCount = 0;

    public static void main(String[] args) {
        // Handle “missing input file” 
        File in = new File("data/products.csv");
        if (!in.exists()) {
            System.out.println("Error: input file not found at data/products.csv");
            return; 
        }

        // Extract
        List<Product> products = extract("data/products.csv");
        int rowsRead = products.size(); // header excluded

        // Transform (uppercase → discount → recategorize → price range)
        List<TransformedProduct> trows = transformUppercase(products);
        List<TransformedProduct> discounted = applyElectronicsDiscount(trows);
        List<TransformedProduct> recategorized = recategorizePremium(discounted);
        List<TransformedProduct> finalRows = assignPriceRange(recategorized);

        // Load (always write header / write rows if any)
        String outPath = "data/transformed_products.csv";
        load(outPath, finalRows);

        // Run summary
        System.out.println("Run Summary:");
        System.out.println(" - Rows read: " + rowsRead);
        System.out.println(" - Rows transformed: " + finalRows.size());
        System.out.println(" - Rows skipped: " + skippedCount);
        System.out.println(" - Output: " + outPath);
    }

    // Extract: read products.csv
    public static List<Product> extract(String filePath) {
        skippedCount = 0; // resets for each run
        List<Product> products = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Error: input file not found at " + filePath);
            return products;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // header
            if (line == null) {
                return products;
            }
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) { skippedCount++; continue; }
                String[] parts = line.split(",");
                if (parts.length < 4) { skippedCount++; continue; }
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    String category = parts[3].trim();
                    products.add(new Product(id, name, price, category));
                } catch (NumberFormatException nfe) {
                    skippedCount++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return products;
    }

    // ONLY uppercase names
    public static List<TransformedProduct> transformUppercase(List<Product> products) {
        List<TransformedProduct> result = new ArrayList<>();
        for (Product p : products) {
            result.add(new TransformedProduct(
                p.getProductId(),
                p.getName().toUpperCase(),
                p.getPrice(),
                p.getCategory(),
                ""   // to be filled later
            ));
        }
        return result;
    }

    // Round to 2 decimals, HALF_UP
    private static double round2(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    // 10% discount for Electronics. Others unchanged
    public static List<TransformedProduct> applyElectronicsDiscount(List<TransformedProduct> rows) {
        List<TransformedProduct> out = new ArrayList<>();
        for (TransformedProduct r : rows) {
            double price = r.getPrice();
            if ("Electronics".equals(r.getCategory())) {
                price = round2(price * 0.90);
            }
            out.add(new TransformedProduct(
                r.getProductId(),
                r.getName(),
                price,
                r.getCategory(),
                ""   // priceRange later
            ));
        }
        return out;
    }

    // Recategorize Electronics over $500 as Premium Electronics
    public static List<TransformedProduct> recategorizePremium(List<TransformedProduct> rows) {
        List<TransformedProduct> out = new ArrayList<>();
        for (TransformedProduct r : rows) {
            String category = r.getCategory();
            if ("Electronics".equals(category) && r.getPrice() > 500.0) {
                category = "Premium Electronics";
            }
            out.add(new TransformedProduct(
                r.getProductId(),
                r.getName(),
                r.getPrice(),
                category,
                ""   // priceRange follows
            ));
        }
        return out;
    }

    // Compute PriceRange from final price
    public static List<TransformedProduct> assignPriceRange(List<TransformedProduct> rows) {
        List<TransformedProduct> out = new ArrayList<>();
        for (TransformedProduct r : rows) {
            String priceRange;
            double price = r.getPrice();

            if (price <= 10.00) {
                priceRange = "Low";
            } else if (price <= 100.00) {
                priceRange = "Medium";
            } else if (price <= 500.00) {
                priceRange = "High";
            } else {
                priceRange = "Premium";
            }

            out.add(new TransformedProduct(
                r.getProductId(),
                r.getName(),
                price,
                r.getCategory(),
                priceRange
            ));
        }
        return out;
    }

    // Load: write header + transformed rows to data/transformed_products.csv
    public static void load(String outputPath, List<TransformedProduct> rows) {
        try {
            // ensure parent folder exists
            File outFile = new File(outputPath);
            File parent = outFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
                // header
                bw.write("ProductID,Name,Price,Category,PriceRange");
                bw.newLine();

                // rows (if any)
                for (TransformedProduct r : rows) {
                    bw.write(r.toCsvRow());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing output: " + e.getMessage());
        }
    }
}
