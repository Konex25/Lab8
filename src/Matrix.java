import java.util.Random;

/**
 * Matrix class represents a 2D matrix of integers.
 * Functions:
 *  - be constructed as a random matrix,
 *  - be printed,
 *  - be transposed,
 *  - be printed in spiral order,
 *  - be multiplied by another Matrix object.
 */
public class Matrix {

    // Internal 2D array is private to preserve encapsulation
    private int[][] data;
    private int rows;
    private int cols;

    /**
     * Constructor that takes an existing 2D array.
     * Public because we might want to wrap an already created matrix.
     */
    public Matrix(int[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Data array must be non-empty.");
        }
        this.rows = data.length;
        this.cols = data[0].length;
        // Deep copy to protect internal state
        this.data = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            if (data[i].length != cols) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
            System.arraycopy(data[i], 0, this.data[i], 0, cols);
        }
    }

    /**
     * Constructor that creates a random matrix with given dimensions and value range.
     * Public – this is the main way to create random matrices from outside the class.
     *
     * Time complexity of filling the matrix: O(rows * cols)
     * Space complexity: O(rows * cols)
     */
    public Matrix(int rows, int cols, int minValue, int maxValue, Random random) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive.");
        }
        if (minValue > maxValue) {
            throw new IllegalArgumentException("minValue must be <= maxValue.");
        }
        if (random == null) {
            throw new IllegalArgumentException("Random cannot be null.");
        }

        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = random.nextInt(maxValue - minValue + 1) + minValue;
            }
        }
    }

    // Public getter for number of rows (read-only).
    public int getRows() {
        return rows;
    }

    // Public getter for number of columns (read-only).
    public int getCols() {
        return cols;
    }

    // Getter for a single element – public to allow reading particular values.
    public int getElement(int row, int col) {
        checkIndex(row, col);
        return data[row][col];
    }

    // Getter for a single element – public to allow reading particular values.
    public void setElement(int row, int col, int value) {
        checkIndex(row, col);
        data[row][col] = value;
    }

    /**
     * Prints the matrix to standard output.
     *
     * Time complexity: O(rows * cols)
     * Space complexity: O(1) extra space.
     */
    public void print() {
        for (int[] row : data) {
            for (int value : row) {
                System.out.printf("%4d", value);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Returns a new Matrix that is the transpose of this matrix.
     *
     * Time complexity: O(rows * cols)
     * Space complexity: O(rows * cols) for the new matrix.
     */
    public Matrix transpose() {
        int[][] transposed = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = data[i][j];
            }
        }
        return new Matrix(transposed);
    }

    /**
     * Prints the elements of the matrix in spiral order.
     *
     * Time complexity: O(rows * cols) – each element is visited once.
     * Space complexity: O(1) extra space.
     */
    public void printSpiral() {
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;

        while (top <= bottom && left <= right) {

            // Top row
            for (int col = left; col <= right; col++) {
                System.out.print(data[top][col] + " ");
            }
            top++;

            // Right column
            for (int row = top; row <= bottom; row++) {
                System.out.print(data[row][right] + " ");
            }
            right--;

            // Bottom row
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    System.out.print(data[bottom][col] + " ");
                }
                bottom--;
            }

            // Left column
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    System.out.print(data[row][left] + " ");
                }
                left++;
            }
        }
        System.out.println();
    }

    /**
     * Multiplies this matrix by another matrix.
     * this: (m x n), other: (n x p) -> result: (m x p)
     *
     * Time complexity: O(m * n * p) – triple nested loop.
     * Space complexity: O(m * p) for the result matrix.
     */
    public Matrix multiply(Matrix other) {
        if (other == null) {
            throw new IllegalArgumentException("Other matrix cannot be null.");
        }
        if (this.cols != other.rows) {
            throw new IllegalArgumentException(
                    "Incompatible matrix dimensions for multiplication: " +
                            this.rows + "x" + this.cols + " * " +
                            other.rows + "x" + other.cols);
        }

        int m = this.rows;
        int n = this.cols;    // same as other.rows
        int p = other.cols;

        int[][] result = new int[m][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result[i][j] = sum;
            }
        }

        return new Matrix(result);
    }

    // Getter for a single element – public to allow reading particular values.
    private void checkIndex(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds: (" + row + ", " + col + ")");
        }
    }
}