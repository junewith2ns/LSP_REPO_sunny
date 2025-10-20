package org.howard.edu.lsp.midterm.question2;

public class AreaCalculator {

    // Circle: π r^2
    public static double area(double radius) {
        validate(radius);
        return Math.PI * radius * radius;
    }

    // Rectangle: w * h
    public static double area(double width, double height) {
        validate(width);
        validate(height);
        return width * height;
    }

    // Triangle: 1/2 * base * height
    public static double area(int base, int height) {
        validate(base);
        validate(height);
        return 0.5 * base * height;
    }

    // Square: side^2
    public static double area(int side) {
        validate(side);
        return (double) side * side;
    }

    private static void validate(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Dimensions provided are not > 0");
        }
    }

    private static void validate(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Dimensions provided are not > 0");
        }
    }
}
