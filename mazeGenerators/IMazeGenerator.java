package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int rows,int cols);

    long measureAlgorithmsTimeMillis (int rows,int cols);
}
