package algorithms.search;

import java.util.Objects;

public abstract class AState {
    private String stateName;
    private AState parentState;

    public AState(String stateName) {
        this.stateName = stateName;
        this.parentState = null;
    }

    public String getStateName() {
        return stateName;
    }

    public void setParentState(AState parentState) {
        this.parentState = parentState;
    }

    public AState getParentState() {
        return parentState;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AState aState = (AState) obj;
        return stateName.equals(aState.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateName);
    }

    @Override
    public String toString() {
        return stateName;
    }}
