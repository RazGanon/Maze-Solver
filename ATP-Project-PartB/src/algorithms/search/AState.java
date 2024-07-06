package algorithms.search;

import java.io.Serializable;

public abstract class AState implements Serializable {
    protected Object object;
    protected AState cameFrom;
    protected double cost;

    /**
     * Returns the cost associated with this state
     * @return the cost of this state
     */
    public double getCost() {
        return cost;
    }

    /**
     * Returns the state from which this state was reached
     * @return the state from which this state was reached
     */
    public AState getCameFrom() {
        return cameFrom;
    }

    /**
     * Returns the object associated with this state
     * @return the object associated with this state
     */
    public Object getObject() {
        return object;
    }

    /**
     * Returns a unique hash code for this state
     * @return the hash code as a string
     */
    public abstract String getHashCode();

    /**
     * Checks if this state is equal to another state
     * @param other the other state to compare with
     * @return true if the states are equal, false otherwise
     */
    public abstract boolean equals(AState other);

    /**
     * Returns a string representation of this state
     * @return the string representation of this state
     */
    @Override
    public String toString() {
        return object.toString();
    }

    /**
     * Sets the cost associated with this state
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
}
