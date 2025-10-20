package org.howard.edu.lsp.midterm.question2;

public class Main {
    public static void main(String[] args) {
  
        double circle = AreaCalculator.area(3.0);
        System.out.println("Circle radius 3.0 -> area = " + circle);

        double rect = AreaCalculator.area(5.0, 2.0);
        System.out.println("Rectangle 5.0 x 2.0 -> area = " + rect);

        double tri = AreaCalculator.area(10, 6);
        System.out.println("Triangle base 10, height 6 -> area = " + tri);

        double sq = AreaCalculator.area(4);
        System.out.println("Square side 4 -> area = " + sq);

        // Exception demo: invoke with an invalid value and handle it
        try {
            AreaCalculator.area(-1.0);  // invalid radius
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /*
    Overloading means using the same method name 'area' for different shapes.
    The methods take different inputs, so Java can tell them apart.
    It’s cleaner than making separate names for every shape.
    */

}
