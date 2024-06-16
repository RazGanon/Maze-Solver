package algorithms.maze3D;

import algorithms.search.AState;

/**
 * Represents a state in a 3D maze, defined by its position (depth, row, column)
 */
public class Maze3DState extends AState {
    private final int rowIndex;
    private final int columnIndex;
    private final int depthIndex;

    /**
     * Constructs a Maze3DState with the specified row, column, and depth indices
     * @param r the row index of the state
     * @param c the column index of the state
     * @param d the depth index of the state
     */
    public Maze3DState(int r, int c, int d) {
        super("(" + d + "," + c + "," + d + ")");
        this.columnIndex = c;
        this.rowIndex = r;
        this.depthIndex = d;
    }

    /**
     * Returns the position of the state as a Position3D object
     * @return the position of the state
     */
    public Position3D getPosition() {
        return new Position3D(rowIndex, columnIndex, depthIndex);
    }

    /**
     * Check if this Maze3DState is equal to another object
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Maze3DState maze3DState = (Maze3DState) obj;
        return getPosition().equals(maze3DState.getPosition());
    }

    /**
     * Returns the hash code of the state based on its position
     * @return the hash code of the state
     */
    @Override
    public int hashCode() {
        return getPosition().hashCode();
    }

    /**
     * Returns a string representation of the state, which is its position
     * @return the string representation of the state
     */
    @Override
    public String toString() {
        return getPosition().toString();
    }
}
