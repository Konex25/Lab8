import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            // ----- Circle & Sphere demo -----
            System.out.print("Enter radius for circle and sphere: ");
            double radius = scanner.nextDouble();

            Circle circle = new Circle(radius);
            Sphere sphere = new Sphere(radius);

            System.out.printf("%nCIRCLE (r = %.2f)%n", circle.getRadius());
            System.out.printf("Circumference: %.4f%n", circle.getCircumference());
            System.out.printf("Area:          %.4f%n", circle.getArea());

            System.out.printf("%nSPHERE (r = %.2f)%n", sphere.getRadius());
            System.out.printf("Surface area:  %.4f%n", sphere.getSurfaceArea());
            System.out.printf("Volume:        %.4f%n", sphere.getVolume());

            // ----- Matrix demo -----
            System.out.print("\nEnter number of rows for matrix A: ");
            int rowsA = readPositiveInt(scanner);

            System.out.print("Enter number of columns for matrix A (and rows for B): ");
            int colsA = readPositiveInt(scanner);

            System.out.print("Enter number of columns for matrix B: ");
            int colsB = readPositiveInt(scanner);

            Random random = new Random();
            int minRandom = 1;
            int maxRandom = 9;

            Matrix A = new Matrix(rowsA, colsA, minRandom, maxRandom, random);
            Matrix B = new Matrix(colsA, colsB, minRandom, maxRandom, random);

            System.out.println("\nMatrix A:");
            A.print();

            System.out.println("Matrix B:");
            B.print();

            System.out.println("Transpose of A:");
            Matrix AT = A.transpose();
            AT.print();

            System.out.println("Spiral order of A:");
            A.printSpiral();

            System.out.println("\nA * B:");
            Matrix product = A.multiply(B);
            product.print();
        }
    }

    /**
     * Reads a positive integer from scanner.
     */
    private static int readPositiveInt(Scanner scanner) {
        int value;
        do {
            value = scanner.nextInt();
        } while (value <= 0);
        return value;
    }
}