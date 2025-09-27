package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVExtractor reads products from data/products.csv
 * and returns a list of raw {@link Product} objects.
 */
public class CSVExtractor {
    private int skippedCount = 0;

    /**
     * Extracts rows from a CSV file into {@link Product} objects.
     *
     * @param filePath path to the input CSV file
     * @return list of Product objects read from the file
     */
    public List<Product> extract(String filePath) {
        skippedCount = 0;
        List<Product> products = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Error: input file not found at " + filePath);
            return products;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // header
            if (line == null) return products;

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

    /**
     * Returns the number of lines skipped during the last extract call.
     *
     * @return number of skipped lines
     */
    public int getSkippedCount() {
        return skippedCount;
    }
}
