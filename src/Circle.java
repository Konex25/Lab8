public class Circle {

    // radius is private to respect encapsulation – only Circle controls its state
    private double radius;

    // Public constructor – we want users of the class to be able to create instances.
    public Circle(double radius) {
        setRadius(radius);
    }
    // Getter – public to allow read-only access to radius from other classes.
    public double getRadius() {
        return radius;
    }
    // Getter – public to allow read-only access to radius from other classes.
    public void setRadius(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative.");
        }
        this.radius = radius;
    }

    /**
     * Computes circumference: 2 * π * r
     * Time complexity: O(1)
     */
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    /**
     * Computes area: π * r^2
     * Time complexity: O(1)
     */
    public double getArea() {
        return Math.PI * radius * radius;
    }
}