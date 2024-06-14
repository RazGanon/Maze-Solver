package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3D  {
    private int[][][] maze;
    private Position3D startPosition;
    private Position3D goalPosition;

    public Maze3D(int[][][] maze, Position3D startPosition, Position3D goalPosition) {

        this.maze = maze;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;
    }

    public int[][][] getMap() {
        return maze;
    }

    public Position3D getStartPosition() {
        return startPosition;
    }

    public Position3D getGoalPosition() {
        return goalPosition;
    }
}