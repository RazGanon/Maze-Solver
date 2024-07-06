package algorithms.mazeGenerators;

import java.util.Random;

public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * Generates an empty maze with the specified number of rows and columns
     * @param row the number of rows in the maze
     * @param column the number of columns in the maze
     * @return a new Maze object representing an empty maze
     * @throws IllegalArgumentException if the number of rows or columns is less than 2
     */
    @Override
    public Maze generate(int row, int column) {
        if (row < 2 || column < 2)
            throw new IllegalArgumentException("Invalid Maze Dimensions");
        Maze newMaze = new Maze(row, column);

        // Initialize the maze with paths (0)
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                newMaze.getMatrix()[i][j] = 0;
            }
        }

        Random rand = new Random();
        //Choose random walls for the start and end positions
        int startWall = rand.nextInt(4);
        int endWall = rand.nextInt(4);

        Position start = getRandomPositionOnWall(startWall, row, column, rand);
        Position end = getRandomPositionOnWall(endWall, row, column, rand);

        newMaze.getMatrix()[start.getRowIndex()][start.getColumnIndex()] = 0; // Set the start position to 0 (path)
        newMaze.getMatrix()[end.getRowIndex()][end.getColumnIndex()] = 0; // Set the end position to 0 (path)

        newMaze.start = start;
        newMaze.goal = end;

        return newMaze;
    }

    /**
     * Gets a random position on the specified wall
     * @param wall the wall index (0=top, 1=right, 2=bottom, 3=left)
     * @param row the number of rows in the maze
     * @param column the number of columns in the maze
     * @param rand the Random object
     * @return a random Position on the specified wall
     */
    private Position getRandomPositionOnWall(int wall, int row, int column, Random rand) {
        switch (wall) {
            case 0: // top wall
                return new Position(0, rand.nextInt(column));
            case 1: // right wall
                return new Position(rand.nextInt(row), column - 1);
            case 2: // bottom wall
                return new Position(row - 1, rand.nextInt(column));
            case 3: // left wall
                return new Position(rand.nextInt(row), 0);
            default:
                throw new IllegalArgumentException("Invalid Wall Index");
        }
    }
}
