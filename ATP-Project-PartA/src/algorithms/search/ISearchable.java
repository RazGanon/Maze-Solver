package algorithms.search;


import algorithms.maze3D.Maze3DState;

import java.util.ArrayList;


public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    ArrayList<AState> getAllPossibleStates(AState state);


}


