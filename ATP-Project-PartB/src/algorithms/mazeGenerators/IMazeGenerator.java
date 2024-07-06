package algorithms.mazeGenerators;

/**
 * Interface for maze generators
 * Provides methods to generate mazes and measure the time taken to generate them
 */
public interface IMazeGenerator {

    /**
     * Generates a maze with the given number of rows and columns
     * @param rows The number of rows in the maze
     * @param cols The number of columns in the maze
     * @return The generated maze
     */
    Maze generate(int rows, int cols);

    /**
     * Measures the time taken to generate a maze
     * @param rows The number of rows in the maze
     * @param cols The number of columns in the maze
     * @return The time taken to generate the maze in milliseconds
     */
    long measureAlgorithmTimeMillis(int rows, int cols);
}
