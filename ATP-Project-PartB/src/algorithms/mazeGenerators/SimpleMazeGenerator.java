package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * Generates a simple maze with the specified number of rows and columns
     * The maze is generated randomly with a single clear path from the start to the end
     * @param row the number of rows in the maze
     * @param column the number of columns in the maze
     * @return a Maze object representing the generated maze
     * @throws IllegalArgumentException if the number of rows or columns is less than 2
     */
    @Override
    public Maze generate(int row, int column) {
        if (row < 2 || column < 2)
            throw new IllegalArgumentException("Invalid Maze Dimensions input");

        Maze newMaze = new Maze(row, column);

        //Initialize the maze with walls (1)
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                newMaze.getMatrix()[i][j] = 1;
            }
        }

        Random rand = new Random();
        int currColumn = 0, randomNum, startX;
        int currRow = rand.nextInt(row);
        startX = currRow;

        //Create a single path from the leftmost to the rightmost column
        while (currColumn < column - 1) {
            randomNum = rand.nextInt(3);
            if (randomNum == 0 || randomNum == 1) {
                currColumn++;
                newMaze.getMatrix()[currRow][currColumn] = 0;
            } else if (randomNum == 2 && currRow + 1 < row) {
                currRow++;
                newMaze.getMatrix()[currRow][currColumn] = 0;
            } else if (randomNum == 3 && currRow > 0) {
                currRow--;
                newMaze.getMatrix()[currRow][currColumn] = 0;
            }
        }

        //Randomly clear additional cells in the maze
        int x, y;
        for (int i = 0; i <= (row * column) / 1.5; i++) {
            x = rand.nextInt(row);
            y = rand.nextInt(column);
            newMaze.getMatrix()[x][y] = 0;
        }

        //Set the start and end positions
        newMaze.getMatrix()[startX][0] = 0;
        newMaze.getMatrix()[currRow][currColumn] = 0;
        newMaze.start = new Position(startX, 0);
        newMaze.goal = new Position(currRow, currColumn);

        return newMaze;
    }
}
