package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
    int x;
    int y;

    /**
     * Constructs a Position with the specified row and column indices
     * @param xPos the row index of the position
     * @param yPos the column index of the position
     * @throws IllegalArgumentException if any of the indices are negative
     */
    public Position(int xPos, int yPos) {
        if (xPos < 0 || yPos < 0)
            throw new IllegalArgumentException("Invalid Position input");
        this.x = xPos;
        this.y = yPos;
    }

    /**
     * Returns a string representation of the position in the format {row,column}
     * @return the string representation of the position
     */
    @Override
    public String toString() {
        return "{" + x + "," + y + '}';
    }

    /**
     * Checks if this Position is equal to another Position
     * @param other the other Position to compare with
     * @return true if the positions are equal, false otherwise
     * @throws IllegalArgumentException if the other position is null
     */
    public boolean equals(Position other) {
        if (other == null)
            throw new IllegalArgumentException("Invalid Argument received");
        return this.x == other.getRowIndex() && this.y == other.getColumnIndex();
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
}
