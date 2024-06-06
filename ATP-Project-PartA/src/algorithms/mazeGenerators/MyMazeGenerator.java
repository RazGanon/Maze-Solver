package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generates a maze using Kruskal's algorithm.
 */
public class MyMazeGenerator extends AMazeGenerator {

    /**
     * Represents an edge between two positions in the maze.
     */
    private static class Edge {
        Position pos1, pos2;

        /**
         * Constructor to initialize an edge with two positions.
         *
         * @param pos1 The first position of the edge.
         * @param pos2 The second position of the edge.
         */
        Edge(Position pos1, Position pos2) {
            this.pos1 = pos1;
            this.pos2 = pos2;
        }
    }

    /**
     * Finds the root of the set in the union-find structure.
     *
     * @param parent The parent array.
     * @param i The element to find the root of.
     * @return The root of the set.
     */
    private int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }

    /**
     * Unites two sets in the union-find structure.
     *
     * @param parent The parent array.
     * @param rank The rank array.
     * @param x The first set to unite.
     * @param y The second set to unite.
     */
    private void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    /**
     * Generates a maze using Kruskal's algorithm.
     *
     * @param rows The number of rows in the maze.
     * @param cols The number of columns in the maze.
     * @return The generated maze.
     */
    @Override
    public Maze generate(int rows, int cols) {
        Maze maze = new Maze(rows, cols);
        int[][] mazeMat = maze.getMaze();
        List<Edge> edges = new ArrayList<>();

        // Initialize maze matrix with walls (value 1)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mazeMat[i][j] = 1;
            }
        }

        // Create all possible edges
        for (int i = 0; i < rows; i += 2) {
            for (int j = 0; j < cols; j += 2) {
                if (i > 0) edges.add(new Edge(new Position(i, j), new Position(i - 2, j)));
                if (j > 0) edges.add(new Edge(new Position(i, j), new Position(i, j - 2)));
            }
        }

        // Shuffle edges to randomize the maze
        Collections.shuffle(edges, new Random());

        // Initialize union-find structure
        int[] parent = new int[rows * cols];
        int[] rank = new int[rows * cols];
        for (int i = 0; i < rows * cols; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        // Kruskal's algorithm
        for (Edge edge : edges) {
            int cell1 = edge.pos1.getRowIndex() * cols + edge.pos1.getColumnIndex();
            int cell2 = edge.pos2.getRowIndex() * cols + edge.pos2.getColumnIndex();
            if (find(parent, cell1) != find(parent, cell2)) {
                union(parent, rank, cell1, cell2);
                mazeMat[edge.pos1.getRowIndex()][edge.pos1.getColumnIndex()] = 0;
                mazeMat[edge.pos2.getRowIndex()][edge.pos2.getColumnIndex()] = 0;
                mazeMat[(edge.pos1.getRowIndex() + edge.pos2.getRowIndex()) / 2][(edge.pos1.getColumnIndex() + edge.pos2.getColumnIndex()) / 2] = 0;
            }
        }

        // Pick random start and end points
        Position start = getRandomWallPoint(rows, cols);
        Position end;
        do {
            end = getRandomWallPoint(rows, cols);
        } while (start.equals(end));

        // Mark start and end points
        maze.setStart(start);
        maze.setEnd(end);
        mazeMat[start.getRowIndex()][start.getColumnIndex()] = 0; // Clear the start point
        mazeMat[end.getRowIndex()][end.getColumnIndex()] = 0; // Clear the end point

        // Set the generated maze matrix
        maze.setMaze(mazeMat);

        return maze;
    }
}
