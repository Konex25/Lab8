/**
 * Computes area: π * r^2
 * Time complexity: O(1)
 */
public class Sphere {

    // radius is private to hide internal representation
    private double radius;

    // Public constructor – allows creating Sphere objects.
    public Sphere(double radius) {
        setRadius(radius);
    }

    // Public getter for radius.
    public double getRadius() {
        return radius;
    }

    // Public setter for radius with basic validation.
    public void setRadius(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative.");
        }
        this.radius = radius;
    }

    // Public setter for radius with basic validation.
    public double getSurfaceArea() {
        return 4 * Math.PI * radius * radius;
    }

    /**
     * Computes volume: 4/3 * π * r^3
     * Time complexity: O(1)
     */
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }
}