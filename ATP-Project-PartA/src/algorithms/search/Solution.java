package algorithms.search;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private ArrayList<AState> solutionPath;

    public Solution() {
        this.solutionPath = new ArrayList<>();
    }

    public void addStateToSolutionPath(AState state) {
        solutionPath.add(state);
    }

    public ArrayList<AState> getSolutionPath() {
        return solutionPath;
    }}