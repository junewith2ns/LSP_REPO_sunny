# Reflection — Ass 3
## What changed from A2 to A3
A2 design: One main class contained extract, multiple transforms, load, and printing. Logic was coupled and harder to test in pieces.
A3 design: Split into small classes with clear roles:
  `CSVExtractor` – reads `data/products.csv`, counts skipped lines.
  `Transformer` – applies all four steps in order: uppercase → discount (HALF_UP) → recategorize → price range.
  `CSVLoader` – writes `data/transformed_products.csv` with header.
  `Product` / `TransformedProduct` – data objects (with `toCsvRow()` helper in `TransformedProduct`).
  `ETLPipeline` – coordinates the pipeline and prints a summary of the run.

## A3 is more object-oriented
Encapsulation - Data is stored in objects. File IO and transformation logic are hidden behind class methods.
Separation of concerns - Each class has a single responsibility (extract / transform / load).
Inheritance & Polymorphism - `TransformedProduct` extends `Product`. The design makes it easy to add alternative Transformers later w/o changing the pipeline.

## OO concepts explicitly used
Object & Class - `Product`, `TransformedProduct`, `CSVExtractor`, `Transformer`, `CSVLoader`, `ETLPipeline`.
Encapsulation - Private fields with public getters. File IO and transformation logic are hidden behind class methods.
Inheritance - `TransformedProduct extends Product`.

## Confirming correctness (A3 == A2)
I tested the same three cases used in A2
1. Normal input - `data/products.csv` with the sample rows - output equals the golden file.
2. Empty input - file has only the header - output contains just the header.
3. Missing input file - file is absent - program prints a clear error and exits.


## Notes / Assumptions
- CSV has no embedded commas or quotes; first line is the header.
- Prices are rounded to two decimals using `RoundingMode.HALF_UP`.
- Relative paths are used; program is run from the project root.
