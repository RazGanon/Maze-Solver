package algorithms.search;

import java.util.List;

public interface ISearchable {
    MazeState getStartState();
    MazeState getGoalState();
    List<MazeState> getAllPossibleStates(MazeState state);
}


