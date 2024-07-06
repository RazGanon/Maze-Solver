package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {

    /**
     * Constructs a MazeState with the specified position, previous state, and cost
     * @param position the position of the state in the maze
     * @param state the previous state from which this state was reached
     * @param cost the cost associated with reaching this state
     * @throws IllegalArgumentException if the position is null
     */
    public MazeState(Position position, AState state, double cost) {
        if (position == null)
            throw new IllegalArgumentException("Invalid Input");
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
        return object.toString();
    }

    /**
     * Determines whether the current state is equal to another state
     * @param other the other state to compare with
     * @return true if the current state is equal to the other state, false otherwise
     * @throws IllegalArgumentException if the other state is null
     */
    @Override
    public boolean equals(AState other) {
        if (other == null)
            throw new IllegalArgumentException("Invalid Input");
        Position a = (Position) object;
        Position b = (Position) other.getObject();
        return a.equals(b);
    }
}
