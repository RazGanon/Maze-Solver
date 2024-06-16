package algorithms.maze3D;

/**
 * Represents a position in a 3D maze, defined by its depth, row, and column indices
 */
public class Position3D {
    private final int depthIndex;
    private final int rowIndex;
    private final int columnIndex;

    /**
     * Constructs a Position3D with the specified row, column, and depth indices
     * @param rowIndex    the row index of the position
     * @param columnIndex the column index of the position
     * @param depthIndex  the depth index of the position
     */
    public Position3D(int rowIndex, int columnIndex, int depthIndex) {
        this.depthIndex = depthIndex;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    /**
     * Returns the depth index of the position.
     * @return the depth index
     */
    public int getDepthIndex() {
        return depthIndex;
    }

    /**
     * Returns the row index of the position.
     * @return the row index
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Returns the column index of the position
     * @return the column index
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * Check if this Position3D is equal to another object
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position3D that = (Position3D) obj;
        return depthIndex == that.depthIndex && rowIndex == that.rowIndex && columnIndex == that.columnIndex;
    }

    /**
     * Returns the hash code of the position based on its indices
     * @return the hash code of the position
     */
    @Override
    public int hashCode() {
        return 31 * depthIndex + 31 * rowIndex + 31 * columnIndex;
    }

    /**
     * Returns a string representation of the position
     * @return the string representation of the position
     */
    @Override
    public String toString() {
        return "{" + depthIndex + "," + rowIndex + "," + columnIndex + "}";
    }
}
