package algorithms.maze3D;

import algorithms.search.*;

public class Maze3DState extends AState {

    /**
     * Constructs a new Maze3DState with the specified position, previous state, and cost
     * @param position the current position of this state in the 3D maze
     * @param state the previous state from which this state was reached
     * @param cost the cost associated with reaching this state
     * @throws IllegalArgumentException if the position is null
     */
    public Maze3DState(Position3D position, AState state, double cost) {
        if (position == null) {
            throw new IllegalArgumentException("Invalid Input");
        }
        this.object = position;
        this.cameFrom = state;
        this.cost = cost;
    }

    /**
     * Returns the hash code of the current state
     * @return a string representing the hash code of the current state
     */
    @Override
    public String getHashCode() {
        String hash = object.toString();
        return hash;
    }

    /**
     * Check if current state is equal to another state
     * @param other the other state to compare with
     * @return true if the current state is equal to the other state, false otherwise
     * @throws IllegalArgumentException if the other state is null
     */
    @Override
    public boolean equals(AState other) {
        if (other == null) {
            throw new IllegalArgumentException("Invalid Input");
        }
        Position3D a = (Position3D) object;
        Position3D b = (Position3D) other.getObject();
        return a.equals(b);
    }
}
