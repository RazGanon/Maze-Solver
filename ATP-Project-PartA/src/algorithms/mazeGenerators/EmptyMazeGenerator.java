package algorithms.mazeGenerators;

/**
 * This class extends AMazeGenerator and is responsible for generating a completely open maze,
 * where all cells are freely walkable. It features the ability to generate a maze with specified
 * dimensions and randomizes the start and end positions on the boundary of the maze.
 */
public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * Generates an empty maze with the specified dimensions. All cells in the generated maze are
     * set to be walkable (denoted by '0'). This method also assigns random but distinct start and
     * end points on the periphery of the maze to facilitate pathfinding algorithms' functionality.
     *
     * @param rows The number of rows in the maze, must be positive.
     * @param cols The number of columns in the maze, must be positive.
     * @return A Maze object with all cells set to '0' (walkable), and with randomly assigned start and end points.
     * @throws IllegalArgumentException if rows or cols are non-positive.
     */
    @Override
    public Maze generate(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Row and column sizes must be greater than 0.");
        }

        Maze maze = new Maze(rows, cols);
        int[][] mazeMat = maze.getMaze();

        // Initialize the maze matrix to zero (empty space)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mazeMat[i][j] = 0;
            }
        }

        // Select random start and end points using the method from the abstract class
        Position start = getRandomWallPoint(rows, cols);
        Position end;
        do {
            end = getRandomWallPoint(rows, cols);
        } while (start.equals(end));

        // Set start and end positions ensuring they are distinct and placed on the boundaries
        maze.setStart(start);
        maze.setEnd(end);
        maze.setMaze(mazeMat);  // Finalize the configuration of the maze matrix

        return maze;
    }
}
