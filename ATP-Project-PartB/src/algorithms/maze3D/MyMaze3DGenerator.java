package algorithms.maze3D;

import java.util.ArrayList;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator {

    /**
     * Generates a new 3D maze using Prim's algorithm for MST
     * @param depth the depth of the maze (number of layers)
     * @param row the number of rows in each layer
     * @param column the number of columns in each row
     * @return a newly generated 3D maze
     * @throws IllegalArgumentException if any of the dimensions are less than 2
     */
    @Override
    public Maze3D generate(int depth, int row, int column) {
        if (depth < 2 || row < 2 || column < 2)
            throw new IllegalArgumentException("Invalid Maze Dimensions Input");
        ArrayList<int[]> wallList = new ArrayList<>();
        Random rand = new Random();
        Maze3D newMaze = new Maze3D(depth, row, column);

        //Initialize the maze with walls
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    newMaze.getMap()[i][j][k] = 3;
                }
            }
        }

        //Select random starting point on maze edges
        int z = rand.nextInt((int) (depth * 0.75 - depth * 0.25) + 1) + (int) (depth * 0.25);
        int x = rand.nextInt((int) (row * 0.75 - row * 0.25) + 1) + (int) (row * 0.25);
        int y = rand.nextInt((int) (column * 0.75 - column * 0.25) + 1) + (int) (column * 0.25);
        newMaze.getMap()[z][x][y] = 0; // This is the starting point
        addNeighbors(newMaze, z, x, y, depth, row, column, wallList, 1);

        int[] randomPoint, randomNeighbor;
        ArrayList<int[]> currNeighbors = new ArrayList<>();

        //Generate maze using Prim's algorithm
        while (!wallList.isEmpty()) {
            randomPoint = wallList.remove(rand.nextInt(wallList.size()));
            addNeighbors(newMaze, randomPoint[0], randomPoint[1], randomPoint[2], depth, row, column, currNeighbors, 0);
            randomNeighbor = currNeighbors.get(rand.nextInt(currNeighbors.size()));

            newMaze.getMap()[randomPoint[0] + (randomNeighbor[0] - randomPoint[0]) / 2]
                    [randomPoint[1] + (randomNeighbor[1] - randomPoint[1]) / 2]
                    [randomPoint[2] + (randomNeighbor[2] - randomPoint[2]) / 2] = 0;
            newMaze.getMap()[randomPoint[0]][randomPoint[1]][randomPoint[2]] = 0;

            currNeighbors.clear();
            addNeighbors(newMaze, randomPoint[0], randomPoint[1], randomPoint[2], depth, row, column, wallList, 1);
        }

        Finalize(newMaze, depth, row, column);

        return newMaze;
    }

    /**
     * Adds the available neighbors of a given position to the list based on their current state
     * @param maze the maze object
     * @param z the depth index of the current position
     * @param x the row index of the current position
     * @param y the column index of the current position
     * @param depth the total depth of the maze
     * @param row the total rows in each layer of the maze
     * @param column the total columns in each row of the maze
     * @param list the list to add the neighbors to
     * @param state the current state to check against
     */
    private void addNeighbors(Maze3D maze, int z, int x, int y, int depth, int row, int column, ArrayList<int[]> list, int state) {
        if (x + 2 <= row - 1) {
            if (maze.getMap()[z][x + 2][y] == state + 2 || (state == 0 && maze.getMap()[z][x + 2][y] == state)) {
                list.add(new int[] { z, x + 2, y });
                if (state != 0)
                    maze.getMap()[z][x + 2][y] -= 2;
            }
        }
        if (x - 2 >= 0) {
            if (maze.getMap()[z][x - 2][y] == state + 2 || (state == 0 && maze.getMap()[z][x - 2][y] == state)) {
                list.add(new int[] { z, x - 2, y });
                if (state != 0)
                    maze.getMap()[z][x - 2][y] -= 2;
            }
        }
        if (y + 2 <= column - 1) {
            if (maze.getMap()[z][x][y + 2] == state + 2 || (state == 0 && maze.getMap()[z][x][y + 2] == state)) {
                list.add(new int[] { z, x, y + 2 });
                if (state != 0)
                    maze.getMap()[z][x][y + 2] -= 2;
            }
        }
        if (y - 2 >= 0) {
            if (maze.getMap()[z][x][y - 2] == state + 2 || (state == 0 && maze.getMap()[z][x][y - 2] == state)) {
                list.add(new int[] { z, x, y - 2 });
                if (state != 0)
                    maze.getMap()[z][x][y - 2] -= 2;
            }
        }
        if (z - 2 >= 0) {
            if (maze.getMap()[z - 2][x][y] == state + 2 || (state == 0 && maze.getMap()[z - 2][x][y] == state)) {
                list.add(new int[] { z - 2, x, y });
                if (state != 0)
                    maze.getMap()[z - 2][x][y] -= 2;
            }
        }
        if (z + 2 <= depth - 1) {
            if (maze.getMap()[z + 2][x][y] == state + 2 || (state == 0 && maze.getMap()[z + 2][x][y] == state)) {
                list.add(new int[] { z + 2, x, y });
                if (state != 0)
                    maze.getMap()[z + 2][x][y] -= 2;
            }
        }
    }

    /**
     * Sets random starting and finishing positions in the maze
     * @param newMaze the maze object
     * @param depth the total depth of the maze
     * @param row the total rows in each layer of the maze
     * @param column the total columns in each row of the maze
     */
    public void Finalize(Maze3D newMaze, int depth, int row, int column){

        for (int i=0;i<depth;i++){
            for (int j=0; j<row;j++){
                for (int k=0;k<column;k++){
                    if (newMaze.getMap()[i][j][k]==3)
                        newMaze.getMap()[i][j][k]=1;
                }
            }
        }

        Random rand = new Random();
        int startX = rand.nextInt(((row-1) - 1) + 1) + 1;
        int startZ = rand.nextInt(((depth-1) - 1) + 1) + 1;
        int endX = rand.nextInt(((row-1) - 1) + 1) + 1;
        int endZ = rand.nextInt(((depth-1) - 1) + 1) + 1;

        while (true){
            if(newMaze.getMap()[startZ][startX][1] == 0)
                break;
            if (startZ +1 <= depth-1){
                if (newMaze.getMap()[startZ+1][startX][0]== 0 )
                    break;
            }
            if (startZ -1 >= 0){
                if (newMaze.getMap()[startZ-1][startX][0]== 0 )
                    break;
            }
            startX = rand.nextInt((row-1 - 0) + 1) + 0;
            startZ = rand.nextInt((depth-1 - 0) + 1) + 0;
        }
        while (true){
            if(newMaze.getMap()[endZ][endX][column-2]== 0)
                break;
            if (endZ +1 <= depth-1){
                if (newMaze.getMap()[endZ+1][endX][column-1]== 0)
                    break;
            }
            if (endZ -1 >= 0){
                if (newMaze.getMap()[endZ-1][endX][column-1]== 0)
                    break;
            }
            endX = rand.nextInt((row-1 - 0) + 1) + 0;
            endZ = rand.nextInt((depth-1 - 0) + 1) + 0;
        }

        newMaze.getMap()[startZ][startX][0]=0;
        newMaze.getMap()[endZ][endX][column-1]=0;

        newMaze.start = new Position3D(startZ,startX,0);
        newMaze.goal = new Position3D(endZ,endX,column-1);
    }
}
