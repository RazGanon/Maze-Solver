package algorithms.maze3D;

import algorithms.search.AState;

/**
 * Represents a 3D maze with a start and goal position
 */
public class Maze3D  {
    private int[][][] maze;
    private Position3D startPosition;
    private Position3D goalPosition;

    /**
     * Constructs a 3D maze with the specified map, start position, and goal position
     * @param maze a 3-dimensional array representing the maze structure
     * @param startPosition the starting position in the maze
     * @param goalPosition  the goal position in the maze
     */
    public Maze3D(int[][][] maze, Position3D startPosition, Position3D goalPosition) {
        this.maze = maze;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
    }

    /**
     * Returns the 3-dimensional array representing the maze structure
     * @return the maze structure as a 3-dimensional array
     */
    public int[][][] getMap() {
        return maze;
    }

    /**
     * Returns the starting position in the maze
     * @return the starting position
     */
    public Position3D getStartPosition() {
        return startPosition;
    }

    /**
     * Returns the goal position in the maze
     * @return the goal position
     */
    public Position3D getGoalPosition() {
        return goalPosition;
    }
}
