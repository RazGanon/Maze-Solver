package algorithms.mazeGenerators;

/**
 * Represents a maze with a start and end position.
 */
public class Maze {
    private int[][] maze;
    private Position start;
    private Position end;

    /**
     * Constructor to initialize the maze with given rows and columns.
     *
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     */
    public Maze(int rows, int cols) {
        this.maze = new int[rows][cols];
    }

    /**
     * Gets the maze matrix.
     *
     * @return The maze matrix.
     */
    public int[][] getMaze() {
        return maze;
    }

    /**
     * Sets the maze matrix.
     *
     * @param maze The maze matrix.
     */
    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    /**
     * Gets the start position in the maze.
     *
     * @return The start position.
     */
    public Position getStart() {
        return start;
    }

    /**
     * Sets the start position in the maze.
     *
     * @param start The start position.
     */
    public void setStart(Position start) {
        this.start = start;
    }

    /**
     * Gets the end position in the maze.
     *
     * @return The end position.
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Sets the end position in the maze.
     *
     * @param end The end position.
     */
    public void setEnd(Position end) {
        this.end = end;
    }

    /**
     * Prints the maze with start and end positions marked as 'S' and 'E' respectively.
     */
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (start != null && i == start.getRowIndex() && j == start.getColumnIndex()) {
                    System.out.print("S ");
                } else if (end != null && i == end.getRowIndex() && j == end.getColumnIndex()) {
                    System.out.print("E ");
                } else {
                    System.out.print(maze[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
