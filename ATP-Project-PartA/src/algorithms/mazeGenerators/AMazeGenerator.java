package algorithms.mazeGenerators;

import java.util.Random;

/**
 * Abstract class for maze generators that provides a template method for generating mazes,
 * measuring the generation time, and selecting random wall points.
 * This class is intended to be subclassed by specific maze generation implementations.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * Generates a maze with the specified number of rows and columns. This method must be
     * implemented by subclasses to provide specific maze generation algorithms.
     *
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     * @return The generated maze as a Maze object.
     */
    public abstract Maze generate(int rows, int cols);

    /**
     * Measures the time taken to generate a maze using the generate method. This method
     * provides a way to benchmark maze generation algorithms implemented by subclasses.
     *
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     * @return The time taken to generate the maze in milliseconds.
     */
    public long measureAlgorithmTimeMillis(int rows, int cols) {
        long startTime = System.currentTimeMillis();
        generate(rows, cols);  // Call the abstract generate method that must be implemented by subclasses
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Generates a random wall point, defined as a position located on one of the outer walls
     * of the maze. This method randomly selects one of the four walls (top, left, bottom, right)
     * and then selects a random point along that wall.
     *
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     * @return A Position object representing a randomly selected wall point.
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
