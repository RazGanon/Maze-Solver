package algorithms.maze3D;

public class Maze3D {
    Position3D start;
    Position3D goal;
    private int[][][] matrix;

    /**
     * Constructs a 3D maze with the specified dimensions
     * @param depth the depth of the maze (number of layers)
     * @param row the number of rows in each layer
     * @param column the number of columns in each row
     * @throws IllegalArgumentException if any of the dimensions are less than 2
     */
    public Maze3D(int depth, int row, int column) {
        if (row < 2 || column < 2 || depth < 2)
            throw new IllegalArgumentException("Invalid Maze Dimensions");
        matrix = new int[depth][row][column];
    }

    /**
     * Returns the 3D matrix representing the maze
     * @return a 3D array of integers representing the maze
     */
    public int[][][] getMap() {
        return matrix;
    }

    /**
     * Returns the starting position of the maze
     * @return the entrance position in the maze
     */
    public Position3D getStartPosition() {
        return start;
    }

    /**
     * Returns the goal position of the maze
     * @return the exit position in the maze
     */
    public Position3D getGoalPosition() {
        return goal;
    }

    /**
     * Prints the 3D maze to the console, marking the entrance with 'S' and the exit with 'E'
     */
    public void print() {
        System.out.println("{");
        for (int depth = 0; depth < matrix.length; depth++) {
            for (int row = 0; row < matrix[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < matrix[0][0].length; col++) {
                    if (depth == start.getDepthIndex() && row == start.getRowIndex() && col == start.getColumnIndex())
                        System.out.print("S ");
                    else if (depth == goal.getDepthIndex() && row == goal.getRowIndex() && col == goal.getColumnIndex())
                        System.out.print("E ");
                    else
                        System.out.print(matrix[depth][row][col] + " ");
                }
                System.out.println("}");
            }
            if (depth < matrix.length - 1) {
                System.out.print("---");
                for (int i = 0; i < matrix[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }

    /**
     * Checks if the position above the given position is open (value is 0).
     * @param s the current position
     * @return true if the position above is open, false otherwise
     */
    public boolean checkUp(Position3D s) {
        return s.getRowIndex() != 0 && matrix[s.getDepthIndex()][s.getRowIndex() - 1][s.getColumnIndex()] == 0;
    }

    /**
     * Checks if the position below the given position is open (value is 0).
     * @param s the current position
     * @return true if the position below is open, false otherwise
     */
    public boolean checkDown(Position3D s) {
        return s.getRowIndex() != matrix[0].length - 1 && matrix[s.getDepthIndex()][s.getRowIndex() + 1][s.getColumnIndex()] == 0;
    }

    /**
     * Checks if the position to the left of the given position is open (value is 0).
     * @param s the current position
     * @return true if the position to the left is open, false otherwise
     */
    public boolean checkLeft(Position3D s) {
        return s.getColumnIndex() != 0 && matrix[s.getDepthIndex()][s.getRowIndex()][s.getColumnIndex() - 1] == 0;
    }

    /**
     * Checks if the position to the right of the given position is open (value is 0).
     * @param s the current position
     * @return true if the position to the right is open, false otherwise
     */
    public boolean checkRight(Position3D s) {
        return s.getColumnIndex() != matrix[0][0].length - 1 && matrix[s.getDepthIndex()][s.getRowIndex()][s.getColumnIndex() + 1] == 0;
    }

    /**
     * Checks if the position above in depth (up) from the given position is open (value is 0).
     * @param s the current position
     * @return true if the position above in depth is open, false otherwise
     */
    public boolean checkTop(Position3D s) {
        return s.getDepthIndex() != matrix.length - 1 && matrix[s.getDepthIndex() + 1][s.getRowIndex()][s.getColumnIndex()] == 0;
    }

    /**
     * Checks if the position below in depth (down) from the given position is open (value is 0).
     * @param s the current position
     * @return true if the position below in depth is open, false otherwise
     */
    public boolean checkBottom(Position3D s) {
        return s.getDepthIndex() != 0 && matrix[s.getDepthIndex() - 1][s.getRowIndex()][s.getColumnIndex()] == 0;
    }
}
