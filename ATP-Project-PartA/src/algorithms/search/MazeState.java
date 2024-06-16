package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.Objects;

/**
 * Represents a specific location within a maze, characterized by row and column indices.
 * This class extends AState and includes additional attributes and methods specific to
 * maze navigation.
 */
public class MazeState extends AState {
    private final int row;
    private final int column;

    /**
     * Constructs a MazeState with specified row and column.
     * Initializes the state name uniquely with the coordinates to assist in identification.
     *
     * @param row The row index of the state within the maze.
     * @param column The column index of the state within the maze.
     */
    public MazeState(int row, int column) {
        super("(" + row + "," + column + ")");  // Constructs the base AState with a unique identifier.
        this.row = row;
        this.column = column;
    }

    /**
     * Gets the row index of this maze state.
     *
     * @return The row index.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column index of this maze state.
     *
     * @return The column index.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Checks if this MazeState is equal to another object. Equality is based on the
     * row and column indices.
     *
     * @param obj The object to compare against.
     * @return true if the specified object is also a MazeState with the same row and column.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MazeState mazeState = (MazeState) obj;
        return row == mazeState.row && column == mazeState.column;
    }

    /**
     * Generates a hash code for this MazeState based on its row and column indices.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
