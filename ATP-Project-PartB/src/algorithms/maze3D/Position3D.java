package algorithms.maze3D;

public class Position3D {
    int value;
    int x;
    int y;
    int z;
    boolean visited;

    /**
     * Constructs a Position3D with the specified depth, row, and column index
     * @param z the depth index of the position
     * @param x the row index of the position
     * @param y the column index of the position
     * @throws IllegalArgumentException if any of the indices are negative
     */
    public Position3D(int z, int x, int y) {
        if (z < 0 || x < 0 || y < 0)
            throw new IllegalArgumentException("Invalid Position Input");
        this.value = 1;
        this.x = x;
        this.y = y;
        this.z = z;
        this.visited = false;
    }

    /**
     * Returns a string representation of the position in the format {depth,row,column}.
     * @return the string representation of the position
     */
    @Override
    public String toString() {
        return "{" + z + "," + x + "," + y + '}';
    }

    /**
     * Checks if this Position3D is equal to another Position3D
     * @param other the other Position3D to compare with
     * @return true if the positions are equal, false otherwise
     * @throws IllegalArgumentException if the other position is null
     */
    public boolean equals(Position3D other) {
        if (other == null)
            throw new IllegalArgumentException("Invalid Input");
        return this.x == other.getRowIndex() && this.y == other.getColumnIndex() && this.z == other.getDepthIndex();
    }

    /**
     * Returns the row index of the position
     * @return the row index
     */
    public int getRowIndex() {
        return x;
    }

    /**
     * Returns the column index of the position
     * @return the column index
     */
    public int getColumnIndex() {
        return y;
    }

    /**
     * Returns the depth index of the position
     * @return the depth index
     */
    public int getDepthIndex() {
        return z;
    }
}
