package algorithms.mazeGenerators;

import java.util.Random;
import java.util.ArrayList;

public class MyMazeGenerator extends AMazeGenerator {

    /**
     * Generates a new maze using Prim's algorithm for minimal spanning tree
     * @param row    the number of rows in the maze
     * @param column the number of columns in the maze
     * @return a newly generated Maze object
     * @throws IllegalArgumentException if the number of rows or columns is less than 2
     */
    @Override
    public Maze generate(int row, int column) {
        if (row < 2 || column < 2)
            throw new IllegalArgumentException("Invalid Maze Dimensions input");

        Random rand = new Random();
        int x = rand.nextInt((int) (row * 0.75 - row * 0.25) + 1) + (int) (row * 0.25);
        int y = rand.nextInt((int) (column * 0.75 - column * 0.25) + 1) + (int) (column * 0.25);

        ArrayList<int[]> wallList = new ArrayList<>();
        Maze newMaze = new Maze(row, column);

        //Initialize the maze with walls
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                newMaze.getMatrix()[i][j] = 3;
            }
        }

        //Set the initial cell and add its neighbors
        newMaze.getMatrix()[x][y] = 0;
        addNeighbors(newMaze, x, y, row, column, wallList, 1);

        int[] randomPoint, randomNeighbor;
        ArrayList<int[]> currNeighbors = new ArrayList<>();

        //Generate the maze using Prim's algorithm
        while (!wallList.isEmpty()) {
            randomPoint = wallList.remove(rand.nextInt(wallList.size()));
            addNeighbors(newMaze, randomPoint[0], randomPoint[1], row, column, currNeighbors, 0);

            randomNeighbor = currNeighbors.get(rand.nextInt(currNeighbors.size()));

            newMaze.getMatrix()[randomPoint[0] + (randomNeighbor[0] - randomPoint[0]) / 2]
                    [randomPoint[1] + (randomNeighbor[1] - randomPoint[1]) / 2] = 0;
            newMaze.getMatrix()[randomPoint[0]][randomPoint[1]] = 0;

            currNeighbors.clear();
            addNeighbors(newMaze, randomPoint[0], randomPoint[1], row, column, wallList, 1);
        }

        Finalize(newMaze, row, column);
        return newMaze;
    }

    /**
     * Adds the available neighbors of a given position to the list based on their current state.
     * @param maze the maze object
     * @param x the row index of the current position
     * @param y the column index of the current position
     * @param row the total rows in the maze
     * @param column the total columns in the maze
     * @param list the list to add the neighbors to
     * @param state the current state to check against
     */
    private void addNeighbors(Maze maze, int x, int y, int row, int column, ArrayList<int[]> list, int state) {
        if (x + 2 <= row - 1) {
            if (maze.getMatrix()[x + 2][y] == state + 2 || (state == 0 && maze.getMatrix()[x + 2][y] == state)) {
                list.add(new int[]{x + 2, y});
                if (state != 0)
                    maze.getMatrix()[x + 2][y] -= 2;
            }
        }
        if (x - 2 >= 0) {
            if (maze.getMatrix()[x - 2][y] == state + 2 || (state == 0 && maze.getMatrix()[x - 2][y] == state)) {
                list.add(new int[]{x - 2, y});
                if (state != 0)
                    maze.getMatrix()[x - 2][y] -= 2;
            }
        }
        if (y + 2 <= column - 1) {
            if (maze.getMatrix()[x][y + 2] == state + 2 || (state == 0 && maze.getMatrix()[x][y + 2] == state)) {
                list.add(new int[]{x, y + 2});
                if (state != 0)
                    maze.getMatrix()[x][y + 2] -= 2;
            }
        }
        if (y - 2 >= 0) {
            if (maze.getMatrix()[x][y - 2] == state + 2 || (state == 0 && maze.getMatrix()[x][y - 2] == state)) {
                list.add(new int[]{x, y - 2});
                if (state != 0)
                    maze.getMatrix()[x][y - 2] -= 2;
            }
        }
    }

    /**
     * Sets random starting and finishing positions in the maze.
     * @param newMaze the maze object
     * @param row the total rows in the maze
     * @param column  the total columns in the maze
     */
    public void Finalize(Maze newMaze, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (newMaze.getMatrix()[i][j] == 3)
                    newMaze.getMatrix()[i][j] = 1;
            }
        }

        Random rand = new Random();
        int start = rand.nextInt(row);
        int end = rand.nextInt(row);

        while (true) {
            if (column == 2) {
                start = 0;
                newMaze.getMatrix()[start][1] = 0;
                break;
            }
            if (newMaze.getMatrix()[start][1] == 0)
                break;
            start = rand.nextInt(row);
        }

        while (true) {
            if (column == 2) {
                end = row - 1;
                newMaze.getMatrix()[end][column - 2] = 0;
                break;
            }
            if (newMaze.getMatrix()[end][column - 2] == 0)
                break;
            end = rand.nextInt(row);
        }

        newMaze.getMatrix()[start][0] = 0;
        newMaze.getMatrix()[end][column - 1] = 0;
        newMaze.start = new Position(start, 0);
        newMaze.goal = new Position(end, column - 1);
    }
}
