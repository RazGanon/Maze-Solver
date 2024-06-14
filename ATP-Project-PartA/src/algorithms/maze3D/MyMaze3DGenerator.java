package algorithms.maze3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator {
    private final Random rand = new Random();

    @Override
    public Maze3D generate(int depth, int rows, int columns) {
        int[][][] maze = new int[rows][columns][depth];

        // Initialize the maze with walls (value 1)
        for (int d = 0; d < rows; d++) {
            for (int r = 0; r < columns; r++) {
                for (int c = 0; c < depth; c++) {
                    maze[d][r][c] = 1;
                }
            }
        }

        // Choose random start and goal positions on the edges
        Position3D start = getRandomEdgePosition(depth, rows, columns);
        Position3D goal;
        do {
            goal = getRandomEdgePosition(depth, rows, columns);
        } while (start.equals(goal));

        // Carve out paths using a simple randomized algorithm
        carvePaths(maze, start);

        return new Maze3D(maze, start, goal);
    }

    private void carvePaths(int[][][] maze, Position3D start) {
        List<Position3D> walls = new ArrayList<>();
        walls.add(start);

        int[] dDepth = {-1, 1, 0, 0, 0, 0};
        int[] dRow = {0, 0, -1, 1, 0, 0};
        int[] dCol = {0, 0, 0, 0, -1, 1};

        while (!walls.isEmpty()) {
            Position3D current = walls.remove(rand.nextInt(walls.size()));
            int d = current.getDepthIndex();
            int r = current.getRowIndex();
            int c = current.getColumnIndex();

            if (maze[d][r][c] == 1) {
                maze[d][r][c] = 0;

                for (int i = 0; i < dDepth.length; i++) {
                    int newDepth = d + dDepth[i];
                    int newRow = r + dRow[i];
                    int newCol = c + dCol[i];
                    if (isInBounds(newRow, newCol, newDepth, maze) && maze[newRow][newCol][newDepth] == 1) {
                        walls.add(new Position3D(newRow, newCol, newDepth));
                    }
                }
            }
        }
    }

    private boolean isInBounds(int depth, int row, int col, int[][][] maze) {
        return depth >= 0 && depth < maze.length && row >= 0 && row < maze[0].length && col >= 0 && col < maze[0][0].length;
    }

    private Position3D getRandomEdgePosition(int depth, int rows, int columns) {
        int face = rand.nextInt(6); // front, back, top, bottom, left, right
        switch (face) {
            case 0: return new Position3D(0, rand.nextInt(rows), rand.nextInt(columns)); // front
            case 1: return new Position3D(depth - 1, rand.nextInt(rows), rand.nextInt(columns)); // back
            case 2: return new Position3D(rand.nextInt(depth), 0, rand.nextInt(columns)); // top
            case 3: return new Position3D(rand.nextInt(depth), rows - 1, rand.nextInt(columns)); // bottom
            case 4: return new Position3D(rand.nextInt(depth), rand.nextInt(rows), 0); // left
            case 5: return new Position3D(rand.nextInt(depth), rand.nextInt(rows), columns - 1); // right
            default: return new Position3D(0, 0, 0); // should never happen
        }
    }
}
