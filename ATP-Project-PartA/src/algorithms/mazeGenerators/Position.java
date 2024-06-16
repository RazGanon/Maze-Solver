package algorithms.mazeGenerators;

/**
 * Represents a position in the maze with row and column indices.
 */
public class Position {
    private int row;
    private int column;

    /**
     * Constructor to initialize a position.
     *
     * @param row    The row index of the position.
     * @param column The column index of the position.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets the row index of the position.
     *
     * @return The row index.
     */
    public int getRowIndex() {
        return row;
    }

    /**
     * Gets the column index of the position.
     *
     * @return The column index.
     */
    public int getColumnIndex() {
        return column;
    }

    /**
     * Returns a string representation of the position.
     * @return A string in the format "{row,column}", representing the current position.
     */
    @Override
    public String toString() {
        return "{" + row + "," + column + "}";
    }
}