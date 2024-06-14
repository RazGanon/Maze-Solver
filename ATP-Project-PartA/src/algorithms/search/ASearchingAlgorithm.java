package algorithms.search;

import java.util.Collections;

// Define the ASearchingAlgorithm abstract class
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int numberOfNodesEvaluated;

    public ASearchingAlgorithm() {
        this.numberOfNodesEvaluated = 0;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }

    protected Solution backtrack(AState goalState) {
        Solution solution = new Solution();
        AState currentState = goalState;
        while (currentState != null) {
            solution.addStateToSolutionPath(currentState);
            currentState = currentState.getParentState();
        }
        Collections.reverse(solution.getSolutionPath());
        return  solution;
    }


    private boolean checkSolution(Solution solution){
        return solution != null;
    }

}