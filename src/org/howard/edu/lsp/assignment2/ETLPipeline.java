package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    public static void main(String[] args) {
        List<Product> products = extract("data/products.csv");

        // Uppercase names
        List<TransformedProduct> trows = transformUppercase(products);

        // 10% discount for Electronics (HALF_UP rounding)
        List<TransformedProduct> discounted = applyElectronicsDiscount(trows);

        // Recategorize Electronics > $500 as Premium Electronics
        List<TransformedProduct> recategorized = recategorizePremium(discounted);

        // Temporary preview
        System.out.println("After recategorization (first few rows):");
        for (int i = 0; i < Math.min(6, recategorized.size()); i++) {
            TransformedProduct r = recategorized.get(i);
            System.out.println(r.getProductId() + "," + r.getName() + "," + r.getPrice() + "," + r.getCategory());
        }
    }

    // Extract: read products.csv
    public static List<Product> extract(String filePath) {
        List<Product> products = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Error: input file not found at " + filePath);
            return products;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // header
            if (line == null) {
                System.out.println("Input file is empty.");
                return products;
            }
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                String category = parts[3].trim();

                products.add(new Product(id, name, price, category));
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

    // Round to 2 decimals, HALF_UP.
    private static double round2(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    // 10% discount for Electronics; others unchanged
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
                ""   // priceRange later
            ));
        }
        return out;
    }
}
