package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVLoader {

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
