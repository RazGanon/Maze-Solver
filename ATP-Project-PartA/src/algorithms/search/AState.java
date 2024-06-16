package algorithms.search;

import java.util.Objects;

/**
 * Abstract base class representing a state in a search algorithm.
 * This class provides basic functionality for state management, including tracking the state's name
 * and its relationship with other states in the search space.
 */
public abstract class AState {
    private String stateName;
    private AState parentState;

    /**
     * Constructs a new AState with the specified name.
     *
     * @param stateName The name or description of the state, typically unique within the search space.
     */
    public AState(String stateName) {
        this.stateName = stateName;
        this.parentState = null;
    }

    /**
     * Returns the name of the state.
     *
     * @return The name of the state.
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Sets the parent state of this state, linking this state to its predecessor in the search path.
     *
     * @param parentState The state that precedes this one in the search path.
     */
    public void setParentState(AState parentState) {
        this.parentState = parentState;
    }

    /**
     * Gets the parent state of this state, if any.
     *
     * @return The parent state, or null if this state is the start of the search path.
     */
    public AState getParentState() {
        return parentState;
    }

    /**
     * Compares this state with another object for equality, based primarily on the state name.
     *
     * @param obj The object to compare with this state.
     * @return true if the specified object is also an AState and has the same name as this state, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AState aState = (AState) obj;
        return stateName.equals(aState.stateName);
    }

    /**
     * Returns a hash code value for the state, derived from the state's name.
     *
     * @return A hash code value for this state.
     */
    @Override
    public int hashCode() {
        return Objects.hash(stateName);
    }

    /**
     * Returns a string representation of the state, which is the state's name.
     *
     * @return A string representation of the state.
     */
    @Override
    public String toString() {
        return stateName;
    }
}
