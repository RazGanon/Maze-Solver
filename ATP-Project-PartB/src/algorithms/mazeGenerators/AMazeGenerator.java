package algorithms.mazeGenerators;

/**
 * Abstract class for maze generators.
 * Provides a template for generating mazes and measuring the time taken to generate them.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * Generates a maze with the given number of rows and columns
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     * @return The generated maze.
     */
    public abstract Maze generate(int rows, int cols);

    /**
     * Measures the time taken to generate a maze
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     * @return The time taken to generate the maze in milliseconds.
     */
    public long measureAlgorithmTimeMillis(int rows, int cols) {
        long startTime = System.currentTimeMillis();
        generate(rows, cols);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

}
