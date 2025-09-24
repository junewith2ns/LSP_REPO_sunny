package org.howard.edu.lsp.assignment3;

import java.io.File;
import java.util.List;

public class ETLPipeline {
    public static void main(String[] args) {
        final String INPUT  = "data/products.csv";
        final String OUTPUT = "data/transformed_products.csv";

        // Missing input file → clear error + exit
        File in = new File(INPUT);
        if (!in.exists()) {
            System.out.println("Error: input file not found at " + INPUT);
            return;
        }

        // Extract
        CSVExtractor extractor = new CSVExtractor();
        List<Product> products = extractor.extract(INPUT);
        int rowsRead = products.size();
        int skipped  = extractor.getSkippedCount();

        // Transform (uppercase → discount → recategorize → price range)
        Transformer transformer = new Transformer();
        List<TransformedProduct> transformed = transformer.runAll(products);

        // Load (always write header; write rows if any)
        CSVLoader loader = new CSVLoader();
        loader.load(OUTPUT, transformed);

        // Summary
        System.out.println("Run Summary:");
        System.out.println(" - Rows read: " + rowsRead);
        System.out.println(" - Rows transformed: " + transformed.size());
        System.out.println(" - Rows skipped: " + skipped);
        System.out.println(" - Output: " + OUTPUT);
    }
}
