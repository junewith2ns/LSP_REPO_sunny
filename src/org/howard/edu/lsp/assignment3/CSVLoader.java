package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * CSVLoader writes transformed product data into a CSV file.
 */
public class CSVLoader {

    /**
     * Writes the transformed rows to the specified output CSV file.
     * Always includes a header row, followed by each product row.
     *
     * @param outputPath the path where the CSV file will be created
     * @param rows list of transformed product rows to write
     */
    public void load(String outputPath, List<TransformedProduct> rows) {
        try {
            File outFile = new File(outputPath);
            File parent = outFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
                // Write header
                bw.write("ProductID,Name,Price,Category,PriceRange");
                bw.newLine();

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
