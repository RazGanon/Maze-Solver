package algorithms.mazeGenerators;

/**
 * Generates an empty maze with no walls.
 */
public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * Generates an empty maze with the given number of rows and columns.
     * The maze contains no walls, and all cells are walkable.
     *
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     * @return The generated maze.
     */
    @Override
    public Maze generate(int rows, int cols) {
        Maze maze = new Maze(rows, cols);
        int[][] mazeMat = maze.getMaze();

        // Initialize the maze matrix with 0 (empty space)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mazeMat[i][j] = 0;
            }
        }
        maze.setStart(new Position(0, 0)); // Start at top-left corner
        maze.setEnd(new Position(rows - 1, cols - 1)); // End at bottom-right corner

        // Set the maze matrix
        maze.setMaze(mazeMat);
        return maze;
    }
}
