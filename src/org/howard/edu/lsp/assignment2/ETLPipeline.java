package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.util.*;

public class ETLPipeline {

    public static void main(String[] args) {
        List<Product> products = extract("data/products.csv");

        // transform (uppercase names only for now)
        List<TransformedProduct> trows = transformUppercase(products);

        // Temporary
        System.out.println("Transformed preview (first few rows):");
        for (int i = 0; i < Math.min(5, trows.size()); i++) {
            TransformedProduct r = trows.get(i);
            System.out.println(r.getProductId() + "," + r.getName() + "," + r.getPrice() + "," + r.getCategory());
        }
    }

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

    /** ONLY uppercase names; keep price/category unchanged; priceRange blank for now. */
    public static List<TransformedProduct> transformUppercase(List<Product> products) {
        List<TransformedProduct> result = new ArrayList<>();
        for (Product p : products) {
            String upperName = p.getName().toUpperCase();
            result.add(new TransformedProduct(
                p.getProductId(),
                upperName,
                p.getPrice(),
                p.getCategory(),
                "" // to be filled
            ));
        }
        return result;
    }
}
