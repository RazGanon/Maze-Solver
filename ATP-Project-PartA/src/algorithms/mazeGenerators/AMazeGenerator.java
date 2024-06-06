package algorithms.mazeGenerators;

import java.util.Random;

/**
 * Abstract class for maze generators.
 * Provides a template for generating mazes and measuring the time taken to generate them.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * Generates a maze with the given number of rows and columns.
     *
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     * @return The generated maze.
     */
    public abstract Maze generate(int rows, int cols);

    /**
     * Measures the time taken to generate a maze.
     *
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

    /**
     * Generates a random wall point in the maze.
     * A wall point is a position on one of the outer walls of the maze.
     *
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     * @return A random wall point as a Position.
     */
    protected Position getRandomWallPoint(int rows, int cols) {
        Random random = new Random();
        int wall = random.nextInt(4); // 0 = top, 1 = left, 2 = bottom, 3 = right
        Position point;

        switch (wall) {
            case 0: // Top wall
                point = new Position(0, random.nextInt(cols));
                break;
            case 1: // Left wall
                point = new Position(random.nextInt(rows), 0);
                break;
            case 2: // Bottom wall
                point = new Position(rows - 1, random.nextInt(cols));
                break;
            case 3: // Right wall
                point = new Position(random.nextInt(rows), cols - 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid wall index: " + wall);
        }

        return point;
    }
}
