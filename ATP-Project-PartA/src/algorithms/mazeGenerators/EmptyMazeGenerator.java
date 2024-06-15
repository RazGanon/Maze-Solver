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
        // Pick random start and end points using the method from the abstract class
        Position start = getRandomWallPoint(rows, cols);
        Position end;
        do {
            end = getRandomWallPoint(rows, cols);
        } while (start.equals(end));

        // Set the start and end positions in the maze
        maze.setStart(start);
        maze.setEnd(end);
        mazeMat[start.getRowIndex()][start.getColumnIndex()] = 0; // Clear the start point
        mazeMat[end.getRowIndex()][end.getColumnIndex()] = 0; // Clear the end point

        // Set the maze matrix
        maze.setMaze(mazeMat);
        return maze;
    }
}
